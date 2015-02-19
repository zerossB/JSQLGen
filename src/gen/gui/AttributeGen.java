package gen.gui;

/**
 *
 * @author marcos
 */
public abstract class AttributeGen {
    public abstract String genObj2Form();
    public abstract String genForm2Obj();
    public abstract String genValidate();
    public abstract String genInstance();
    public abstract String genInitComponentPData();
    public abstract String genInitComponentTBData();
    public abstract String genDeclaration();
    
    public abstract String genXMLpData();
    public abstract String genXMLtbData();
    
    public abstract String genRefreshTab();
    public abstract String genModelTitle();
    public abstract String genModelType();
    public abstract String genModelEdit();

    public abstract String genXMLTabColumn();
    public abstract String genXMLTabModel();
}
