package ASML;

public class A_Bool extends AM_Exp{
	
	public boolean b;

    public A_Bool(boolean b) {
        this.b = b;
    }

    public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }
}
