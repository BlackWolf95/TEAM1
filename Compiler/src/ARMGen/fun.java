package ARMGen;
import Instructions.*;
import java.util.Collections;
import java.util.List;

public class fun {

	public Label lb;
	public List<Variable> arg;
	public List<Variable> local;
	public List<Inst_Interface> instruc;
	
	public fun(Label lb, List<Variable> arg, List<Variable> local, List<Inst_Interface> instruc){
		
		this.lb = lb;
		this.arg = Collections.unmodifiableList(arg);
		this.local = Collections.unmodifiableList(local);
		this.instruc = Collections.unmodifiableList(instruc);
		
	}
	
	public fun(Label lb, List<Variable> local, List<Inst_Interface> instruc){
		
		this.lb = lb;
		this.local = Collections.unmodifiableList(local);
		this.instruc = Collections.unmodifiableList(instruc);
		
	}
}
