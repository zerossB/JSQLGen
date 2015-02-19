package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeFixedListGen extends AttributeGen{

    private project.AttributeFixedList att;
    
    public AttributeFixedListGen(project.Attribute att){
        this.att = (project.AttributeFixedList)att;
    }
    
    public String genStaticList(){
        String code = "";
        java.util.StringTokenizer tokenList = new java.util.StringTokenizer(att.getListOptions(),"\n");
        code += "    public static String[] "+att.getName().toUpperCase()+" = {\n";
        while(tokenList.hasMoreTokens()){
            code += "        \""+tokenList.nextToken()+"\",\n";
        }
        code = code.substring(0, code.length()-2)+"\n";
        code += "    };\n";
        return code;
    }

    @Override
    public String genDeclaration(){
        String code = "";
        if(att.getTypeStore()==project.AttributeFixedList.TypeStore.INDEX){
            code += "    private Integer "+att.getName()+";\n";
        } else {
            code += "    private String "+att.getName()+";\n";
        }
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        if(att.getTypeStore()==project.AttributeFixedList.TypeStore.INDEX){
            code += "        "+att.getName()+" = 0;\n";
        } else {
            code += "        "+att.getName()+" = \"\";\n";
        }
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        if(att.getTypeStore()==project.AttributeFixedList.TypeStore.INDEX){
            code += "    /**\n";
            code += "     * @return the "+att.getName()+"\n";
            code += "     */\n";
            code += "    public Integer get"+att.getNameU()+"() { return "+att.getName()+"; }\n";

            code += "    /**\n";
            code += "     * @return the "+att.getName()+" Formatted\n";
            code += "     */\n";
            code += "    public String get"+att.getNameU()+"F() { return "+att.getName().toUpperCase()+"["+att.getName()+"]; }\n";

            code += "    /**\n";
            code += "     * @param "+att.getName()+" the "+att.getName()+" to set\n";
            code += "     */\n";
            code += "    public void set"+att.getNameU()+"(Integer "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";
        } else {
            code += "    /**\n";
            code += "     * @return the "+att.getName()+"\n";
            code += "     */\n";
            code += "    public String get"+att.getNameU()+"() { return "+att.getName()+"; }\n";

            code += "    /**\n";
            code += "     * @param "+att.getName()+" the "+att.getName()+" to set\n";
            code += "     */\n";
            code += "    public void set"+att.getNameU()+"(String "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";
        }
        return code;
    }
}
