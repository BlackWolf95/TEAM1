package ASML;

import Tool.Id;

public class A_Add extends AM_Exp{

	public AM_Exp a_e1;
	public AM_Exp a_e2;
	
	public A_Add(AM_Exp a_e1, AM_Exp a_e2) {
		super();
		this.a_e1 = a_e1;
		this.a_e2 = a_e2;
	}	
	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }

}
