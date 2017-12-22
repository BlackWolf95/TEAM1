package ASML_Code_Generation;

import java.util.List;

import Tool.Id;

public class A_formal_args extends AM_Exp{

	public List<Id> id;
	//public A_Unit a_unit;
	
	public A_formal_args(List<Id> id){
		this.id = id;
		//this.a_unit = a_unit;
	}
	
	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }
}
