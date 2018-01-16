package registers;
import java.util.*;
import ARMGen.fun;
//import Instructions.Arguments;
import Instructions.Variable;
public class Alloc {
	//public static ArrayList<Registers> reg = new ArrayList<Registers>(9);
	//public static ArrayList<Registers> arg_reg = new ArrayList<Registers>(4);


	public static void allocation(fun fun) {
	        for (Variable var : fun.get_local()) {

	                        if (var.get_register() == null) {
	                                var.assign_register();
	                        }
	                

	        }
/*        
	        for(Variable arg : fun.get_arguments()) {
	        	String varname = arg.var_name;
	        	System.out.println("\n" + "hello" + varname + "\n");
	        	try {
                    if (arg.get_argregister()== null) {
                            ((Arguments)arg).assign_argregister();
                    }
            }
            catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
            }
	        }
*/        
	}

}
