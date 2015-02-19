package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeIntegerGen extends AttributeGen{

    private project.AttributeInteger att;
    
    public AttributeIntegerGen(project.Attribute att){
        this.att = (project.AttributeInteger)att;
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
        if(att.isUnique())
            code += "        "+att.getName()+" = null;\n";
        else
            code += "        "+att.getName()+" = "+att.getStartValue()+";\n";
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
        code += "    public void set"+att.getNameU()+"(String "+att.getName()+") { this."+att.getName()+" = Integer.parseInt("+att.getName()+"); }\n";
        return code;
    }
}
