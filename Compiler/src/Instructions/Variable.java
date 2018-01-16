package Instructions;
import java.util.*;
import ARMGen.fun;
import registers.*;

public class Variable {

	public String var_name;
	public Integer offset;
	public Integer arg_offset;
	public fun fun;
	public Registers reg;
	public Registers arg_reg;
	public HashMap<Registers,Variable> regmap;
	
//Constructor the initialize the variable with a name and a function	
	public Variable(String var_name, fun fun) {
		this.var_name = var_name;
		this.fun = fun;
	}      
//Assign register to local variables
	public void assign_register() {    
        if (fun.loc_reg.isEmpty()){                       //if the list of local registers for the function is empty
                stack_spill();                            //spill the variables to stack
        } else {
                Registers reg = fun.loc_reg.get(0);       //if not empty then get a register from the list
                fun.loc_reg.remove(0);                    //Remove it from the list    
                this.set_register(reg);                   //add the variable to this register
        }
	}
//Assign registers to arguments
	public void assign_argregister() {
        if (fun.arg_reg.isEmpty()) {       				  //if the list of argument registers for the function is empty 
                stack_spill_arg();                        //spill the arguments to stack
        } else {
                Registers reg = fun.arg_reg.get(0);       //if not empty then get a register from the list
                fun.arg_reg.remove(0);					  //Remove it from the list
                this.set_arg_register(reg);               //add the argument to the register
        }
	}
//Get the variable name
	public String get_name() {
		return var_name;
	}
//Get the register for local variable
	public Registers get_register() {
		return reg;
	}
//Get the argument register	
	public Registers get_argregister() {
		return arg_reg;
	}
//Get the offset for variable	
	public Integer get_offset() {
		return offset;
	}
//Get the offset for the argument	
	public Integer get_argoffset() {
		return arg_offset;
	}
//Set the register for the variable	
	public void set_register(Registers reg1) {
		reg = reg1;
	}
//Set the register for the argument	
	public void set_arg_register(Registers reg1) {
		arg_reg = reg1;
	}
//Set the given offset if it is a multiple of 4	
	public void set_offset(Integer off) {
		if(off % 4 == 0) {
			offset = off;
		}
		else {
			System.out.println("Incorrect offset : " + off);
		}
	}
//Set the offset for argument if it is a multiple of 4	
	public void set_argoffset(Integer off) {
		if(off % 4 == 0) {
			arg_offset = off;
		}
		else {
			System.out.println("Incorrect offset : " + off);
		}
	}
//Spill the variables to stack	
	public void stack_spill() {
		Integer off = this.fun.get_offset();   			//Get the existing offset for the function
		this.fun.set_offset(off + 4);          			//Increment the offset for the function
		this.set_offset(off);                  			//Set the offset for the variable
	}
//Spill the arguments to stack	
	public void stack_spill_arg() {
		  Integer off = this.fun.get_argoffset(); 		//Get the existing offset for the function
	      this.fun.set_argoffset(off + 4);				//Increment the offset for the function
	      this.set_argoffset(off);						//Set the offset for the argument
	}
//Remove the variable register from the list of registers available for the function	
	public void remove() {
		fun.loc_reg.add(this.reg);
	}
//Remove the argument register from the list of registers available for the function	
	public void remove_arg() {
		fun.arg_reg.add(this.arg_reg);
	}
//Print the status of the variables and arguments	
	public void get_status() {               
		if(this.get_register() != null) {                //Variable in register
			System.out.println("Variable " + this.get_name() + " is in register " + this.get_register().GetRegName());
		} else if (this.get_offset() != null) {          //Variable has offset and stored on stack
			System.out.println("Variable " + this.get_name() + " is in memory at [fp + " + this.get_offset() + "]");
		} else {                                         //Variable not stored
			System.out.println("Variable " + this.get_name() + " not stored");
		}
		
		if (this.get_argregister() != null) {            //Argument in register
            System.out.println("Argument " + this.get_name() + " is in register " + this.get_argregister().GetRegName());
		} else if (this.get_argoffset() != null) {       //Argument has offset and stored on stack
              System.out.println("Argument " + this.get_name() + " is in memory at [fp + " + this.get_argoffset() + "]");
		} else {                                         //Argument not stored
              System.out.println("Argument " + this.get_name() + " not stored");
		}
		
	}
}
