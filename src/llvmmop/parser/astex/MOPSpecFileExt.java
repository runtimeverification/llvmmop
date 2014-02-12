package llvmmop.parser.astex;

import java.util.List;

import llvmmop.parser.ast.ImportDeclaration;
import llvmmop.parser.ast.PackageDeclaration;
import llvmmop.parser.astex.mopspec.LLVMMOPSpecExt;
import llvmmop.parser.astex.visitor.GenericVisitor;
import llvmmop.parser.astex.visitor.VoidVisitor;

public class MOPSpecFileExt extends ExtNode {
	PackageDeclaration pakage = null;
	List<ImportDeclaration> imports = null;
	List<LLVMMOPSpecExt> specList = null;
	
    public MOPSpecFileExt(int line, int column, PackageDeclaration pakage, List<ImportDeclaration> imports, List<LLVMMOPSpecExt> specList) {
        super(line, column);
        this.pakage = pakage;
        this.imports = imports;
        this.specList = specList;
    }
    public MOPSpecFileExt() {
		super(0,0);
	}
	public PackageDeclaration getPakage() {
        return pakage;
    }


    public List<ImportDeclaration> getImports() {
        return imports;
    }
	
    public List<LLVMMOPSpecExt> getSpecs() {
        return specList;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }
    
    /**
     * returns the LLVMMOPSpecExt object for a specification with a specified name
     *
     */
  
	public LLVMMOPSpecExt getSpec(String name) {
		for(LLVMMOPSpecExt spec:this.getSpecs()){
			if(spec.getName().compareTo(name)==0)
				return spec;
		}
		return null;
	}

}
