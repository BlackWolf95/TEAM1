package registers;
import Instructions.*;

import java.util.*;

import ASML.*;
import Expression.*;

public class Allocate {	
	
	public List<Variable> locals = new ArrayList<>();	
	public Allocate(List<Variable> locals)
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
