package ASML;

import Tool.Id;
import Types.Type;

public class A_Let extends AM_Exp {
	
	public Id a_id;
	public Type type; 
    public AM_Exp e1;
    public AM_Exp e2;
    
    public A_Let(Id a_id, Type type, AM_Exp e1, AM_Exp e2) {
		this.a_id = a_id;
		this.type = type;
		this.e1 = e1;
		this.e2 = e2;
	}
    
    public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }

}
