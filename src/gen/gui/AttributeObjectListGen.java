package gen.gui;

/**
 *
 * @author marcos
 */
public class AttributeObjectListGen extends AttributeGen{

    private project.Project proj;
    private project.ClassBean classBean;
    private project.AttributeObjectList att;
    private project.ClassBean classRef;
    
    public AttributeObjectListGen(project.Project proj, project.ClassBean classBean, project.Attribute att){
        this.proj = proj;
        this.classBean = classBean;
        this.att = (project.AttributeObjectList)att;
        this.classRef = this.proj.getClassBean(this.att.getClassRef());
    }
    
    @Override
    public String genObj2Form(){
        String code = "";
        return code;
    }
    @Override
    public String genForm2Obj(){
        String code = "";
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
        code += "        p"+att.getNameU()+" = new "+classRef.getPackage(proj) +"."+classRef.getName()+"Tab(this, connection, "+classBean.getNameL()+");\n";
        return code;
    }
    @Override
    public String genInitComponentPData(){
        String code = "";
        return code;
    }
    @Override
    public String genInitComponentTBData(){
        String code = "";
        code += "        tbData.addTab(\""+att.getNameU()+"\", new javax.swing.ImageIcon(getClass().getResource(\"/toolbox/images/stock_data-table-16.png\")), p"+att.getNameU()+"); // NOI18N\n";
        return code;
    }
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private javax.swing.JPanel p"+att.getNameU()+";\n";
        return code;
    }
    @Override
    public String genXMLpData(){
        String code = "";
        return code;
    }
    @Override
    public String genXMLtbData(){
        String code = "";
        code += "        <Container class=\"javax.swing.JPanel\" name=\"p"+att.getNameU()+"\">\n";
        code += "          <AuxValues>\n";
        code += "            <AuxValue name=\"JavaCodeGenerator_CreateCodeCustom\" type=\"java.lang.String\" value=\"new "+classRef.getPackage(proj) +"."+classRef.getName()+"Tab(this, connection, "+classBean.getNameL()+");\"/>\n";
        code += "          </AuxValues>\n";
        code += "          <Constraints>\n";
        code += "            <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout\" value=\"org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout$JTabbedPaneConstraintsDescription\">\n";
        code += "              <JTabbedPaneConstraints tabName=\""+att.getNameU()+"\">\n";
        code += "                <Property name=\"tabTitle\" type=\"java.lang.String\" value=\""+att.getNameU()+"\"/>\n";
        code += "                <Property name=\"tabIcon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "                  <Image iconType=\"3\" name=\"/toolbox/images/stock_data-table-16.png\"/>\n";
        code += "                </Property>\n";
        code += "              </JTabbedPaneConstraints>\n";
        code += "            </Constraint>\n";
        code += "          </Constraints>\n";
        code += "          <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignFlowLayout\"/>\n";
        code += "        </Container>\n";
        return code;
    }
    @Override
    public String genRefreshTab(){
        String code = "";
        return code;
    }
    @Override
    public String genModelTitle(){
        String code = "";
        return code;
    }
    @Override
    public String genModelType(){
        String code = "";
        return code;
    }
    @Override
    public String genModelEdit(){
        String code = "";
        return code;
    }

    @Override
    public String genXMLTabColumn(){
        String code = "";
        return code;
    }
    @Override
    public String genXMLTabModel(){
        String code = "";
        return code;
    }
}
