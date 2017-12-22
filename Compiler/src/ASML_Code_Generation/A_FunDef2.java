package ASML_Code_Generation;

public class A_FunDef2 extends A_FunDef {
	
	public A_Label a_label;
	public A_Float a_float;
	public A_FunDef fundef;
	
	public A_FunDef2(A_Label a_label,A_Float a_float,A_FunDef fundef){
		this.a_label=a_label;
		this.a_float=a_float;
		this.fundef=fundef;
	}
	
	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }

}
