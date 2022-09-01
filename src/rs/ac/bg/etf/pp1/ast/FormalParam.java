// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class FormalParam extends FormParam {

    private Type Type;
    private FormParamId FormParamId;

    public FormalParam (Type Type, FormParamId FormParamId) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FormParamId=FormParamId;
        if(FormParamId!=null) FormParamId.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public FormParamId getFormParamId() {
        return FormParamId;
    }

    public void setFormParamId(FormParamId FormParamId) {
        this.FormParamId=FormParamId;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(FormParamId!=null) FormParamId.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FormParamId!=null) FormParamId.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FormParamId!=null) FormParamId.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParam(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParamId!=null)
            buffer.append(FormParamId.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParam]");
        return buffer.toString();
    }
}
