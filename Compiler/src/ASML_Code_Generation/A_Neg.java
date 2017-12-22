package ASML_Code_Generation;

public class A_Neg extends AM_Exp{
	
	public A_Ident a_ident;
	
	public A_Neg(A_Ident a_ident){
		this.a_ident=a_ident;
	}

	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }
}
