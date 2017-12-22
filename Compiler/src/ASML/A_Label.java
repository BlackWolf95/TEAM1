package ASML;

public class A_Label extends AM_Exp{
	
	String a_label;
	
	public A_Label(){
		
	}
	
	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }

}
