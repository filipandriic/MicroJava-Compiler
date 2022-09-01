// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ClassVarNamesList extends ClassVarNameList {

    private ClassVarNameList ClassVarNameList;
    private ClassVarNamePart ClassVarNamePart;

    public ClassVarNamesList (ClassVarNameList ClassVarNameList, ClassVarNamePart ClassVarNamePart) {
        this.ClassVarNameList=ClassVarNameList;
        if(ClassVarNameList!=null) ClassVarNameList.setParent(this);
        this.ClassVarNamePart=ClassVarNamePart;
        if(ClassVarNamePart!=null) ClassVarNamePart.setParent(this);
    }

    public ClassVarNameList getClassVarNameList() {
        return ClassVarNameList;
    }

    public void setClassVarNameList(ClassVarNameList ClassVarNameList) {
        this.ClassVarNameList=ClassVarNameList;
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
        if(ClassVarNameList!=null) ClassVarNameList.accept(visitor);
        if(ClassVarNamePart!=null) ClassVarNamePart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarNameList!=null) ClassVarNameList.traverseTopDown(visitor);
        if(ClassVarNamePart!=null) ClassVarNamePart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarNameList!=null) ClassVarNameList.traverseBottomUp(visitor);
        if(ClassVarNamePart!=null) ClassVarNamePart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarNamesList(\n");

        if(ClassVarNameList!=null)
            buffer.append(ClassVarNameList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarNamePart!=null)
            buffer.append(ClassVarNamePart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarNamesList]");
        return buffer.toString();
    }
}
