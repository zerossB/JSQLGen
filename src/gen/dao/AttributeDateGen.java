package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeDateGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeDate att;
    
    public AttributeDateGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeDate)att;
    }
    
    @Override
    public String genCreateTable(){
        String code = "";
        code += "                   + \""+att.getName()+" DATE NOT NULL,\"\n";
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
        code += "        statement.setDate("+idx+", new java.sql.Date("+varName+".get"+att.getNameU()+"().getTime()));\n";
        return code;
    }
    @Override
    public String genRs2Obj(){
        String code = "";
        code += "        "+classBean.getNameL()+"Load.set"+att.getNameU()+"(resultSet.getDate(\""+att.getName()+"\"));\n";
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
