package ASML;

public class A_If extends AM_Exp{

//	public A_Ident a_id1;
//	public A_Ident a_id2;
	public AM_Exp am_e1;
	public AM_Exp am_e2;
	public AM_Exp am_e3;
	
	public A_If(AM_Exp am_e1, AM_Exp am_e2, AM_Exp am_e3) {
		super();
		this.am_e1 = am_e1;
		this.am_e2 = am_e2;
		this.am_e3 = am_e3;
	}
	
	 public <E> E accept(AM_Objvisitor<E> v) {
	        return v.visit(this);
	    }
	 public void accept(AM_Visitor v) {
	        v.visit(this);
	    }
}
