package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeObjectListGen extends AttributeGen{

    private project.AttributeObjectList att;
    private project.Project proj;
    private project.ClassBean classRef;
    
    public AttributeObjectListGen(project.Attribute att, project.Project proj){
        this.att = (project.AttributeObjectList)att;
        this.proj = proj;
        this.classRef = this.proj.getClassBean(this.att.getClassRef());
    }
    
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private java.util.List<"+classRef.getPackage(proj)+"."+classRef.getName()+"> "+att.getName()+";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        "+att.getName()+" = new java.util.ArrayList<"+classRef.getPackage(proj)+"."+classRef.getName()+">();\n";
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        code += "    /**\n";
        code += "     * @return the "+att.getName()+"\n";
        code += "     */\n";
        code += "    public java.util.List<"+classRef.getPackage(proj)+"."+classRef.getName()+"> get"+att.getNameU()+"() { return "+att.getName()+"; }\n";
        code += "    /**\n";
        code += "     * @param "+att.getName()+" the "+att.getName()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(java.util.List<"+classRef.getPackage(proj)+"."+classRef.getName()+"> "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";
        return code;
    }
}
