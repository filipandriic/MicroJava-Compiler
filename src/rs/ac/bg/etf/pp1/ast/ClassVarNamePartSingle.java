// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ClassVarNamePartSingle extends ClassVarNamePart {

    private String classVarName;

    public ClassVarNamePartSingle (String classVarName) {
        this.classVarName=classVarName;
    }

    public String getClassVarName() {
        return classVarName;
    }

    public void setClassVarName(String classVarName) {
        this.classVarName=classVarName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarNamePartSingle(\n");

        buffer.append(" "+tab+classVarName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarNamePartSingle]");
        return buffer.toString();
    }
}
