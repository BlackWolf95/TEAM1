package K_Nor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
import Types.TFloat;
import Types.TFun;
import Types.TInt;
import Types.Type;
import Visiteur.ObjVisitor;
import Visiteur.Visitor;

public class KNor implements ObjVisitor<Exp> {
	
	
	public KNor() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public Exp visit(Unit e) {
		// TODO Auto-generated method stub
		Exp unit=new Unit();
		return unit;
	} 

	@Override
	public Exp visit(Bool e) {
		// TODO Auto-generated method stub
		Exp bool= new Bool(e.b);
		return bool;
	}

	@Override
	public Exp visit(Int e) {
		// TODO Auto-generated method stub
		Exp In = new Int(e.i);
		return In;
	}

	@Override
	public Exp visit(Float e) {
		// TODO Auto-generated method stub
		Exp f = new Float(e.f);
		return f;
	}

	@Override
	public Exp visit(Not e) {
		// TODO Auto-generated method stub
		Exp not = new Not(e); 
		return not;
	}

	@Override
	public Exp visit(Neg e) {
		// TODO Auto-generated method stub
		Exp neg = new Neg(e);
		return neg;
	}

	@Override
	public Exp visit(Add e) {
		// TODO Auto-generated method stub
		Id id1 = Id.gen();
		Id id2 = Id.gen();
		Var v1 = new Var(id1);
		Var v2 = new Var(id2);
		Add add=new Add(v1, v2);
		Let l2=new Let(id2, new TInt(), e.e2.accept(this), add);
		Let l1=new Let(id1, new TInt(), e.e1.accept(this), l2);
		return l1;
	}

	@Override
	public Exp visit(Sub e) {
		// TODO Auto-generated method stub
		Id id1 = Id.gen();
 		Id id2 = Id.gen();
 		Var v1 = new Var(id1);
 		Var v2 = new Var(id2);
  		Sub sub = new Sub(v1, v2);
  		Let l2 = new Let(id2, new TInt(), e.e2.accept(this), sub);
  		Let l1 = new Let(id1, new TInt(), e.e1.accept(this), l2);
		return l1;
	}

	@Override
	public Exp visit(FNeg e) {
		// TODO Auto-generated method stub
		Exp fneg = new FNeg(e);
		return fneg;
	}

	@Override
	public Exp visit(FAdd e) {
		// TODO Auto-generated method stub
		Id id1 = Id.gen();
		Id id2 = Id.gen();
		Var v1 = new Var(id1);
		Var v2 = new Var(id2);
		FAdd fadd=new FAdd(v1, v2);
		Let l2=new Let(id2, new TInt(), e.e2.accept(this), fadd);
		Let l1=new Let(id1, new TInt(), e.e1.accept(this), l2);
		return l1;
	}

	@Override
	public Exp visit(FSub e) {
		// TODO Auto-generated method stub
		Id id1 = Id.gen();
 		Id id2 = Id.gen();
 		Var v1 = new Var(id1);
 		Var v2 = new Var(id2);
  		FSub fsub = new FSub(v1, v2);
  		Let l2 = new Let(id2, new TInt(), e.e2.accept(this), fsub);
  		Let l1 = new Let(id1, new TInt(), e.e1.accept(this), l2);
		return l1;
	}

	@Override
	public Exp visit(FMul e) {
		// TODO Auto-generated method stub
		Id id1 = Id.gen();
 		Id id2 = Id.gen();
 		Var v1 = new Var(id1);
 		Var v2 = new Var(id2);
  		FMul fmul = new FMul(v1, v2);
  		Let l2 = new Let(id2, new TInt(), e.e2.accept(this), fmul);
  		Let l1 = new Let(id1, new TInt(), e.e1.accept(this), l2);
		return l1;
	}

	@Override
	public Exp visit(FDiv e) {
		// TODO Auto-generated method stub
		Id id1 = Id.gen();
		Id id2 = Id.gen();
		Var v1 = new Var(id1);
		Var v2 = new Var(id2);
		FDiv fdiv = new FDiv(v1,v2);
		Let l2 = new Let(id2, new TFloat(), e.e2.accept(this), fdiv);
		Let l1 = new Let(id1, new TFloat(), e.e1.accept(this), l2); 
		return l1;
	}

	@Override
	public Exp visit(Eq e) {
		// TODO Auto-generated method stub
		return new Eq(e.e1.accept(this), e.e2.accept(this));
	}

	@Override
	public Exp visit(LE e) {
		// TODO Auto-generated method stub
		return new LE(e.e1.accept(this), e.e2.accept(this));
	}

	@Override
	public Exp visit(If e) {
		// TODO Auto-generated method stub
		Exp iff=new If(e.e1.accept(this),e.e2.accept(this), e.e3.accept(this));
		return iff;
	}

	@Override
	public Exp visit(Let e) {
		// TODO Auto-generated method stub
		Id id1 = e.id;
		Type type= e.t;
  		Let l2 = new Let(id1, type, e.e1.accept(this), e.e2.accept(this));
		return l2;
	}

	@Override
	public Exp visit(Var e) {
		// TODO Auto-generated method stub
		Id id = e.id;
		Var v = new Var(id);
		return v;
	}

	@Override
	public Exp visit(LetRec e) {
		// TODO Auto-generated method stub
		FunDef fd=e.fd;
		LetRec letrec=new LetRec(fd, e.e.accept(this));
		return letrec;
	}

	@Override
	public Exp visit(App e) {
		// TODO Auto-generated method stub
		Exp e1=e.e.accept(this);
		List<Exp> list=new ArrayList<Exp>();
		for(int i=0;i<list.size();i++){
			list.add(e.es.get(i));
		}
		return new App(e1, list);
	}

	@Override
	public Exp visit(Tuple e) {
		// TODO Auto-generated method stub
		List<Exp> list=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			list.add(e.es.get(i));
		}
		return new Tuple(list);
	}

	@Override
	public Exp visit(LetTuple e) {
		// TODO Auto-generated method stub
		List<Id> listId=new ArrayList<Id>();
		for(int i=0;i<listId.size();i++){
			listId.add(e.ids.get(i));
		}
		
		List<Type> listType=new ArrayList<Type>();
		for(int i=0;i<listType.size();i++){
			listType.add(e.ts.get(i));
		}
		
		return new LetTuple(listId, listType, e.e1.accept(this), e.e2.accept(this));
	}

	@Override
	public Exp visit(Array e) {
		// TODO Auto-generated method stub
		return new Array(e.e1.accept(this), e.e2.accept(this));
	}

	@Override
	public Exp visit(Get e) {
		// TODO Auto-generated method stub
		return new Get(e.e1.accept(this), e.e2.accept(this));
	}

	@Override
	public Exp visit(Put e) {
		// TODO Auto-generated method stub
		return new Put(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
	}

}
