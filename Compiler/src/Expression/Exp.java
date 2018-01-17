package Expression;
import java.util.*;
import Tool.Id;
import Types.*;
import Visiteur.*;
import Visiteur.ObjVisitor;
import Visiteur.Visitor;

public abstract class Exp {

	 public abstract void accept(Visitor v);

	 public abstract <E> E accept(ObjVisitor<E> v);
     
}
