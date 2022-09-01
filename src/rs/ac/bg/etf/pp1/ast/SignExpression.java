// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class SignExpression extends Expr {

    private ExprInfoMore ExprInfoMore;

    public SignExpression (ExprInfoMore ExprInfoMore) {
        this.ExprInfoMore=ExprInfoMore;
        if(ExprInfoMore!=null) ExprInfoMore.setParent(this);
    }

    public ExprInfoMore getExprInfoMore() {
        return ExprInfoMore;
    }

    public void setExprInfoMore(ExprInfoMore ExprInfoMore) {
        this.ExprInfoMore=ExprInfoMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprInfoMore!=null) ExprInfoMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprInfoMore!=null) ExprInfoMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprInfoMore!=null) ExprInfoMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SignExpression(\n");

        if(ExprInfoMore!=null)
            buffer.append(ExprInfoMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SignExpression]");
        return buffer.toString();
    }
}
