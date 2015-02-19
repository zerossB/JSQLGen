package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeBooleanGen extends AttributeGen{

    private project.AttributeBoolean att;
    
    public AttributeBooleanGen(project.Attribute att){
        this.att = (project.AttributeBoolean)att;
    }
    
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private Boolean "+att.getName()+";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        "+att.getName()+" = "+att.getInitialValue()+";\n";
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        code += "    /**\n";
        code += "     * @return "+att.getName()+"\n";
        code += "     */\n";
        code += "    public Boolean is"+att.getNameU()+"() { return "+att.getName()+"; }\n";
        code += "    public Boolean get"+att.getNameU()+"() { return "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(Boolean "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";
        return code;
    }
}
