// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class VarNameSingle extends VarNameList {

    private VarNamePart VarNamePart;

    public VarNameSingle (VarNamePart VarNamePart) {
        this.VarNamePart=VarNamePart;
        if(VarNamePart!=null) VarNamePart.setParent(this);
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
        if(VarNamePart!=null) VarNamePart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarNamePart!=null) VarNamePart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarNamePart!=null) VarNamePart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarNameSingle(\n");

        if(VarNamePart!=null)
            buffer.append(VarNamePart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarNameSingle]");
        return buffer.toString();
    }
}
