package gen.gui;

/**
 *
 * @author marcos
 */
public class AttributeFloatGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeFloat att;

    public AttributeFloatGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeFloat)att;
    }

    @Override
    public String genObj2Form(){
        String code = "";
        code += "        t"+att.getNameU()+".setText("+classBean.getNameL()+".get"+att.getNameU()+"F());\n";
        return code;
    }
    @Override
    public String genForm2Obj(){
        String code = "";
        code += "        "+classBean.getNameL()+".set"+att.getNameU()+"F(t"+att.getNameU()+".getText());\n";
        return code;
    }
    @Override
    public String genValidate(){
        String code = "";
        code += "        if(new java.text.DecimalFormat(\""+att.getMaskFormat()+"\").parse(t"+att.getNameU()+".getText(),new java.text.ParsePosition(0)).floatValue()<0) fieldError +=\""+att.getNameU()+"\\n\";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        t"+att.getNameU()+" = new javax.swing.JFormattedTextField();\n";
        return code;
    }
    @Override
    public String genInitComponentPData(){
        String code = "";
        code += "        t"+att.getNameU()+".setBorder(javax.swing.BorderFactory.createTitledBorder(\""+att.getNameU()+"\"));\n";
        code += "        t"+att.getNameU()+".setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(\""+att.getMaskFormat()+"\"))));\n";
        code += "        t"+att.getNameU()+".setHorizontalAlignment(javax.swing.JTextField.TRAILING);\n";
        code += "        pData.add(t"+att.getNameU()+");\n";
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
        code += "    private javax.swing.JFormattedTextField t"+att.getNameU()+";\n";
        return code;
    }

    @Override
    public String genXMLpData(){
        String code = "";
        code += "        <Component class=\"javax.swing.JFormattedTextField\" name=\"t"+att.getNameU()+"\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"border\" type=\"javax.swing.border.Border\" editor=\"org.netbeans.modules.form.editors2.BorderEditor\">\n";
        code += "              <Border info=\"org.netbeans.modules.form.compat2.border.TitledBorderInfo\">\n";
        code += "                <TitledBorder title=\""+att.getNameU()+"\"/>\n";
        code += "              </Border>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"formatterFactory\" type=\"javax.swing.JFormattedTextField$AbstractFormatterFactory\" editor=\"org.netbeans.modules.form.editors.AbstractFormatterFactoryEditor\">\n";
        code += "              <Format format=\""+att.getMaskFormat()+"\" subtype=\"-1\" type=\"0\"/>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"horizontalAlignment\" type=\"int\" value=\"11\"/>\n";
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
        code += "            line.add("+classBean.getNameL()+".get"+att.getNameU()+"F());\n";
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
        code += "java.lang.Integer.class, ";
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
        code += "                <Column editable=\"false\" title=\""+att.getNameU()+"\" type=\"java.lang.Integer\"/>\n";
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
