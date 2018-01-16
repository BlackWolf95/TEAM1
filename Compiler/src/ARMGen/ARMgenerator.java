package ARMGen;
import Instructions.*;
import registers.*;
import java.io.PrintStream;
import java.util.*;

public class ARMgenerator {
	public StringBuffer dataBuffer = new StringBuffer();
    public StringBuffer textBuffer = new StringBuffer();
    public int counter = 1;
    public int no_reg = 9;
    public int no_arg_reg = 2;
    
    public ARMgenerator() {
    	dataBuffer.append("\t.data\n");
    	//text section
    	textBuffer.append("\t.text\n");
    	textBuffer.append("\t.global _start\n");
    	textBuffer.append("_start:\n");
    	textBuffer.append("\tBL _main\n");
    }
    
    public void armgen(List<fun> func) {
    	 for (fun fd : func) {
    		 //List<Arguments> arguments = fd.get_arguments();      //fetch arguments of function
	         List<Inst_Interface> instruction = fd.get_instruction();   //fetch all instructions of function
	         HashSet<Variable> local = fd.get_local();           //fetch local variables of function
             int no_var= local.size();                  //size of local variables
             String fun_name = fd.get_name();               //name of function
             // generate the function label
             gen_label(fun_name);
             //print prologue    
             prologue(fun_name);
             //push local variables    
             push_local(no_var);
             
    	     for (Inst_Interface instr : instruction){

                  if(instr instanceof Integer_Add){
                     gen_addcode((Integer_Add) instr);
                  }
                  else if (instr instanceof Integer_Sub) {
                     gen_subcode((Integer_Sub) instr);
                  }
                   else if (instr instanceof Integer_Mul) {
                     gen_mulcode((Integer_Mul) instr);
                  }

                  else if (instr instanceof Assign){
                      gen_assigncode((Assign) instr);
                  }
                  else if (instr instanceof Call){
                      gen_call((Call) instr);
                  }
                  else if(instr instanceof If_Inst) {
                	  String ret_val = gen_ifcode((If_Inst)instr);
                	  gen_blabel(ret_val);
                  }
                  else if(instr instanceof Noop) {
                	  
                  }
                  else{
                       System.out.println("Instruction is not supported\n");
                  }
               }

               //pop the local variables;
               pop_local(no_var);
               //function_epilogue();
               epilogue(fun_name);
    	 }
    }
    

    public void gen_label(String fun_name) {
    	textBuffer.append("_").append(fun_name).append(":\n");
    }	
    
    public void gen_blabel(String fun_name) {                              //for branch
    	textBuffer.append(fun_name).append(":\n");
    }
    
    public String gen_tlabel() {
    	counter++;
    	return "cont" + String.valueOf(counter);   
    }
  
    public void push_local(int no_var) {
    	if(no_var > no_reg) {                                   //if number of var more than register 
    		textBuffer.append("\tSUB sp, #").append((no_var-no_reg)*4).append("\n"); //add to stack
    	}
    }	

    public void push_loc_reg() {
    	textBuffer.append("\tSTMFD sp!,{r4-r12}\n");
    }
    
    public void push_args(int no_args) {
    	 if(no_args > no_arg_reg){
    	       textBuffer.append("\tSUB sp, #").append((no_args-no_arg_reg)*4).append("\n");
    	    }
    }
    public String get_label(String fun_name) {
    	return "_"+fun_name;
    }


    public void prologue(String fun_name) {
    	if(fun_name.equals("main")) {
    		  textBuffer.append("\t@MAIN PROLOGUE\n");
    		  textBuffer.append("\tSUB sp, #4\n");
    		  textBuffer.append("\tLDR lr, [sp]\n");
    		  textBuffer.append("\tSUB sp, #4\n");
    		  textBuffer.append("\tSTR fp, [sp]\n");
    		  textBuffer.append("\tMOV fp, sp\n\n");
    	}	
    	
    	
    	else {
    		  textBuffer.append("\t@FUNCTION PROLOGUE\n");
    		  textBuffer.append("\tSTMFD sp!, {fp, lr}\n");
    	      textBuffer.append("\tADD fp, sp, #4\n\n");
    	}
    		
    }	
    
    public void epilogue(String fun_name) {
    	if(fun_name.equals("main")) {
    		textBuffer.append("\n\t@MAIN EPILOGUE\n");
    		textBuffer.append("\tADD sp, #4\n");
    		textBuffer.append("\tMOV sp, fp\n");
    		textBuffer.append("\tLDR fp, [sp]\n");
    		textBuffer.append("\tADD sp, #4\n\n");
    		// output terminal (not sure)
    		textBuffer.append("\tMOV r7, #1\n");
    		textBuffer.append("\tswi 0\n");

  	}	
  	
  	
  	else {
  			textBuffer.append("\n\t@FUNCTION EPILOGUE\n");
  			textBuffer.append("\tSUB sp, fp, #4\n");
  			textBuffer.append("\tLDMFD sp!, {fp, lr}\n");
  			textBuffer.append("\tBX lr\n\n");
  	}
  		
   }	
   
   public void pop_local(int no_var) {
	   if(no_var > no_reg) {                                   //if number of var more than register 
   		textBuffer.append("\tADD sp, #").append((no_var-no_reg)*4).append("\n"); //remove from stack
   	}
   }
   
   public void pop_loc_reg() {
	   textBuffer.append("\tLDMFD sp!, {r4-r12}\n");
   }
   public void gen_addcode(Integer_Add instruction) {
	   Object operand1 = instruction.operandlist.get(0);
	   Object operand2 = instruction.operandlist.get(1);
	   String dest = "r0"; 
	   String place1="", place2="";
	   
	   if(operand1 instanceof Integer_Op) {
		   //String vn = ((Integer_Op) operand1).var_name;
		   //Registers reg_tmp = ((Integer_Op)operand1).get_register();
		   if(((Integer_Op)operand1).get_register()!= null) {
			   place1 = ((Integer_Op)operand1).get_register().GetRegName();
		   }
		   else {
			   place1="[fp, #" + ((Integer_Op)operand1).get_offset().toString() +"]";
               textBuffer.append("\tLDR r0 , "). append(place1).append("\n");
               place1="r0";
		   }		   
	   }
	   else if(operand1 instanceof Arguments) {
		   if(((Arguments)operand1).get_register()!=null){
               place1=((Arguments)operand1).get_register().GetRegName();
             }
             else{
               place1="[fp, #" + ((Arguments)operand1).get_offset().toString() +"]";
               textBuffer.append("\tLDR r0 , "). append(place1).append("\n");
               place1="r0";
             }		   
	   }
	   if(operand2 instanceof Integer_Op){
           if(((Integer_Op)operand2).get_register()!=null){
             place2=((Integer_Op)operand2).get_register().GetRegName();
           }
           else{
             place2="[fp ,#" + ((Integer_Op)operand2).get_offset().toString() +"]";
             textBuffer.append("\tLDR r1 , "). append(place2).append("\n");
             place2="r1";

           }
     }

     else if(operand2 instanceof Arguments){
       if(((Arguments)operand2).get_register()!=null){
         place2=((Arguments)operand2).get_register().GetRegName();
       }
       else{

         place2="[fp, #" + ((Arguments)operand1).get_offset().toString()+"]";  //not sure,check for other operation as well
         textBuffer.append("\tLDR r0 , "). append(place2).append("\n");
         place2="r0";
       }
     }
	   
	 if(operand1 instanceof Integer && operand2 instanceof Variable){

           arith_op("ADD",dest ,(int)operand1, place2);
     }

     else if(operand1 instanceof Variable && operand2 instanceof Variable){
           arith_op("ADD",dest ,place1, place2);
     }
     else if(operand1 instanceof Integer && operand2 instanceof Integer){

         arith_op("ADD",dest ,(int)operand1, (int)operand2);
     }
     else if(operand1 instanceof Variable && operand2 instanceof Integer){

       arith_op("ADD",dest ,place1, (int)operand2);

     }  
   }
   
   public void gen_subcode(Integer_Sub instruction) {
	   Object operand1 = instruction.operandlist.get(0);
	   Object operand2 = instruction.operandlist.get(1);
	   String dest = "r0"; 
	   String place1="", place2="";
	   
	   if(operand1 instanceof Integer_Op) {
		   if(((Integer_Op)operand1).get_register()!= null) {
			   place1 = ((Integer_Op)operand1).get_register().GetRegName();
		   }
		   else {
			   place1="[fp, #" + ((Integer_Op)operand1).get_offset().toString()  +"]";
               textBuffer.append("\tLDR r0 , "). append(place1).append("\n");
               place1="r0";
		   }		   
	   }
	   else if(operand1 instanceof Arguments) {
		   if(((Arguments)operand1).get_register()!=null){
               place1=((Arguments)operand1).get_register().GetRegName();
             }
             else{
               place1="[fp, #" + ((Arguments)operand1).get_offset().toString() +"]";
               textBuffer.append("\tLDR r0 , "). append(place1).append("\n");
               place1="r0";
             }		   
	   }
	   if(operand2 instanceof Integer_Op){
           if(((Integer_Op)operand2).get_register()!=null){
             place2=((Integer_Op)operand2).get_register().GetRegName();
           }
           else{
             place2="[fp ,#" + ((Integer_Op)operand2).get_offset().toString() +"]";
             textBuffer.append("\tLDR r1 , "). append(place2).append("\n");
             place2="r1";

           }
     }

     else if(operand2 instanceof Arguments){
       if(((Arguments)operand2).get_register()!=null){
         place2=((Arguments)operand2).get_register().GetRegName();
       }
       else{

         place2="[fp, #" + ((Arguments)operand1).get_offset().toString() +"]";  //not sure
         textBuffer.append("\tLDR r0 , "). append(place2).append("\n");
         place2="r0";
       }
     }
	   
	 if(operand1 instanceof Integer && operand2 instanceof Variable){

           arith_op("SUB",dest ,(int)operand1, place2);
     }

     else if(operand1 instanceof Variable && operand2 instanceof Variable){
           arith_op("SUB",dest ,place1, place2);
     }
     else if(operand1 instanceof Integer && operand2 instanceof Integer){

           arith_op("SUB",dest ,(int)operand1, (int)operand2);
     }
     else if(operand1 instanceof Variable && operand2 instanceof Integer){

           arith_op("SUB",dest ,place1, (int)operand2);
 
     }  
   }

   public void gen_mulcode(Integer_Mul instruction) {
	   Object operand1 = instruction.operandlist.get(0);
	   Object operand2 = instruction.operandlist.get(1);
	   String dest = "r0";  
	   String place1="", place2="";
	   
	   if(operand1 instanceof Integer_Op) {
		   if(((Integer_Op)operand1).get_register()!= null) {
			   place1 = ((Integer_Op)operand1).get_register().GetRegName();
		   }
		   else {
			   place1="[fp, #" + ((Integer_Op)operand1).get_offset().toString()  +"]";
               textBuffer.append("\tLDR r0 , "). append(place1).append("\n");
               place1="r0";
		   }		   
	   }
	   else if(operand1 instanceof Arguments) {
		   if(((Arguments)operand1).get_register()!=null){
               place1=((Arguments)operand1).get_register().GetRegName();
             }
             else{
               place1="[fp, #" + ((Arguments)operand1).get_offset().toString() +"]";
               textBuffer.append("\tLDR r0 , "). append(place1).append("\n");
               place1="r0";
             }		   
	   }
	   if(operand2 instanceof Integer_Op){
           if(((Integer_Op)operand2).get_register()!=null){
             place2=((Integer_Op)operand2).get_register().GetRegName();
           }
           else{
             place2="[fp ,#" + ((Integer_Op)operand2).get_offset().toString() +"]";
             textBuffer.append("\tLDR r1 , "). append(place2).append("\n");
             place2="r1";

           }
     }

     else if(operand2 instanceof Arguments){
       if(((Arguments)operand2).get_register()!=null){
         place2=((Arguments)operand2).get_register().GetRegName();
       }
       else{

         place2="[fp, #" + ((Arguments)operand1).get_offset().toString() +"]";  //not sure
         textBuffer.append("\tLDR r0 , "). append(place2).append("\n");
         place2="r0";
       }
     }
	   
	 if(operand1 instanceof Integer && operand2 instanceof Variable){

           arith_op("MUL",dest ,(int)operand1, place2);
     }

     else if(operand1 instanceof Variable && operand2 instanceof Variable){
           arith_op("MUL",dest ,place1, place2);
     }
     else if(operand1 instanceof Integer && operand2 instanceof Integer){

           arith_op("MUL",dest ,(int)operand1, (int)operand2);
     }
     else if(operand1 instanceof Variable && operand2 instanceof Integer){

           arith_op("MUL",dest ,place1, (int)operand2);

     }  
   }
   
//Code generation for arithmetic operations where operand1 is a register and operand2 is a register
   public void arith_op(String operation, String dest, String place1, String place2){

        textBuffer.append("\t").append(operation).append(" ").append(dest).append(", ")
                        .append(place1).append(", ").append(place2).append("\n");
   }
 //Code generation for arithmetic operations where operand1 is a register and operand2 is an integer
   public void arith_op(String operation, String dest, String place1, int operand2){

        textBuffer.append("\t").append(operation).append(" ").append(dest).append(", ")
                        .append(place1).append(", #").append(operand2).append("\n");
   }
 //Code generation for arithmetic operations where operand1 is an integer val and operand2 is a register
   public void arith_op(String operation, String dest, int operand1, String place2){
        textBuffer.append("\t").append(operation).append(" ").append(dest).append(", #")
                        .append(operand1).append(", ").append(place2).append("\n");
   }
 //Code generation for arithmetic operations where operand1 is an int value and operand2 is an int value
   public void arith_op(String operation, String dest, int operand1, int operand2){

        textBuffer.append("\t").append(operation).append(" ").append(dest).append(", #")
                        .append(operand1).append(", #").append(operand2).append("\n");
   }

//Code generation for Assign instruction   
   public void gen_assigncode(Assign instr){

       Object operand1= instr.operandlist.get(0);
       Object operand2= instr.operandlist.get(1);
       String place1="", place2="";
       String off1 = "";   

   //operand1 is a variable  
       if(operand1 instanceof Variable) {
           if(((Variable)operand1).get_register()!=null) {                     //operand1 has a register
                   place1=((Variable)operand1).get_register().GetRegName();     
           }
           else{                                                               //does not have a register
               System.out.println(((Variable)operand1).get_name());
               off1="[fp , #-" + ((Variable)operand1).get_offset() +"]";
               place1="r0";
           }
   }



   if(operand2 instanceof Variable) {
           //Operand2  is a local variable with register
           if(((Variable)operand2).get_register()!=null &&   ((Variable)operand2).get_argregister()==null && ((Variable)operand2).get_argoffset()==null) {
                   place2=((Variable)operand2).get_register().GetRegName();
           }
           //Operand2 is a local variable with offset
           else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_offset()==null && ((Variable)operand2).get_argregister()==null) {

                   place2="[fp ,#-" + ((Variable)operand2).get_offset().toString() +"]";
                   textBuffer.append("\tLDR r1 , ").append(place2).append("\n");
                   place2="r1";

           }

           // Operand2 is an argument with a register
           else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_offset()==null && ((Variable)operand2).get_argregister()!=null) {

                   place2=((Variable)operand2).get_argregister().GetRegName();
           }


           //Operand2 is an argument with offset on stack
           else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_argoffset()!=null && ((Variable)operand2).get_offset()==null ) {

                   place2="[fp ,#" + ((Variable)operand2).get_argoffset().toString() +"]";
                   textBuffer.append("\tLDR r1 , ").append(place2).append("\n");
                   place2="r1";
           }

   }


//If Operand1 is a variable or an integer variable and operand2 is a variable
   if((operand1 instanceof Integer_Op || operand1 instanceof Variable)&& operand2 instanceof Variable) {
	       textBuffer.append("\tMOV ").append(place1).append(", ").append(place2).append("\n"); //Move operand2 value in place1
           if(off1!="") {																		//if operand1 offset is not empty
                   textBuffer.append("\tSTR r0 , ").append(off1).append("\n"); //store place1 value in operand1 address on stack 
           }
   }
//Operand1 is a variable or integer variable and operand2 is an integer
   else if((operand1 instanceof Integer_Op || operand1 instanceof Variable) && operand2 instanceof Integer) {     
           textBuffer.append("\tLDR ").append(place1).append(", =").append((int)operand2).append("\n");  //Load operand2 value in place1
           if(off1!="") {                                                                               //if operand1 offset is not empty
                   textBuffer.append("\tSTR r0 , ").append(off1).append("\n"); //store place1 value in operand1 address on stack
           }

   }
//Operand1 is an integer variable and operand2 is an instruction
   else if(operand1 instanceof Integer_Op && operand2 instanceof Inst_Interface) {

           String reg= place1;


           if(operand2 instanceof Integer_Add) {

                   gen_addcode((Integer_Add) operand2);
                   textBuffer.append("\tMOV ").append(reg).append(", ").append("r0").append("\n"); //mov return value in place1

           }
           else if(operand2 instanceof Integer_Sub) {

                   gen_subcode((Integer_Sub) operand2);
                   textBuffer.append("\tMOV ").append(reg).append(", ").append("r0").append("\n");//mov return value in place1
           }

           else if(operand2 instanceof Integer_Mul) {

                   gen_mulcode((Integer_Mul) operand2);
                   textBuffer.append("\tMOV ").append(reg).append(", ").append("r0").append("\n");//mov return value in place1
           }

           else if(operand2 instanceof Call) {
                   gen_call((Call) operand2);

                   if(off1!="") {                            //operand1 is on stack
                           textBuffer.append("\tMOV  r1 , ").append(((Call)operand2).get_return()).append("\n"); //mov return value to r1
                           textBuffer.append("\tSTR r1 , ").append(off1).append("\n"); //store return value to operand1 address on stack
                   }
                   else{
                           String reg1 = ((Call)operand2).get_return();
                           textBuffer.append("\tMOV ").append(reg).append(", ").append(reg1).append("\n");  //move return value to place1
                   }
           }

           else if(operand2 instanceof If_Inst){             

               gen_ifcode((If_Inst) operand2);
               textBuffer.append("\tMOV ").append(reg).append(", ").append("r0").append("\n");  
           }
   }

	
 }	
	
//Code generation for call instruction	
   public void gen_call(Call instr){

	   List<Object> args = instr.get_args();
       String fun_name = get_label(instr.get_name());
       int no_args=args.size();

       if(instr.get_name().equals("print_int")) {
               if(args.get(0)!=null){
                   if(args.size() != 0) {
                       if(!(args.get(0) instanceof Integer)) {
                               Variable argument = (Variable)args.get(0);
                               //variable has a register
                               if(argument.get_register()!= null ) {
                            	       String reg1 = argument.get_register().GetRegName();
                            	       textBuffer.append("\t").append("MOV ").append("r0").append(", ")
                            	        .append(reg1).append("\n");
                               }
                               //variable has an offset
                               else if(argument.get_register()== null ) {
                                       String loc_off="[fp ,#-" + ((Variable)argument).get_offset() +"]";
                                       textBuffer.append("\tLDR r0 , ").append(loc_off).append("\n");
                               }
                               //variable and argument have offset
                               else {
                                       String loc_off="[fp ,#-" + ((Variable)argument).get_argoffset().toString() +"]";
                                       textBuffer.append("\tLDR r0 , ").append(loc_off).append("\n");
                               }

                       }

                   }
               }
               textBuffer.append("\tBL min_caml_print_int\n");
               textBuffer.append("\tBL min_caml_print_newline\n");
               return;
       }

       push_loc_reg();             //push local registers to stack

       if(no_args!=0) {
               Object obj = args.get(0);      
               if(obj instanceof Variable) {    //if first argument is a variable
                       for(int i=0; i<no_args; i++) {
                               Variable arg = (Variable)args.get(i);
                               //Variable and Argument have a register assigned
                               if(arg.get_register()!= null && arg.get_argregister()!=null) {
                            	   	   String reg1 = arg.get_argregister().GetRegName();
                            	   	   String reg2 = arg.get_register().GetRegName();
                            	   	 textBuffer.append("\t").append("MOV ").append(reg1).append(", ") //move variable to argument reg
                         	        .append(reg2).append("\n");                                
                               }
                               //Variable has a register and Argument does not
                               else if (arg.get_register()!= null && arg.get_argregister()==null) {
                                       String val =arg.get_register().GetRegName();
                                       textBuffer.append("\tSUB sp, #4\n");                      //push arg on stack
                                       textBuffer.append("\t").append("MOV ").append("r0").append(", ") //move variable to r0
                           	        .append(val).append("\n"); 
                                       textBuffer.append("\tSTR r0, [sp]\n");                       //store r0 value on stack in arg
                               }
                               //Variable is on stack and argument has a register
                               else if(arg.get_register()==null && arg.get_argregister()!=null ) { 
                                       String loc_off="[fp ,#" + ((Variable)arg).get_offset().toString() +"]"; //var offset
                                       textBuffer.append("\tLDR r0 , ").append(loc_off).append("\n");   //load var value in r0
                                       String val =arg.get_argregister().GetRegName();                 //argument register
                                       textBuffer.append("\t").append("MOV ").append(val).append(", ")   //move var value in r0 to arg register
                           	        .append("r0").append("\n");
                               }
                               //Variable is on stack and argument is added to stack
                               else if(arg.get_register()==null && arg.get_argregister()==null) {
                                       String loc_off="[fp ,#" + ((Variable)arg).get_offset().toString() +"]"; //var offset
                                       textBuffer.append("\tLDR r0 , ").append(loc_off).append("\n");          //load var value in r0
                                       textBuffer.append("\tSUB sp, #4\n");                             //push arg on stack
                                       textBuffer.append("\tSTR r0, [sp]\n");                           //store var value to arg 

                               }
                               else {
                                       String loc_off="[fp ,#" + ((Variable)arg).get_offset().toString() +"]";
                                       textBuffer.append("\tLDR r1 , ").append(loc_off).append("\n");
                                       textBuffer.append("\tSUB sp, #4\n");
                                       textBuffer.append("\t").append("MOV ").append("r1").append(", ")
                           	        .append("r0").append("\n");
                                       textBuffer.append("\tSTR r0, [sp]\n");
                               }
                       }
               }
       }

       textBuffer.append("\tBL ").append(fun_name).append("\n");
       pop_loc_reg();       //pop local registers	   
   }  
	    
	 
   
   public void gen_branchcode(fun fun, String ret_label) {
	   //List<Variable> args = fun.get_arguments();
       List<Inst_Interface> instr = fun.get_instruction();
       //HashSet<Variable> local = fun.get_local();
       //int loc_size= local.size();
       String fun_name = fun.get_name();
       gen_blabel(fun_name);
       
       for (Inst_Interface instruction : instr) {

           if(instruction instanceof Integer_Add) {
                   gen_addcode((Integer_Add)instruction);
           }
           else if (instruction instanceof Integer_Sub) {
                   gen_subcode((Integer_Sub)instruction);
           }
           else if (instruction instanceof Integer_Mul) {
                   gen_mulcode((Integer_Mul) instruction);
           }

           else if (instruction instanceof Assign) {
                   gen_assigncode((Assign)instruction);

           }
           else if (instruction instanceof Call) {
                   gen_call((Call)instruction);

           }
           else if(instruction instanceof If_Inst){

                   gen_ifcode((If_Inst)instruction);
           }
           else if(instruction instanceof Noop) {
        	       gen_noopcode((Noop) instruction);
           }
           else{
                   System.out.println("Instruction Not Supported\n");
           }
   }

   textBuffer.append("\tb ").append(ret_label).append("\n");
       
   }
   
   public void gen_noopcode(Noop instr){

       Integer a = ((Integer_Op)instr.objnoop).get_val();
       textBuffer.append("\tLDR ").append("r0").append(", =").append(a).append("\n");

}
   
   public String gen_ifcode(If_Inst instr) {
	   fun branchthen = instr.fun_then;
	   fun branchelse = instr.fun_else;
	   branchthen.print_variable_status();

       BoolExp exp = instr.cond.get_expression();
	   System.out.println(instr.cond);
	   System.out.println(exp);

	   String place1="", place2 = "";
	   //String offset1 = "";                

	      if(exp instanceof BoolEq){
	         Variable operand1 = (Variable)(((BoolEq)exp).operandlist.get(0));
	         Variable operand2 = (Variable)(((BoolEq)exp).operandlist.get(1));


	          //Variable that has a register assigned
	        if(((Variable)operand1).get_register()!=null &&   ((Variable)operand1).get_argregister() ==null && ((Variable)operand1).get_argoffset()==null) {
	                place1=((Variable)operand1).get_register().GetRegName();
	        }
	        //Variable that is in stack
	        else if(((Variable)operand1).get_register()==null && ((Variable)operand1).get_offset()!=null && ((Variable)operand1).get_argregister()==null) {

	                place1="[fp ,#-" + ((Variable)operand2).get_offset().toString() +"]";
	                textBuffer.append("\tLDR r0 , ").append(place1).append("\n");
	                place1="r0";

	        }

	        // if the operand is an argument that has register assigned
	        else if(((Variable)operand1).get_register()==null && ((Variable)operand1).get_offset()==null && ((Variable)operand1).get_argregister()!=null) {

	                place1=((Variable)operand1).get_argregister().GetRegName();
	        }


	        // if the operand is an argument in memory
	        else if(((Variable)operand1).get_register()==null && ((Variable)operand1).get_argoffset()!=null && ((Variable)operand1).get_offset()==null ) {

	                place1="[fp ,#" + ((Variable)operand2).get_argoffset().toString() +"]";
	                textBuffer.append("\tLDR r0 , ").append(place1).append("\n");
	                place1="r0";
	        }

	        if(((Variable)operand2).get_register()!=null &&   ((Variable)operand2).get_argregister()==null && ((Variable)operand2).get_argoffset()==null) {
	            place2=((Variable)operand2).get_register().GetRegName();
	        }
	        // it operand2 is a local variable in memory
	        else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_offset()==null && ((Variable)operand2).get_argregister()==null) {

	            place2="[fp ,#-" + ((Variable)operand2).get_offset().toString() +"]";
	            textBuffer.append("\tLDR r1 , ").append(place2).append("\n");
	            place2="r1";

	        }

	        // if operand2 is an argument with a register assigned
	        else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_offset()==null && ((Variable)operand2).get_argregister()!=null) {

	            place2=((Variable)operand2).get_argregister().GetRegName();
	        }


	        // if operand2 is an argument in stack
	        else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_argoffset()!=null && ((Variable)operand2).get_offset()==null ) {

	            place2="[fp ,#" + ((Variable)operand2).get_argoffset().toString() +"]";
	            textBuffer.append("\tLDR r1 , ").append(place2).append("\n");
	            place2="r1";
	        }


	        textBuffer.append("\tCMP ").append(place1).append(" , "). append(place2).append("\n");
	        textBuffer.append("\tBEQ ").append(instr.fun_then.get_name());
	        textBuffer.append("\tBA ").append(instr.fun_else.get_name());
	      }

	      else if (exp instanceof BoolLe){

	        Variable operand1 = (Variable)(((BoolLe)exp).operandlist.get(0));
	         Variable operand2 = (Variable)(((BoolLe)exp).operandlist.get(0));


	          //operand1 is a variable with register assigned
	        if(((Variable)operand1).get_register()!=null &&   ((Variable)operand1).get_argregister()==null && ((Variable)operand1).get_argoffset()==null) {
	                place1=((Variable)operand1).get_register().GetRegName();
	        }
	        //operand1 is a variable in stack
	        else if(((Variable)operand1).get_register()==null && ((Variable)operand1).get_offset()!=null && ((Variable)operand1).get_argregister()==null) {

	                place1="[fp ,#-" + ((Variable)operand2).get_offset().toString() +"]";
	                textBuffer.append("\tLDR r0 , ").append(place1).append("\n");
	                place1="r0";

	        }

	        // operand1 is an argument with register assigned
	        else if(((Variable)operand1).get_register()==null && ((Variable)operand1).get_offset()==null && ((Variable)operand1).get_argregister()!=null) {

	                place1=((Variable)operand1).get_argregister().GetRegName();
	        }


	        // operand1 is an argument in stack
	        else if(((Variable)operand1).get_register()==null && ((Variable)operand1).get_argoffset()!=null && ((Variable)operand1).get_offset()==null ) {

	                place1="[fp ,#" + ((Variable)operand2).get_argoffset().toString() +"]";
	                textBuffer.append("\tLDR r0 , ").append(place1).append("\n");
	                place1="r0";
	        }
            //operand2 is a variable with register assigned
	        if(((Variable)operand2).get_register()!=null &&   ((Variable)operand2).get_argregister()==null && ((Variable)operand2).get_argoffset()==null) {
	            place2=((Variable)operand2).get_register().GetRegName();
	        }
	        // operand2 is a variable in stack
	        else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_offset()==null && ((Variable)operand2).get_argregister()==null) {

	            place2="[fp ,#-" + ((Variable)operand2).get_offset().toString() +"]";
	            textBuffer.append("\tLDR r1 , ").append(place2).append("\n");
	            place2="r1";

	        }

	        //operand2 is an argument with a register assigned
	        else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_offset()==null && ((Variable)operand2).get_argregister()!=null) {

	            place2=((Variable)operand2).get_argregister().GetRegName();
	        }


	        // operand2 is an argument in stack
	        else if(((Variable)operand2).get_register()==null && ((Variable)operand2).get_argoffset()!=null && ((Variable)operand2).get_offset()==null ) {

	            place2="[fp ,#" + ((Variable)operand2).get_argoffset().toString() +"]";
	            textBuffer.append("\tLDR r1 , ").append(place2).append("\n");
	            place2="r1";
	        }


	        textBuffer.append("\tCMP ").append(place1).append(" , "). append(place2).append("\n");
	        textBuffer.append("\tBLE ").append(instr.fun_then.get_name());
	        textBuffer.append("\tBA ").append(instr.fun_else.get_name());


	      }

	      else if (exp instanceof True){

	        textBuffer.append("\tBA ").append(instr.fun_then.get_name());



	      }

	      else if(exp instanceof False){
	        textBuffer.append("\tBA ").append(instr.fun_else.get_name());
	      }
	      else{

	          System.out.println(" ????");  
	      }

	      String ret_label = gen_tlabel();


	      gen_branchcode(branchthen, ret_label);  //code for then


	      gen_branchcode(branchelse, ret_label);  //code for else

	      return ret_label;
   }
}