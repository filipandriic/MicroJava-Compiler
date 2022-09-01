// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ProgramVarDeclarations extends ProgramVarDeclarationList {

    private ProgramVarDeclarationList ProgramVarDeclarationList;
    private ProgramVarDeclarationPart ProgramVarDeclarationPart;

    public ProgramVarDeclarations (ProgramVarDeclarationList ProgramVarDeclarationList, ProgramVarDeclarationPart ProgramVarDeclarationPart) {
        this.ProgramVarDeclarationList=ProgramVarDeclarationList;
        if(ProgramVarDeclarationList!=null) ProgramVarDeclarationList.setParent(this);
        this.ProgramVarDeclarationPart=ProgramVarDeclarationPart;
        if(ProgramVarDeclarationPart!=null) ProgramVarDeclarationPart.setParent(this);
    }

    public ProgramVarDeclarationList getProgramVarDeclarationList() {
        return ProgramVarDeclarationList;
    }

    public void setProgramVarDeclarationList(ProgramVarDeclarationList ProgramVarDeclarationList) {
        this.ProgramVarDeclarationList=ProgramVarDeclarationList;
    }

    public ProgramVarDeclarationPart getProgramVarDeclarationPart() {
        return ProgramVarDeclarationPart;
    }

    public void setProgramVarDeclarationPart(ProgramVarDeclarationPart ProgramVarDeclarationPart) {
        this.ProgramVarDeclarationPart=ProgramVarDeclarationPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgramVarDeclarationList!=null) ProgramVarDeclarationList.accept(visitor);
        if(ProgramVarDeclarationPart!=null) ProgramVarDeclarationPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramVarDeclarationList!=null) ProgramVarDeclarationList.traverseTopDown(visitor);
        if(ProgramVarDeclarationPart!=null) ProgramVarDeclarationPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramVarDeclarationList!=null) ProgramVarDeclarationList.traverseBottomUp(visitor);
        if(ProgramVarDeclarationPart!=null) ProgramVarDeclarationPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramVarDeclarations(\n");

        if(ProgramVarDeclarationList!=null)
            buffer.append(ProgramVarDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgramVarDeclarationPart!=null)
            buffer.append(ProgramVarDeclarationPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramVarDeclarations]");
        return buffer.toString();
    }
}
