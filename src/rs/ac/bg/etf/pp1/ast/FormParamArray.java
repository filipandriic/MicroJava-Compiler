// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class FormParamArray extends FormParamId {

    private String formName;

    public FormParamArray (String formName) {
        this.formName=formName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName=formName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParamArray(\n");

        buffer.append(" "+tab+formName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamArray]");
        return buffer.toString();
    }
}
