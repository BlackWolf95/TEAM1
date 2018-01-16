package ARMGen;
import java.util.*;
import registers.Registers;
import Instructions.*;
import Expression.Add;
import Expression.App;
import Expression.Array;
import Expression.Bool;
import Expression.Eq;
import Expression.Exp;
import Expression.FAdd;
import Expression.FDiv;
import Expression.FMul;
import Expression.FNeg;
import Expression.FSub;
import Expression.Float;
import Expression.FunDef;
import Expression.Get;
import Expression.If;
import Expression.Int;
import Expression.LE;
import Expression.Let;
import Expression.LetRec;
import Expression.LetTuple;
import Expression.Neg;
import Expression.Not;
import Expression.Put;
import Expression.Sub;
import Expression.Tuple;
import Expression.Unit;
import Expression.Var;
import Tool.Id;
import Types.TFloat;
import Types.TFun;
import Types.TInt;
import Types.Type;

public class DataStrucConversion {
	
	public static Integer temp_Id = 0;
	public static Integer temp_eId = 0;
	public static Integer label = 0;
	
	public String get_temp_varname() { 
        temp_Id++;
        return "temp_var" + temp_Id.toString();  
}
	public String get_temp_boolexp() { 
        temp_eId++;
        return "temp_Boolexp" + temp_eId.toString();  
}
	public String get_new_label() {
        label++;
        return "label" + label.toString();
}

	public Object visit(Exp e, fun fun) {
        if (e instanceof Add) {
                return (Integer_Add)visit((Add)e, fun);
        }
        else if (e instanceof Sub) {
                return (Integer_Sub) visit((Sub)e, fun);
        }
        else if (e instanceof Let) {
                visit((Let)e, fun);
        }
        else if (e instanceof LetRec) {
                visit((LetRec)e, fun);
        }
        else if (e instanceof Int) {
                return (Integer) visit((Int)e, fun);
        }
        else if (e instanceof Bool) {
                return (boolean) visit((Bool)e, fun);
        }
        else if (e instanceof Not) {
                return (boolean) visit((Bool)e, fun);
        }
        else if (e instanceof Var) {
                return (Variable) visit((Var)e, fun);
        }
        else if (e instanceof App) {
                visit((App)e, fun);
        }
        else if (e instanceof Neg) {
                return (Integer) visit((Neg)e, fun);
        }
        else if (e instanceof If) {
                return (If_Inst) visit((If)e, fun);
        }
        return null;
}
	public Integer_Add visit(Add e, fun fun) {
        System.out.println("ADD");
        ArrayList<Variable> varlist = new ArrayList<Variable>();
        String var1 = ((Var)e.e1).id.toString();
        String var2 = ((Var)e.e2).id.toString();
        for (Variable v : fun.get_local()){
                if (var1 == v.get_name()) {
                        varlist.add(v);
                }
        }
        if (varlist.size() == 0) {
                Integer_Op temp1 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e1, fun), fun);
                varlist.add(temp1);
        }

        for (Variable v : fun.get_local()) {
                if (var2 == v.get_name()) {
                        varlist.add(v);
                }
        }
        if (varlist.size() == 0) {
                Integer_Op temp2 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e2, fun), fun);
                varlist.add(temp2);
        }


        try {
                Integer_Add instr = new Integer_Add(fun, varlist.get(0), varlist.get(1));
                fun.add_instructionlist(instr);
                return instr;
        } catch (IndexOutOfBoundsException exception) {
                Integer_Op temp1 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e1, fun), fun);
                Integer_Op temp2 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e2, fun), fun);
                fun.get_local().add(temp1);
                fun.get_local().add(temp2);
                Integer_Add instr = new Integer_Add(fun, temp1, temp2);
                fun.add_instructionlist(instr);
                return instr;
        }
}
public Integer_Sub visit(Sub e, fun fun){
    System.out.println("SUB");
    ArrayList<Variable> varlist = new ArrayList<Variable>();

    String var1 = ((Var)e.e1).id.toString();
    String var2 = ((Var)e.e2).id.toString();

    for (Variable v : fun.get_local()){
            if (var1 == v.get_name()) {
                    varlist.add(v);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp1 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e1, fun), fun);
            varlist.add(temp1);
    }

    for (Variable v : fun.get_local()) {
            if (var2 == v.get_name()) {
                    varlist.add(v);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp2 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e2, fun), fun);
            varlist.add(temp2);
    }


    try {
            Integer_Sub instr = new Integer_Sub(fun, varlist.get(0), varlist.get(1));
            fun.add_instructionlist(instr);
            return instr;
    } catch (IndexOutOfBoundsException exception) {
            Integer_Op temp1 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e1, fun), fun);
            Integer_Op temp2 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e2, fun), fun);
            fun.get_local().add(temp1);
            fun.get_local().add(temp2);
            Integer_Sub instr = new Integer_Sub(fun, temp1, temp2);
            fun.add_instructionlist(instr);
            return instr;
    }
}

public void visit(Let e, fun fun) {
	System.out.println("LET");
    if (e.e1 instanceof Int) {
            Integer val = (Integer) visit(e.e1, fun);
            Integer_Op var = new Integer_Op(e.id.toString(), val, fun);
            Assign instr = new Assign(fun, var, val);
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof Neg) {
            Integer val = (Integer) visit(e.e1, fun);
            Integer_Op var = new Integer_Op(e.id.toString(), val, fun);
            Assign instr = new Assign(fun, var, val);
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof App) {
            visit(e.e1, fun);
    }
    else if (e.e1 instanceof Add) {
            Integer_Add addI = (Integer_Add) visit(e.e1, fun);
            Integer_Op var = new Integer_Op(e.id.toString(), 0, fun);
            Assign instr = new Assign(fun, var, addI);
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof Sub) {
            Integer_Sub subI = (Integer_Sub) visit(e.e1, fun);
            Integer_Op var = new Integer_Op(e.id.toString(), 0, fun);
            Assign instr = new Assign(fun, var, subI);
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof Var) {
            Variable var = new Variable(e.id.toString(), fun);
            Assign instr = new Assign(fun, var, (Variable)visit(e.e1, fun));
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof If) {
            If_Inst ifI= (If_Inst) visit(e.e1, fun);
            Integer_Op var = new Integer_Op(e.id.toString(), 0, fun);
            Assign instr = new Assign(fun, var, ifI);
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else {
            visit(e.e1, fun);
    }
    visit(e.e2, fun);
}

public Variable visit(Var e, fun fun){
    String var_name = ((Var)e).id.toString();

    for (Variable var : fun.get_local()) {
            if (var_name == var.get_name()) {
                    return var;
            }
    }
    return null;
}

public Integer visit(Int e, fun fun){
    Integer_Op i = new Integer_Op(get_temp_varname(), e.i, fun); 
    Noop instr = new Noop(i);
    fun.add_instructionlist(instr);
    return e.i;
}

public Integer visit(Neg e, fun fun){
    Integer i = (Integer) visit(e.e, fun);
    return -i;
}

public void visit(App e, fun fun){
    System.out.println("APP");
    ArrayList<Object> varlist = new ArrayList<Object>();
    for (Exp ex : e.es) {
            Object var = (Object) visit(ex, fun);
            if (var instanceof Integer) {
                    var = new Integer_Op(get_temp_varname(), (Integer)var, fun);
                    ((Variable)var).assign_register();
                    ((Variable)var).remove();
                    Assign instr = new Assign(fun, var, ((Integer_Op)var).get_val());
                    fun.add_instructionlist(instr);
            }
            else if (var instanceof Boolean) {
                    var = new Bool_Op(get_temp_varname(), (boolean)var, fun);
                    ((Variable)var).assign_register();
                    ((Variable)var).remove();
                    Assign instr = new Assign(fun, var, ((Bool_Op)var).get_expression());
                    fun.add_instructionlist(instr);
            }
            varlist.add(var);
    }

    for (Object obj : varlist) {
            if (obj instanceof Variable) {
                    ((Variable)obj).assign_argregister();
            }
    }
    for (Object obj : varlist) {
            if (obj instanceof Variable) {
                    ((Variable)obj).remove_arg();
            }
    }
    Call instr = new Call(varlist, ((Var)e.e).id.toString());
    fun.add_instructionlist(instr);
}

public boolean visit(Expression.Bool e, fun fun){
    boolean b = e.b;
    return b;
}

public boolean visit(Not e, fun fun){
    boolean b = (boolean) visit(e.e, fun);
    return !b;
}

public BoolEq visit(Eq e, fun fun){
    ArrayList<Variable> varlist = new ArrayList<Variable>();

    String var1 = ((Var)e.e1).id.toString();
    String var2 = ((Var)e.e2).id.toString();

    for (Variable var : fun.get_local()) {
            if (var1 == var.get_name()) {
                    varlist.add(var);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp1 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e1, fun), fun);
            varlist.add(temp1);
    }

    for (Variable var : fun.get_local()) {
            if (var2 == var.get_name()) {
                    varlist.add(var);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp2 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e2, fun), fun);
            varlist.add(temp2);
    }

    BoolEq exp = new BoolEq(get_temp_boolexp(), fun, varlist.get(0), varlist.get(1));
    return exp;
}

public BoolLe visit(LE e, fun fun){
    ArrayList<Variable> varlist = new ArrayList<Variable>();

    String var1 = ((Var)e.e1).id.toString();
    String var2 = ((Var)e.e2).id.toString();

    for (Variable var : fun.get_local()) {
            if (var1 == var.get_name()) {
                    varlist.add(var);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp1 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e1, fun), fun);
            varlist.add(temp1);
    }

    for (Variable var : fun.get_local()) {
            if (var2 == var.get_name()) {
                    varlist.add(var);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp2 = new Integer_Op(get_temp_varname(), (Integer)visit(e.e2, fun), fun);
            varlist.add(temp2);
    }

    BoolLe exp = new BoolLe(get_temp_boolexp(), fun, varlist.get(0), varlist.get(1));
    return exp;
}

public If_Inst visit(If e, fun fun) {
    Bool_Op cond = new Bool_Op(get_temp_varname(), (BoolExp)visit(e.e1, fun), fun);
    fun branchthen = new fun(get_new_label(), new ArrayList(), new ArrayList<Inst_Interface>(), fun.loc_reg, fun.arg_reg);
    visit(e.e2, branchthen);
    fun branchelse = new fun(get_new_label(), new ArrayList(), new ArrayList<Inst_Interface>(), fun.loc_reg, fun.arg_reg);
    visit(e.e3, branchelse);
    If_Inst instr = new If_Inst(cond, branchthen, branchelse);
    fun.add_instructionlist(instr);
    return instr;
}

public void visit(LetRec e, fun fun){
    System.out.println("LETREC");

    ArrayList<Variable> arglist = new ArrayList<Variable>();
    ArrayList<Registers> reglist= new ArrayList<Registers>(9);
    ArrayList<Registers> arg_reglist = new ArrayList<Registers>(2);
    Registers.reg_initialization(reglist, arg_reglist);

    fun func = new fun(e.fd.id.toString(), arglist, new ArrayList<Inst_Interface>(), reglist, arg_reglist);

    for (Id id : e.fd.args) {
            Variable arg = new Variable(id.toString(), func);
            arglist.add(arg);
    }
    visit(e.fd.e, func);
    visit(e.e, fun);
}

public Inst_Interface visit(Expression.Float e, fun fun){
        return null;
}

public Inst_Interface visit(FNeg e, fun fun){
        return null;
}

public Inst_Interface visit(FAdd e, fun fun){
        return null;
}

public Inst_Interface visit(FSub e, fun fun){
        return null;
}

public Inst_Interface visit(FMul e, fun fun){
        return null;
}

public Inst_Interface visit(FDiv e, fun fun){
        return null;
}

public Inst_Interface visit(Tuple e, fun fun){
        return null;
}

public Inst_Interface visit(LetTuple e, fun fun){
        return null;
}

public Inst_Interface visit(Array e, fun fun){
        return null;
}

public Inst_Interface visit(Get e, fun fun){
        return null;
}

public Inst_Interface visit(Put e, fun fun){
        return null;
}

}
