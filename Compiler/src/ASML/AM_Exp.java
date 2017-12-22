package ASML;


public abstract class AM_Exp {
	
	public abstract void accept(AM_Visitor v);
	
	public abstract<E> E accept(AM_Objvisitor<E> v);
		
	

}
