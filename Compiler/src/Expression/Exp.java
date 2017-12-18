package Expression;
import Visiteur.ObjVisitor;
import Visiteur.Visitor;

public abstract class Exp {

	 public abstract void accept(Visitor v);

	 public abstract <E> E accept(ObjVisitor<E> v);
}
