package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeFixedListGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeFixedList att;
    
    public AttributeFixedListGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeFixedList)att;
    }
    
    @Override
    public String genCreateTable(){
        String code = "";
        switch(att.getTypeStore()){
            case INDEX:
                code += "                   + \""+att.getName()+" INT NOT NULL,\"\n";
                break;
            case ITEM:
                code += "                   + \""+att.getName()+" VARCHAR("+att.getMaxLength()+") NOT NULL,\"\n";
                break;
        }
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
        switch(att.getTypeStore()){
            case INDEX:
                code += "        statement.setInt("+idx+", "+varName+".get"+att.getNameU()+"());\n";
                break;
            case ITEM:
                code += "        statement.setString("+idx+", "+varName+".get"+att.getNameU()+"());\n";
                break;
        }
        return code;
    }
    @Override
    public String genRs2Obj(){
        String code = "";
        switch(att.getTypeStore()){
            case INDEX:
                code += "        "+classBean.getNameL()+"Load.set"+att.getNameU()+"(resultSet.getInt(\""+att.getName()+"\"));\n";
                break;
            case ITEM:
                code += "        "+classBean.getNameL()+"Load.set"+att.getNameU()+"(resultSet.getString(\""+att.getName()+"\"));\n";
                break;
        }
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
