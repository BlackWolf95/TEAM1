package ASML;


public class A_Int extends AM_Exp{
	
	public int i;

    public A_Int(int i) {
        this.i = i;
    }

    public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }

}
