// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class NullischoExpression extends Expr {

    private ExprInfoMore ExprInfoMore;
    private ExprInfoMore ExprInfoMore1;

    public NullischoExpression (ExprInfoMore ExprInfoMore, ExprInfoMore ExprInfoMore1) {
        this.ExprInfoMore=ExprInfoMore;
        if(ExprInfoMore!=null) ExprInfoMore.setParent(this);
        this.ExprInfoMore1=ExprInfoMore1;
        if(ExprInfoMore1!=null) ExprInfoMore1.setParent(this);
    }

    public ExprInfoMore getExprInfoMore() {
        return ExprInfoMore;
    }

    public void setExprInfoMore(ExprInfoMore ExprInfoMore) {
        this.ExprInfoMore=ExprInfoMore;
    }

    public ExprInfoMore getExprInfoMore1() {
        return ExprInfoMore1;
    }

    public void setExprInfoMore1(ExprInfoMore ExprInfoMore1) {
        this.ExprInfoMore1=ExprInfoMore1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprInfoMore!=null) ExprInfoMore.accept(visitor);
        if(ExprInfoMore1!=null) ExprInfoMore1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprInfoMore!=null) ExprInfoMore.traverseTopDown(visitor);
        if(ExprInfoMore1!=null) ExprInfoMore1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprInfoMore!=null) ExprInfoMore.traverseBottomUp(visitor);
        if(ExprInfoMore1!=null) ExprInfoMore1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NullischoExpression(\n");

        if(ExprInfoMore!=null)
            buffer.append(ExprInfoMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprInfoMore1!=null)
            buffer.append(ExprInfoMore1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NullischoExpression]");
        return buffer.toString();
    }
}
