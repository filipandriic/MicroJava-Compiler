// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class MultipleOptionalArgsList extends OptionalArgs {

    private OptionalArgs OptionalArgs;
    private OptArgs OptArgs;

    public MultipleOptionalArgsList (OptionalArgs OptionalArgs, OptArgs OptArgs) {
        this.OptionalArgs=OptionalArgs;
        if(OptionalArgs!=null) OptionalArgs.setParent(this);
        this.OptArgs=OptArgs;
        if(OptArgs!=null) OptArgs.setParent(this);
    }

    public OptionalArgs getOptionalArgs() {
        return OptionalArgs;
    }

    public void setOptionalArgs(OptionalArgs OptionalArgs) {
        this.OptionalArgs=OptionalArgs;
    }

    public OptArgs getOptArgs() {
        return OptArgs;
    }

    public void setOptArgs(OptArgs OptArgs) {
        this.OptArgs=OptArgs;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalArgs!=null) OptionalArgs.accept(visitor);
        if(OptArgs!=null) OptArgs.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalArgs!=null) OptionalArgs.traverseTopDown(visitor);
        if(OptArgs!=null) OptArgs.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalArgs!=null) OptionalArgs.traverseBottomUp(visitor);
        if(OptArgs!=null) OptArgs.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleOptionalArgsList(\n");

        if(OptionalArgs!=null)
            buffer.append(OptionalArgs.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptArgs!=null)
            buffer.append(OptArgs.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleOptionalArgsList]");
        return buffer.toString();
    }
}
