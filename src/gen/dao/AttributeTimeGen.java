package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeTimeGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeTime att;
    
    public AttributeTimeGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeTime)att;
    }
    
    @Override
    public String genCreateTable(){
        String code = "";
        code += "                   + \""+att.getName()+" TIME NOT NULL,\"\n";
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
        code += "        statement.setTime("+idx+", new java.sql.Time("+varName+".get"+att.getNameU()+"().getTime()));\n";
        return code;
    }
    @Override
    public String genRs2Obj(){
        String code = "";
        code += "        "+classBean.getNameL()+"Load.set"+att.getNameU()+"(resultSet.getTime(\""+att.getName()+"\"));\n";
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
