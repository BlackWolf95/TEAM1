package ASML_Code_Generation;

import Expression.Add;
import Expression.App;
import Expression.Array;
import Expression.Bool;
import Expression.Eq;
import Expression.FAdd;
import Expression.FDiv;
import Expression.FMul;
import Expression.FNeg;
import Expression.FSub;
import Expression.Float;
import Expression.Get;
import Expression.If;
import Expression.Int;
import Expression.LE;
import Expression.Let;
import Expression.LetRec;
import Expression.LetTuple;
import Expression.Neg;
import Expression.Not;
import Expression.Put;
import Expression.Sub;
import Expression.Tuple;
import Expression.Unit;
import Expression.Var;

public interface AM_Objvisitor<E> {

	    E visit(A_Unit e);
	    E visit(A_Bool e);
	    E visit(A_Int e);
	    E visit(A_Float e);
	    E visit(A_Nop e);
	    E visit(A_Neg e);
	    E visit(A_Add e);
	    E visit(A_Sub e);
	    E visit(A_FNeg e);
	    E visit(A_FAdd e);
	    E visit(A_FSub e);
	    E visit(A_FMul e);
	    E visit(A_FDiv e);
	    E visit(A_Eq e);
	    E visit(A_LE e);
	    E visit(A_If e);
	    
	    
	    E visit(A_Ident e);
	    E visit(A_Let e);
	    E visit(A_Label e);
	    
	    
	    E visit(A_Var e);
	    E visit(A_LetRec e);
	    E visit(A_App e);
	    E visit(A_Tuple e);
	    E visit(A_LetTuple e);
	    E visit(A_Array e);
	    E visit(A_Get e);
	    E visit(A_Put e);
		E visit(A_formal_args e);
		E visit(A_asmt e);
		E visit(A_FunDefMain e);
		E visit(A_FunDef2 e);
		E visit(A_FunDef3 e);
		E visit(A_Call e);
		E visit(A_Men e);
		E visit(A_Mem2 e);
		E visit(A_Callclo e);
		
	    
}
