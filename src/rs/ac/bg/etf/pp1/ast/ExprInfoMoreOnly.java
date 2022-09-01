// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ExprInfoMoreOnly extends ExprInfoMore {

    private ExprSign ExprSign;
    private ExprInfo ExprInfo;

    public ExprInfoMoreOnly (ExprSign ExprSign, ExprInfo ExprInfo) {
        this.ExprSign=ExprSign;
        if(ExprSign!=null) ExprSign.setParent(this);
        this.ExprInfo=ExprInfo;
        if(ExprInfo!=null) ExprInfo.setParent(this);
    }

    public ExprSign getExprSign() {
        return ExprSign;
    }

    public void setExprSign(ExprSign ExprSign) {
        this.ExprSign=ExprSign;
    }

    public ExprInfo getExprInfo() {
        return ExprInfo;
    }

    public void setExprInfo(ExprInfo ExprInfo) {
        this.ExprInfo=ExprInfo;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprSign!=null) ExprSign.accept(visitor);
        if(ExprInfo!=null) ExprInfo.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprSign!=null) ExprSign.traverseTopDown(visitor);
        if(ExprInfo!=null) ExprInfo.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprSign!=null) ExprSign.traverseBottomUp(visitor);
        if(ExprInfo!=null) ExprInfo.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprInfoMoreOnly(\n");

        if(ExprSign!=null)
            buffer.append(ExprSign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprInfo!=null)
            buffer.append(ExprInfo.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprInfoMoreOnly]");
        return buffer.toString();
    }
}
