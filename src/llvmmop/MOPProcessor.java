/**
 * @author Feng Chen, Dongyun Jin
 * The class handling the mop specification tree
 */

package llvmmop;

import java.util.List;

import llvmmop.output.AspectJCode;
import llvmmop.parser.ast.ImportDeclaration;
import llvmmop.parser.ast.MOPSpecFile;
import llvmmop.parser.ast.body.BodyDeclaration;
import llvmmop.parser.ast.mopspec.EventDefinition;
import llvmmop.parser.ast.mopspec.LLVMMOPSpec;
import llvmmop.parser.ast.mopspec.MOPParameter;
import llvmmop.parser.ast.visitor.CollectUserVarVisitor;
import llvmmop.util.Tool;

public class MOPProcessor {
	public static boolean verbose = false;

	public String name;

	public MOPProcessor(String name) {
		this.name = name;
	}

	private void registerUserVar(LLVMMOPSpec mopSpec) throws MOPException {
		for (EventDefinition event : mopSpec.getEvents()) {
			MOPNameSpace.addUserVariable(event.getId());
			for(MOPParameter param : event.getMOPParameters()){
				MOPNameSpace.addUserVariable(param.getName());
			}
		}
		for (MOPParameter param : mopSpec.getParameters()) {
			MOPNameSpace.addUserVariable(param.getName());
		}
		MOPNameSpace.addUserVariable(mopSpec.getName());
		for (BodyDeclaration bd : mopSpec.getDeclarations()) {
			List<String> vars = bd.accept(new CollectUserVarVisitor(), null);

			if (vars != null)
				MOPNameSpace.addUserVariables(vars);
		}
	}
	
	public String translate2RV(MOPSpecFile mopSpecFile) throws MOPException {
		String rvresult = "";
		if (mopSpecFile.getPakage() != null) {
			rvresult += mopSpecFile.getPakage().toString();
		}
		if (mopSpecFile.getImports() != null) {
			for (ImportDeclaration id : mopSpecFile.getImports()) {
				rvresult += id.toString();
			}
		}
		for(LLVMMOPSpec mopSpec : mopSpecFile.getSpecs()){
			if(LLVMMOPMain.translate2RV) {
				rvresult += mopSpec.toRVString();
			}
		}
		rvresult = Tool.changeIndentation(rvresult, "", "\t");
		return rvresult;
	}

	public String process(MOPSpecFile mopSpecFile) throws MOPException {
		String result = "";
		
		// register all user variables to MOPNameSpace to avoid conflicts
		for(LLVMMOPSpec mopSpec : mopSpecFile.getSpecs())
			registerUserVar(mopSpec);

		// Error Checker
		for(LLVMMOPSpec mopSpec : mopSpecFile.getSpecs()){
			MOPErrorChecker.verify(mopSpec);
		}

		// Generate output code
		
		if (LLVMMOPMain.translate2RV) {
			result = (new AspectJCode(name, mopSpecFile)).toRVString();
		}

		// Do indentation
		result = Tool.changeIndentation(result, "", "\t");

		return result;
	}


}
