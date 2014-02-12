package llvmmop.parser;

import java.io.File;

import llvmmop.parser.ast.MOPSpecFile;
import llvmmop.parser.astex.MOPSpecFileExt;
import llvmmop.parser.main_parser.LLVMMOPParser;

public class Main {

	public static void main(String[] args) {
		try {
			MOPSpecFileExt f = LLVMMOPParser.parse(new File(args[0]));
			MOPSpecFile o = LLVMMOPExtender.translateMopSpecFile(f);
			
			System.out.print(o.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
