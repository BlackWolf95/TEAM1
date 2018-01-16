import registers.Registers;
import ARMGen.fun;
import ARMGen.ARMgenerator;
import Instructions.*;
import java.util.*;
public class testarm {



	   public static void main(String[] args){

		   ARMgenerator  arm = new ARMgenerator();


	        List<Inst_Interface> instr = new ArrayList<Inst_Interface>();
	        List<Inst_Interface> add_instr = new ArrayList<Inst_Interface>();

	        //global structure
	        List<fun> funs= new ArrayList<fun>();

	        

	        //variables
	        Integer x= new Integer(1);
	        Integer f = new Integer(2);
	        
	        ArrayList<Registers> registers = new ArrayList<Registers>(9);
	        ArrayList<Registers> param_registers = new  ArrayList<Registers>(2);

	        ArrayList<Registers> registers1 = new ArrayList<Registers>(9);
	        ArrayList<Registers> param_registers1 = new  ArrayList<Registers>(2);

	        HashSet<Variable> locals = new HashSet<Variable>();
	        HashSet<Variable> flocals= new HashSet<Variable>();

	        Registers.reg_initialization(registers, param_registers);
	        Registers.reg_initialization(registers1, param_registers1);
	       //

	        //functions
	        fun fundef= new fun("main", null, instr, registers, param_registers);
	        //Function fadd = new Function("add", null, add_instr, registers, param_registers);
	        fun _f = new fun("f", null, add_instr, registers1, param_registers1);


	       //RegisterUtils.showRegisters(registers);

	        ArrayList<Object> _f_params = new ArrayList<Object>();
	        Integer_Op a = new Integer_Op("a", -1,_f);
	        Integer_Op b = new Integer_Op("b", -1,_f);
	        a.assign_register();
	        a.get_status();
	        b.assign_register();
	        b.get_status();
	        _f_params.add(a);
	        _f_params.add(b);


	        Integer_Op fz = new Integer_Op("z", -1, _f);
	        Integer_Op ft = new Integer_Op("t", 2, _f);
	        Integer_Op fq = new Integer_Op("q", -1, _f);

	        Integer_Op mz = new Integer_Op("z", -1, fundef);
	        Integer_Op vx = new Integer_Op("x",0, fundef);
	        Integer_Op vy = new Integer_Op("y",1, fundef);
	        Integer_Op vw = new Integer_Op("w",3, fundef);

	        vx.assign_register();
	        vy.assign_register();
	        vw.assign_register();
	        mz.assign_register();

	        fq.assign_register();
	        fz.assign_register();
	        ft.assign_register();


	        flocals.add(fz);
	        flocals.add(ft);
	        flocals.add(fq);

	        locals.add(vx);
	        locals.add(vy);
	        locals.add(vw);
	        locals.add(mz);

	        fundef.set_local(locals);
	        _f.set_local(flocals);

	        vx.assign_argregister();
	        vy.assign_argregister();

	        vx.get_status();
	        vy.get_status();

	        List<Object> params = new ArrayList<Object>();
	        params.add(vx);
	        params.add(vy);

	        Registers.printRegister(registers1);
	        Registers.printRegister(param_registers1);



	        Assign q = new Assign(fundef, vx, 0);
	        Assign p = new Assign(fundef, vy, 1);
	        Assign wass = new Assign(fundef, vw, 2);
	        Call call_f = new Call(params, "f");
	        Assign lmain = new Assign(fundef, mz, call_f);
	        //InstructionCALL call_f = new InstructionCALL(params, "f");

	        List<Object> para = new ArrayList<Object>();
	        mz.assign_argregister();
	        para.add(mz);
	        Call call_min = new Call(para, "print_int");



	        Integer_Add add_f = new Integer_Add(_f, a, b);
	        Assign vz = new Assign(_f, fz, add_f);
	        Assign vt = new Assign(_f, ft, 2);
	        Integer_Sub sub_f= new Integer_Sub(_f, fz, ft);
	        Assign qass = new Assign(_f, fq, sub_f);
	        Integer_Add add_q = new Integer_Add(_f, fq, ft);

	        //fundef.addInstruction(call);
	        fundef.add_instructionlist(q);
	        fundef.add_instructionlist(p);
	        fundef.add_instructionlist(wass);
	        fundef.add_instructionlist(lmain);
	        fundef.add_instructionlist(call_min);
	        //fundef.addInstruction(ci);
	        //fundef.addInstruction(sub);

	        //fundef.addInstruction(ass);stash
	        _f.add_instructionlist(vz);
	        _f.add_instructionlist(vt);
	        _f.add_instructionlist(qass);
	        _f.add_instructionlist(add_q);

	        //fadd.addInstruction(add);
	        //fadd.addInstruction(fadd_ass);
	        //List<Parameter> para= new ArrayList<Parameter>();
	        //para.add(new Parameter(b.getName(),b.getValue(), param_registers, fadd));
	        //fadd.addInstruction(new InstructionCALL(para,"print_int"));
	        //fadd.addInstruction(add);


	        //fundef.addInstruction(sub);
	        //fundef.addInstruction(mul);
	        funs.add(fundef);
	        funs.add(_f);
	        arm.armgen(funs);

	        StringBuffer result= arm.textBuffer;

	        System.out.println(result);


	        

	   }

	}

