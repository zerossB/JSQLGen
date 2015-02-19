package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeTimeGen extends AttributeGen{

    private project.AttributeTime att;
    
    public AttributeTimeGen(project.Attribute att){
        this.att = (project.AttributeTime)att;
    }
    
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private java.util.Date "+att.getName()+";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        if(att.isUseCurrentTime())
            code += "        "+att.getName()+" = new java.util.Date();\n";
        else
            code += "        set"+att.getNameU()+"F(\"HH:mm:ss\",\"00:00:00\");\n";
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        code += "    /**\n";
        code += "     * @return "+att.getNameU()+"\n";
        code += "     */\n";
        code += "    public java.util.Date get"+att.getNameU()+"() { return "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param pattern Formato de "+att.getNameU()+". Ex \"HH:mm:ss\" ou \"HH:mm\"\n";
        code += "     * @return "+att.getNameU()+" Formatado\n";
        code += "     */\n";
        code += "    public String get"+att.getNameU()+"F(String pattern) { return new java.text.SimpleDateFormat(pattern).format("+att.getName()+"); }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(java.util.Date "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param pattern Formato de "+att.getNameU()+". Ex \"HH:mm:ss\" ou \"HH:mm\"\n";
        code += "     * @param "+att.getName()+" - String "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"F(String pattern, String "+att.getName()+") { this."+att.getName()+" = new java.text.SimpleDateFormat(pattern).parse("+att.getName()+", new java.text.ParsePosition(0)); }\n";
        return code;
    }
}
