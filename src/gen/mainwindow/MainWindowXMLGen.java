package gen.mainwindow;

/**
 *
 * @author marcos
 */
public class MainWindowXMLGen {

    private project.Project proj;

    /**
     * Construtor
     * @param proj
     */
    public MainWindowXMLGen(project.Project proj){
        this.proj = proj;
    }
    
    public String getXMLCode(){
        String code = "";
        code += "<?xml version=\"1.1\" encoding=\"UTF-8\" ?>\n";
        code += "<Form version=\"1.3\" maxVersion=\"1.7\" type=\"org.netbeans.modules.form.forminfo.JFrameFormInfo\">\n";
        code += "  <NonVisualComponents>\n";
        code += "    <Menu class=\"javax.swing.JMenuBar\" name=\"menuBar\">\n";
        code += "      <SubComponents>\n";
        code += "        <Menu class=\"javax.swing.JMenu\" name=\"mArquivo\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"mnemonic\" type=\"int\" value=\"65\"/>\n";
        code += "            <Property name=\"text\" type=\"java.lang.String\" value=\"Arquivo\"/>\n";
        code += "          </Properties>\n";
        code += "          <SubComponents>\n";
        for(project.ClassBean classBean : proj.getClassBeans()){
            if(!classBean.isDependent(proj)){
                code += "            <MenuItem class=\"javax.swing.JMenuItem\" name=\"m"+classBean.getName()+"\">\n";
                code += "              <Properties>\n";
                code += "                <Property name=\"text\" type=\"java.lang.String\" value=\""+classBean.getName()+"\"/>\n";
                code += "              </Properties>\n";
                code += "              <Events>\n";
                code += "                <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"m"+classBean.getName()+"ActionPerformed\"/>\n";
                code += "              </Events>\n";
                code += "            </MenuItem>\n";
            }
        }
        code += "            <MenuItem class=\"javax.swing.JPopupMenu$Separator\" name=\"jSeparator1\">\n";
        code += "            </MenuItem>\n";
        code += "            <MenuItem class=\"javax.swing.JMenuItem\" name=\"mSair\">\n";
        code += "              <Properties>\n";
        code += "                <Property name=\"text\" type=\"java.lang.String\" value=\"Sair\"/>\n";
        code += "              </Properties>\n";
        code += "              <Events>\n";
        code += "                <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"mSairActionPerformed\"/>\n";
        code += "              </Events>\n";
        code += "            </MenuItem>\n";
        code += "          </SubComponents>\n";
        code += "        </Menu>\n";
        code += "        <Menu class=\"javax.swing.JMenu\" name=\"mRelatorio\">\n";
        code += "          <Properties>\n";
        code += "            <Property name=\"mnemonic\" type=\"int\" value=\"82\"/>\n";
        code += "            <Property name=\"text\" type=\"java.lang.String\" value=\"Relat&#xf3;rios\"/>\n";
        code += "          </Properties>\n";
        code += "          <SubComponents>\n";
        code += "            <MenuItem class=\"javax.swing.JMenuItem\" name=\"mSQLViewer\">\n";
        code += "              <Properties>\n";
        code += "                <Property name=\"text\" type=\"java.lang.String\" value=\"Consulta SQL\"/>\n";
        code += "              </Properties>\n";
        code += "              <Events>\n";
        code += "                <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"mSQLViewerActionPerformed\"/>\n";
        code += "              </Events>\n";
        code += "            </MenuItem>\n";
        code += "          </SubComponents>\n";
        code += "        </Menu>\n";
        code += "      </SubComponents>\n";
        code += "    </Menu>\n";
        code += "  </NonVisualComponents>\n";
        code += "  <Properties>\n";
        code += "    <Property name=\"defaultCloseOperation\" type=\"int\" value=\"0\"/>\n";
        code += "    <Property name=\"extendedState\" type=\"int\" editor=\"org.netbeans.modules.form.RADConnectionPropertyEditor\">\n";
        code += "       <Connection code=\"MAXIMIZED_BOTH\" type=\"code\"/>\n";
        code += "    </Property>\n";
        code += "  </Properties>\n";
        code += "  <SyntheticProperties>\n";
        code += "    <SyntheticProperty name=\"menuBar\" type=\"java.lang.String\" value=\"menuBar\"/>\n";
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
        code += "</Form>\n";
        return code;
    }
}
