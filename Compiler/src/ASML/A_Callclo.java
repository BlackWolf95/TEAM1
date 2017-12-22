package ASML;

import java.util.List;

import Tool.Id;

public class A_Callclo extends AM_Exp{

	public Id id;
	public List<Id> listId;
	
	public A_Callclo(Id id, List<Id> listId) {
		this.id = id;
		this.listId = listId;
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
