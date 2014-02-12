package llvmmop.output.combinedaspect;

import java.util.List;

import llvmmop.MOPException;
import llvmmop.output.MOPVariable;
import llvmmop.parser.ast.mopspec.LLVMMOPSpec;

public class LockManager {

	//HashMap<LLVMMOPSpec, GlobalLock> locks = new HashMap<LLVMMOPSpec, GlobalLock>();
	
	GlobalLock lock;

	public LockManager(String name, List<LLVMMOPSpec> specs) throws MOPException {
//		for (LLVMMOPSpec spec : specs) {
//			if (spec.isSync())
//				locks.put(spec, new GlobalLock(new MOPVariable(spec.getName() + "_MOPLock")));
//		}
		
		lock = new GlobalLock(new MOPVariable(name + "_MOPLock"));
	}

/*	public GlobalLock getLock(LLVMMOPSpec spec){
		return locks.get(spec);
	}
*/
	public GlobalLock getLock(){
		return lock;
	}

	public String decl() {
		String ret = "";

/*		if (locks.size() <= 0)
			return ret;
*/
/*		ret += "// Declarations for Locks \n";
		for (GlobalLock lock : locks.values()) {
			ret += lock;
		}
		ret += "\n";
*/
		ret += "// Declarations for the Lock \n";
		ret += lock;
		ret += "\n";
		
		return ret;
	}

}
