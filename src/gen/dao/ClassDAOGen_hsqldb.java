package gen.dao;

/**
 *
 * @author marcos
 */
public class ClassDAOGen_hsqldb extends ClassDAOGen {

    /**
     * Construtor
     * @param proj
     * @param classBean
     */
    public ClassDAOGen_hsqldb(project.Project proj, project.ClassBean classBean){
        super(proj, classBean);
    }

    @Override
    public String getJavaCode(){
        String code = super.getJavaCode();
        code = code.replace("SELECT IDENTITY_VAL_LOCAL() FROM "+classBean.getName()+"", "CALL IDENTITY()");
        java.util.List<project.Attribute> attsID = classBean.getAttributesOfType(project.Attribute.Type.IDENTITY);
        for(project.Attribute attID : attsID){
            project.AttributeIdentity att = (project.AttributeIdentity)attID;
            code = code.replace(""+att.getName()+" INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH "+att.getInitialValue()+", INCREMENT BY "+att.getIncrementValue()+"),", ""+att.getName()+" IDENTITY,");
            code = code.replace(",\"\n                   + \"CONSTRAINT PK_"+classBean.getName()+" PRIMARY KEY ("+att.getName()+")", "");
        }
        return code;
    }
}
