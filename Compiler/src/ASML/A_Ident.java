package ASML;


public class A_Ident extends AM_Exp{
	
	public String A_id;
	
	public A_Ident(String A_id){
		this.A_id=A_id;
	}
	
	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }
}
