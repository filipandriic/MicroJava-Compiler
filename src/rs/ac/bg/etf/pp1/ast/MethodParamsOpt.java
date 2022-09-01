// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class MethodParamsOpt extends MethodPars {

    private OptionalArgs OptionalArgs;

    public MethodParamsOpt (OptionalArgs OptionalArgs) {
        this.OptionalArgs=OptionalArgs;
        if(OptionalArgs!=null) OptionalArgs.setParent(this);
    }

    public OptionalArgs getOptionalArgs() {
        return OptionalArgs;
    }

    public void setOptionalArgs(OptionalArgs OptionalArgs) {
        this.OptionalArgs=OptionalArgs;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalArgs!=null) OptionalArgs.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalArgs!=null) OptionalArgs.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalArgs!=null) OptionalArgs.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodParamsOpt(\n");

        if(OptionalArgs!=null)
            buffer.append(OptionalArgs.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodParamsOpt]");
        return buffer.toString();
    }
}
