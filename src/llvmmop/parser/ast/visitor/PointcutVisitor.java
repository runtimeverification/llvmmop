package llvmmop.parser.ast.visitor;

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

public interface PointcutVisitor<R, A> {

	public R visit(PointCut p, A arg);
	
	public R visit(ArgsPointCut p, A arg);

	public R visit(CombinedPointCut p, A arg);

	public R visit(NotPointCut p, A arg);

	public R visit(ConditionPointCut p, A arg);

	public R visit(FieldPointCut p, A arg);

	public R visit(MethodPointCut p, A arg);

	public R visit(TargetPointCut p, A arg);

	public R visit(ThisPointCut p, A arg);

	public R visit(CFlowPointCut p, A arg);

	public R visit(IFPointCut p, A arg);

	public R visit(IDPointCut p, A arg);

	public R visit(WithinPointCut p, A arg);

	public R visit(ThreadPointCut p, A arg);
	
	public R visit(ThreadNamePointCut p, A arg);

	public R visit(ThreadBlockedPointCut p, A arg);
	
	public R visit(EndProgramPointCut p, A arg);

	public R visit(EndThreadPointCut p, A arg);
	
	public R visit(EndObjectPointCut p, A arg);

	public R visit(StartThreadPointCut p, A arg);
	
}
