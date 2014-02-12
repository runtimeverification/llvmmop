package llvmmop.output.combinedaspect.event.advice;

import llvmmop.output.MOPVariable;
import llvmmop.output.combinedaspect.CombinedAspect;
import llvmmop.output.combinedaspect.MOPStatistics;
import llvmmop.parser.ast.mopspec.EventDefinition;
import llvmmop.parser.ast.mopspec.LLVMMOPSpec;
import llvmmop.parser.ast.mopspec.MOPParameters;

public class AdviceBody {
	LLVMMOPSpec mopSpec;
	public EventDefinition event;
	public MOPVariable monitorName;
	public String specName;
	public String fileName;
	
	public MOPStatistics stat;
	
	public boolean isGeneral;
	MOPParameters eventParams;

	public boolean isFullParam;
	CombinedAspect aspect;
	
	public AdviceBody(LLVMMOPSpec mopSpec, EventDefinition event, CombinedAspect combinedAspect) {
		this.mopSpec = mopSpec;
		this.specName = mopSpec.getName();
		this.aspect = combinedAspect;
		this.event = event;
		this.eventParams = event.getMOPParametersOnSpec();
		this.stat = combinedAspect.statManager.getStat(mopSpec);
		this.isGeneral = mopSpec.isGeneral();
		this.isFullParam = eventParams.equals(mopSpec.getParameters());
		this.fileName = combinedAspect.getFileName();
	}
}
