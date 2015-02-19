package gen.gui;

/**
 *
 * @author marcos
 */
public class AttributeBooleanGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeBoolean att;
    
    public AttributeBooleanGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeBoolean)att;
    }
    
    @Override
    public String genObj2Form(){
        String code = "";
        code += "        c"+att.getNameU()+".setSelected("+classBean.getNameL()+".get"+att.getNameU()+"());\n";
        return code;
    }
    @Override
    public String genForm2Obj(){
        String code = "";
        code += "        "+classBean.getNameL()+".set"+att.getNameU()+"(c"+att.getNameU()+".isSelected());\n";
        return code;
    }
    @Override
    public String genValidate(){
        String code = "";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        c"+att.getNameU()+" = new javax.swing.JCheckBox();\n";
        return code;
    }
    @Override
    public String genInitComponentPData(){
        String code = "";
        code += "        c"+att.getNameU()+".setText(\""+att.getNameU()+"\");\n";
        code += "        pData.add(c"+att.getNameU()+");\n";
        return code;
    }
    @Override
    public String genInitComponentTBData(){
        String code = "";
        return code;
    }
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private javax.swing.JCheckBox c"+att.getNameU()+";\n";
        return code;
    }
    
    @Override
    public String genXMLpData(){
        String code = "";
        code += "        <Component class=\"javax.swing.JCheckBox\" name=\"c"+att.getNameU()+"\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"text\" type=\"java.lang.String\" value=\""+att.getNameU()+"\"/>\n";
        code += "          </Properties>\n";
        code += "        </Component>\n";
        return code;
    }
    @Override
    public String genXMLtbData(){
        String code = "";
        return code;
    }
    
    @Override
    public String genRefreshTab(){
        String code = "";
        code += "            line.add("+classBean.getNameL()+".get"+att.getNameU()+"());\n";
        return code;
    }
    @Override
    public String genModelTitle(){
        String code = "";
        code += "\""+att.getNameU()+"\", ";
        return code;
    }
    @Override
    public String genModelType(){
        String code = "";
        code += "java.lang.Boolean.class, ";
        return code;
    }
    @Override
    public String genModelEdit(){
        String code = "";
        code += "false, ";
        return code;
    }

    @Override
    public String genXMLTabColumn(){
        String code = "";
        code += "                <Column editable=\"false\" title=\""+att.getNameU()+"\" type=\"java.lang.Boolean\"/>\n";
        return code;
    }
    @Override
    public String genXMLTabModel(){
        String code = "";
        code += "                <Column maxWidth=\"-1\" minWidth=\"-1\" prefWidth=\"-1\" resizable=\"true\">\n";
        code += "                  <Title/>\n";
        code += "                  <Editor/>\n";
        code += "                  <Renderer/>\n";
        code += "                </Column>\n";
        return code;
    }

}
