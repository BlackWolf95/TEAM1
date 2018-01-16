package registers;
//import Instructions.Variable;
import java.util.*;

public class Registers {	
	
	private Integer reg_no;
	
	public Registers(Integer reg_no)
	{
		this.reg_no = reg_no;
	}
// To get the register number	
	public Integer get_regno() {
		return reg_no;
	}
//To get the name of the register	
	public String GetRegName() {
		return "r" + reg_no.toString();
	}	
//Initialize the list of variable and arguments registers	
	public static void reg_initialization(ArrayList<Registers> reglist, ArrayList<Registers> arg_reglist) {
		for(int i = 4; i<12;i++) {
			Registers register = new Registers(i);
			reglist.add(register);
		}
		
		for(int i =2; i<4;i++) {
			Registers register = new Registers(i);
			arg_reglist.add(register);
		}
	}	
//Printing list of empty registers	
    public static void printRegister(ArrayList<Registers> reglist) {
    	for (Registers reg : reglist) {
            System.out.print("r" + reg.get_regno() + " empty/n");
    }
    }
    }

