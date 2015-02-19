package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeFloatGen extends AttributeGen{

    private project.AttributeFloat att;
    
    public AttributeFloatGen(project.Attribute att){
        this.att = (project.AttributeFloat)att;
    }
    
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private Float "+att.getName()+";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        "+att.getName()+" = "+att.getStartValue()+"f;\n";
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        code += "    /**\n";
        code += "     * @return the "+att.getName()+"\n";
        code += "     */\n";
        code += "    public Float get"+att.getNameU()+"() { return "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @return "+att.getName()+" Formatado\n";
        code += "     */\n";
        code += "    public String get"+att.getNameU()+"F() { return new java.text.DecimalFormat(\""+att.getMaskFormat()+"\").format("+att.getName()+"); }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" the "+att.getName()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(Float "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" - String "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"F(String "+att.getName()+") { this."+att.getName()+" = new java.text.DecimalFormat(\""+att.getMaskFormat()+"\").parse("+att.getName()+",new java.text.ParsePosition(0)).floatValue(); }\n";
        return code;
    }
}
