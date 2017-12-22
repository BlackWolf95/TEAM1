package ARMGen;
import Instructions.*;
import registers.Reg_assign;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ARMgenerator {
	StringBuffer ARMBuffer = new StringBuffer();


    private static Map<String, String> IMPORTS = new LinkedHashMap<>();
    private String[] register;
    private int reg_no;

    private String return_word = "ret"; 


    static {
        IMPORTS.put("print_newline", "min_caml_print_newline");
        IMPORTS.put("print_int", "min_caml_print_int");

    }


    public ARMgenerator() {
		ARMBuffer.append("\t.text \n\t.global _start \n_main:");
    }

    public void armgen(List<fun> func) { 
        for (fun fd : func) {
            armgen(fd);
        }
    }

    private void armgen(fun funDef) {

        gen_label(funDef.lb);  //label name
        // initialization for the register allocator
        register = new String[11];
        reg_no = 4;


        // Instructions in the function body
        for (Inst_Interface instruction : funDef.instruc) {
            Inst_Interface.inst_type typ = instruction.Get_Inst_type();
            if (typ != Inst_Interface.inst_type.Label) {
                ARMBuffer.append("\n\n\t@ ").append(instruction.toString());
            }

            if(typ == Inst_Interface.inst_type.Label)
            {
            	gen_label((Label) instruction);
            }
            
            else if(typ == Inst_Interface.inst_type.Call)
            {
            	gen_call((Call) instruction);
            }
            else if(typ == Inst_Interface.inst_type.Assign)
            {
            	gen_assign((Assign) instruction);
            }
            else if(typ == Inst_Interface.inst_type.Integer_Add)
            {
            	gen_intadd((Integer_Add) instruction);
            }
            else if(typ == Inst_Interface.inst_type.Integer_Sub)
            {
            	gen_intsub((Integer_Sub) instruction);
            }
            else if(typ == Inst_Interface.inst_type.Jump)
            {
            	gen_jump((Jump) instruction);
            }
            else if(typ == Inst_Interface.inst_type.Return)
            {
            	gen_ret((Return) instruction);
            }
            else
            {
                    throw new RuntimeException(
                            "Generating ARM assembly for " + instruction.Get_Inst_type() + " instructions not supported yet"
                    );
            }

        }
    }


    private int new_reg(){

        int j = registers.Reg_assign.getRegsLocal();
        if(j != registers.Reg_assign.INT_MIN)
        {
        	return j;
        }
        else
        {
        	 String tmp = register[reg_no];
             ARMBuffer.append("\n\tLDR r0, =").append(tmp);
             ARMBuffer.append("\n\tSTR r").append(reg_no).append(", [r0]");
             int return_reg = reg_no;
             if(reg_no > 11)
            	 reg_no = 4;
             else
            	 ++reg_no;

             return return_reg;
        }

    }


    private int get_reg(String var){
        int i;

        if(var.equals(return_word)){
            return 11;
        }

        for (i = 4; i<=12; i++){
            if(register[i] != null && register[i].equals(var)){
                return i;
            }
        }

        return -1;
    }


    private void Assignment(Variable var, Operands op) {
        String v = var.var_name;
        int rd;
        Operands.op_type typ = op.Get_Operand_Type();
        if(typ == Operands.op_type.Integer)
        {
        	 rd = new_reg();
             int val = ((Integer_Op)op).val;
             register[rd] = v;
             ARMBuffer.append("\n\tLDR r").append(rd).append(", =").append(val);
             return;
        }
        
        else if(typ == Operands.op_type.Variable) {
        	 if((rd = get_reg(v)) != -1){
                 Assignment("r"+rd, op);
             }
        	
        }
        else
        {
        	 rd = new_reg();
             register[rd] = v;
             Assignment("r"+rd,op);
             return;
        }


    }

    private void Assignment(Variable var, String register_s) {
        int rd;
        String v = var.var_name;

        if((rd = get_reg(v)) != -1){
            ARMBuffer.append("\n\tMOV r").append(rd).append(", ").append(register_s);
            return;
        }

        // here the v is not encountered yet
        rd = new_reg();
        register[rd] = v;
        ARMBuffer.append("\n\tMOV r").append(rd).append(", ").append(register);
        return;

    }

    private void Assignment(String register_s,Operands op) {
    	Operands.op_type typ = op.Get_Operand_Type();
    	if(typ == Operands.op_type.Integer) {
    		 ARMBuffer.append("\n\tLDR ").append(register_s);
             ARMBuffer.append(", =").append(((Integer_Op) op).val);
             return;	
    	}
    	
    	else if(typ == Operands.op_type.Label) {
    		 ARMBuffer.append("\n\tLDR ").append(register_s);
             ARMBuffer.append(", =").append(((Label)op).label_name);
             return;
    	}
    	else if(typ == Operands.op_type.Variable) {
    		 int rd;
             String v = ((Variable)op).var_name;

             if((rd = get_reg(v)) != -1){
                 ARMBuffer.append("\n\tMOV ").append(register_s).append(", r").append(rd);
                 return;
             }

        }
    }
    private void Assignment(String destR, String srcR) {
        ARMBuffer.append("\n\tMOV ").append(destR).append(", ").append(srcR);
    }
    
    private void binary_op(
            String ins,
            Variable var,
            Operands op1,
            Operands op2
    ) {
        Assignment("r1", op1); 
        Assignment("r2", op2); 
        ARMBuffer.append("\n\t").append(ins).append(" r0, r1, r2");

        Assignment(var, "r0"); //
    }

    private void gen_label(Label i) {
        ARMBuffer.append("\n").append(i.label_name).append(":");
    }

    private void gen_call(Call cl) {
        String name = cl.name;
        String new_name = IMPORTS.get(name);
        if (new_name != null) name = new_name;


        if (name.equals("min_caml_print_int")) {
            Assignment("r0", cl.arguments.get(0)); // 
            ARMBuffer.append("\n\tBL ").append(name);

            if(cl.retval != null){
                Assignment(cl.retval, "r11");
            }

            return;
        }

        if (name.equals("min_caml_print_newline")) {
            ARMBuffer.append("\n\tBL min_caml_print_newline");

            if(cl.retval != null){
                Assignment(cl.retval, "r11");
            }
            return;
        }


        if(cl.retval != null){
            Assignment(cl.retval, "r11");
        }

        ARMBuffer.append("\n\t@ end call");
    }


    private void gen_assign(Assign i) {
        Assignment(i.var, i.op); 
    }

    private void gen_intadd(Integer_Add i) {

        binary_op("ADD", i.var, i.op1, i.op2);
    }

    private void gen_intsub(Integer_Sub i) {
        binary_op("SUB", i.var, i.op1, i.op2);
    }

    private void gen_jump(Jump i) {
    	ARMBuffer.append("\n\tBAL ").append(i.label.label_name);
    }

    private void gen_ret(Return i) {
        if (i.op == null) return;
        if (i.op instanceof Variable) {
            if (((Variable) i.op).var_name.equals(return_word)) {
                return;
            }
        }
        Assignment("r11", i.op);
    }

    public void ARM_assembly_write(PrintStream out) {
        out.println(ARMBuffer.toString());
    }

}

