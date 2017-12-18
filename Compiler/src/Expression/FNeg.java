package Expression;

import Visiteur.ObjVisitor;
import Visiteur.Visitor;

public class FNeg extends Exp {
    public final Exp e;

    public FNeg(Exp e) {
        this.e = e;
    }

    public <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
