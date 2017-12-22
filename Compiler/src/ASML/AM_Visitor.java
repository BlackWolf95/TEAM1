package ASML;

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

public interface AM_Visitor {

	void visit(A_Unit e);
    void visit(A_Bool e);
    void visit(A_Int e);
    void visit(A_Float e);
    void visit(A_Nop e);
    void visit(A_Neg e);
    void visit(A_Add e);
    void visit(A_Sub e);
    void visit(A_FNeg e);
    void visit(A_FAdd e);
    void visit(A_FSub e);
    void visit(A_FMul e);
    void visit(A_FDiv e);
    void visit(A_Eq e);
    void visit(A_LE e);
    void visit(A_If e);
    
    
    
    void visit(A_Let e);
    
    
    
    
    void visit(A_Var e);
    void visit(A_LetRec e);
    void visit(A_App e);
    void visit(A_Tuple e);
    void visit(A_LetTuple e);
    void visit(A_Array e);
    void visit(A_Get e);
    void visit(A_Put e);
	void visit(A_Ident e);
	void visit(A_Label e);
	void visit(A_formal_args e);
	void visit(A_asmt e);
	void visit(A_FunDefMain e);
	void visit(A_FunDef2 e);
	void visit(A_FunDef3 e);
	void visit(A_Call e);
	void visit(A_Men e);
	void visit(A_Mem2 e);
	void visit(A_Callclo e);
	
}
