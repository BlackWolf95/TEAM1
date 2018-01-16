package ARMGen;
import java.util.*;
import registers.Registers;
import Instructions.*;
import java.util.List;

public class fun {

	public String name;
	public List<Variable> arg;
	public HashSet<Variable> local = new HashSet<Variable>();
	public ArrayList<Registers> loc_reg;  
	public ArrayList<Registers> arg_reg;  
	public List<Inst_Interface> instruc;
	public Integer offset = 4;
	public Integer arg_offset = 4;
//Constructor with function name,arguments,instructions,registers	
	public fun(String name, List<Variable> arg, List<Inst_Interface> instruc, ArrayList<Registers> loc_reg, ArrayList<Registers> arg_reg){
		
		this.name = name;
		this.arg = arg;
		this.instruc = instruc;
		this.loc_reg = loc_reg;
		this.arg_reg = arg_reg;
		
	}
//Adding instruction to the list		
	public void add_instructionlist(Inst_Interface instruction) {
		instruc.add(instruction);
	}
//Get the list of instructions
	public List<Inst_Interface> get_instruction(){
		return instruc;
	}
//Get the function name	
	public String get_name() {
		return name;
	}
//Get the variable offset for the function	
	public Integer get_offset() {
		return offset;
	}
//Get the argument offset for the function	
	public Integer get_argoffset(){
		return arg_offset;
	}
//Get the set of variables	
	public HashSet<Variable> get_local(){
		return local;
	}
//Get the list of arguments	
	public List<Variable> get_arguments(){
		return arg;
	}
//Get the list iterator for functions	
	public ListIterator<Inst_Interface> iterator(){
		
		return instruc.listIterator();
	}
//Set the offset for the variables	
	public void set_offset(Integer off) {
		offset = off;
	}
//Set the offset for arguments	
	public void set_argoffset(Integer off) {
		arg_offset = off;
	}
//Set the local variables	
	public void set_local (HashSet<Variable> local_var) {
		local = local_var;
	}
//Print the instructions	
	public void print() {
		for( Inst_Interface i : instruc) {
			i.print();
		}
	}
//Print the status of variables	
	public void print_variable_status() {
		for( Variable var : local) {
			var.get_status();
		}
	}
//Print the local variables	
	public void print_local() {
		for(Variable loc : local) {
			System.out.println(loc.get_name());
		}
	}
}
