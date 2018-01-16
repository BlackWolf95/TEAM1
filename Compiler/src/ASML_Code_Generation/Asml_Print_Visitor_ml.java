package ASML_Code_Generation;

import java.util.Iterator;
import java.util.List;

import Expression.Add;
import Expression.App;
import Expression.Array;
import Expression.Bool;
import Expression.Eq;
import Expression.Exp;
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
import Visiteur.Visitor;

public class Asml_Print_Visitor_ml implements Visitor {

	@Override
	public void visit(Unit e) {
		// TODO Auto-generated method stub
		 System.out.print("()");
	}

	@Override
	public void visit(Bool e) {
		// TODO Auto-generated method stub
		 System.out.print(e.b);
	}

	@Override
	public void visit(Int e) {
		// TODO Auto-generated method stub
		 System.out.print(e.i);
	}

	@Override
	public void visit(Float e) {
		// TODO Auto-generated method stub
		 String s = String.format("%.2f", e.f);
	     System.out.print(s);
	}

	@Override
	public void visit(Not e) {
		// TODO Auto-generated method stub
		System.out.print("(not ");
        e.e.accept(this);
        System.out.print(")");
	}

	@Override
	public void visit(Neg e) {
		// TODO Auto-generated method stub
		System.out.print("(- ");
	    e.e.accept(this);
	    System.out.print(")");
	}

	@Override
	public void visit(Add e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     e.e1.accept(this);
	     System.out.print(" + ");
	     e.e2.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(Sub e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     e.e1.accept(this);
	     System.out.print(" - ");
	     e.e2.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(FNeg e) {
		// TODO Auto-generated method stub
		 System.out.print("(-. ");
	     e.e.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(FAdd e) {
		// TODO Auto-generated method stub
		System.out.print("(");
        e.e1.accept(this);
        System.out.print(" +. ");
        e.e2.accept(this);
        System.out.print(")");
	}

	@Override
	public void visit(FSub e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     e.e1.accept(this);
	     System.out.print(" -. ");
	     e.e2.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(FMul e) {
		// TODO Auto-generated method stub
		System.out.print("(");
        e.e1.accept(this);
        System.out.print(" *. ");
        e.e2.accept(this);
        System.out.print(")");
	}

	@Override
	public void visit(FDiv e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     e.e1.accept(this);
	     System.out.print(" /. ");
	     e.e2.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(Eq e) {
		// TODO Auto-generated method stub
		  System.out.print("(");
	      e.e1.accept(this);
	      System.out.print(" = ");
	      e.e2.accept(this);
	      System.out.print(")");
	}

	@Override
	public void visit(LE e) {
		// TODO Auto-generated method stub
		System.out.print("(");
        e.e1.accept(this);
        System.out.print(" <= ");
        e.e2.accept(this);
        System.out.print(")");
	}

	@Override
	public void visit(If e) {
		// TODO Auto-generated method stub
		System.out.print("(if ");
        e.e1.accept(this);
        System.out.print(" then ");
        e.e2.accept(this);
        System.out.print(" else ");
        e.e3.accept(this);
        System.out.print(")");
	}

	@Override
	public void visit(Let e) {
		// TODO Auto-generated method stub
		System.out.print("(let ");
        System.out.print(e.id);
        System.out.print(" = ");
        e.e1.accept(this);
        System.out.print(" in ");
        e.e2.accept(this);
        System.out.print(")");
	}

	@Override
	public void visit(Var e) {
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
    void printInfix2(List<Exp> l, String op) {
        if (l.isEmpty()) {
            return;
        }
        Iterator<Exp> it = l.iterator();
        it.next().accept(this);
        while (it.hasNext()) {
            System.out.print(op);
            it.next().accept(this);
        }
    }

	@Override
	public void visit(LetRec e) {
		// TODO Auto-generated method stub
		 System.out.print("(let rec " + e.fd.id + " ");
	     printInfix(e.fd.args, " ");
	     System.out.print(" = ");
	     e.fd.e.accept(this);
	     System.out.print(" in ");
	     e.e.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(App e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     e.e.accept(this);
	     System.out.print(" ");
	     printInfix2(e.es, " ");
	     System.out.print(")");
	}

	@Override
	public void visit(Tuple e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     printInfix2(e.es, ", ");
	     System.out.print(")");
	}

	@Override
	public void visit(LetTuple e) {
		// TODO Auto-generated method stub
		 System.out.print("(let (");
	     printInfix(e.ids, ", ");
	     System.out.print(") = ");
	     e.e1.accept(this);
	     System.out.print(" in ");
	     e.e2.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(Array e) {
		// TODO Auto-generated method stub
		 System.out.print("(Array.create ");
	     e.e1.accept(this);
	     System.out.print(" ");
	     e.e2.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(Get e) {
		// TODO Auto-generated method stub
		 e.e1.accept(this);
	     System.out.print(".(");
	     e.e2.accept(this);
	     System.out.print(")");
	}

	@Override
	public void visit(Put e) {
		// TODO Auto-generated method stub
		 System.out.print("(");
	     e.e1.accept(this);
	     System.out.print(".(");
	     e.e2.accept(this);
	     System.out.print(") <- ");
	     e.e3.accept(this);
	     System.out.print(")");
	}

}
