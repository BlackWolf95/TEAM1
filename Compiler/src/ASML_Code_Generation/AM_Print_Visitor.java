package ASML_Code_Generation;

import java.util.Iterator;
import java.util.List;

import Expression.Exp;
import Visiteur.Visitor;

public class AM_Print_Visitor implements AM_Visitor{

	@Override
	public void visit(A_Unit e) {
		// TODO Auto-generated method stub
		 System.out.print("()");
		
	}

	@Override
	public void visit(A_Bool e) {
		// TODO Auto-generated method stub
		// System.out.print(e.b);
	}

	@Override
	public void visit(A_Int e) {
		// TODO Auto-generated method stub
		System.out.print(e.i);
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
		 System.out.print("(- ");
	     e.a_ident.accept(this);
	     System.out.print(")");
		
	}

	@Override
	public void visit(A_Add e) {
		// TODO Auto-generated method stub
		    System.out.print("(");
	        e.a_e1.accept(this);
	        System.out.print(" + ");
	        e.a_e2.accept(this);
	        System.out.print(")");
		
	}

	@Override
	public void visit(A_Sub e) {
		// TODO Auto-generated method stub
		System.out.print("(");
        e.a_e1.accept(this);
        System.out.print(" - ");
        e.a_e2.accept(this);
        System.out.print(")");
		
	}

	@Override
	public void visit(A_FNeg e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_FAdd e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_FSub e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_FMul e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_FDiv e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Eq e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
		 e.a_e1.accept(this);
	     System.out.print(" = ");
	     e.a_e2.accept(this);
	     System.out.print(")");
		
	}

	@Override
	public void visit(A_LE e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     e.a_e1.accept(this);
	     System.out.print(" <= ");
	     e.a_e2.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(A_If e) {
		// TODO Auto-generated method stub
		 System.out.print("(if ");
	     e.am_e1.accept(this);
	     System.out.print(" then ");
	     e.am_e2.accept(this);
	     System.out.print(" else ");
	     e.am_e3.accept(this);
	     System.out.print(")");
		
	}

	@Override
	public void visit(A_Let e) {
		// TODO Auto-generated method stub
		System.out.print("(let ");
        System.out.print(e.a_id);
        System.out.print(" = ");
        e.e1.accept(this);
        System.out.print(" in ");
        e.e2.accept(this);
        System.out.print(")");
	}

	@Override
	public void visit(A_Var e) {
		// TODO Auto-generated method stub
		System.out.print(e.id);
	}

	
	
	// print sequence of identifiers 
    static <E> void printInfix(List<E> l, String op) {
        if (l.isEmpty()) {
            return;
        }
        Iterator<E> it = l.iterator();
        System.out.print(it.next());
        while (it.hasNext()) {
            System.out.print(op + it.next());
        }
    }

    // print sequence of Exp
    void printInfix2(List<AM_Exp> l, String op) {
        if (l.isEmpty()) {
            return;
        }
        Iterator<AM_Exp> it = l.iterator();
        it.next().accept(this);
        while (it.hasNext()) {
            System.out.print(op);
            it.next().accept(this);
        }
    }
    
    
	@Override
	public void visit(A_LetRec e) {
		// TODO Auto-generated method stub
		 System.out.print("(let rec " + e.a_df.id + " ");
	     printInfix(e.a_df.args, " ");
	     System.out.print(" = ");
	     e.a_df.e.accept(this);
	     System.out.print(" in ");
	     e.a_e.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(A_App e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     e.e.accept(this);
	     System.out.print(" ");
	     printInfix2(e.es, " ");
	     System.out.print(")");
		
	}

	@Override
	public void visit(A_Tuple e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_LetTuple e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Array e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Get e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Put e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(A_Ident e) {
		// TODO Auto-generated method stub
		System.out.print(e.A_id);
	}

	@Override
	public void visit(A_Label e) {
		// TODO Auto-generated method stub
		System.out.print(e.a_label);
	}

	@Override
	public void visit(A_formal_args e) {
		// TODO Auto-generated method stub
		    System.out.print("(");
	        printInfix(e.id, ", ");
	        System.out.print(")");
		
	}

	@Override
	public void visit(A_asmt e) {
		// TODO Auto-generated method stub
		System.out.println(e);
	}

	@Override
	public void visit(A_FunDefMain e) {
		// TODO Auto-generated method stub
		System.out.print("(let ");
		System.out.println("=");
	    e.a_asmt.accept(this); 
	    System.out.print(")");
	}

	@Override
	public void visit(A_FunDef2 e) {
		// TODO Auto-generated method stub
		System.out.print("(let ");
		e.a_label.accept(this);
		System.out.println("=");
		System.out.println(e.a_float);
		System.out.println(e.fundef);
		System.out.print(")");
		
	}

	@Override
	public void visit(A_FunDef3 e) {
		// TODO Auto-generated method stub
		System.out.print("(let ");
		e.a_label.accept(this);
		e.a_for.accept(this);
		System.out.println("=");
		e.a_asmt.accept(this);
		e.fundef.accept(this);
		System.out.print(")");
	}

	@Override
	public void visit(A_Call e) {
		// TODO Auto-generated method stub
		System.out.print("(Call ");
		System.out.println(e.id);
		printInfix(e.listId, ", ");
        System.out.print(")");
		
		
	}

	@Override
	public void visit(A_Men e) {
		// TODO Auto-generated method stub
		System.out.print("(MEM ");
		System.out.println(e.id);
		System.out.println("+");
		e.a_e.accept(this);
		System.out.println(")");
	}

	@Override
	public void visit(A_Mem2 e) {
		// TODO Auto-generated method stub
		System.out.print("(MEM ");
		System.out.println(e.id1);
		System.out.println("+");
		e.a_e.accept(this);
		System.out.println("=");
		System.out.println(e.id2);
		System.out.println(")");
		
	}

	@Override
	public void visit(A_Callclo e) {
		// TODO Auto-generated method stub
		System.out.print("(Call ");
		System.out.println(e.id);
		printInfix(e.listId, ", ");
        System.out.print(")");
	}

}
