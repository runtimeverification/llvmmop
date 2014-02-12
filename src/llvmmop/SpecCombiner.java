package llvmmop;

import java.util.ArrayList;
import java.util.List;

import llvmmop.parser.ast.ImportDeclaration;
import llvmmop.parser.ast.MOPSpecFile;
import llvmmop.parser.ast.PackageDeclaration;
import llvmmop.parser.ast.mopspec.LLVMMOPSpec;

public class SpecCombiner {

	static public MOPSpecFile process(ArrayList<MOPSpecFile> specFiles) throws MOPException{
		PackageDeclaration pakage = null;
		List<ImportDeclaration> imports = new ArrayList<ImportDeclaration>();
		List<LLVMMOPSpec> specList = new ArrayList<LLVMMOPSpec>();
		
		for(MOPSpecFile specFile : specFiles){
			//package decl
			PackageDeclaration pakage2 = specFile.getPakage();
			if(pakage == null)
				pakage = pakage2;
			else {
				if(!pakage2.getName().getName().equals(pakage.getName().getName()))
					throw new MOPException("Specifications need to be in the same package to be combined.");
			}
			
			//imports
			List<ImportDeclaration> imports2 = specFile.getImports();
			
			for(ImportDeclaration imp2 : imports2){
				boolean included = false;
				for(ImportDeclaration imp : imports){
					if(imp2.getName().getName().equals(imp.getName().getName())){
						included = true;
						break;
					}
				}
				
				if(!included)
					imports.add(imp2);
			}
			
			//specs
			List<LLVMMOPSpec> specList2 = specFile.getSpecs();
			
			for(LLVMMOPSpec spec2 : specList2){
				boolean included = false;
				for(LLVMMOPSpec spec : specList){
					if(spec2.getName().equals(spec.getName())){
						included = true;
						break;
					}
				}
				
				if(!included)
					specList.add(spec2);
			}
		}
		
		return new MOPSpecFile(0, 0, pakage, imports, specList);
	}
}
