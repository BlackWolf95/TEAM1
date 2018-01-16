package Instructions;

import java.util.*;

public class Noop implements Inst_Interface{

	public Object objnoop;
	
	public Noop(Object objnoop) {
		this.objnoop = objnoop;
	}
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Noop;
	}

	@Override
	public List<Object> get_operands() {
		// TODO Auto-generated method stub
		return new ArrayList<Object>();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("NOOP:" + this.objnoop);
	}

}
