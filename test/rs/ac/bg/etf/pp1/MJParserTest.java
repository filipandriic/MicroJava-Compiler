package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class MJParserTest {
	
	public static String test_name = "test/test302";

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			File sourceCode = new File(test_name + ".mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser mj_parser = new MJParser(lexer);
	        Symbol s = mj_parser.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value); 
	        MyTab.init();
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SemanticPass semantic_pass = new SemanticPass();
			prog.traverseBottomUp(semantic_pass); 
			
			log.info("===================================");
			Tab.dump();
			
			if (!mj_parser.errorDetected && !semantic_pass.is_error_detected()) {
				File output_code = new File("test/program.obj");
				if (output_code.exists()) output_code.delete();
				
				CodeGenerator code_generator = new CodeGenerator();
				prog.traverseBottomUp(code_generator);
				
				Code.dataSize = semantic_pass.get_n_vars();
				Code.mainPc = code_generator.get_main_pc();
				Code.write(new FileOutputStream(output_code));
				
				log.info("Parsing successfull");
			} else
				log.info("Parsing not successfull");
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	
}
