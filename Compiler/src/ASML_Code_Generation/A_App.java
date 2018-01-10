package ASML_Code_Generation;

import java.util.List;

import Expression.Exp;

public class A_App extends AM_Exp {

	 public  AM_Exp e;
	 public  List<AM_Exp> es;

	 public A_App(AM_Exp e, List<AM_Exp> es) {
	        this.e = e;
	        this.es = es;
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
