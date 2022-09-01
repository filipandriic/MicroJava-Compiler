// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class WhileSingleStmt extends SingleStatement {

    private StartDoWhileLoop StartDoWhileLoop;
    private Statement Statement;
    private StartWhileCondition StartWhileCondition;
    private Condition Condition;

    public WhileSingleStmt (StartDoWhileLoop StartDoWhileLoop, Statement Statement, StartWhileCondition StartWhileCondition, Condition Condition) {
        this.StartDoWhileLoop=StartDoWhileLoop;
        if(StartDoWhileLoop!=null) StartDoWhileLoop.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.StartWhileCondition=StartWhileCondition;
        if(StartWhileCondition!=null) StartWhileCondition.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
    }

    public StartDoWhileLoop getStartDoWhileLoop() {
        return StartDoWhileLoop;
    }

    public void setStartDoWhileLoop(StartDoWhileLoop StartDoWhileLoop) {
        this.StartDoWhileLoop=StartDoWhileLoop;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public StartWhileCondition getStartWhileCondition() {
        return StartWhileCondition;
    }

    public void setStartWhileCondition(StartWhileCondition StartWhileCondition) {
        this.StartWhileCondition=StartWhileCondition;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StartDoWhileLoop!=null) StartDoWhileLoop.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(StartWhileCondition!=null) StartWhileCondition.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StartDoWhileLoop!=null) StartDoWhileLoop.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(StartWhileCondition!=null) StartWhileCondition.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StartDoWhileLoop!=null) StartDoWhileLoop.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(StartWhileCondition!=null) StartWhileCondition.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileSingleStmt(\n");

        if(StartDoWhileLoop!=null)
            buffer.append(StartDoWhileLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StartWhileCondition!=null)
            buffer.append(StartWhileCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileSingleStmt]");
        return buffer.toString();
    }
}
