package registers;
import Instructions.*;
import Visiteur.ObjVisitor;
import Visiteur.Visitor;
import Expression.*;
import Tool.Id;
import Types.Type;

import java.util.*;

public class Reg_assign {
	
	//list of local variables of the function
	public static int INT_MIN = -32767; 
	//public List<FunDef> function = new ArrayList<>();
	public List<Variable> local = new ArrayList<>();
	public ArrayList<String> regname=new ArrayList<String>();
	
	int n = local.size();
	static String[] regLocal = new String[7];
	static String[] regParam = new String[4];
	

	public Reg_assign(List<Variable> local) {
		this.local = local;
		//this.function = function;
	}
	
	//we would call assignLocal() from call_Local()
	
	public void  call_Local() {
		int i;
		for(i=0;i<n;i++) {
			String s=assignLocal(i);
			regname.add(s);
		}
			
		
		
	}

	
	public static int getRegsLocal() {
		int i1=INT_MIN;
		for (int i = 4; i <= 12; i++)
		{
			if(regLocal[i]==null) {
				i1 = i;
			}
		}
		return i1; 
	}
	
	/*
	public void assignParam() {		
		//create register in the form of string arrays
		//int i=getRegsParam();
		//regParam[i]= function.get(i).toString();	
		}
	
	public int getRegsParam() {
		int i1=INT_MIN;
		for (int i = 0; i <= 3; i++)
		{
			if(regParam[i]==null) {
				i1 = i;
			}
		}
		return i1; 
	}
	*/
}

	
	








