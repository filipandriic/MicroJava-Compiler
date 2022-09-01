// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ConstNames extends ConstNameList {

    private ConstNameList ConstNameList;
    private ConstNamePart ConstNamePart;

    public ConstNames (ConstNameList ConstNameList, ConstNamePart ConstNamePart) {
        this.ConstNameList=ConstNameList;
        if(ConstNameList!=null) ConstNameList.setParent(this);
        this.ConstNamePart=ConstNamePart;
        if(ConstNamePart!=null) ConstNamePart.setParent(this);
    }

    public ConstNameList getConstNameList() {
        return ConstNameList;
    }

    public void setConstNameList(ConstNameList ConstNameList) {
        this.ConstNameList=ConstNameList;
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
        if(ConstNameList!=null) ConstNameList.accept(visitor);
        if(ConstNamePart!=null) ConstNamePart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstNameList!=null) ConstNameList.traverseTopDown(visitor);
        if(ConstNamePart!=null) ConstNamePart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstNameList!=null) ConstNameList.traverseBottomUp(visitor);
        if(ConstNamePart!=null) ConstNamePart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstNames(\n");

        if(ConstNameList!=null)
            buffer.append(ConstNameList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstNamePart!=null)
            buffer.append(ConstNamePart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstNames]");
        return buffer.toString();
    }
}
