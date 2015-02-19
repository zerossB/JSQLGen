package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeObjectGen extends AttributeGen{

    private project.Project proj;
    private project.ClassBean classBean;
    private project.AttributeObject att;
    
    public AttributeObjectGen(project.Project proj, project.ClassBean classBean, project.Attribute att){
        this.proj = proj;
        this.classBean = classBean;
        this.att = (project.AttributeObject)att;
    }
    
    @Override
    public String genCreateTable(){
        String code = "";
        project.ClassBean classRef = proj.getClassBean(att.getClassRef());
        project.Attribute attRef = classRef.getPrimaryKey();
        if(attRef.getType() == project.Attribute.Type.STRING){
            project.AttributeString attString = (project.AttributeString)classRef.getPrimaryKey();
            code += "                   + \""+att.getName()+" VARCHAR("+attString.getLength()+") NOT NULL,\"\n";
        } else{
            code += "                   + \""+att.getName()+" INT NOT NULL,\"\n";
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
        project.ClassBean classRef = proj.getClassBean(att.getClassRef());
        project.Attribute attRef = classRef.getPrimaryKey();
        if(attRef.getType() == project.Attribute.Type.STRING){
            code += "        statement.setString("+idx+", "+varName+".get"+att.getNameU()+"().get"+proj.getClassBean(att.getClassRef()).getPrimaryKey().getNameU()+"());\n";
        } else {
            code += "        statement.setInt("+idx+", "+varName+".get"+att.getNameU()+"().get"+proj.getClassBean(att.getClassRef()).getPrimaryKey().getNameU()+"());\n";
        }
        return code;
    }
    @Override
    public String genRs2Obj(){
        project.ClassBean classRef = proj.getClassBean(att.getClassRef());
        project.Attribute attRef = classRef.getPrimaryKey();
        String code = "";
        code += "        try {\n";
        if(attRef.getType() == project.Attribute.Type.STRING){
            code += "            "+classBean.getNameL()+"Load.set"+att.getNameU()+"("+classRef.getPackage(proj)+"."+classRef.getName()+"DAO.loadBy"+classRef.getPrimaryKey().getNameU()+"(connection, resultSet.getString(\""+att.getName()+"\")));\n";
        } else {
            code += "            "+classBean.getNameL()+"Load.set"+att.getNameU()+"("+classRef.getPackage(proj)+"."+classRef.getName()+"DAO.loadBy"+classRef.getPrimaryKey().getNameU()+"(connection, resultSet.getInt(\""+att.getName()+"\")));\n";
        }
            

        code += "        } catch (java.sql.SQLException e) {\n";
        code += "            "+classBean.getNameL()+"Load.set"+att.getNameU()+"(new "+classRef.getPackage(proj)+"."+classRef.getName()+"());\n";
        code += "        }\n";
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
        code += att.getClassRef()+"."+att.getAttribLookUp()+",";
        return code;
    }
    
    
}
