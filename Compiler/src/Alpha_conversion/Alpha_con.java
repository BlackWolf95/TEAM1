package Alpha_conversion;

import java.util.ArrayList;
import java.util.HashMap;
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
import Types.TInt;
import Types.Type;
import Visiteur.ObjVisitor;

public class Alpha_con implements ObjVisitor<Exp> {

	HashMap<String,String> hm=new HashMap<>();
	@Override
	public Exp visit(Unit e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Bool e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Int e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Float e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Not e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Neg e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Add e) {
		// TODO Auto-generated method stub
		
		e.e1.accept(this);
		e.e2.accept(this);
		return null;
		
		
	}

	@Override
	public Exp visit(Sub e) {
		// TODO Auto-generated method stub
		return null;
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
		return new If(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
	}

	@Override
	public Exp visit(Let e) {
		// TODO Auto-generated method stub
		HashMap<String,String> hm1 = (HashMap<String, String>) hm.clone();
		Id id=e.id;
		Type type=e.t;
		e.e1.accept(this);
		
		if (hm.get(e.id.toString())!=null) {
			Id newid=id.gen();
			hm.put(id.toString(), newid.toString());
			e.id=newid;
		}
		else{
			hm.put(id.toString(),id.toString());
		}
		
		e.e2.accept(this);
		hm=hm1;
		return e;
	}

	@Override
	public Exp visit(Var e) {
		// TODO Auto-generated method stub
		HashMap<String,String> hm2 = (HashMap<String, String>) hm.clone();
		Id id=e.id;
		if(hm.get(e.id.toString())!=null){
			Id newid=id.gen();
			hm.put(id.toString(), newid.toString());
			e.id=newid;
		}else {
			hm.put(id.toString(), id.toString());
		}
		hm=hm2;
		return e;
	}

	@Override
	public Exp visit(LetRec e) {
		// TODO Auto-generated method stub
		HashMap<String,String> hm3 = (HashMap<String, String>) hm.clone();
		FunDef fd=e.fd;
		Id id=fd.id;
		Type type=fd.type;
		List<Id> listId=new ArrayList<>();
		fd.e.accept(this);
        if(hm.get(fd.args.toString())!=null){
        	List<Id> newListId=new ArrayList<>();
        	for(int i=0;i<listId.size();i++){
//        	List<Id> newListId=new ArrayList<>();
			newListId.add(Id.gen());
			hm.put(fd.args.get(i).toString(), newListId.get(i).toString());
			fd.args.add(newListId.get(i));
        	}
		}else{
           for(int i=0;i<listId.size();i++){
           hm.put(fd.args.get(i).toString(), fd.args.get(i).toString());
           }
		
		}
		e.e.accept(this);
		hm=hm3;
		return e;
	}

	@Override
	public Exp visit(App e) {
		// TODO Auto-generated method stub
		return null;
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
		HashMap<String,String> hm4 = (HashMap<String, String>) hm.clone();
		List<Id> listId=new ArrayList<Id>();
		if(hm.get(listId.toString())!=null){
			List<Id> newListId=new ArrayList<>();
        	for(int i=0;i<listId.size();i++){
			newListId.add(Id.gen());
			hm.put(listId.get(i).toString(), newListId.get(i).toString());
		}
		}else{
		    for(int i=0;i<listId.size();i++){
		    hm.put(listId.get(i).toString(), listId.get(i).toString());
		    }		
		}
		List<Type> listType=new ArrayList<Type>();
		for(int i=0;i<listType.size();i++){
			listType.add(e.ts.get(i));
		}
		hm=hm4;
		return new LetTuple(listId,listType, e.e1.accept(this), e.e2.accept(this));
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
