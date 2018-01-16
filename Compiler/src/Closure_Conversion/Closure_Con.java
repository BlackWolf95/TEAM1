package Closure_Conversion;

import java.util.ArrayList;
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
import Expression.FunDef;
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
import Tool.Id;
import Types.Type;
import Visiteur.ObjVisitor;

public class Closure_Con implements ObjVisitor<Exp>{

	@Override
	public Exp visit(Unit e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(Bool e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(Int e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(Float e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(Not e) {
		// TODO Auto-generated method stub
		 Exp not=e.e.accept(this);	
		 return not;
	}

	@Override
	public Exp visit(Neg e) {
		// TODO Auto-generated method stub
		Exp neg=e.e.accept(this);
		return neg;
	}

	@Override
	public Exp visit(Add e) {
		// TODO Auto-generated method stub
		Exp add1=e.e1.accept(this);
		Exp add2=e.e2.accept(this);
		Add add=new Add(add1,add2);
		return add;
	}

	@Override
	public Exp visit(Sub e) {
		// TODO Auto-generated method stub
		Exp sub1=e.e1.accept(this);
		Exp sub2=e.e2.accept(this);
		Sub sub=new Sub(sub1, sub2);
		return sub;
	}

	@Override
	public Exp visit(FNeg e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FAdd e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FSub e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FMul e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FDiv e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Eq e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(LE e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(If e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Let e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(Var e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(LetRec e) {
		// TODO Auto-generated method stub
		String label=e.fd.id.toString();
//		//FunDef funDef=e.fd;
//		Id id=e.fd.id;
		Type type=e.fd.type;
		List<Id> listId=e.fd.args;
		List<Var> listVar=new ArrayList<Var>();
        for(int i=0;i<listId.size();i++){
//           if(listId.get(i).) {       	  
           listVar.add(new Var(listId.get(i)));
//           }
          }
        System.out.println("label: "+label);
  		for(int i=0;i<listVar.size();i++){
  		System.out.println("parameters:" +listVar.get(i));
  		}
  		System.out.println("code:");
  		LetRec letrec=new LetRec(e.fd, e.e.accept(this));
  		return letrec;
	}

	@Override
	public Exp visit(App e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(Tuple e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(LetTuple e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(Array e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Get e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Put e) {
		// TODO Auto-generated method stub
		return null;
	}

}
