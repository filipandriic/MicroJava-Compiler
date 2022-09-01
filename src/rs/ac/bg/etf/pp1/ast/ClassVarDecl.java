// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ClassVarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private ClassVarNameList ClassVarNameList;

    public ClassVarDecl (Type Type, ClassVarNameList ClassVarNameList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ClassVarNameList=ClassVarNameList;
        if(ClassVarNameList!=null) ClassVarNameList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ClassVarNameList getClassVarNameList() {
        return ClassVarNameList;
    }

    public void setClassVarNameList(ClassVarNameList ClassVarNameList) {
        this.ClassVarNameList=ClassVarNameList;
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
        if(Type!=null) Type.accept(visitor);
        if(ClassVarNameList!=null) ClassVarNameList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ClassVarNameList!=null) ClassVarNameList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ClassVarNameList!=null) ClassVarNameList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarNameList!=null)
            buffer.append(ClassVarNameList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarDecl]");
        return buffer.toString();
    }
}
