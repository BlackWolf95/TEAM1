package ASML;

public class A_FunDef3 extends A_FunDef{

	public A_Label a_label;
	public A_formal_args a_for;
	public A_asmt a_asmt;
	public A_FunDef fundef;
	
	public A_FunDef3(A_Label a_label,A_formal_args a_for,A_asmt a_asmt,A_FunDef fundef){
		this.a_label=a_label;
		this.a_for=a_for;
		this.a_asmt=a_asmt;
		this.fundef=fundef;
	}
	
	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }
}
