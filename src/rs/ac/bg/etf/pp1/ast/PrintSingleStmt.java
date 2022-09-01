// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class PrintSingleStmt extends SingleStatement {

    private PrintSingleStatement PrintSingleStatement;

    public PrintSingleStmt (PrintSingleStatement PrintSingleStatement) {
        this.PrintSingleStatement=PrintSingleStatement;
        if(PrintSingleStatement!=null) PrintSingleStatement.setParent(this);
    }

    public PrintSingleStatement getPrintSingleStatement() {
        return PrintSingleStatement;
    }

    public void setPrintSingleStatement(PrintSingleStatement PrintSingleStatement) {
        this.PrintSingleStatement=PrintSingleStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(PrintSingleStatement!=null) PrintSingleStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PrintSingleStatement!=null) PrintSingleStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PrintSingleStatement!=null) PrintSingleStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintSingleStmt(\n");

        if(PrintSingleStatement!=null)
            buffer.append(PrintSingleStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintSingleStmt]");
        return buffer.toString();
    }
}
