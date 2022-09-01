package rs.ac.bg.etf.pp1;


import java.util.List;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	private int n_vars;
	private boolean main_exists = false;
	private boolean error_detected = false;
	private int do_while_loops = 0;
	
	private boolean is_negative = false;
	private boolean is_array = false;
	
	private Struct current_type;
	private String current_type_name;
	private Struct current_method_return_type;
	private List<Struct> current_actual_parameters = new LinkedList<>();
	private Obj current_method = null;
	
	private int curr_level = 0;
	
	Logger log = Logger.getLogger(getClass());
	
	public int get_n_vars() {
		return n_vars;
	}
	
	public boolean is_error_detected() {
		return error_detected;
	}
	
	// reports
	
	public void report_error(String message, SyntaxNode info) {
		error_detected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	// utils
	
	public boolean equivalent_types(Struct src, Struct dst) {
		
		if (src == dst) return true;
		
		if (src.getKind() == Struct.Array && dst.getKind() == Struct.Array
				&& equivalent_types(src.getElemType(), dst.getElemType()))
			return true;
		
		return false;
	}
	
	public boolean compatible_types(Struct src, Struct dst) {
		
		if ((src.isRefType() && dst == Tab.nullType)
				|| (src == Tab.nullType && dst.isRefType()))
			return true;
		
		return equivalent_types(src, dst);
	}
	
	public boolean assignment_compatible_types(Struct src, Struct dst) {
		
		if (src == Tab.nullType && dst.isRefType()) return true;
		
//		if (src.getKind() == Struct.Class && dst.getKind() == Struct.Class
//				&& src.getClass().getSuperclass() == dst.getClass())
//				return true;
		
		return equivalent_types(src, dst);
	}
	
	public void parseVarDecl(int kind, String name, Struct type) {
		if (Tab.currentScope().findSymbol(name) != null) {
			report_error("Variable " + current_type_name + " " + name + " already declared", null);
			return;
		}
		
		Obj obj = Tab.insert(kind, name, type);
		obj.setFpPos(2); // to not mix up with method's arguments
		if (kind == Obj.Fld)
			obj.setAdr(obj.getAdr() + 1);
		
		String s = "Global";
		if (curr_level > 0) s = "Local";
		
		report_info(s + " variable " + current_type_name + " " + name + " declared", null);
	}
	
	public boolean parseActualParameters(Obj method) {
		
		int form_pars = 0;
		int all_pars = 0;
		List<Obj> locals = new LinkedList<>();
		
		for (Obj local_symbol : method.getLocalSymbols()) {
			if (local_symbol.getFpPos() == 0) form_pars++;
			if (local_symbol.getFpPos() != 2) all_pars++;
			locals.add(local_symbol);
		}
		
		if (current_actual_parameters.size() < form_pars || current_actual_parameters.size() > all_pars) return false;
		
		for (int i = 0; i < current_actual_parameters.size(); i++) {
			if (!assignment_compatible_types(current_actual_parameters.get(i), locals.get(i).getType()))
				return false;
		}
		
		return true;
	}
	// visit
	
	public void visit(ProgramName programName) {
		programName.obj = Tab.insert(Obj.Prog, programName.getProgName(), Tab.noType);
		Tab.openScope();
		report_info("start main", programName);
	}
	
	public void visit(Program program) {
		if (!main_exists)
			report_error("Main method is missing", program);
		
		n_vars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgramName().obj);
		Tab.closeScope();

		report_info("end main", program);
	}
	
	public void visit(VarNamePartSingle varName) {
		parseVarDecl(Obj.Var, varName.getVarName(), current_type);
	}
	
	public void visit(VarNamePartArray varName) {
		parseVarDecl(Obj.Var, varName.getVarName(), new Struct(Struct.Array, current_type));
	}
	
	public void visit(ClassVarNamePartSingle classVar) {
		parseVarDecl(Obj.Fld, classVar.getClassVarName(), current_type);
	}
	
	public void visit(ClassVarNamePartArray classVar) {
		parseVarDecl(Obj.Fld, classVar.getClassVarName(), new Struct(Struct.Array, current_type));
	}
	
	
	public void visit(ConstNamePart constVar) {
		if (Tab.currentScope().findSymbol(constVar.getConstName()) != null) {
			report_error("Constant " + current_type_name + " " + constVar.getConstName() + " already declared", constVar);
			return;
		}
		
		if (current_type != Tab.intType && current_type != Tab.charType && current_type != MyTab.boolType) {
			report_error("Constant has to be int, char, or bool type", constVar);
		}
		
		int addr = 0;
		
		if (current_type == constVar.getConstValue().obj.getType()) {
			addr = constVar.getConstValue().obj.getAdr();
		} else
			report_error("Constant types not matching", constVar);
		
		report_info("Const " + current_type_name + " " + constVar.getConstName() + " declared", constVar);
		Obj obj = Tab.insert(Obj.Con, constVar.getConstName(), current_type);
		obj.setAdr(addr);
	}
	
	public void visit(MethodReturnVal methodReturnVal) {
		current_method_return_type = methodReturnVal.getType().struct;
		report_info("Method returns " + methodReturnVal.getType().getTypeName(), methodReturnVal);
	}
	
	public void visit(MethodReturnVoid methodReturnVoid) {
		current_method_return_type = Tab.noType;
		report_info("Method returns void", methodReturnVoid);
	}
	
	public void visit(MethodName methodName) {
		if (Tab.currentScope().findSymbol(methodName.getMethodName()) != null) {
			report_error("Method " + methodName.getMethodName() + " already declared", null);
			return;
		}
		
		curr_level++;
		current_method = Tab.insert(Obj.Meth, methodName.getMethodName(), current_method_return_type);
		methodName.obj = current_method;
		Tab.openScope();
		report_info("start method " + methodName.getMethodName(), methodName);
	}
	
	public void visit(FormParamSingle formParamSingle) {
		if (Tab.currentScope().findSymbol(formParamSingle.getFormName()) != null) {
			report_error("Formal argument already declared", formParamSingle);
			return;
		}
		
		Obj obj = Tab.insert(Obj.Var, formParamSingle.getFormName(), current_type);
		report_info("Formal argument " + formParamSingle.getFormName() + " declared", formParamSingle);
	}
	
	public void visit(FormParamArray formParamArray) {
		if (Tab.currentScope().findSymbol(formParamArray.getFormName()) != null) {
			report_error("Argument already declared", formParamArray);
			return;
		}
		
		Obj obj = Tab.insert(Obj.Var, formParamArray.getFormName(), new Struct(Struct.Array, current_type));
		report_info("Formal argument " + formParamArray.getFormName() + " declared", formParamArray);
	}
	
	public void visit(OptionalArg optionalArg) {
		if (Tab.currentScope().findSymbol(optionalArg.getOptName()) != null) {
			report_error("Optional argument already declared", optionalArg);
			return;
		}
		
		if (current_type != optionalArg.getConstValue().obj.getType()) {
			report_error("Default argument type not matching", optionalArg);
		}
		
		Obj obj = Tab.insert(Obj.Var, optionalArg.getOptName(), current_type);
		obj.setFpPos(1);
		report_info("Optional argument " + optionalArg.getOptName() + " declared", optionalArg);
	}
	
	public void visit(MethodDeclaration methodDeclaration) {
		Tab.chainLocalSymbols(current_method);
		Tab.closeScope();
		
		if (methodDeclaration.getMethodName().getMethodName().equals("main")) {
			main_exists = true;
			
			if (current_method.getType() != Tab.noType)
				report_error("Main method can't return type", methodDeclaration);
			
			int num_args = 0;
			for (Obj local_symbol : current_method.getLocalSymbols())
				if (local_symbol.getFpPos() != 2)
					num_args++;
			
			if (num_args > 0)
				report_error("Main method can't have arguments", methodDeclaration);
		}
		
		current_method = null;
		curr_level--;
	}
	
	public void visit(DesignatorNamed designatorNamed) {
		Obj obj = Tab.find(designatorNamed.getDesignatorName());
		
		if (obj == Tab.noObj)
			report_error("The variable " + designatorNamed.getDesignatorName() + " isn't declared", designatorNamed);
		else {
			String s = "Global";
			if (curr_level > 0) s = "Local";
			report_info(s + " variable " + designatorNamed.getDesignatorName() + " used", designatorNamed);
		}
		
		designatorNamed.obj = obj;
	}
	
	public void visit(DesignatorArray designatorArray) {
		Obj obj = designatorArray.getDesignator().obj;
		
		Obj res = Tab.noObj;
		
		if (obj.getType().getKind() != Struct.Array)
			report_error("Variable " + obj.getName() + " is not an array", designatorArray);
		else if (designatorArray.getExpr().struct != Tab.intType)
			report_error("Index must be int type", designatorArray);
		else {
			res = new Obj(Obj.Elem, "element", obj.getType().getElemType());
			String s = "Global";
			if (curr_level > 0) s = "Local";
			report_info(s + " array " + designatorArray.getDesignator().obj.getName() + " used", designatorArray);
		}
			
		designatorArray.obj = res;
	}
	
	public void visit(StartDoWhileLooping startDoWhileLoop) {
		do_while_loops++;
	}
	
	public void visit(WhileSingleStmt whileSingleStmt) {
		do_while_loops--;
	}
	
	public void visit(BreakSingleStmt breakSingleStmt) {
		if (do_while_loops == 0)
			report_error("Use break only in while loops", breakSingleStmt);
	}
	
	public void visit(ContinueSingleStmt continueSingleStmt) {
		if (do_while_loops == 0)
			report_error("Use continue only in while loops", continueSingleStmt);
	}
	
	public void visit(ReadSingleStmt readSingleStmt) {
		int designator_kind = readSingleStmt.getDesignator().obj.getKind();
		Struct designator_type = readSingleStmt.getDesignator().obj.getType();
		
		if (designator_kind != Obj.Var && designator_kind != Obj.Elem && designator_kind != Obj.Fld)
			report_error("Can't read this kind of variable", readSingleStmt);
		else if (designator_type != Tab.intType && designator_type != Tab.charType && designator_type != MyTab.boolType)
			report_error("Can't read this type of variable", readSingleStmt);
	}
	
	public void visit(PrintSingleStmtNumber printSingleStmtNumber) {
		Struct expr_type = printSingleStmtNumber.getExpr().struct;
		
		if (expr_type != Tab.intType && expr_type != Tab.charType && expr_type != MyTab.boolType)
			report_error("Print only integer, character or boolean types", printSingleStmtNumber);
	}
	
	public void visit(PrintSingleStmtExpr printSingleStmtNumber) {
		Struct expr_type = printSingleStmtNumber.getExpr().struct;
		
		if (expr_type != Tab.intType && expr_type != Tab.charType && expr_type != MyTab.boolType)
			report_error("Print only integer, character or boolean types", printSingleStmtNumber);
		
	}
	
	public void visit(DesignatorStmtAssignOp designatorStmtAssignOp) {
		int designator_kind = designatorStmtAssignOp.getDesignator().obj.getKind();
		
		if (designator_kind != Obj.Var && designator_kind != Obj.Elem && designator_kind != Obj.Fld)
			report_error("Can't assign to this kind of variable", designatorStmtAssignOp);
		
		if (!compatible_types(designatorStmtAssignOp.getDesignator().obj.getType(), designatorStmtAssignOp.getExpr().struct))
			report_error("Types aren't compatible in assingment", designatorStmtAssignOp);
	}
	
	public void visit(DesignatorIncrement designatorIncrement) {
		Obj current_designator = designatorIncrement.getDesignator().obj;
		int designator_kind = current_designator.getKind();
		Struct designator_type = current_designator.getType();
		
		if (designator_kind != Obj.Var && designator_kind != Obj.Elem && designator_kind != Obj.Fld)
			report_error("Can't increment this kind of variable", designatorIncrement);
		else if (designator_type != Tab.intType)
			report_error("Can't increment this type of variable", designatorIncrement);
		
		report_info("Increment on variable " + current_designator.getName(), designatorIncrement);
	}
	
	public void visit(DesignatorDecrement designatorDecrement) {
		Obj current_designator = designatorDecrement.getDesignator().obj;
		int designator_kind = current_designator.getKind();
		Struct designator_type = current_designator.getType();
		
		if (designator_kind != Obj.Var && designator_kind != Obj.Elem && designator_kind != Obj.Fld)
			report_error("Can't increment this kind of variable", designatorDecrement);
		else if (designator_type != Tab.intType)
			report_error("Can't increment this type of variable", designatorDecrement);
		
		report_info("Decrement on variable " + current_designator.getName(), designatorDecrement);
	}
	
	public void visit(DesignatorNoActPars designatorNoActPars) {
		Obj current_designator = designatorNoActPars.getDesignator().obj;
		int designator_kind = current_designator.getKind();
		
		if (designator_kind != Obj.Meth)
			report_error("Name " + current_designator.getName() + " should be a function", designatorNoActPars);
		
		if (!parseActualParameters(current_designator))
			report_error("Actual and formal parameters don't match", designatorNoActPars);
	}
	
	public void visit(DesignatorActPars designatorActPars) {
		Obj current_designator = designatorActPars.getDesignator().obj;
		int designator_kind = current_designator.getKind();
		
		if (designator_kind != Obj.Meth)
			report_error("Name " + current_designator.getName() + " should be a function", designatorActPars);
		
		if (!parseActualParameters(current_designator))
			report_error("Actual and formal parameters don't match", designatorActPars);
	}
	
	public void visit(ReturnVoidSingleStmt returnVoidSingleStmt) {
		if (current_method == null)
			report_error("Return has to be inside of a method", returnVoidSingleStmt);
		else if (current_method_return_type != Tab.noType)
			report_error("Method return type should be void", returnVoidSingleStmt);
	}
	
	public void visit(ReturnExprSingleStmt returnExprSingleStmt) {
		if (current_method == null)
			report_error("Return has to be inside of a method", returnExprSingleStmt);
		else if (current_method.getType() != returnExprSingleStmt.getExpr().struct)
			report_error("Method return type doesn't match", returnExprSingleStmt);
	}
	
	public void visit(MultipleActPars multipleActPars) {
		current_actual_parameters.add(multipleActPars.getExpr().struct);
	}
	
	public void visit(SingleActPars singleActPars) {
		current_actual_parameters.add(singleActPars.getExpr().struct);
	}
	
	public void visit(SignExpression signExpression) {
		signExpression.struct = signExpression.getExprInfoMore().struct;
	}
	
	public void visit(NullischoExpression nullischoExpression) {
		Struct type = Tab.intType;
		if (nullischoExpression.getExprInfoMore().struct != Tab.intType ||
				nullischoExpression.getExprInfoMore1().struct != Tab.intType) {
			report_error("In ?? both types have to be int", nullischoExpression);
			type = Tab.noType;
		}
		
		nullischoExpression.struct = type;
	}
	
	public void visit(ExprInfoMoreOnly exprInfoMoreOnly) {
		if (is_negative && exprInfoMoreOnly.getExprInfo().struct != Tab.intType) {
			report_error("Only int can be negative", exprInfoMoreOnly);
		}
		exprInfoMoreOnly.struct = exprInfoMoreOnly.getExprInfo().struct;
		is_negative = false;
	}
	
	public void visit(ExprInfoSingle exprInfoSingle) {
		exprInfoSingle.struct = exprInfoSingle.getTerm().struct;
	}
	
	public void visit(ExprInfoMultiple exprInfoMultiple) {
		Struct type = Tab.noType;
		if (exprInfoMultiple.getExprInfo().struct != Tab.intType ||
				exprInfoMultiple.getTerm().struct != Tab.intType)
			report_error("Can't use + and - on non int types", exprInfoMultiple);
		else
			type = Tab.intType;
		
		exprInfoMultiple.struct = type;
	}
	
	public void visit(NegativeExprSign negativeExprSign) {
		is_negative = true;
	}
	
	public void visit(SingleTerm singleTerm) {
		singleTerm.struct = singleTerm.getFactor().struct;
	}
	
	public void visit(MultipleTerm multipleTerm) {
		Struct type = Tab.intType;
		
		if (multipleTerm.getFactor().struct != Tab.intType ||
				multipleTerm.getTerm().struct != Tab.intType) {
			report_error("Can't use *, / or % on non int types", multipleTerm);
			type = Tab.noType;
		}
		
		multipleTerm.struct = type;
	}
	
	public void visit(FactorDesignator factorDesignator) {
		factorDesignator.struct = factorDesignator.getDesignator().obj.getType();
	}
	
	public void visit(FactorDesignatorFunction factorDesignatorFunction) {
		Struct type = Tab.noType;
		
		if (factorDesignatorFunction.getDesignator().obj.getKind() != Obj.Meth)
			report_error("Designator " + factorDesignatorFunction.getDesignator().obj.getName() + " should be a function", factorDesignatorFunction);
		
		if (parseActualParameters(factorDesignatorFunction.getDesignator().obj)) {
			type = factorDesignatorFunction.getDesignator().obj.getType();
			report_info("Call function " + factorDesignatorFunction.getDesignator().obj.getName() + " ", factorDesignatorFunction);
		} else
			report_error("Actual parameters don't match for function " + factorDesignatorFunction.getDesignator().obj.getName(), factorDesignatorFunction);
			
		factorDesignatorFunction.struct = type;
		current_actual_parameters.clear();
	}
	
	public void visit(FactorDesignatorFunctionArgs factorDesignatorFunctionArgs) {
		Struct type = Tab.noType;
		
		if (factorDesignatorFunctionArgs.getDesignator().obj.getKind() != Obj.Meth)
			report_error("Designator " + factorDesignatorFunctionArgs.getDesignator().obj.getName() + " should be a function", factorDesignatorFunctionArgs);
		
		if (parseActualParameters(factorDesignatorFunctionArgs.getDesignator().obj)) {
			type = factorDesignatorFunctionArgs.getDesignator().obj.getType();
			report_info("Call function " + factorDesignatorFunctionArgs.getDesignator().obj.getName() + " ", factorDesignatorFunctionArgs);
		} else
			report_error("Actual parameters don't match for function " + factorDesignatorFunctionArgs.getDesignator().obj.getName(), factorDesignatorFunctionArgs);
		
		factorDesignatorFunctionArgs.struct = type;
		current_actual_parameters.clear();
	}
	
	public void visit(FactorConst factorConst) {
		factorConst.struct = factorConst.getConstValue().obj.getType();
	}
	
	public void visit(FactorNew factorNew) {
		if (is_array) {
			factorNew.struct = new Struct(Struct.Array, factorNew.getType().struct);
			report_info("New array", factorNew);
		} else {
			
		}
		
		is_array = false;
	}
	
	public void visit(FactorNewParams factorNewParams) {
		if (factorNewParams.getExpr().struct != Tab.intType)
			report_error("Index of array must be int", factorNewParams);
		
		is_array = true;
	}
	
	public void visit(FactorExpr factorExpr) {
		factorExpr.struct = factorExpr.getExpr().struct;
	}
	
	public void visit(CondFactExpr condFactExpr) {
		if (condFactExpr.getExpr().struct != MyTab.boolType)
			report_error("Condition must be bool type", condFactExpr);
	}
	
	public void visit(CondFactRelOp condFactRelOp) {
		if (!(compatible_types(condFactRelOp.getExpr().struct, condFactRelOp.getExpr1().struct))) {
			report_error("Types are not compatible in if statement", condFactRelOp);
		}
		
		if (condFactRelOp.getExpr().struct.isRefType() &&
				(!(condFactRelOp.getRelOp() instanceof RelOpEqual) || !(condFactRelOp.getRelOp() instanceof RelOpNotEqual)))
			report_error("Only use == or != with arrays and classes", condFactRelOp);
	}
	
	public void visit(NumConst numConst) {
		numConst.obj = new Obj(Obj.Con, "integer", Tab.intType);
		numConst.obj.setAdr(numConst.getValue());
	}
	
	public void visit(CharConst charConst) {
		charConst.obj = new Obj(Obj.Con, "character", Tab.charType);
		charConst.obj.setAdr(charConst.getValue());
	}
	
	public void visit(BoolConst boolConst) {
		boolConst.obj = new Obj(Obj.Con, "boolean", MyTab.boolType);
		boolConst.obj.setAdr(boolConst.getValue() ? 1 : 0);
	}
	
	public void visit(Type type) {
		Obj obj = Tab.find(type.getTypeName());
		
		if (obj == Tab.noObj) {
			report_error("No type found: " + type.getTypeName(), null);
			type.struct = Tab.noType;
		} else if (obj.getKind() == Obj.Type) {
			type.struct = obj.getType();
		} else {
			report_error(type.getTypeName() + " is not a type", type);
			type.struct = Tab.noType;
		}
		
		current_type = type.struct;
		current_type_name = type.getTypeName();
	}
}
