// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ClassName ClassName;
    private ClassExtendsDecl ClassExtendsDecl;
    private ClassInfo ClassInfo;

    public ClassDecl (ClassName ClassName, ClassExtendsDecl ClassExtendsDecl, ClassInfo ClassInfo) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.ClassExtendsDecl=ClassExtendsDecl;
        if(ClassExtendsDecl!=null) ClassExtendsDecl.setParent(this);
        this.ClassInfo=ClassInfo;
        if(ClassInfo!=null) ClassInfo.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public ClassExtendsDecl getClassExtendsDecl() {
        return ClassExtendsDecl;
    }

    public void setClassExtendsDecl(ClassExtendsDecl ClassExtendsDecl) {
        this.ClassExtendsDecl=ClassExtendsDecl;
    }

    public ClassInfo getClassInfo() {
        return ClassInfo;
    }

    public void setClassInfo(ClassInfo ClassInfo) {
        this.ClassInfo=ClassInfo;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassName!=null) ClassName.accept(visitor);
        if(ClassExtendsDecl!=null) ClassExtendsDecl.accept(visitor);
        if(ClassInfo!=null) ClassInfo.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(ClassExtendsDecl!=null) ClassExtendsDecl.traverseTopDown(visitor);
        if(ClassInfo!=null) ClassInfo.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(ClassExtendsDecl!=null) ClassExtendsDecl.traverseBottomUp(visitor);
        if(ClassInfo!=null) ClassInfo.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassExtendsDecl!=null)
            buffer.append(ClassExtendsDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassInfo!=null)
            buffer.append(ClassInfo.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
