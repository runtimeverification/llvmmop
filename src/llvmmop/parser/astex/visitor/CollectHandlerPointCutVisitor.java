package llvmmop.parser.astex.visitor;

import java.util.ArrayList;
import java.util.List;

import llvmmop.parser.ast.aspect.ArgsPointCut;
import llvmmop.parser.ast.aspect.CFlowPointCut;
import llvmmop.parser.ast.aspect.CombinedPointCut;
import llvmmop.parser.ast.aspect.ConditionPointCut;
import llvmmop.parser.ast.aspect.EndObjectPointCut;
import llvmmop.parser.ast.aspect.EndProgramPointCut;
import llvmmop.parser.ast.aspect.EndThreadPointCut;
import llvmmop.parser.ast.aspect.FieldPointCut;
import llvmmop.parser.ast.aspect.IDPointCut;
import llvmmop.parser.ast.aspect.IFPointCut;
import llvmmop.parser.ast.aspect.MethodPointCut;
import llvmmop.parser.ast.aspect.NotPointCut;
import llvmmop.parser.ast.aspect.PointCut;
import llvmmop.parser.ast.aspect.StartThreadPointCut;
import llvmmop.parser.ast.aspect.TargetPointCut;
import llvmmop.parser.ast.aspect.ThisPointCut;
import llvmmop.parser.ast.aspect.ThreadBlockedPointCut;
import llvmmop.parser.ast.aspect.ThreadNamePointCut;
import llvmmop.parser.ast.aspect.ThreadPointCut;
import llvmmop.parser.ast.aspect.WithinPointCut;
import llvmmop.parser.astex.aspect.EventPointCut;
import llvmmop.parser.astex.aspect.HandlerPointCut;

public class CollectHandlerPointCutVisitor implements PointcutVisitor<List<HandlerPointCut>, Object> {

	@Override
	public List<HandlerPointCut> visit(PointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(ArgsPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(CombinedPointCut p, Object arg) {
		List<HandlerPointCut> ret = new ArrayList<HandlerPointCut>();
		
		for(PointCut p2 : p.getPointcuts()){
			List<HandlerPointCut> temp = p2.accept(this, arg);
			if(temp != null){
				ret.addAll(temp);
			}
		}
		return ret;
	}

	@Override
	public List<HandlerPointCut> visit(NotPointCut p, Object arg) {
		return p.getPointCut().accept(this, arg);
	}

	@Override
	public List<HandlerPointCut> visit(ConditionPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(FieldPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(MethodPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(TargetPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(ThisPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(CFlowPointCut p, Object arg) {
		return p.getPointCut().accept(this, arg);
	}

	@Override
	public List<HandlerPointCut> visit(IFPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(IDPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(WithinPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(ThreadPointCut p, Object arg) {
		return null;
	}
	
	@Override
	public List<HandlerPointCut> visit(ThreadBlockedPointCut p, Object arg) {
		return null;
	}
	
	@Override
	public List<HandlerPointCut> visit(ThreadNamePointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(EndProgramPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(EndThreadPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(EndObjectPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(StartThreadPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(EventPointCut p, Object arg) {
		return null;
	}

	@Override
	public List<HandlerPointCut> visit(HandlerPointCut p, Object arg) {
		List<HandlerPointCut> ret = new ArrayList<HandlerPointCut>();
		ret.add(p);
		return ret;
	}
}
