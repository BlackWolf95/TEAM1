package ASML_Code_Generation;

public class A_Unit extends AM_Exp{

	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }
}
