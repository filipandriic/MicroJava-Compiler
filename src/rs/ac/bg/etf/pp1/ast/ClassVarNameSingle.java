// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ClassVarNameSingle extends ClassVarNameList {

    private ClassVarNamePart ClassVarNamePart;

    public ClassVarNameSingle (ClassVarNamePart ClassVarNamePart) {
        this.ClassVarNamePart=ClassVarNamePart;
        if(ClassVarNamePart!=null) ClassVarNamePart.setParent(this);
    }

    public ClassVarNamePart getClassVarNamePart() {
        return ClassVarNamePart;
    }

    public void setClassVarNamePart(ClassVarNamePart ClassVarNamePart) {
        this.ClassVarNamePart=ClassVarNamePart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarNamePart!=null) ClassVarNamePart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarNamePart!=null) ClassVarNamePart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarNamePart!=null) ClassVarNamePart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarNameSingle(\n");

        if(ClassVarNamePart!=null)
            buffer.append(ClassVarNamePart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarNameSingle]");
        return buffer.toString();
    }
}
