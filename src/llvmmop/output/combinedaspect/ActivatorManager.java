package llvmmop.output.combinedaspect;

import java.util.List;
import java.util.TreeMap;

import llvmmop.output.MOPVariable;
import llvmmop.parser.ast.mopspec.LLVMMOPSpec;

public class ActivatorManager {

	List<LLVMMOPSpec> specs;
	TreeMap<LLVMMOPSpec, MOPVariable> activators = new TreeMap<LLVMMOPSpec, MOPVariable>();

	public ActivatorManager(String name, List<LLVMMOPSpec> specs) {
		this.specs = specs;
		for (LLVMMOPSpec spec : specs) {
			activators.put(spec, new MOPVariable(spec.getName() + "_activated"));
		}
	}

	public MOPVariable getActivator(LLVMMOPSpec spec) {
		return activators.get(spec);
	}

	public String decl() {
		String ret = "";

		for (MOPVariable activator : activators.values()) {
			ret += "static boolean " + activator + " = false;\n";
		}

		if (activators.size() > 0)
			ret += "\n";

		return ret;
	}

	public String reset() {
		String ret = "";

		for (MOPVariable activator : activators.values()) {
			ret += activator + " = false;\n";
		}

		if (activators.size() > 0)
			ret += "\n";

		return ret;
	}

}
