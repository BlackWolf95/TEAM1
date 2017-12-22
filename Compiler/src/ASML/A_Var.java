package ASML;

import Tool.Id;

public class A_Var extends AM_Exp{
	public Id id;
 	
	public A_Var( Id id){
		this.id=id;
	}

	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }

}
