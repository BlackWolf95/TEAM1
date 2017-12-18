package Expression;

import Visiteur.ObjVisitor;
import Visiteur.Visitor;

public class Float extends Exp {
    public float f;

    public Float(float f) {
        this.f = f;
    }

    public <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}