// generated with ast extension for cup
// version 0.8
// 26/7/2022 6:34:7


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodsDeclaration extends ClassMethodsDecl {

    private FirstClassMethod FirstClassMethod;
    private MethodDeclarationList MethodDeclarationList;

    public ClassMethodsDeclaration (FirstClassMethod FirstClassMethod, MethodDeclarationList MethodDeclarationList) {
        this.FirstClassMethod=FirstClassMethod;
        if(FirstClassMethod!=null) FirstClassMethod.setParent(this);
        this.MethodDeclarationList=MethodDeclarationList;
        if(MethodDeclarationList!=null) MethodDeclarationList.setParent(this);
    }

    public FirstClassMethod getFirstClassMethod() {
        return FirstClassMethod;
    }

    public void setFirstClassMethod(FirstClassMethod FirstClassMethod) {
        this.FirstClassMethod=FirstClassMethod;
    }

    public MethodDeclarationList getMethodDeclarationList() {
        return MethodDeclarationList;
    }

    public void setMethodDeclarationList(MethodDeclarationList MethodDeclarationList) {
        this.MethodDeclarationList=MethodDeclarationList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FirstClassMethod!=null) FirstClassMethod.accept(visitor);
        if(MethodDeclarationList!=null) MethodDeclarationList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FirstClassMethod!=null) FirstClassMethod.traverseTopDown(visitor);
        if(MethodDeclarationList!=null) MethodDeclarationList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FirstClassMethod!=null) FirstClassMethod.traverseBottomUp(visitor);
        if(MethodDeclarationList!=null) MethodDeclarationList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassMethodsDeclaration(\n");

        if(FirstClassMethod!=null)
            buffer.append(FirstClassMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclarationList!=null)
            buffer.append(MethodDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodsDeclaration]");
        return buffer.toString();
    }
}
