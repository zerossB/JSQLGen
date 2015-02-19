package gen.gui;

/**
 *
 * @author marcos
 */
public class AttributeObjectGen extends AttributeGen{

    private project.Project proj;
    private project.ClassBean classBean;
    private project.AttributeObject att;
    private project.ClassBean classRef;
    
    public AttributeObjectGen(project.Project proj, project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.proj = proj;
        this.att = (project.AttributeObject)att;
        this.classRef = this.proj.getClassBean(this.att.getClassRef());
    }
    
    @Override
    public String genObj2Form(){
        String code = "";
        code += "        c"+att.getNameU()+".setSelectedItem("+classBean.getNameL()+".get"+att.getNameU()+"().get"+att.getAttribLookUpU()+"());\n";
        return code;
    }
    @Override
    public String genForm2Obj(){
        String code = "";
        code += "        try{\n";
        if(classRef.getAttribute(att.getAttribLookUp()).getType() == project.Attribute.Type.STRING)
            code += "            "+classBean.getNameL()+".set"+att.getNameU()+"("+classRef.getPackage(proj)+"."+classRef.getName()+"DAO.loadBy"+att.getAttribLookUpU()+"(connection, c"+att.getNameU()+".getSelectedItem().toString()));\n";
        else
            code += "            "+classBean.getNameL()+".set"+att.getNameU()+"("+classRef.getPackage(proj)+"."+classRef.getName()+"DAO.loadBy"+att.getAttribLookUpU()+"(connection, Integer.parseInt(c"+att.getNameU()+".getSelectedItem().toString())));\n";
        code += "        } catch(java.sql.SQLException e){\n";
        code += "            javax.swing.JOptionPane.showMessageDialog(this, \"Falha na leitura do banco de dados!\\n\"+e.getMessage(),\"Aviso\",javax.swing.JOptionPane.ERROR_MESSAGE);\n";
        code += "        }\n";
        return code;
    }
    @Override
    public String genValidate(){
        String code = "";
        code += "        if(c"+att.getNameU()+".getSelectedItem()==null) fieldError +=\""+att.getNameU()+"\\n\";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        c"+att.getNameU()+" = new javax.swing.JComboBox("+classRef.getPackage(proj)+"."+classRef.getName()+"DAO.load"+att.getAttribLookUpU()+"List(connection).toArray());\n";
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
        code += "            <AuxValue name=\"JavaCodeGenerator_CreateCodeCustom\" type=\"java.lang.String\" value=\"new javax.swing.JComboBox("+classRef.getPackage(proj) +"."+classRef.getName()+"DAO.load"+att.getAttribLookUpU()+"List(connection).toArray());\"/>\n";
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
        code += "            line.add("+classBean.getNameL()+".get"+att.getNameU()+"().get"+att.getAttribLookUpU()+"());\n";
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
