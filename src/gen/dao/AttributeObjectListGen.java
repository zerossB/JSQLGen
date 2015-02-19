package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeObjectListGen extends AttributeGen{

    private project.Project proj;
    private project.ClassBean classBean;
    private project.AttributeObjectList att;
    
    public AttributeObjectListGen(project.Project proj, project.ClassBean classBean, project.Attribute att){
        this.proj = proj;
        this.classBean = classBean;
        this.att = (project.AttributeObjectList)att;
    }
    
    @Override
    public String genCreateTable(){
        String code = "";
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
        project.ClassBean classRef = proj.getClassBean(att.getClassRef());
        code += "        "+classBean.getNameL()+"Load.set"+att.getNameU()+"("+classRef.getPackage(proj) +"."+classRef.getName()+"DAO.loadList(connection, "+classBean.getNameL()+"Load));\n";
        return code;
    }
    @Override
    public String genSelect(){
        String code = "";
        return code;
    }
    @Override
    public String genView(){
        String code = "";
        return code;
    }
}
