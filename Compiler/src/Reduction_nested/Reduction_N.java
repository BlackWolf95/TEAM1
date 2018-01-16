package Reduction_nested;

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
import Visiteur.Visitor;

public class Reduction_N implements ObjVisitor<Exp>{

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
		Exp fneg=new FNeg(e);
		return fneg;
	}

	@Override
	public Exp visit(FAdd e) {
		// TODO Auto-generated method stub
		Exp fadd1=e.e1.accept(this);
		Exp fadd2=e.e2.accept(this);
		FAdd fAdd=new FAdd(fadd1,fadd2);
		return fAdd;
	}

	@Override
	public Exp visit(FSub e) {
		// TODO Auto-generated method stub
		Exp fsub1=e.e1.accept(this);
		Exp fsub2=e.e2.accept(this);
		FSub fSub=new FSub(fsub1, fsub2);
		return fSub;
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
//		Exp eq=new Eq(e.e1.accept(this), e.e2.accept(this));
		return null;
		
	}

	@Override
	public Exp visit(LE e) {
		// TODO Auto-generated method stub
		//Exp le=new LE(e.e1.accept(this), e.e2.accept(this));
		return null;
	}

	@Override
	public Exp visit(If e) {
		// TODO Auto-generated method stub 
		return new If(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
	}

	@Override
	public Exp visit(Let e) {
		// TODO Auto-generated method stub
		Id id=e.id;
		Type type=e.t;
		Exp exp1=e.e1;
		Exp exp2=e.e2.accept(this);
		
		if(exp1 instanceof Let){
			Id id1=((Let) exp1).id;
			Type type2=((Let) exp1).t;
			Exp exp3=((Let) exp1).e1;
			Exp exp4=((Let) exp1).e2;
			Let l1=new Let(id, type, exp4, exp2);
			Let l2=new Let(id1, type2, exp3, l1);
			return l2.accept(this);
			//return l2;
		}else {
			Let l2=new Let(id, type, e.e1.accept(this), e.e2.accept(this));
			return l2;
		}
		
	}

	@Override
	public Exp visit(Var e) {
		// TODO Auto-generated method stub
		return e;
	}

	@Override
	public Exp visit(LetRec e) {
		// TODO Auto-generated method stub
		
		return e;
	}

	@Override
	public Exp visit(App e) {
		// TODO Auto-generated method stub
		Exp e1=e.e.accept(this);
		List<Exp> list=new ArrayList<Exp>();
		for(int i=0;i<e.es.size();i++){
			list.add(e.es.get(i));
		}
		return new App(e1, list);
	}

	@Override
	public Exp visit(Tuple e) {
		// TODO Auto-generated method stub
		List<Exp> list=new ArrayList<>();
		for(int i=0;i<e.es.size();i++){
			list.add(e.es.get(i));
		}
		return new Tuple(list);
	}

	@Override
	public Exp visit(LetTuple e) {
		// TODO Auto-generated method stub
		List<Id> listId=new ArrayList<Id>();
		for(int i=0;i<e.ids.size();i++){
			listId.add(e.ids.get(i));
		}
		
		List<Type> listType=new ArrayList<Type>();
		for(int i=0;i<listType.size();i++){
			listType.add(e.ts.get(i));
		}
		Exp E1=e.e1.accept(this);
		if(E1 instanceof Let){
			Let letExp=(Let)E1;
			Id id=letExp.id;
			Type t=letExp.t;
			Exp e1_let=letExp.e1.accept(this);
			Exp e2_let=letExp.e2.accept(this);
			Exp E2=e.e2.accept(this);
			return new Let(id, t, e1_let, new LetTuple(listId, listType, e2_let, E2).accept(this));
		}else if(E1 instanceof LetTuple){
			LetTuple letTuExp=(LetTuple) E1;
			List<Id> listId1=new ArrayList<Id>();
			List<Type> listType2=new ArrayList<Type>();
			Exp e1_letTu=letTuExp.e1.accept(this);
			Exp e2_letTu=letTuExp.e2.accept(this);
			Exp E2=e.e2.accept(this);
			return new LetTuple(listId1, listType2, e1_letTu, new LetTuple(listId, listType, e2_letTu, E2).accept(this));
		}else if(E1 instanceof LetRec){
			LetRec letRecExp=(LetRec) E1;
			FunDef fd=letRecExp.fd;
			Exp e_letrec=letRecExp.e;
			Exp E2=e.e2.accept(this);
			return new LetRec(fd, new LetTuple(listId, listType, e_letrec, E2).accept(this));
		}else{
			return new LetTuple(listId, e.ts, E1, e.e2.accept(this));
		}
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
