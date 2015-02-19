package gen.gui;

/**
 *
 * @author marcos
 */
public class AttributeImageGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeImage att;

    public AttributeImageGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeImage)att;
    }

    @Override
    public String genObj2Form(){
        String code = "";
        code += "        l"+att.getNameU()+".setIcon("+classBean.getNameL()+".get"+att.getNameU()+"());\n";
        return code;
    }
    @Override
    public String genForm2Obj(){
        String code = "";
        if(att.isFixedWidth())
            code += "        "+classBean.getNameL()+".set"+att.getNameU()+"((javax.swing.ImageIcon)l"+att.getNameU()+".getIcon(),"+att.getWidth()+");\n";
        else
            code += "        "+classBean.getNameL()+".set"+att.getNameU()+"((javax.swing.ImageIcon)l"+att.getNameU()+".getIcon());\n";
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
        code += "        p"+att.getNameU()+" = new javax.swing.JPanel();\n";
        code += "        l"+att.getNameU()+" = new javax.swing.JLabel();\n";
        code += "        p"+att.getNameU()+"Buttons = new javax.swing.JPanel();\n";
        code += "        b"+att.getNameU()+"Paste = new javax.swing.JButton();\n";
        code += "        b"+att.getNameU()+"Save = new javax.swing.JButton();\n";
        code += "        b"+att.getNameU()+"Remove = new javax.swing.JButton();\n";
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
        code += "        p"+att.getNameU()+".setLayout(new java.awt.BorderLayout());\n";
        code += "        l"+att.getNameU()+".setHorizontalAlignment(javax.swing.SwingConstants.CENTER);\n";
        code += "        p"+att.getNameU()+".add(l"+att.getNameU()+", java.awt.BorderLayout.CENTER);\n";
        code += "        p"+att.getNameU()+"Buttons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));\n";
        code += "        b"+att.getNameU()+"Paste.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/toolbox/images/stock_paste-16.png\"))); // NOI18N\n";
        code += "        b"+att.getNameU()+"Paste.setText(\"Colar da Área de Transferência\");\n";
        code += "        b"+att.getNameU()+"Paste.addActionListener(new java.awt.event.ActionListener() {\n";
        code += "            public void actionPerformed(java.awt.event.ActionEvent evt) {\n";
        code += "                b"+att.getNameU()+"PasteActionPerformed(evt);\n";
        code += "            }\n";
        code += "        });\n";
        code += "        p"+att.getNameU()+"Buttons.add(b"+att.getNameU()+"Paste);\n";
        code += "        b"+att.getNameU()+"Save.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/toolbox/images/stock_export-16.png\"))); // NOI18N\n";
        code += "        b"+att.getNameU()+"Save.setText(\"Salvar Imagem\");\n";
        code += "        b"+att.getNameU()+"Save.addActionListener(new java.awt.event.ActionListener() {\n";
        code += "            public void actionPerformed(java.awt.event.ActionEvent evt) {\n";
        code += "                b"+att.getNameU()+"SaveActionPerformed(evt);\n";
        code += "            }\n";
        code += "        });\n";
        code += "        p"+att.getNameU()+"Buttons.add(b"+att.getNameU()+"Save);\n";
        code += "        b"+att.getNameU()+"Remove.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/toolbox/images/stock_delete-16.png\"))); // NOI18N\n";
        code += "        b"+att.getNameU()+"Remove.setText(\"Remover Imagem\");\n";
        code += "        b"+att.getNameU()+"Remove.addActionListener(new java.awt.event.ActionListener() {\n";
        code += "            public void actionPerformed(java.awt.event.ActionEvent evt) {\n";
        code += "                b"+att.getNameU()+"RemoveActionPerformed(evt);\n";
        code += "            }\n";
        code += "        });\n";
        code += "        p"+att.getNameU()+"Buttons.add(b"+att.getNameU()+"Remove);\n";
        code += "        p"+att.getNameU()+".add(p"+att.getNameU()+"Buttons, java.awt.BorderLayout.PAGE_END);\n";
        code += "        tbData.addTab(\""+att.getNameU()+"\", new javax.swing.ImageIcon(getClass().getResource(\"/toolbox/images/stock_insert_image-16.png\")), p"+att.getNameU()+"); // NOI18N\n";
        return code;
    }

    public String genMethods(){
        String code = "";
        code += "\n";
        code += "    //"+att.getNameU()+"\n";
        code += "    private void b"+att.getNameU()+"PasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b"+att.getNameU()+"PasteActionPerformed\n";
        code += "        try {\n";
        code += "            form2obj();\n";
        if(att.isFixedWidth())
            code += "            "+classBean.getNameL()+".set"+att.getNameU()+"(new javax.swing.ImageIcon((java.awt.Image)java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferData(java.awt.datatransfer.DataFlavor.imageFlavor)),"+att.getWidth()+");\n";
        else
            code += "            "+classBean.getNameL()+".set"+att.getNameU()+"(new javax.swing.ImageIcon((java.awt.Image)java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferData(java.awt.datatransfer.DataFlavor.imageFlavor)));\n";
        code += "            obj2form();\n";
        code += "        } catch(java.awt.datatransfer.UnsupportedFlavorException e){\n";
        code += "            javax.swing.JOptionPane.showMessageDialog(this, \"O Conteúdo da Área de Transferência Inválido!\",\"Aviso\",javax.swing.JOptionPane.ERROR_MESSAGE);\n";
        code += "        } catch(java.io.IOException e){\n";
        code += "            javax.swing.JOptionPane.showMessageDialog(this, \"Erro de E / S !\",\"Aviso\",javax.swing.JOptionPane.ERROR_MESSAGE);\n";
        code += "        } catch(java.awt.HeadlessException e){\n";
        code += "            javax.swing.JOptionPane.showMessageDialog(this, \"Recurso de Área de Transferência não suportada!\",\"Aviso\",javax.swing.JOptionPane.ERROR_MESSAGE);\n";
        code += "        }\n";
        code += "    }//GEN-LAST:event_b"+att.getNameU()+"PasteActionPerformed\n";
        code += "    private void b"+att.getNameU()+"SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b"+att.getNameU()+"SaveActionPerformed\n";
        code += "        javax.swing.JFileChooser browser = new javax.swing.JFileChooser();\n";
        code += "        browser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);\n";
        code += "        browser.setMultiSelectionEnabled(false);\n";
        code += "        browser.setFileFilter(\n";
        code += "            new javax.swing.filechooser.FileFilter() {\n";
        code += "                @Override\n";
        code += "                public String getDescription() {\n";
        code += "                    return \"PNG - Portable Network Graphics (*.png)\";\n";
        code += "                }\n";
        code += "                @Override\n";
        code += "                public boolean accept(java.io.File file) {\n";
        code += "                    if(file.getName().endsWith(\".png\") || file.isDirectory()) return true;\n";
        code += "                    else return false;\n";
        code += "                }\n";
        code += "            }\n";
        code += "        );\n";
        code += "        if (browser.showSaveDialog(this)==javax.swing.JFileChooser.CANCEL_OPTION || browser.getSelectedFile().getName().isEmpty()) return;\n";
        code += "        String pathFile = browser.getSelectedFile().getAbsolutePath();\n";
        code += "        try {\n";
        code += "            if(!browser.getSelectedFile().getName().isEmpty()) {\n";
        code += "                form2obj();\n";
        code += "                if(!pathFile.toUpperCase().endsWith(\".PNG\")) pathFile +=\".png\";\n";
        code += "                "+classBean.getNameL()+".save"+att.getNameU()+"(pathFile, \"PNG\");\n";
        code += "            }\n";
        code += "        } catch (java.io.IOException e) {\n";
        code += "            javax.swing.JOptionPane.showMessageDialog(this,\"Arquivo inválido !\");\n";
        code += "        }\n";
        code += "    }//GEN-LAST:event_b"+att.getNameU()+"SaveActionPerformed\n";
        code += "    private void b"+att.getNameU()+"RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b"+att.getNameU()+"RemoveActionPerformed\n";
        code += "        if(javax.swing.JOptionPane.showConfirmDialog(this, \"Deseja remover a imagem?\",\"Questão\",javax.swing.JOptionPane.OK_CANCEL_OPTION)==javax.swing.JOptionPane.OK_OPTION) {\n";
        code += "            l"+att.getNameU()+".setIcon(new javax.swing.ImageIcon());\n";
        code += "        }\n";
        code += "    }//GEN-LAST:event_b"+att.getNameU()+"RemoveActionPerformed\n";
        return code;
    }
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private javax.swing.JButton b"+att.getNameU()+"Paste;\n";
        code += "    private javax.swing.JButton b"+att.getNameU()+"Remove;\n";
        code += "    private javax.swing.JButton b"+att.getNameU()+"Save;\n";
        code += "    private javax.swing.JLabel l"+att.getNameU()+";\n";
        code += "    private javax.swing.JPanel p"+att.getNameU()+";\n";
        code += "    private javax.swing.JPanel p"+att.getNameU()+"Buttons;\n";
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
        code += "          <Constraints>\n";
        code += "            <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout\" value=\"org.netbeans.modules.form.compat2.layouts.support.JTabbedPaneSupportLayout$JTabbedPaneConstraintsDescription\">\n";
        code += "              <JTabbedPaneConstraints tabName=\""+att.getNameU()+"\">\n";
        code += "                <Property name=\"tabTitle\" type=\"java.lang.String\" value=\""+att.getNameU()+"\"/>\n";
        code += "                <Property name=\"tabIcon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "                  <Image iconType=\"3\" name=\"/toolbox/images/stock_insert_image-16.png\"/>\n";
        code += "                </Property>\n";
        code += "              </JTabbedPaneConstraints>\n";
        code += "            </Constraint>\n";
        code += "          </Constraints>\n";
        code += "          <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\"/>\n";
        code += "          <SubComponents>\n";
        code += "            <Component class=\"javax.swing.JLabel\" name=\"l"+att.getNameU()+"\">\n";
        code += "              <Properties>\n";
        code += "                <Property name=\"horizontalAlignment\" type=\"int\" value=\"0\"/>\n";
        code += "              </Properties>\n";
        code += "              <Constraints>\n";
        code += "                <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription\">\n";
        code += "                  <BorderConstraints direction=\"Center\"/>\n";
        code += "                </Constraint>\n";
        code += "              </Constraints>\n";
        code += "            </Component>\n";
        code += "            <Container class=\"javax.swing.JPanel\" name=\"p"+att.getNameU()+"Buttons\">\n";
        code += "              <Constraints>\n";
        code += "                <Constraint layoutClass=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout\" value=\"org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription\">\n";
        code += "                  <BorderConstraints direction=\"Last\"/>\n";
        code += "                </Constraint>\n";
        code += "              </Constraints>\n";
        code += "              <Layout class=\"org.netbeans.modules.form.compat2.layouts.DesignFlowLayout\">\n";
        code += "                <Property name=\"alignment\" type=\"int\" value=\"0\"/>\n";
        code += "              </Layout>\n";
        code += "              <SubComponents>\n";
        code += "                <Component class=\"javax.swing.JButton\" name=\"b"+att.getNameU()+"Paste\">\n";
        code += "                  <Properties>\n";
        code += "                    <Property name=\"icon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "                      <Image iconType=\"3\" name=\"/toolbox/images/stock_paste-16.png\"/>\n";
        code += "                    </Property>\n";
        code += "                    <Property name=\"text\" type=\"java.lang.String\" value=\"Colar da &#xc1;rea de Transfer&#xea;ncia\"/>\n";
        code += "                  </Properties>\n";
        code += "                  <Events>\n";
        code += "                    <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"b"+att.getNameU()+"PasteActionPerformed\"/>\n";
        code += "                  </Events>\n";
        code += "                </Component>\n";
        code += "                <Component class=\"javax.swing.JButton\" name=\"b"+att.getNameU()+"Save\">\n";
        code += "                  <Properties>\n";
        code += "                    <Property name=\"icon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "                      <Image iconType=\"3\" name=\"/toolbox/images/stock_export-16.png\"/>\n";
        code += "                    </Property>\n";
        code += "                    <Property name=\"text\" type=\"java.lang.String\" value=\"Salvar Imagem\"/>\n";
        code += "                  </Properties>\n";
        code += "                  <Events>\n";
        code += "                    <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"b"+att.getNameU()+"SaveActionPerformed\"/>\n";
        code += "                  </Events>\n";
        code += "                </Component>\n";
        code += "                <Component class=\"javax.swing.JButton\" name=\"b"+att.getNameU()+"Remove\">\n";
        code += "                  <Properties>\n";
        code += "                    <Property name=\"icon\" type=\"javax.swing.Icon\" editor=\"org.netbeans.modules.form.editors2.IconEditor\">\n";
        code += "                      <Image iconType=\"3\" name=\"/toolbox/images/stock_delete-16.png\"/>\n";
        code += "                    </Property>\n";
        code += "                    <Property name=\"text\" type=\"java.lang.String\" value=\"Remover Imagem\"/>\n";
        code += "                  </Properties>\n";
        code += "                  <Events>\n";
        code += "                    <EventHandler event=\"actionPerformed\" listener=\"java.awt.event.ActionListener\" parameters=\"java.awt.event.ActionEvent\" handler=\"b"+att.getNameU()+"RemoveActionPerformed\"/>\n";
        code += "                  </Events>\n";
        code += "                </Component>\n";
        code += "              </SubComponents>\n";
        code += "            </Container>\n";
        code += "          </SubComponents>\n";
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
