package ASML_Code_Generation;

import Expression.Exp;

public class A_Eq extends AM_Exp{

	public AM_Exp a_e1;
    public AM_Exp a_e2;

    public A_Eq(AM_Exp a_e1, AM_Exp a_e2) {
        this.a_e1 = a_e1;
        this.a_e2 = a_e2;
    }

	@Override
	public void accept(AM_Visitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E> E accept(AM_Objvisitor<E> v) {
		// TODO Auto-generated method stub
		return null;
	}

}
