package rs.ac.bg.etf.pp1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.ParameterCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VariableCounter;
import rs.ac.bg.etf.pp1.ast.AddOpPlus;
import rs.ac.bg.etf.pp1.ast.BreakSingleStmt;
import rs.ac.bg.etf.pp1.ast.CondFactExpr;
import rs.ac.bg.etf.pp1.ast.CondFactRelOp;
import rs.ac.bg.etf.pp1.ast.ContinueSingleStmt;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorArray;
import rs.ac.bg.etf.pp1.ast.DesignatorDecrement;
import rs.ac.bg.etf.pp1.ast.DesignatorIncrement;
import rs.ac.bg.etf.pp1.ast.DesignatorNamed;
import rs.ac.bg.etf.pp1.ast.DesignatorStmtAssignOp;
import rs.ac.bg.etf.pp1.ast.ExprInfoMultiple;
import rs.ac.bg.etf.pp1.ast.FactorConst;
import rs.ac.bg.etf.pp1.ast.FactorDesignator;
import rs.ac.bg.etf.pp1.ast.FactorDesignatorFunction;
import rs.ac.bg.etf.pp1.ast.FactorDesignatorFunctionArgs;
import rs.ac.bg.etf.pp1.ast.FactorNewParams;
import rs.ac.bg.etf.pp1.ast.IfPartSingleStmt;
import rs.ac.bg.etf.pp1.ast.IfSingleStmt;
import rs.ac.bg.etf.pp1.ast.MethodDeclaration;
import rs.ac.bg.etf.pp1.ast.MethodName;
import rs.ac.bg.etf.pp1.ast.MulOpDiv;
import rs.ac.bg.etf.pp1.ast.MulOpMul;
import rs.ac.bg.etf.pp1.ast.MultipleActPars;
import rs.ac.bg.etf.pp1.ast.MultipleTerm;
import rs.ac.bg.etf.pp1.ast.NegativeExprSign;
import rs.ac.bg.etf.pp1.ast.NoOrCondition;
import rs.ac.bg.etf.pp1.ast.OrCondition;
import rs.ac.bg.etf.pp1.ast.PrintSingleStmtExpr;
import rs.ac.bg.etf.pp1.ast.ReadSingleStmt;
import rs.ac.bg.etf.pp1.ast.RelOpEqual;
import rs.ac.bg.etf.pp1.ast.RelOpGEQ;
import rs.ac.bg.etf.pp1.ast.RelOpGreater;
import rs.ac.bg.etf.pp1.ast.RelOpLess;
import rs.ac.bg.etf.pp1.ast.RelOpNotEqual;
import rs.ac.bg.etf.pp1.ast.ReturnExprSingleStmt;
import rs.ac.bg.etf.pp1.ast.ReturnVoidSingleStmt;
import rs.ac.bg.etf.pp1.ast.SingleActPars;
import rs.ac.bg.etf.pp1.ast.StartDoWhileLooping;
import rs.ac.bg.etf.pp1.ast.StartIfLooping;
import rs.ac.bg.etf.pp1.ast.StartWhileLoopCondition;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.WhileSingleStmt;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	private int main_pc;
	private Struct current_type;
	private boolean return_exists = false;
	
	private int current_actual_params = 0;
	
	private Stack<Integer> while_stack = new Stack<>();
	private Stack<Integer> for_fixup = new Stack<>();
	private Stack<Integer> else_fixup = new Stack<>();
	private Stack<Integer> break_fixup = new Stack<>();
	private Stack<Integer> continue_fixup = new Stack<>();
	private Stack<Integer> start_while_condition = new Stack<>();
	
	private int num_of_cond;
	
	private Stack<Integer> if_start = new Stack<>();
	private Map<Integer, List<Obj>> map = new HashMap<>();
	private Map<Integer, Integer> num_map = new HashMap<>();

	
	public int get_main_pc() {
		return main_pc;
	}
	
	// visit
	
	public void visit(ContinueSingleStmt continueSingleStmt) {
		Code.putJump(0);
		continue_fixup.add(Code.pc - 2);
	}
	
	public void visit(BreakSingleStmt breakSingleStmt) {
		Code.putJump(0);
		break_fixup.add(Code.pc - 2);
	}
	
	public void visit(StartIfLooping startIfLooping) {

		num_of_cond = 0;
	}
	
	public void visit(IfPartSingleStmt ifPartSingleStmt) {
		Code.putJump(0);
		else_fixup.add(Code.pc - 2);
		
		List<Integer> help = new LinkedList<>();
		List<Integer> curr_fixup = new LinkedList<>();
		while (!for_fixup.isEmpty())
			help.add(for_fixup.pop());
		
		for (int i = help.size() - 1; i >= 0; i--)
			curr_fixup.add(help.get(i));
		
		
		for (int i = 0; i < num_of_cond; i++) {
			if (or_condition_adr.size() > 0) {
				int old = Code.pc;
				Code.pc = or_condition_adr.remove(0);
				Code.fixup(curr_fixup.remove(0));
				Code.pc = old;
				
				old = Code.pc;
				Code.pc = if_start.remove(0);
				Code.fixup(curr_fixup.remove(0));
				Code.pc = old;
			} else {
				Code.fixup(curr_fixup.remove(0));
			}
		}
		num_of_cond = 0;
	}
	
	public void visit(IfSingleStmt ifSingleStmt) {
		Code.fixup(else_fixup.pop());
	}
	
	public void visit(StartDoWhileLooping startDoWhileLoop) {
		while_stack.add(Code.pc);

		num_of_cond = 0;
	}
	
	public void visit(StartWhileLoopCondition startWhileLoopCondition) {
		start_while_condition.add(Code.pc);
	}
	
	public void visit(WhileSingleStmt whileSingleStmt) {
		int old;
		
		if (!continue_fixup.isEmpty()) {
			old = Code.pc;
			Code.pc = start_while_condition.pop();
			Code.fixup(continue_fixup.pop());
			Code.pc = old;
		}
		
		
		Code.putJump(0);
		
		if (!break_fixup.isEmpty()) Code.fixup(break_fixup.pop());
		
		int do_help = Code.pc - 2; 
		List<Integer> help = new LinkedList<>();
		List<Integer> curr_fixup = new LinkedList<>();
		while (!for_fixup.isEmpty())
			help.add(for_fixup.pop());
		
		for (int i = help.size() - 1; i >= 0; i--)
			curr_fixup.add(help.get(i));
		
		
		for (int i = 0; i < num_of_cond; i++) {
			if (or_condition_adr.size() > 0) {
				old = Code.pc;
				Code.pc = or_condition_adr.remove(0);
				Code.fixup(curr_fixup.remove(0));
				Code.pc = old;
				
				old = Code.pc;
				Code.pc = if_start.remove(0);
				Code.fixup(curr_fixup.remove(0));
				Code.pc = old;
			} else {
				Code.fixup(curr_fixup.remove(0));
			}
		}
		num_of_cond = 0;
		old = Code.pc;
		Code.pc = while_stack.pop();
		Code.fixup(do_help);
		Code.pc = old;
	}
	
	private List<Integer> or_condition_adr = new LinkedList<>();
	
	public void visit(OrCondition orCondition) {
		System.out.println("orcond");
		if (orCondition.getParent() instanceof OrCondition) {
			or_condition_adr.add(Code.pc);
		} else {
			if_start.add(Code.pc);
		}
	}
	
	public void visit(NoOrCondition noOrCondition) {
		System.out.println("noorcond");
		if (noOrCondition.getParent() instanceof OrCondition) {
			Code.putJump(0);
			or_condition_adr.add(Code.pc);
			for_fixup.add(Code.pc - 2);
		}
	}
	
	public void visit(CondFactExpr condFactExpr) {
		num_of_cond++;
		Code.loadConst(1);
		int code = Code.eq;
		Code.putFalseJump(code, 0);
		for_fixup.add(Code.pc - 2);
	}
	
	public void visit(CondFactRelOp condFactRelOp) {
		num_of_cond++;
		int code;
		if (condFactRelOp.getRelOp() instanceof RelOpEqual)
			code = Code.eq;
		else if (condFactRelOp.getRelOp() instanceof RelOpNotEqual)
			code = Code.ne;
		else if (condFactRelOp.getRelOp() instanceof RelOpGreater)
			code = Code.gt;
		else if (condFactRelOp.getRelOp() instanceof RelOpGEQ)
			code = Code.ge;
		else if (condFactRelOp.getRelOp() instanceof RelOpLess)
			code = Code.lt;
		else
			code = Code.le;
		
		
		Code.putFalseJump(code, 0);
		for_fixup.add(Code.pc - 2);
	}
	
	public void visit(ReturnVoidSingleStmt returnVoidSingleStmt) {
		return_exists = true;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnExprSingleStmt returnExprSingleStmt) {
		return_exists = true;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MethodName methodName) {
		if (methodName.getMethodName().equals("main"))
			main_pc = Code.pc;
		
		methodName.obj.setAdr(Code.pc);
		
		SyntaxNode parent = methodName.getParent();
		VariableCounter var_counter = new VariableCounter();
		parent.traverseTopDown(var_counter);
		
		ParameterCounter parameter_counter = new ParameterCounter();
		parent.traverseTopDown(parameter_counter);
		
		map.put(methodName.obj.getAdr(), parameter_counter.get_default_params());
		num_map.put(methodName.obj.getAdr(), parameter_counter.get_count());
		
		Code.put(Code.enter);
		Code.put(parameter_counter.get_count());
		Code.put(parameter_counter.get_count() + var_counter.get_count());
	}
	
	public void visit(MethodDeclaration methodDeclaration) {
		
		if (!return_exists && methodDeclaration.getMethodName().obj.getType() == Tab.noType) {
			Code.put(Code.exit);
			Code.put(Code.return_);
		} else if (!return_exists) {
			Code.put(Code.trap);
			Code.put(1);
		}
		
		return_exists = false;
	}
	
	public void visit(DesignatorNamed designatorNamed) {
		if (designatorNamed.getParent() instanceof Designator) {
			Code.load(designatorNamed.obj);
		}
	}
	
	public void visit(DesignatorArray designatorArray) {
		if (designatorArray.getParent() instanceof Designator) {
			Code.load(designatorArray.obj);
		}
	}
	
	public void visit(SingleActPars singleActPars) {
		current_actual_params++;
	}
	
	public void visit(MultipleActPars multipleActPars) {
		current_actual_params++;
	}
	
	public void visit(FactorDesignatorFunctionArgs factorDesignatorFunctionArgs) {
		int current_num_params = current_actual_params;
		current_actual_params = 0;
		
		int num_of_params = num_map.get(factorDesignatorFunctionArgs.getDesignator().obj.getAdr());
		List<Obj> default_params = map.get(factorDesignatorFunctionArgs.getDesignator().obj.getAdr());
		int form_params = num_of_params - default_params.size();
		
		for (int i = current_num_params - form_params; i < default_params.size(); i++)
			Code.load(default_params.get(i));
		
		Code.put(Code.call);
		Code.put2(factorDesignatorFunctionArgs.getDesignator().obj.getAdr() - Code.pc + 1);
	}
	
	public void visit(FactorDesignatorFunction factorDesignatorFunction) {
		
		int current_num_params = 0;
		List<Obj> default_params = map.get(factorDesignatorFunction.getDesignator().obj.getAdr());
		
		for (; current_num_params < default_params.size(); current_num_params++)
			Code.load(default_params.get(current_num_params));
		
		Code.put(Code.call);
		Code.put2(factorDesignatorFunction.getDesignator().obj.getAdr() - Code.pc + 1);
	}

	public void visit(DesignatorStmtAssignOp designatorStmtAssignOp) {
		Code.store(designatorStmtAssignOp.getDesignator().obj);
	}
	
	public void visit(DesignatorIncrement designatorIncrement) {
		Code.load(designatorIncrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorIncrement.getDesignator().obj);
	}
	
	public void visit(DesignatorDecrement designatorDecrement) {
		Code.load(designatorDecrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorDecrement.getDesignator().obj);
	}
	
	public void visit(FactorConst factorConst) {
		Code.load(factorConst.getConstValue().obj);
	}
	
	public void visit(FactorDesignator factorDesignator) {
		Code.load(factorDesignator.getDesignator().obj);
	}
	
	public void visit(FactorNewParams factorNewParams) {
		Code.put(Code.newarray);
		
		if (current_type == Tab.charType)
			Code.put(0);
		else
			Code.put(1);
	}
	
	public void visit(NegativeExprSign negativeExprSign) {
		Code.put(Code.neg);
	}
	
	public void visit(ExprInfoMultiple exprInfoMultiple) {
		if (exprInfoMultiple.getAddOp() instanceof AddOpPlus)
			Code.put(Code.add);
		else
			Code.put(Code.sub);
	}
	
	public void visit(MultipleTerm multipleTerm) {
		if (multipleTerm.getMulOp() instanceof MulOpMul)
			Code.put(Code.mul);
		else if (multipleTerm.getMulOp() instanceof MulOpDiv)
			Code.put(Code.div);
		else
			Code.put(Code.rem);
	}
	
	public void visit(PrintSingleStmtExpr printSingleStmtExpr) {
		if (printSingleStmtExpr.getExpr().struct == Tab.charType) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		} else {
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}
	
	public void visit(ReadSingleStmt readSingleStmt) {
		if (readSingleStmt.getDesignator().obj.getType() == Tab.charType)
			Code.put(Code.bread);	
		else {
			Code.put(Code.read);
		}
		Code.store(readSingleStmt.getDesignator().obj);
	}
	
	public void visit(Type type) {
		current_type = type.struct;
	}
}
