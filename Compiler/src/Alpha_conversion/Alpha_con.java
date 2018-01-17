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
		
//		e.e1.accept(this);
//		e.e2.accept(this);
		return new Add(e.e1.accept(this),e.e2.accept(this));
		
		
	}

	@Override
	public Exp visit(Sub e) {
		// TODO Auto-generated method stub
		return new Sub(e.e1.accept(this),e.e2.accept(this));
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
		return e;
	}

	@Override
	public Exp visit(LE e) {
		// TODO Auto-generated method stub
		return e;
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
			Id newid=Id.gen();
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
		//HashMap<String,String> hm2 = (HashMap<String, String>) hm.clone();
//		Id id=e.id;
//		Var ret;
//		System.out.println(e.id +"" +hm);
//		if(hm.get(e.id.toString())!=null){
//			System.out.println(hm.get(e.id.toString()));
////			Id newid=id.gen();
////			hm.put(id.toString(), newid.toString());
//			return new Var(new Id(hm.get(e.id.toString())));
//			//e.id.id=hm.get(e.id.toString());
//		}else {
//			System.out.println("cc");
//			//hm.put(id.toString(), id.toString());
//			ret=e;
//		}
//		//hm=hm2;
//		return ret;
		if(hm.get(e.id.toString())!=null){
			String x=hm.get(e.id.toString());
			e=new Var(new Id(x));
		}
		//System.out.println(e.id);
		return e;
	}

	@Override
	public Exp visit(LetRec e) {
		// TODO Auto-generated method stub
//		HashMap<String,String> hm3 = (HashMap<String, String>) hm.clone();
		HashMap<String,String> hm5 = (HashMap<String, String>) hm.clone();
		FunDef fd=e.fd;
		Id id_fd=fd.id;
		Type type_fd=fd.type;
//		List<Id> listId=new ArrayList<>();
//		Exp e_fd=fd.e.accept(this);
//        if(hm.get(fd.args.toString())!=null){
//        	List<Id> newListId=new ArrayList<>();
//        	for(int i=0;i<e.fd.args.size();i++){
////        	List<Id> newListId=new ArrayList<>();
//			newListId.add(Id.gen());
//			hm.put(fd.args.get(i).toString(), newListId.get(i).toString());
//			fd.args.add(newListId.get(i));
//        	}
//		}else{
//           for(int i=0;i<e.fd.args.size();i++){
//           hm.put(fd.args.get(i).toString(), fd.args.get(i).toString());
//           }
//		
//		}
		if (hm.get(id_fd.toString())!=null) {
			Id newid=Id.gen();
			hm.put(id_fd.toString(), newid.toString());
			id_fd=newid;
		}
		else{
			hm.put(id_fd.toString(),id_fd.toString());
		}
		Exp e_fd=fd.e.accept(this);
		hm=hm5;
		   
		   HashMap<String,String> hm3 = (HashMap<String, String>) hm.clone();
	       List<Id> newListId=new ArrayList<>();
	       for(int i=0;i<e.fd.args.size();i++){
	       if(hm.get(fd.args.toString())!=null){
		   newListId.add(Id.gen());
		   hm.put(fd.args.get(i).toString(), newListId.get(i).toString());
		   fd.args.add(newListId.get(i));
	       }else{
	 	    hm.put(fd.args.get(i).toString(), fd.args.get(i).toString());       
		   }
		   }
		Exp e_letRec=e.e.accept(this);
		hm=hm3;
		FunDef funDef=new FunDef(id_fd, type_fd, fd.args, e_fd);
		LetRec letRec=new LetRec(funDef, e_letRec);
		return letRec;
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
		HashMap<String,String> hm4 = (HashMap<String, String>) hm.clone();
//		List<Id> listId=new ArrayList<Id>();
		List<Id> newListId=new ArrayList<>();
		//System.out.println(e.ids.toString());
		
//			List<Id> newListId=new ArrayList<>();
        	for(int i=0;i<e.ids.size();i++){
        	//	System.out.println(e.ids.get(i));
        	if(hm.get(e.ids.get(i).toString())!=null){
			newListId.add(Id.gen());
			hm.put(e.ids.get(i).toString(), newListId.get(i).toString());
		    }else{
		    newListId.add(e.ids.get(i));
		   // hm.put(listId.get(i).toString(), listId.get(i).toString());
		    hm.put(e.ids.get(i).toString(), e.ids.get(i).toString());
		    
		}
//		List<Type> listType=new ArrayList<Type>();
//		for(int i=0;i<listType.size();i++){
//			listType.add(e.ts.get(i));
//		}
        	}
		Exp e1_letTuple = e.e1.accept(this);
		Exp e2_letTuple=e.e2.accept(this);
		hm=hm4;
		return new LetTuple(newListId,e.ts, e1_letTuple,e2_letTuple);
		//return e;
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
