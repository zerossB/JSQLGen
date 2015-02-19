package gen.gui;

/**
 *
 * @author marcos
 */
public class AttributeFixedListGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeFixedList att;
    
    public AttributeFixedListGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeFixedList)att;
    }
    
    @Override
    public String genObj2Form(){
        String code = "";
        if(att.getTypeStore()==project.AttributeFixedList.TypeStore.INDEX)
            code += "        c"+att.getNameU()+".setSelectedIndex("+classBean.getNameL()+".get"+att.getNameU()+"());\n";
        else
            code += "        c"+att.getNameU()+".setSelectedItem("+classBean.getNameL()+".get"+att.getNameU()+"());\n";
        return code;
    }
    @Override
    public String genForm2Obj(){
        String code = "";
        if(att.getTypeStore()==project.AttributeFixedList.TypeStore.INDEX)
            code += "        "+classBean.getNameL()+".set"+att.getNameU()+"(c"+att.getNameU()+".getSelectedIndex());\n";
        else
            code += "        "+classBean.getNameL()+".set"+att.getNameU()+"(c"+att.getNameU()+".getSelectedItem().toString());\n";
        return code;
    }
    @Override
    public String genValidate(){
        String code = "";
        if(att.getTypeStore()==project.AttributeFixedList.TypeStore.INDEX)
            code += "        if(c"+att.getNameU()+".getSelectedIndex()==-1) fieldError +=\""+att.getNameU()+"\\n\";\n";
        else
            code += "        if(c"+att.getNameU()+".getSelectedItem()==null) fieldError +=\""+att.getNameU()+"\\n\";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        c"+att.getNameU()+" = new javax.swing.JComboBox("+classBean.getName()+"."+att.getName().toUpperCase()+");\n";
        return code;
    }
    @Override
    public String genInitComponentPData(){
        String code = "";
        code += "        c"+att.getNameU()+".setBorder(javax.swing.BorderFactory.createTitledBorder(\""+att.getNameU()+"\"));\n";
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
        code += "    private javax.swing.JComboBox c"+att.getNameU()+";\n";
        return code;
    }
    @Override
    public String genXMLpData(){
        String code = "";
        code += "        <Component class=\"javax.swing.JComboBox\" name=\"c"+att.getNameU()+"\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"border\" type=\"javax.swing.border.Border\" editor=\"org.netbeans.modules.form.editors2.BorderEditor\">\n";
        code += "              <Border info=\"org.netbeans.modules.form.compat2.border.TitledBorderInfo\">\n";
        code += "                <TitledBorder title=\""+att.getNameU()+"\"/>\n";
        code += "              </Border>\n";
        code += "            </Property>\n";
        code += "          </Properties>\n";
        code += "          <AuxValues>\n";
        code += "            <AuxValue name=\"JavaCodeGenerator_CreateCodeCustom\" type=\"java.lang.String\" value=\"new javax.swing.JComboBox("+classBean.getName()+"."+att.getName().toUpperCase()+");\"/>\n";
        code += "          </AuxValues>\n";
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
        if(att.getTypeStore()==project.AttributeFixedList.TypeStore.INDEX)
            code += "            line.add("+classBean.getNameL()+".get"+att.getNameU()+"F());\n";
        else
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
        code += "java.lang.String.class, ";
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
        code += "                <Column editable=\"false\" title=\""+att.getNameU()+"\" type=\"java.lang.String\"/>\n";
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
