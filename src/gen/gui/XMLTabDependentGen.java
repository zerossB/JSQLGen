package gen.gui;

/**
 *
 * @author marcos
 */
public class XMLTabDependentGen {

    private project.Project proj;
    private project.ClassBean classBean;

    /**
     * Construtor
     * @param proj
     * @param classBean 
     */
    public XMLTabDependentGen(project.Project proj, project.ClassBean classBean){
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
        code += "<Form version=\"1.5\" maxVersion=\"1.7\" type=\"org.netbeans.modules.form.forminfo.JPanelFormInfo\">\n";
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
        code += "    <AuxValue name=\"designerSize\" type=\"java.awt.Dimension\" value=\"-84,-19,0,5,115,114,0,18,106,97,118,97,46,97,119,116,46,68,105,109,101,110,115,105,111,110,65,-114,-39,-41,-84,95,68,20,2,0,2,73,0,6,104,101,105,103,104,116,73,0,5,119,105,100,116,104,120,112,0,0,1,44,0,0,1,-44\"/>\n";
        code += "  </AuxValues>\n";
        code += "  <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\"/>\n";
        code += "  <SubComponents>\n";
        code += "    <Container class=\"javax.swing.JScrollPane\" name=\"sTable\">\n";
        code += "      <AuxValues>\n";
        code += "        <AuxValue name=\"autoScrollPane\" type=\"java.lang.Boolean\" value=\"true\"/>\n";
        code += "      </AuxValues>\n";
        code += "      <Constraints>\n";
        code += "        <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription\">\n";
        code += "          <BorderConstraints direction=\"Center\"/>\n";
        code += "        </Constraint>\n";
        code += "      </Constraints>\n";
        code += "      <Layout class=\"org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout\"/>\n";
        code += "      <SubComponents>\n";
        code += "        <Component class=\"javax.swing.JTable\" name=\"tTable\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"model\" type=\"javax.swing.table.TableModel\" editor=\"org.netbeans.modules.form.editors2.TableModelEditor\">\n";
        String codeTmp = "";
        int columnCount = 0;
        for(project.Attribute att : classBean.getAttributes()){
            codeTmp += getAttributeGen(att).genXMLTabColumn();
            if(att.getType()!=project.Attribute.Type.IMAGE && att.getType()!=project.Attribute.Type.OBJECT_LIST)
                columnCount++;
        }
        code += "              <Table columnCount=\""+columnCount+"\" rowCount=\"0\">\n";
        code += codeTmp;
        code += "              </Table>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"columnModel\" type=\"javax.swing.table.TableColumnModel\" editor=\"org.netbeans.modules.form.editors2.TableColumnModelEditor\">\n";
        code += "              <TableColumnModel selectionModel=\"0\">\n";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genXMLTabModel();
        }
        code += "              </TableColumnModel>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"tableHeader\" type=\"javax.swing.table.JTableHeader\" editor=\"org.netbeans.modules.form.editors2.JTableHeaderEditor\">\n";
        code += "              <TableHeader reorderingAllowed=\"true\" resizingAllowed=\"true\"/>\n";
        code += "            </Property>\n";
        code += "          </Properties>\n";
        code += "          <Events>\n";
        code += "            <EventHandler event=\"mouseClicked\" listener=\"java.awt.event.MouseListener\" parameters=\"java.awt.event.MouseEvent\" handler=\"tTableMouseClicked\"/>\n";
        code += "          </Events>\n";
        code += "        </Component>\n";
        code += "      </SubComponents>\n";
        code += "    </Container>\n";
        code += "    <Container class=\"javax.swing.JPanel\" name=\"pButtons\">\n";
        code += "      <Constraints>\n";
        code += "        <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription\">\n";
        code += "          <BorderConstraints direction=\"South\"/>\n";
        code += "        </Constraint>\n";
        code += "      </Constraints>\n";
        code += "      <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignFlowLayout\">\n";
        code += "        <Property name=\"alignment\" type=\"int\" value=\"0\"/>\n";
        code += "      </Layout>\n";
        code += "      <SubComponents>\n";
        code += "        <Component class=\"javax.swing.JButton\" name=\"bAdd\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"icon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "              <Image iconType=\"3\" name=\"/toolbox/images/stock_new-16.png\"/>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"mnemonic\" type=\"int\" value=\"78\"/>\n";
        code += "            <Property name=\"text\" type=\"java.lang.String\" value=\"Adicionar\"/>\n";
        code += "          </Properties>\n";
        code += "          <Events>\n";
        code += "            <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"bAddActionPerformed\"/>\n";
        code += "          </Events>\n";
        code += "        </Component>\n";
        code += "        <Component class=\"javax.swing.JButton\" name=\"bRem\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"icon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "              <Image iconType=\"3\" name=\"/toolbox/images/stock_delete-16.png\"/>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"mnemonic\" type=\"int\" value=\"82\"/>\n";
        code += "            <Property name=\"text\" type=\"java.lang.String\" value=\"Remover\"/>\n";
        code += "          </Properties>\n";
        code += "          <Events>\n";
        code += "            <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"bRemActionPerformed\"/>\n";
        code += "          </Events>\n";
        code += "        </Component>\n";
        code += "        <Component class=\"javax.swing.JButton\" name=\"bEdit\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"icon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "              <Image iconType=\"3\" name=\"/toolbox/images/stock_open-16.png\"/>\n";
        code += "            </Property>\n";
        code += "            <Property name=\"mnemonic\" type=\"int\" value=\"69\"/>\n";
        code += "            <Property name=\"text\" type=\"java.lang.String\" value=\"Editar\"/>\n";
        code += "          </Properties>\n";
        code += "          <Events>\n";
        code += "            <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"bEditActionPerformed\"/>\n";
        code += "          </Events>\n";
        code += "        </Component>\n";
        code += "      </SubComponents>\n";
        code += "    </Container>\n";
        code += "  </SubComponents>\n";
        code += "</Form>\n";
        return code;
    }
}
