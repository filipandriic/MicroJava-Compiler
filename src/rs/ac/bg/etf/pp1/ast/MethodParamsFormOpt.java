// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class MethodParamsFormOpt extends MethodPars {

    private FormPars FormPars;
    private OptionalArgs OptionalArgs;

    public MethodParamsFormOpt (FormPars FormPars, OptionalArgs OptionalArgs) {
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.OptionalArgs=OptionalArgs;
        if(OptionalArgs!=null) OptionalArgs.setParent(this);
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
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
        if(FormPars!=null) FormPars.accept(visitor);
        if(OptionalArgs!=null) OptionalArgs.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(OptionalArgs!=null) OptionalArgs.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(OptionalArgs!=null) OptionalArgs.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodParamsFormOpt(\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalArgs!=null)
            buffer.append(OptionalArgs.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodParamsFormOpt]");
        return buffer.toString();
    }
}
