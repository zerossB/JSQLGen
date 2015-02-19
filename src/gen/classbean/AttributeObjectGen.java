package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeObjectGen extends AttributeGen{

    private project.AttributeObject att;
    private project.Project proj;
    private project.ClassBean classRef;
    
    public AttributeObjectGen(project.Attribute att, project.Project proj){
        this.att = (project.AttributeObject)att;
        this.proj = proj;
        this.classRef = this.proj.getClassBean(this.att.getClassRef());
    }
    
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private "+classRef.getPackage(proj)+"."+classRef.getName()+" "+att.getName()+";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        "+att.getName()+" = new "+classRef.getPackage(proj)+"."+classRef.getName()+"();\n";
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        code += "    /**\n";
        code += "     * @return "+att.getName()+"\n";
        code += "     */\n";
        code += "    public "+classRef.getPackage(proj)+"."+classRef.getName()+" get"+att.getNameU()+"() { return "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"("+classRef.getPackage(proj)+"."+classRef.getName()+" "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";
        return code;
    }
}
