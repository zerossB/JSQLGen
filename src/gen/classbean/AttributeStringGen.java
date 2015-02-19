package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeStringGen extends AttributeGen{

    private project.AttributeString att;
    
    public AttributeStringGen(project.Attribute att){
        this.att = (project.AttributeString)att;
    }
    
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private String "+att.getName()+";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        if(att.isUnique())
            code += "        "+att.getName()+" = null;\n";
        else 
            code += "        "+att.getName()+" = \""+att.getStartValue()+"\";\n";
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        code += "    /**\n";
        code += "     * @return "+att.getName()+"\n";
        code += "     */\n";
        code += "    public String get"+att.getNameU()+"() { return "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" "+att.getNameU()+" to set\n";
        code += "     */\n";
        //code += "    public void set"+att.getNameU()+"(String "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";
        code += "    public void set"+att.getNameU()+"(String "+att.getName()+") { this."+att.getName()+" = ("+att.getName()+".length()>"+att.getLength()+"?"+att.getName()+".substring(0,"+att.getLength()+"):"+att.getName()+").replace('\\\'','`'); }\n\n";
        return code;
    }
}
