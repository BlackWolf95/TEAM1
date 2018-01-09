package TypeChecker;

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
import Types.TBool;
import Types.TFloat;
import Types.TInt;
import Types.TUnit;
import Types.Type;
import Visiteur.ObjVisitor;

public class TypeChecking implements ObjVisitor<Type> {

	public TypeChecking(){
		
	}

	@Override
	public Type visit(Unit e) {
		// TODO Auto-generated method stub
		return new TUnit();
	}

	@Override
	public Type visit(Bool e) {
		// TODO Auto-generated method stub
		
		return new TBool() {
		};
	}

	@Override
	public Type visit(Int e) {
		// TODO Auto-generated method stub
		return new TInt();
	}

	@Override
	public Type visit(Float e) {
		// TODO Auto-generated method stub
  	    return new TFloat();
	}

	@Override
	public Type visit(Not e) {
		Type ty=e.e.accept(this);
		// TODO Auto-generated method stub
		if(ty instanceof TBool){
			return new TBool();
		}else {
			System.out.println("erreur,ce type n'est pas TBool");
			System.exit(1);
		}
		return null;
		
	}

	@Override
	public Type visit(Neg e) {
		// TODO Auto-generated method stub
		Type ty=e.e.accept(this);
		if (ty instanceof TInt){
			return new TInt();
		}else {
			System.out.println("erreur,ce type n'est pas TBool");
			System.exit(1);
		}
//		if(type instanceof )
		return null;
	}

	@Override
	public Type visit(Add e) {
		// TODO Auto-generated method stub
		Type ty1=e.e1.accept(this);
		Type ty2=e.e2.accept(this);
		if(ty1.equals(ty2)){
			return ty1;
		}else {
			System.out.println("erreur,ce n'est pas le meme type");
			System.exit(1);
		}
		return null;
	}

	@Override
	public Type visit(Sub e) {
		// TODO Auto-generated method stub
		Type ty1=e.e1.accept(this);
		Type ty2=e.e2.accept(this);
		if(ty1 instanceof TInt || ty2 instanceof TInt){
			return new TInt();
		}else {
			System.out.println("erreur,ce n'est pas le meme type");
			System.exit(1);
		}
		return null;
	}

	@Override
	public Type visit(FNeg e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(FAdd e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(FSub e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(FMul e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(FDiv e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(Eq e) {
		// TODO Auto-generated method stub
		Type ty1=e.e1.accept(this);
		Type ty2=e.e1.accept(this);
		if (ty1.equals(ty2)){
			return ty1;
		}else {
			System.out.println("erreur,ce n'est pas le meme type");
			System.exit(1);
		}
		
		return null;
	}

	@Override
	public Type visit(LE e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(If e) {
		// TODO Auto-generated method stub
		Type ty1=e.e1.accept(this);
		Type ty2=e.e2.accept(this);
		Type ty3=e.e3.accept(this);
		if(ty1 instanceof TBool){
						
		}
		return null;
	}

	@Override
	public Type visit(Let e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(Var e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(LetRec e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(App e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(Tuple e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(LetTuple e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(Array e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(Get e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(Put e) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
