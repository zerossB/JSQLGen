package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeIdentityGen extends AttributeGen{

    protected project.ClassBean classBean;
    protected project.AttributeIdentity att;

    public AttributeIdentityGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeIdentity)att;
    }

    @Override
    public String genCreateTable(){
        String code = "";
        code += "                   + \""+att.getName()+" INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH "+att.getInitialValue()+", INCREMENT BY "+att.getIncrementValue()+"),\"\n";
        return code;
    }
    @Override
    public String genInsert(){
        String code = "";
        return code;
    }
    @Override
    public String genUpdate(){
        String code = "";
        return code;
    }
    @Override
    public String genStatementSet(int idx, String varName){
        String code = "";
        return code;
    }
    @Override
    public String genRs2Obj(){
        String code = "";
        code += "        "+classBean.getNameL()+"Load.set"+att.getNameU()+"(resultSet.getInt(\""+att.getName()+"\"));\n";
        return code;
    }
    @Override
    public String genSelect(){
        String code = "";
        code += att.getName()+",";
        return code;
    }
    @Override
    public String genView(){
        String code = "";
        code +=  classBean.getName()+"."+att.getName()+",";
        return code;
    }
}
