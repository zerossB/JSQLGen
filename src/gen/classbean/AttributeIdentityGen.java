package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeIdentityGen extends AttributeGen{

    private project.AttributeIdentity att;
    
    public AttributeIdentityGen(project.Attribute att){
        this.att = (project.AttributeIdentity)att;
    }
    
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private Integer "+att.getName()+";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        "+att.getName()+" = null;\n";
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        code += "    /**\n";
        code += "     * @return "+att.getName()+"\n";
        code += "     */\n";
        code += "    public Integer get"+att.getNameU()+"() { return "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(Integer "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" - String "+att.getName()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(String "+att.getName()+") { this."+att.getName()+" = ("+att.getName()+".equals(\"null\") || "+att.getName()+".isEmpty())?null:Integer.parseInt("+att.getName()+"); }\n";
        return code;
    }
}
