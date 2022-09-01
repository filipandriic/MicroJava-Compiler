// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class VarNamesList extends VarNameList {

    private VarNameList VarNameList;
    private VarNamePart VarNamePart;

    public VarNamesList (VarNameList VarNameList, VarNamePart VarNamePart) {
        this.VarNameList=VarNameList;
        if(VarNameList!=null) VarNameList.setParent(this);
        this.VarNamePart=VarNamePart;
        if(VarNamePart!=null) VarNamePart.setParent(this);
    }

    public VarNameList getVarNameList() {
        return VarNameList;
    }

    public void setVarNameList(VarNameList VarNameList) {
        this.VarNameList=VarNameList;
    }

    public VarNamePart getVarNamePart() {
        return VarNamePart;
    }

    public void setVarNamePart(VarNamePart VarNamePart) {
        this.VarNamePart=VarNamePart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarNameList!=null) VarNameList.accept(visitor);
        if(VarNamePart!=null) VarNamePart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarNameList!=null) VarNameList.traverseTopDown(visitor);
        if(VarNamePart!=null) VarNamePart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarNameList!=null) VarNameList.traverseBottomUp(visitor);
        if(VarNamePart!=null) VarNamePart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarNamesList(\n");

        if(VarNameList!=null)
            buffer.append(VarNameList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarNamePart!=null)
            buffer.append(VarNamePart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarNamesList]");
        return buffer.toString();
    }
}
