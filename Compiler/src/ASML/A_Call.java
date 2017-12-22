package ASML;

import java.util.List;

import Tool.Id;

public class A_Call extends AM_Exp {
	public Id id;
	public A_Call(Id id, List<Id> listId) {
		this.id = id;
		this.listId = listId;
	}
	public List<Id> listId;
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
