package rs.ac.bg.etf.pp1;

import java.util.LinkedList;
import java.util.List;
import rs.ac.bg.etf.pp1.ast.FormalParam;
import rs.ac.bg.etf.pp1.ast.OptionalArg;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CounterVisitor extends VisitorAdaptor {

	protected int count;
	protected List<Obj> default_params = new LinkedList<>();
	
	public int get_count() {
		return count;
	}
	
	public List<Obj> get_default_params() {
		return default_params;
	}
	
	public static class ParameterCounter extends CounterVisitor {
		
		public void visit(FormalParam formParam) {
			count++;
		}
		
		public void visit(OptionalArg optArgs) {
			count++;
			default_params.add(optArgs.getConstValue().obj);
		}
	}
	
	public static class VariableCounter extends CounterVisitor {
		
		public void visit(VarDecl varDecl) {
			count++;
		}
		
	}
	
}
