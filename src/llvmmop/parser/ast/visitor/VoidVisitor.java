/*
 * Copyright (C) 2008 Feng Chen.
 * 
 * This file is part of JavaMOP parser.
 *
 * JavaMOP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JavaMOP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JavaMOP.  If not, see <http://www.gnu.org/licenses/>.
 */

package llvmmop.parser.ast.visitor;

import llvmmop.parser.ast.CompilationUnit;
import llvmmop.parser.ast.ImportDeclaration;
import llvmmop.parser.ast.Node;
import llvmmop.parser.ast.PackageDeclaration;
import llvmmop.parser.ast.TypeParameter;
import llvmmop.parser.ast.body.AnnotationDeclaration;
import llvmmop.parser.ast.body.AnnotationMemberDeclaration;
import llvmmop.parser.ast.body.ClassOrInterfaceDeclaration;
import llvmmop.parser.ast.body.ConstructorDeclaration;
import llvmmop.parser.ast.body.EmptyMemberDeclaration;
import llvmmop.parser.ast.body.EmptyTypeDeclaration;
import llvmmop.parser.ast.body.EnumConstantDeclaration;
import llvmmop.parser.ast.body.EnumDeclaration;
import llvmmop.parser.ast.body.FieldDeclaration;
import llvmmop.parser.ast.body.InitializerDeclaration;
import llvmmop.parser.ast.body.MethodDeclaration;
import llvmmop.parser.ast.body.Parameter;
import llvmmop.parser.ast.body.VariableDeclarator;
import llvmmop.parser.ast.body.VariableDeclaratorId;
import llvmmop.parser.ast.expr.ArrayAccessExpr;
import llvmmop.parser.ast.expr.ArrayCreationExpr;
import llvmmop.parser.ast.expr.ArrayInitializerExpr;
import llvmmop.parser.ast.expr.AssignExpr;
import llvmmop.parser.ast.expr.BinaryExpr;
import llvmmop.parser.ast.expr.BooleanLiteralExpr;
import llvmmop.parser.ast.expr.CastExpr;
import llvmmop.parser.ast.expr.CharLiteralExpr;
import llvmmop.parser.ast.expr.ClassExpr;
import llvmmop.parser.ast.expr.ConditionalExpr;
import llvmmop.parser.ast.expr.DoubleLiteralExpr;
import llvmmop.parser.ast.expr.EnclosedExpr;
import llvmmop.parser.ast.expr.FieldAccessExpr;
import llvmmop.parser.ast.expr.InstanceOfExpr;
import llvmmop.parser.ast.expr.IntegerLiteralExpr;
import llvmmop.parser.ast.expr.IntegerLiteralMinValueExpr;
import llvmmop.parser.ast.expr.LongLiteralExpr;
import llvmmop.parser.ast.expr.LongLiteralMinValueExpr;
import llvmmop.parser.ast.expr.MarkerAnnotationExpr;
import llvmmop.parser.ast.expr.MemberValuePair;
import llvmmop.parser.ast.expr.MethodCallExpr;
import llvmmop.parser.ast.expr.NameExpr;
import llvmmop.parser.ast.expr.NormalAnnotationExpr;
import llvmmop.parser.ast.expr.NullLiteralExpr;
import llvmmop.parser.ast.expr.ObjectCreationExpr;
import llvmmop.parser.ast.expr.QualifiedNameExpr;
import llvmmop.parser.ast.expr.SingleMemberAnnotationExpr;
import llvmmop.parser.ast.expr.StringLiteralExpr;
import llvmmop.parser.ast.expr.SuperExpr;
import llvmmop.parser.ast.expr.SuperMemberAccessExpr;
import llvmmop.parser.ast.expr.ThisExpr;
import llvmmop.parser.ast.expr.UnaryExpr;
import llvmmop.parser.ast.expr.VariableDeclarationExpr;
import llvmmop.parser.ast.stmt.AssertStmt;
import llvmmop.parser.ast.stmt.BlockStmt;
import llvmmop.parser.ast.stmt.BreakStmt;
import llvmmop.parser.ast.stmt.CatchClause;
import llvmmop.parser.ast.stmt.ContinueStmt;
import llvmmop.parser.ast.stmt.DoStmt;
import llvmmop.parser.ast.stmt.EmptyStmt;
import llvmmop.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import llvmmop.parser.ast.stmt.ExpressionStmt;
import llvmmop.parser.ast.stmt.ForStmt;
import llvmmop.parser.ast.stmt.ForeachStmt;
import llvmmop.parser.ast.stmt.IfStmt;
import llvmmop.parser.ast.stmt.LabeledStmt;
import llvmmop.parser.ast.stmt.ReturnStmt;
import llvmmop.parser.ast.stmt.SwitchEntryStmt;
import llvmmop.parser.ast.stmt.SwitchStmt;
import llvmmop.parser.ast.stmt.SynchronizedStmt;
import llvmmop.parser.ast.stmt.ThrowStmt;
import llvmmop.parser.ast.stmt.TryStmt;
import llvmmop.parser.ast.stmt.TypeDeclarationStmt;
import llvmmop.parser.ast.stmt.WhileStmt;
import llvmmop.parser.ast.type.ClassOrInterfaceType;
import llvmmop.parser.ast.type.PrimitiveType;
import llvmmop.parser.ast.type.ReferenceType;
import llvmmop.parser.ast.type.VoidType;
import llvmmop.parser.ast.type.WildcardType;
import llvmmop.parser.ast.MOPSpecFile;
import llvmmop.parser.ast.mopspec.*;
import llvmmop.parser.ast.aspect.*;;

/**
 * @author Julio Vilmar Gesser
 */
public interface VoidVisitor<A> {

    public void visit(Node n, A arg);
    
    //- JavaMOP components
    
    public void visit(MOPSpecFile f, A arg);
    
    public void visit(LLVMMOPSpec s, A arg);
    
    public void visit(MOPParameter p, A arg);
    
    public void visit(EventDefinition e, A arg);
    
    public void visit(PropertyAndHandlers p, A arg);
    
    public void visit(Formula f, A arg);
    
    //- AspectJ components --------------------
    
    public void visit(WildcardParameter w, A arg);
    
    public void visit(ArgsPointCut p, A arg);
    
    public void visit(CombinedPointCut p, A arg);
    
    public void visit(NotPointCut p, A arg);
    
    public void visit(ConditionPointCut p, A arg);
    
    public void visit(CountCondPointCut p, A arg);
    
    public void visit(FieldPointCut p, A arg);
    
    public void visit(MethodPointCut p, A arg);
    
    public void visit(TargetPointCut p, A arg);
    
    public void visit(ThisPointCut p, A arg);

    public void visit(CFlowPointCut p, A arg);

    public void visit(IFPointCut p, A arg);
    
    public void visit(IDPointCut p, A arg);

    public void visit(WithinPointCut p, A arg);

    public void visit(ThreadPointCut p, A arg);
    
    public void visit(ThreadNamePointCut p, A arg);
    
    public void visit(ThreadBlockedPointCut p, A arg);

    public void visit(EndProgramPointCut p, A arg);

    public void visit(EndThreadPointCut p, A arg);
    
    public void visit(EndObjectPointCut p, A arg);

    public void visit(StartThreadPointCut p, A arg);

    public void visit(FieldPattern p, A arg);
    
    public void visit(MethodPattern p, A arg);
    
    public void visit(CombinedTypePattern p, A arg);
    
    public void visit(NotTypePattern p, A arg);
    
    public void visit(BaseTypePattern p, A arg);

    //- Compilation Unit ----------------------------------

    public void visit(CompilationUnit n, A arg);

    public void visit(PackageDeclaration n, A arg);

    public void visit(ImportDeclaration n, A arg);

    public void visit(TypeParameter n, A arg);

    //- Body ----------------------------------------------

    public void visit(ClassOrInterfaceDeclaration n, A arg);

    public void visit(EnumDeclaration n, A arg);

    public void visit(EmptyTypeDeclaration n, A arg);

    public void visit(EnumConstantDeclaration n, A arg);

    public void visit(AnnotationDeclaration n, A arg);

    public void visit(AnnotationMemberDeclaration n, A arg);

    public void visit(FieldDeclaration n, A arg);

    public void visit(VariableDeclarator n, A arg);

    public void visit(VariableDeclaratorId n, A arg);

    public void visit(ConstructorDeclaration n, A arg);

    public void visit(MethodDeclaration n, A arg);

    public void visit(Parameter n, A arg);

    public void visit(EmptyMemberDeclaration n, A arg);

    public void visit(InitializerDeclaration n, A arg);

    //- Type ----------------------------------------------

    public void visit(ClassOrInterfaceType n, A arg);

    public void visit(PrimitiveType n, A arg);

    public void visit(ReferenceType n, A arg);

    public void visit(VoidType n, A arg);

    public void visit(WildcardType n, A arg);

    //- Expression ----------------------------------------

    public void visit(ArrayAccessExpr n, A arg);

    public void visit(ArrayCreationExpr n, A arg);

    public void visit(ArrayInitializerExpr n, A arg);

    public void visit(AssignExpr n, A arg);

    public void visit(BinaryExpr n, A arg);

    public void visit(CastExpr n, A arg);

    public void visit(ClassExpr n, A arg);

    public void visit(ConditionalExpr n, A arg);

    public void visit(EnclosedExpr n, A arg);

    public void visit(FieldAccessExpr n, A arg);

    public void visit(InstanceOfExpr n, A arg);

    public void visit(StringLiteralExpr n, A arg);

    public void visit(IntegerLiteralExpr n, A arg);

    public void visit(LongLiteralExpr n, A arg);

    public void visit(IntegerLiteralMinValueExpr n, A arg);

    public void visit(LongLiteralMinValueExpr n, A arg);

    public void visit(CharLiteralExpr n, A arg);

    public void visit(DoubleLiteralExpr n, A arg);

    public void visit(BooleanLiteralExpr n, A arg);

    public void visit(NullLiteralExpr n, A arg);

    public void visit(MethodCallExpr n, A arg);

    public void visit(NameExpr n, A arg);

    public void visit(ObjectCreationExpr n, A arg);

    public void visit(QualifiedNameExpr n, A arg);

    public void visit(SuperMemberAccessExpr n, A arg);

    public void visit(ThisExpr n, A arg);

    public void visit(SuperExpr n, A arg);

    public void visit(UnaryExpr n, A arg);
    
    public void visit(VariableDeclarationExpr n, A arg);

    public void visit(MarkerAnnotationExpr n, A arg);

    public void visit(SingleMemberAnnotationExpr n, A arg);

    public void visit(NormalAnnotationExpr n, A arg);

    public void visit(MemberValuePair n, A arg);

    //- Statements ----------------------------------------

    public void visit(ExplicitConstructorInvocationStmt n, A arg);

    public void visit(TypeDeclarationStmt n, A arg);

    public void visit(AssertStmt n, A arg);

    public void visit(BlockStmt n, A arg);

    public void visit(LabeledStmt n, A arg);

    public void visit(EmptyStmt n, A arg);

    public void visit(ExpressionStmt n, A arg);

    public void visit(SwitchStmt n, A arg);

    public void visit(SwitchEntryStmt n, A arg);

    public void visit(BreakStmt n, A arg);

    public void visit(ReturnStmt n, A arg);

    public void visit(IfStmt n, A arg);

    public void visit(WhileStmt n, A arg);

    public void visit(ContinueStmt n, A arg);

    public void visit(DoStmt n, A arg);

    public void visit(ForeachStmt n, A arg);

    public void visit(ForStmt n, A arg);

    public void visit(ThrowStmt n, A arg);

    public void visit(SynchronizedStmt n, A arg);

    public void visit(TryStmt n, A arg);

    public void visit(CatchClause n, A arg);

}
