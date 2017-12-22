package ASML;

import Tool.Id;

public class A_Mem2 extends AM_Exp{

	public Id id1;
	public AM_Exp a_e;
	public Id id2;
	
	public A_Mem2(Id id1, AM_Exp a_e, Id id2) {
		super();
		this.id1 = id1;
		this.a_e = a_e;
		this.id2 = id2;
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
