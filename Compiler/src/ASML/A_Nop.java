package ASML;

public class A_Nop extends AM_Exp {

	public AM_Exp am_e;
	
	public A_Nop(AM_Exp am_e){
		this.am_e=am_e;
	}
	
	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }
}
