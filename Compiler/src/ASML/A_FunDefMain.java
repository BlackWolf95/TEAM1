package ASML;

public class A_FunDefMain extends A_FunDef{

	public A_asmt a_asmt;
	
	public A_FunDefMain(A_asmt a_asmt){
		this.a_asmt=a_asmt;
	}
	
	public <E> E accept(AM_Objvisitor<E> v) {
        return v.visit(this);
    }
    public void accept(AM_Visitor v) {
        v.visit(this);
    }
		
}
