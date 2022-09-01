// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class DesingatorStatementAssignOpClass extends DesignatorStatement {

    private DesignatorStatementAssignOp DesignatorStatementAssignOp;

    public DesingatorStatementAssignOpClass (DesignatorStatementAssignOp DesignatorStatementAssignOp) {
        this.DesignatorStatementAssignOp=DesignatorStatementAssignOp;
        if(DesignatorStatementAssignOp!=null) DesignatorStatementAssignOp.setParent(this);
    }

    public DesignatorStatementAssignOp getDesignatorStatementAssignOp() {
        return DesignatorStatementAssignOp;
    }

    public void setDesignatorStatementAssignOp(DesignatorStatementAssignOp DesignatorStatementAssignOp) {
        this.DesignatorStatementAssignOp=DesignatorStatementAssignOp;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementAssignOp!=null) DesignatorStatementAssignOp.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementAssignOp!=null) DesignatorStatementAssignOp.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementAssignOp!=null) DesignatorStatementAssignOp.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesingatorStatementAssignOpClass(\n");

        if(DesignatorStatementAssignOp!=null)
            buffer.append(DesignatorStatementAssignOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesingatorStatementAssignOpClass]");
        return buffer.toString();
    }
}
