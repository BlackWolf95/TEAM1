package ASML_Code_Generation;

import Expression.FunDef;

public class A_LetRec extends AM_Exp{

	public Asml_Fundef a_df;
	public AM_Exp a_e;
	
	public A_LetRec(Asml_Fundef fd, AM_Exp a_e) {
		this.a_df = fd;
		this.a_e = a_e;
	}
	
	@Override
	public void accept(AM_Visitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E> E accept(AM_Objvisitor<E> v) {
		// TODO Auto-generated method stub
		return null;
	}

}
