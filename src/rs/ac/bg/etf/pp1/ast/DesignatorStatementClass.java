// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementClass extends DesignatorStatement {

    private DesignatorOperation DesignatorOperation;

    public DesignatorStatementClass (DesignatorOperation DesignatorOperation) {
        this.DesignatorOperation=DesignatorOperation;
        if(DesignatorOperation!=null) DesignatorOperation.setParent(this);
    }

    public DesignatorOperation getDesignatorOperation() {
        return DesignatorOperation;
    }

    public void setDesignatorOperation(DesignatorOperation DesignatorOperation) {
        this.DesignatorOperation=DesignatorOperation;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorOperation!=null) DesignatorOperation.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOperation!=null) DesignatorOperation.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOperation!=null) DesignatorOperation.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementClass(\n");

        if(DesignatorOperation!=null)
            buffer.append(DesignatorOperation.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementClass]");
        return buffer.toString();
    }
}
