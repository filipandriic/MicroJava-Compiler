// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ConstNameSingle extends ConstNameList {

    private ConstNamePart ConstNamePart;

    public ConstNameSingle (ConstNamePart ConstNamePart) {
        this.ConstNamePart=ConstNamePart;
        if(ConstNamePart!=null) ConstNamePart.setParent(this);
    }

    public ConstNamePart getConstNamePart() {
        return ConstNamePart;
    }

    public void setConstNamePart(ConstNamePart ConstNamePart) {
        this.ConstNamePart=ConstNamePart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstNamePart!=null) ConstNamePart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstNamePart!=null) ConstNamePart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstNamePart!=null) ConstNamePart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstNameSingle(\n");

        if(ConstNamePart!=null)
            buffer.append(ConstNamePart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstNameSingle]");
        return buffer.toString();
    }
}
