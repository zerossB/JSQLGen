package gen.gui;

/**
 *
 * @author marcos
 */
public class XMLFormGen {

    private project.Project proj;
    private project.ClassBean classBean;

    /**
     * Construtor
     * @param proj
     * @param classBean 
     */
    public XMLFormGen(project.Project proj, project.ClassBean classBean){
        this.proj = proj;
        this.classBean = classBean;
    }
    
    /**
     * 
     * @param att
     * @return 
     */
    private AttributeGen getAttributeGen(project.Attribute att){
        switch(att.getType()){
            case IDENTITY    : return new AttributeIdentityGen(classBean, att);
            case INTEGER     : return new AttributeIntegerGen(classBean, att);
            case FLOAT       : return new AttributeFloatGen(classBean, att);
            case BOOLEAN     : return new AttributeBooleanGen(classBean, att);
            case STRING      : return new AttributeStringGen(classBean, att);
            case DATE        : return new AttributeDateGen(classBean, att);
            case TIME        : return new AttributeTimeGen(classBean, att);
            case IMAGE       : return new AttributeImageGen(classBean, att);
            case FIXED_LIST  : return new AttributeFixedListGen(classBean, att);
            case OBJECT      : return new AttributeObjectGen(proj, classBean, att);
            case OBJECT_LIST : return new AttributeObjectListGen(proj, classBean, att);
            default: return null;
        }
    }
    
    public String getXMLCode(){
        String code = "";
        code += "<?xml version=\"1.1\" encoding=\"UTF-8\" ?>\n";
        code += "<Form version=\"1.5\" maxVersion=\"1.7\" type=\"org.netbeans.modules.form.forminfo.JDialogFormInfo\">\n";
        code += "  <Properties>\n";
        code += "    <Property name=\"defaultCloseOperation\" type=\"int\" value=\"2\"/>\n";
        code += "    <Property name=\"title\" type=\"java.lang.String\" value=\"Formul&#xe1;rio de "+classBean.getName()+"\"/>\n";
        code += "    <Property name=\"modal\" type=\"boolean\" value=\"true\"/>\n";
        code += "  </Properties>\n";
        code += "  <SyntheticProperties>\n";
        code += "    <SyntheticProperty name=\"formSize\" type=\"java.awt.Dimension\" value=\"-84,-19,0,5,115,114,0,18,106,97,118,97,46,97,119,116,46,68,105,109,101,110,115,105,111,110,65,-114,-39,-41,-84,95,68,20,2,0,2,73,0,6,104,101,105,103,104,116,73,0,5,119,105,100,116,104,120,112,0,0,2,88,0,0,3,32\"/>\n";
        code += "    <SyntheticProperty name=\"formSizePolicy\" type=\"int\" value=\"0\"/>\n";
        code += "    <SyntheticProperty name=\"generateSize\" type=\"boolean\" value=\"true\"/>\n";
        code += "    <SyntheticProperty name=\"generateCenter\" type=\"boolean\" value=\"true\"/>\n";
        code += "  </SyntheticProperties>\n";
        code += "  <Events>\n";
        code += "    <EventHandler event=\"windowClosing\" listener=\"java.awt.event.WindowListener\" parameters=\"java.awt.event.WindowEvent\" handler=\"formWindowClosing\"/>\n";
        code += "  </Events>\n";
        code += "  <AuxValues>\n";
        code += "    <AuxValue name=\"FormSettings_autoResourcing\" type=\"java.lang.Integer\" value=\"0\"/>\n";
        code += "    <AuxValue name=\"FormSettings_autoSetComponentName\" type=\"java.lang.Boolean\" value=\"false\"/>\n";
        code += "    <AuxValue name=\"FormSettings_generateFQN\" type=\"java.lang.Boolean\" value=\"true\"/>\n";
        code += "    <AuxValue name=\"FormSettings_generateMnemonicsCode\" type=\"java.lang.Boolean\" value=\"false\"/>\n";
        code += "    <AuxValue name=\"FormSettings_i18nAutoMode\" type=\"java.lang.Boolean\" value=\"false\"/>\n";
        code += "    <AuxValue name=\"FormSettings_layoutCodeTarget\" type=\"java.lang.Integer\" value=\"1\"/>\n";
        code += "    <AuxValue name=\"FormSettings_listenerGenerationStyle\" type=\"java.lang.Integer\" value=\"0\"/>\n";
        code += "    <AuxValue name=\"FormSettings_variablesLocal\" type=\"java.lang.Boolean\" value=\"false\"/>\n";
        code += "    <AuxValue name=\"FormSettings_variablesModifier\" type=\"java.lang.Integer\" value=\"2\"/>\n";
        code += "  </AuxValues>\n";
        code += "  <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\"/>\n";
        code += "  <SubComponents>\n";
        code += "    <Container class=\"javax.swing.JPanel\" name=\"pData\">\n";
        code += "      <Constraints>\n";
        code += "        <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription\">\n";
        code += "          <BorderConstraints direction=\"North\"/>\n";
        code += "        </Constraint>\n";
        code += "      </Constraints>\n";
        code += "      <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignGridLayout\">\n";
        code += "        <Property name=\"columns\" type=\"int\" value=\"0\"/>\n";
        code += "        <Property name=\"rows\" type=\"int\" value=\"5\"/>\n";
        code += "      </Layout>\n";
        
        code += "      <SubComponents>\n";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genXMLpData();
        }
        code += "      </SubComponents>\n";
        code += "    </Container>\n";
        
        code += "    <Container class=\"javax.swing.JTabbedPane\" name=\"tbData\">\n";
        code += "      <Constraints>\n";
        code += "        <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription\">\n";
        code += "          <BorderConstraints direction=\"Center\"/>\n";
        code += "        </Constraint>\n";
        code += "      </Constraints>\n";
        code += "      <Layout class=\"org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout\"/>\n";
        
        code += "      <SubComponents>\n";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genXMLtbData();
        }
        code += "      </SubComponents>\n";
        code += "    </Container>\n";

        code += "    <Container class=\"javax.swing.JPanel\" name=\"pButtons\">\n";
        code += "      <Constraints>\n";
        code += "        <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription\">\n";
        code += "          <BorderConstraints direction=\"South\"/>\n";
        code += "        </Constraint>\n";
        code += "      </Constraints>\n";
        code += "      <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignFlowLayout\">\n";
        code += "        <Property name=\"alignment\" type=\"int\" value=\"2\"/>\n";
        code += "      </Layout>\n";
        code += "      <SubComponents>\n";
        code += "        <Component class=\"javax.swing.JButton\" name=\"bOk\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"icon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "              <Image iconType=\"3\" name=\"/toolbox/images/stock_calc-accept-16.png\"/>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"mnemonic\" type=\"int\" value=\"79\"/>\n";
        code += "            <Property name=\"text\" type=\"java.lang.String\" value=\"Ok\"/>\n";
        code += "          </Properties>\n";
        code += "          <Events>\n";
        code += "            <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"bOkActionPerformed\"/>\n";
        code += "          </Events>\n";
        code += "        </Component>\n";
        code += "        <Component class=\"javax.swing.JButton\" name=\"bCancel\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"icon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "              <Image iconType=\"3\" name=\"/toolbox/images/stock_calc-cancel-16.png\"/>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"mnemonic\" type=\"int\" value=\"67\"/>\n";
        code += "            <Property name=\"text\" type=\"java.lang.String\" value=\"Cancelar\"/>\n";
        code += "          </Properties>\n";
        code += "          <Events>\n";
        code += "            <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"bCancelActionPerformed\"/>\n";
        code += "          </Events>\n";
        code += "        </Component>\n";
        code += "      </SubComponents>\n";
        code += "    </Container>\n";
        
        code += "  </SubComponents>\n";
        code += "</Form>\n";
        return code;
    }
}
