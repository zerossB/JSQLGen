package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeDateGen extends AttributeGen{

    private project.AttributeDate att;
    
    public AttributeDateGen(project.Attribute att){
        this.att = (project.AttributeDate)att;
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
        if(att.isUseCurrentDate())
            code += "        "+att.getName()+" = new java.util.Date();\n";
        else
            code += "        set"+att.getNameU()+"F(\"dd/MM/yyyy\",\"01/01/0001\");\n";

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
        code += "     * @param pattern Formato de "+att.getNameU()+". Ex \"yyyy-MM-dd\" ou \"dd/MM/yyyy\"\n";
        code += "     * @return "+att.getNameU()+" Formatado\n";
        code += "     */\n";
        code += "    public String get"+att.getNameU()+"F(String pattern) { return new java.text.SimpleDateFormat(pattern).format("+att.getName()+"); }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(java.util.Date "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param pattern Formato de "+att.getNameU()+". Ex \"yyyy-MM-dd\" ou \"dd/MM/yyyy\"\n";
        code += "     * @param "+att.getName()+" - String "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"F(String pattern, String "+att.getName()+") { this."+att.getName()+" = new java.text.SimpleDateFormat(pattern).parse("+att.getName()+", new java.text.ParsePosition(0)); }\n";
        return code;
    }
}
