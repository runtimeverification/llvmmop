package llvmmop;

import java.io.ByteArrayInputStream;
import java.io.File;

import llvmmop.parser.LLVMMOPExtender;
import llvmmop.parser.ast.MOPSpecFile;
import llvmmop.parser.astex.MOPSpecFileExt;
import llvmmop.parser.main_parser.LLVMMOPParser;
import llvmmop.util.Tool;

public class SpecExtractor {

	static private String convertFileToString(String path) throws MOPException {
		String content;
		try {
			content = Tool.convertFileToString(path);
		} catch (Exception e) {
			throw new MOPException(e.getMessage());
		}
		return content;
	}

	static private String getAnnotations(String input) throws MOPException {
		String content = "";

		int start = input.indexOf("/*@", 0), end;

		while (start > -1) {
			end = input.indexOf("@*/", start);

			if (end > -1)
				content += input.substring(start + 3, end); // 4 means /*@ + a space
			else
				throw new MOPException("annotation block didn't end");

			start = input.indexOf("/*@", start + 1);
		}
		return content;
	}

	static public String process(File file) throws MOPException {
		if (Tool.isSpecFile(file.getName())) {
			return convertFileToString(file.getAbsolutePath());
		} else if (Tool.isJavaFile(file.getName())) {
			String javaContent = convertFileToString(file.getAbsolutePath());
			String specContent = getAnnotations(javaContent);
			return specContent;
		} else {
			return "";
		}
	}

	static public MOPSpecFile parse(String input) throws MOPException {
		MOPSpecFile mopSpecFile;
		try {
			MOPSpecFileExt mopSpecFileExt = LLVMMOPParser.parse(new ByteArrayInputStream(input.getBytes()));
			mopSpecFile = LLVMMOPExtender.translateMopSpecFile(mopSpecFileExt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MOPException("Error when parsing a specification file:\n" + e.getMessage());
		}

		return mopSpecFile;
	}

}
