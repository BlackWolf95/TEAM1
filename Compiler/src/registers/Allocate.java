package registers;

import java.util.*;

import ASML.*;
import Expression.*;

public class Allocate {	
	
	public List<Var> locals = new ArrayList<>();	
	public Allocate(List<Var> locals)
	{
		this.locals = locals;
	}
	
	
	
	public String[] GetRegister()
	{
		//locals = Asml.getVariables();
		Reg_assign assign = new Reg_assign(locals);
		return assign.regLocal;
	}
	
	
	
}
