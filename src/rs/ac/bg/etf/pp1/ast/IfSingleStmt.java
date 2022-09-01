// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class IfSingleStmt extends SingleStatement {

    private IfPartStatement IfPartStatement;
    private ElsePartStatement ElsePartStatement;

    public IfSingleStmt (IfPartStatement IfPartStatement, ElsePartStatement ElsePartStatement) {
        this.IfPartStatement=IfPartStatement;
        if(IfPartStatement!=null) IfPartStatement.setParent(this);
        this.ElsePartStatement=ElsePartStatement;
        if(ElsePartStatement!=null) ElsePartStatement.setParent(this);
    }

    public IfPartStatement getIfPartStatement() {
        return IfPartStatement;
    }

    public void setIfPartStatement(IfPartStatement IfPartStatement) {
        this.IfPartStatement=IfPartStatement;
    }

    public ElsePartStatement getElsePartStatement() {
        return ElsePartStatement;
    }

    public void setElsePartStatement(ElsePartStatement ElsePartStatement) {
        this.ElsePartStatement=ElsePartStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfPartStatement!=null) IfPartStatement.accept(visitor);
        if(ElsePartStatement!=null) ElsePartStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfPartStatement!=null) IfPartStatement.traverseTopDown(visitor);
        if(ElsePartStatement!=null) ElsePartStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfPartStatement!=null) IfPartStatement.traverseBottomUp(visitor);
        if(ElsePartStatement!=null) ElsePartStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfSingleStmt(\n");

        if(IfPartStatement!=null)
            buffer.append(IfPartStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElsePartStatement!=null)
            buffer.append(ElsePartStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfSingleStmt]");
        return buffer.toString();
    }
}
