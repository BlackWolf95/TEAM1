package ASML_Code_Generation;

import java.util.ArrayList;
import java.util.List;

import ASML_Code_Generation.AM_Exp;
import ASML_Code_Generation.AM_Visitor;
import ASML_Code_Generation.A_Add;
import ASML_Code_Generation.A_App;
import ASML_Code_Generation.A_Array;
import ASML_Code_Generation.A_Bool;
import ASML_Code_Generation.A_Call;
import ASML_Code_Generation.A_Callclo;
import ASML_Code_Generation.A_Eq;
import ASML_Code_Generation.A_FAdd;
import ASML_Code_Generation.A_FDiv;
import ASML_Code_Generation.A_FMul;
import ASML_Code_Generation.A_FNeg;
import ASML_Code_Generation.A_FSub;
import ASML_Code_Generation.A_Float;
import ASML_Code_Generation.A_FunDef2;
import ASML_Code_Generation.A_FunDef3;
import ASML_Code_Generation.A_FunDefMain;
import ASML_Code_Generation.A_Get;
import ASML_Code_Generation.A_Ident;
import ASML_Code_Generation.A_If;
import ASML_Code_Generation.A_Int;
import ASML_Code_Generation.A_LE;
import ASML_Code_Generation.A_Label;
import ASML_Code_Generation.A_Let;
import ASML_Code_Generation.A_LetRec;
import ASML_Code_Generation.A_LetTuple;
import ASML_Code_Generation.A_Mem2;
import ASML_Code_Generation.A_Men;
import ASML_Code_Generation.A_Neg;
import ASML_Code_Generation.A_Nop;
import ASML_Code_Generation.A_Put;
import ASML_Code_Generation.A_Sub;
import ASML_Code_Generation.A_Tuple;
import ASML_Code_Generation.A_Unit;
import ASML_Code_Generation.A_Var;
import ASML_Code_Generation.A_asmt;
import ASML_Code_Generation.A_formal_args;
import Expression.Exp;
import Expression.FunDef;
import Instructions.Variable;

public class Asml_Str implements AM_Visitor{

	//list of local variables of the function
    public List<AM_Exp> local = new ArrayList<>();
    
    //list of expressions to be assigned to variables
    public List<AM_Exp> exp = new ArrayList<>();
    
    //list of functions to be called
    public List<AM_Exp> function = new ArrayList<>();

	@Override
	public void visit(A_Unit e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Bool e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void visit(A_Int e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Float e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Nop e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(A_Neg e) {
		// TODO Auto-generated method stub
		e.accept(this);
	}

	@Override
	public void visit(A_Add e) {
		// TODO Auto-generated method stub
		e.a_e1.accept(this);
		e.a_e2.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_Sub e) {
		// TODO Auto-generated method stub
	    e.a_e1.accept(this);
	    e.a_e2.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_FNeg e) {
		// TODO Auto-generated method stub
		e.accept(this);
	}

	@Override
	public void visit(A_FAdd e) {
		// TODO Auto-generated method stub
		
		exp.add(e);
	}

	@Override
	public void visit(A_FSub e) {
		// TODO Auto-generated method stub
		exp.add(e);
	}

	@Override
	public void visit(A_FMul e) {
		// TODO Auto-generated method stub
		exp.add(e);
	}

	@Override
	public void visit(A_FDiv e) {
		// TODO Auto-generated method stub
		exp.add(e);
	}

	@Override
	public void visit(A_Eq e) {
		// TODO Auto-generated method stub
		e.a_e1.accept(this);
		e.a_e2.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_LE e) {
		// TODO Auto-generated method stub
		e.a_e1.accept(this);
		e.a_e2.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_If e) {
		// TODO Auto-generated method stub
		e.am_e1.accept(this);
		e.am_e2.accept(this);
		e.am_e3.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_Let e) {
		// TODO Auto-generated method stub
		e.e1.accept(this);
		e.e2.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_Var e) {
		// TODO Auto-generated method stub
		local.add(e);
	}

	@Override
	public void visit(A_LetRec e) {
		// TODO Auto-generated method stub
		//function.add(e);
	}

	@Override
	public void visit(A_App e) {
		// TODO Auto-generated method stub
		//
	}

	@Override
	public void visit(A_Tuple e) {
		// TODO Auto-generated method stub
        //		
	}

	@Override
	public void visit(A_LetTuple e) {
		// TODO Auto-generated method stub
		//
	}

	@Override
	public void visit(A_Array e) {
		// TODO Auto-generated method stub
		//
	}

	@Override
	public void visit(A_Get e) {
		// TODO Auto-generated method stub
		//
	}

	@Override
	public void visit(A_Put e) {
		// TODO Auto-generated method stub
		//
	}

	@Override
	public void visit(A_Ident e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Label e) {
		// TODO Auto-generated method stub
		e.accept(this);
		function.add(e);
	}

	@Override
	public void visit(A_formal_args e) {
		// TODO Auto-generated method stub
		e.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_asmt e) {
		// TODO Auto-generated method stub
	    e.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_FunDefMain e) {
		// TODO Auto-generated method stub
		e.a_asmt.accept(this);
		function.add(e);
	}

	@Override
	public void visit(A_FunDef2 e) {
		// TODO Auto-generated method stub
		e.a_label.accept(this);
		e.a_float.accept(this);
		e.fundef.accept(this);
		function.add(e);
	}

	@Override
	public void visit(A_FunDef3 e) {
		// TODO Auto-generated method stub
		e.a_asmt.accept(this);
		e.a_for.accept(this);
		e.a_label.accept(this);
		e.fundef.accept(this);
		function.add(e);
	}

	@Override
	public void visit(A_Call e) {
		// TODO Auto-generated method stub
		e.accept(this);
		function.add(e);
	}

	@Override
	public void visit(A_Men e) {
		// TODO Auto-generated method stub
		e.a_e.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_Mem2 e) {
		// TODO Auto-generated method stub
		e.a_e.accept(this);
		exp.add(e);
	}

	@Override
	public void visit(A_Callclo e) {
		// TODO Auto-generated method stub
		e.accept(this);
		function.add(e);
	}
}
