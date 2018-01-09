package ASML_Code_Generation;

import Tool.Id;

public class A_Men extends AM_Exp{

	public Id id;
	public AM_Exp a_e;
	
	public A_Men(Id id, AM_Exp a_e) {
		super();
		this.id = id;
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
