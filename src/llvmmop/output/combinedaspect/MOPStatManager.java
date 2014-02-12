package llvmmop.output.combinedaspect;

import java.util.HashMap;
import java.util.List;

import llvmmop.LLVMMOPMain;
import llvmmop.MOPException;
import llvmmop.output.MOPVariable;
import llvmmop.parser.ast.mopspec.EventDefinition;
import llvmmop.parser.ast.mopspec.LLVMMOPSpec;

public class MOPStatManager {

	HashMap<LLVMMOPSpec, MOPStatistics> stats = new HashMap<LLVMMOPSpec, MOPStatistics>();

	MOPVariable statClass;
	MOPVariable statObject;
	
	public MOPStatManager(String name, List<LLVMMOPSpec> specs) throws MOPException {
		for (LLVMMOPSpec spec : specs) {
			stats.put(spec, new MOPStatistics(name, spec));
		}
		
		
		statClass = new MOPVariable(name + "_Statistics");
		statObject = new MOPVariable(name + "_StatisticsInstance");
	}

	public MOPStatistics getStat(LLVMMOPSpec spec){
		return stats.get(spec);
	}
	
	public String statClass() {
		String ret = "";

		if (!LLVMMOPMain.statistics2)
			return ret;
		
		ret = "class " + statClass + " extends Thread implements javamoprt.MOPObject {\n";
		
		ret += "static public long numTotalEvents = 0;\n"; 
		ret += "static public long numTotalMonitors = 0;\n"; 
		
		ret += "public void run() {\n";
		{
			ret += "System.err.println(\"# of total events: \" + " + statClass + ".numTotalEvents);\n";
			ret += "System.err.println(\"# of total monitors: \" + " + statClass + ".numTotalMonitors);\n";
		}
		ret += "}\n";
		
		ret += "}\n";
		
		return ret;
	}
	
	public String incEvent(LLVMMOPSpec spec, EventDefinition event){
		String ret = "";

		if (!LLVMMOPMain.statistics2)
			return ret;

		ret += statClass + ".numTotalEvents++;\n";
		
		return ret;
	}
	
	public String incMonitor(LLVMMOPSpec spec){
		String ret = "";

		if (!LLVMMOPMain.statistics2)
			return ret;
		
		ret += statClass + ".numTotalMonitors++;\n";
		
		return ret;
	}
	
	public String fieldDecl2() {
		String ret = "";

		if (!LLVMMOPMain.statistics2)
			return ret;

		ret += "static " + statClass + " " + statObject + ";\n";
		
		return ret;
	}

	public String constructor() {
		String ret = "";

		if (!LLVMMOPMain.statistics2)
			return ret;

		ret += statObject + " = new " + statClass + "();\n";
		ret += "Runtime.getRuntime().addShutdownHook(" + statObject + ");\n";
		
		return ret;
	}
	
	
	
	public String fieldDecl() {
		String ret = "";

		if (!LLVMMOPMain.statistics)
			return ret;

		ret += "// Declarations for Statistics \n";
		for (MOPStatistics stat : stats.values()) {
			ret += stat.fieldDecl();
		}
		ret += "\n";

		return ret;
	}

	public String advice() {
		String ret = "";

		if (!LLVMMOPMain.statistics)
			return ret;

		ret += "\n";
		ret += "// advices for Statistics \n";
		for (MOPStatistics stat : stats.values()) {
			ret += stat.advice();
		}

		return ret;
	}

}
