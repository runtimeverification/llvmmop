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

package llvmmop.parser.astex.visitor;

import llvmmop.parser.astex.aspect.EventPointCut;
import llvmmop.parser.astex.aspect.HandlerPointCut;
import llvmmop.parser.astex.mopspec.*;
import llvmmop.parser.astex.mopspec.LLVMMOPSpecExt;

/**
 * @author Julio Vilmar Gesser
 */
public interface VoidVisitor<A> extends llvmmop.parser.ast.visitor.VoidVisitor<A> {

	// All extended componenets
	
    //- JavaMOP components
    
	public void visit(ReferenceSpec r, A arg);

    public void visit(LLVMMOPSpecExt s, A arg);
    
    public void visit(EventDefinitionExt e, A arg);
    
    public void visit(PropertyAndHandlersExt p, A arg);
    
    public void visit(FormulaExt f, A arg);
    
    public void visit(ExtendedSpec extendedSpec, A arg);
    
    //- AspectJ components --------------------
    
    public void visit(EventPointCut p, A arg);
    
    public void visit(HandlerPointCut p, A arg);

}
