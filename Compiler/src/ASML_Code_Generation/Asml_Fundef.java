package ASML_Code_Generation;

import java.util.List;

import Expression.Exp;
import Tool.Id;
import Types.Type;

public class Asml_Fundef {

	public  Id id;
    public  Type type;
    public  List<Id> args;
    public  AM_Exp e;

    public Asml_Fundef(Id id, Type t, List<Id> args, AM_Exp e) {
        this.id = id;
        this.type = t;
        this.args = args;
        this.e = e;
    }
}
