package ASML_STRUCTURE;

import java.util.ArrayList;
import java.util.List;

import ASML.AM_Exp;
import ASML.AM_Visitor;
import ASML.A_Add;
import ASML.A_App;
import ASML.A_Array;
import ASML.A_Bool;
import ASML.A_Call;
import ASML.A_Callclo;
import ASML.A_Eq;
import ASML.A_FAdd;
import ASML.A_FDiv;
import ASML.A_FMul;
import ASML.A_FNeg;
import ASML.A_FSub;
import ASML.A_Float;
import ASML.A_FunDef2;
import ASML.A_FunDef3;
import ASML.A_FunDefMain;
import ASML.A_Get;
import ASML.A_Ident;
import ASML.A_If;
import ASML.A_Int;
import ASML.A_LE;
import ASML.A_Label;
import ASML.A_Let;
import ASML.A_LetRec;
import ASML.A_LetTuple;
import ASML.A_Mem2;
import ASML.A_Men;
import ASML.A_Neg;
import ASML.A_Nop;
import ASML.A_Put;
import ASML.A_Sub;
import ASML.A_Tuple;
import ASML.A_Unit;
import ASML.A_Var;
import ASML.A_asmt;
import ASML.A_formal_args;
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
