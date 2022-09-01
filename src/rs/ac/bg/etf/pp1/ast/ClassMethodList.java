// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodList extends ClassMethods {

    private ClassMethodsDecl ClassMethodsDecl;

    public ClassMethodList (ClassMethodsDecl ClassMethodsDecl) {
        this.ClassMethodsDecl=ClassMethodsDecl;
        if(ClassMethodsDecl!=null) ClassMethodsDecl.setParent(this);
    }

    public ClassMethodsDecl getClassMethodsDecl() {
        return ClassMethodsDecl;
    }

    public void setClassMethodsDecl(ClassMethodsDecl ClassMethodsDecl) {
        this.ClassMethodsDecl=ClassMethodsDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassMethodsDecl!=null) ClassMethodsDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassMethodsDecl!=null) ClassMethodsDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassMethodsDecl!=null) ClassMethodsDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassMethodList(\n");

        if(ClassMethodsDecl!=null)
            buffer.append(ClassMethodsDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodList]");
        return buffer.toString();
    }
}
