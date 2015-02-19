package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeBooleanGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeBoolean att;
    
    public AttributeBooleanGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeBoolean)att;
    }
    
    @Override
    public String genCreateTable(){
        String code = "";
        code += "                   + \""+att.getName()+" SMALLINT NOT NULL,\"\n";
        return code;
    }
    @Override
    public String genInsert(){
        String code = "";
        code += att.getName()+",";
        return code;
    }
    @Override
    public String genUpdate(){
        String code = "";
        code += "                   + \""+att.getName()+" = ?,\"\n";
        return code;
    }
    @Override
    public String genStatementSet(int idx, String varName){
        String code = "";
        code += "        statement.setBoolean("+idx+", "+varName+".get"+att.getNameU()+"());\n";
        return code;
    }
    @Override
    public String genRs2Obj(){
        String code = "";
        code += "        "+classBean.getNameL()+"Load.set"+att.getNameU()+"(resultSet.getBoolean(\""+att.getName()+"\"));\n";
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
