package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeFloatGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeFloat att;
    
    public AttributeFloatGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeFloat)att;
    }
    
    @Override
    public String genCreateTable(){
        String code = "";
        code += "                   + \""+att.getName()+" FLOAT NOT NULL,\"\n";
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
        code += "        statement.setFloat("+idx+", "+varName+".get"+att.getNameU()+"());\n";
        return code;
    }
    @Override
    public String genRs2Obj(){
        String code = "";
        code += "        "+classBean.getNameL()+"Load.set"+att.getNameU()+"(resultSet.getFloat(\""+att.getName()+"\"));\n";
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
