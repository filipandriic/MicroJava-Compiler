// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ElsePartStmt extends ElsePartStatement {

    private StartElseLoop StartElseLoop;
    private Statement Statement;

    public ElsePartStmt (StartElseLoop StartElseLoop, Statement Statement) {
        this.StartElseLoop=StartElseLoop;
        if(StartElseLoop!=null) StartElseLoop.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public StartElseLoop getStartElseLoop() {
        return StartElseLoop;
    }

    public void setStartElseLoop(StartElseLoop StartElseLoop) {
        this.StartElseLoop=StartElseLoop;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StartElseLoop!=null) StartElseLoop.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StartElseLoop!=null) StartElseLoop.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StartElseLoop!=null) StartElseLoop.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ElsePartStmt(\n");

        if(StartElseLoop!=null)
            buffer.append(StartElseLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ElsePartStmt]");
        return buffer.toString();
    }
}
