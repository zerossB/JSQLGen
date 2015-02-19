package gen.gui;

/**
 *
 * @author marcos
 */
public class ClassTabDependentGen {

    private project.Project proj;
    private project.ClassBean classBean;

    /**
     * Construtor
     * @param proj
     * @param classBean 
     */
    public ClassTabDependentGen(project.Project proj, project.ClassBean classBean){
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
    
    public String getJavaCode(){
        project.ClassBean classOwner = this.proj.getClassBean(classBean.getOwner(proj));
        String code = "";

        code += "package "+classBean.getPackage(proj)+";\n";
        code += "/**\n";
        code += " *\n";
        code += " * @author marcos\n";
        code += " */\n";
        code += "public class "+classBean.getName()+"Tab extends javax.swing.JPanel {\n";
        code += "    private java.awt.Window parent;\n";
        code += "    private java.sql.Connection connection;\n";
        code += "    private "+classOwner.getPackage(proj) +"."+classOwner.getName()+" "+classOwner.getNameL()+"Owner;\n";
        code += "    private java.util.List<"+classBean.getName()+"> list;\n";
        code += "\n";
        
        code += "    /** Creates new form "+classBean.getName()+"Tab */\n";
        code += "    public "+classBean.getName()+"Tab(java.awt.Window parent, java.sql.Connection connection, "+classOwner.getPackage(proj) +"."+classOwner.getName()+" "+classOwner.getNameL()+"Owner) {\n";
        code += "        this.parent = parent;\n";
        code += "        this.connection = connection;\n";
        code += "        this."+classOwner.getNameL()+"Owner = "+classOwner.getNameL()+"Owner;\n";
        
        java.util.List<project.Attribute> attList = classOwner.getAttributesOfType(project.Attribute.Type.OBJECT_LIST);
        project.AttributeObjectList attOwner = new project.AttributeObjectList();
        for(project.Attribute att : attList){
            project.AttributeObjectList attObj = (project.AttributeObjectList)att;
            if(attObj.getClassRef().compareTo(classBean.getName())==0)
                attOwner = attObj;
        }
        code += "        this.list = "+classOwner.getNameL()+"Owner.get"+attOwner.getNameU()+"();\n";

        code += "        initComponents();\n";
        code += "        refresh();\n";
        code += "    }\n";
        code += "\n";

        code += "    /** Atualiza tabela */\n";
        code += "    private void refresh(){\n";
        code += "        javax.swing.table.DefaultTableModel mTable = (javax.swing.table.DefaultTableModel)tTable.getModel();\n";
        code += "        int lineSel = tTable.getSelectedRow();\n";
        code += "        while(mTable.getRowCount()>0) mTable.removeRow(0);\n";
        code += "        for("+classBean.getName()+" "+classBean.getNameL()+": list) {\n";
        code += "            java.util.List<Object> line = new java.util.ArrayList<Object>();\n";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genRefreshTab();
        }
        code += "            mTable.addRow(line.toArray());\n";
        code += "        }\n";
        code += "        if(lineSel>=0 && lineSel<list.size()) tTable.setRowSelectionInterval(lineSel, lineSel);\n";
        code += "    }\n";

        code += "\n";
        code += "    /** This method is called from within the constructor to\n";
        code += "     * initialize the form.\n";
        code += "     * WARNING: Do NOT modify this code. The content of this method is\n";
        code += "     * always regenerated by the Form Editor.\n";
        code += "     */\n";
        code += "    @SuppressWarnings(\"unchecked\")\n";
        code += "    // <editor-fold defaultstate=\"collapsed\" desc=\"Generated Code\">//GEN-BEGIN:initComponents\n";
        code += "    private void initComponents() {\n";
        code += "        sTable = new javax.swing.JScrollPane();\n";
        code += "        tTable = new javax.swing.JTable();\n";
        code += "        pButtons = new javax.swing.JPanel();\n";
        code += "        bAdd = new javax.swing.JButton();\n";
        code += "        bRem = new javax.swing.JButton();\n";
        code += "        bEdit = new javax.swing.JButton();\n";
        code += "        setLayout(new java.awt.BorderLayout());\n";
        code += "        tTable.setModel(new javax.swing.table.DefaultTableModel(\n";
        code += "            new Object [][] {\n";
        code += "            },\n";
        code += "            new String [] {\n";
        code += "                ";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genModelTitle();
        }
        code = code.substring(0, code.length()-2);
        code += "\n";
        code += "            }\n";
        code += "        ) {\n";
        code += "            Class[] types = new Class [] {\n";
        code += "                ";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genModelType();
        }
        code = code.substring(0, code.length()-2);
        code += "\n";
        code += "            };\n";
        code += "            boolean[] canEdit = new boolean [] {\n";
        code += "                ";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genModelEdit();
        }
        code = code.substring(0, code.length()-2);
        code += "\n";
        code += "            };\n";
        code += "            public Class getColumnClass(int columnIndex) {\n";
        code += "                return types [columnIndex];\n";
        code += "            }\n";
        code += "            public boolean isCellEditable(int rowIndex, int columnIndex) {\n";
        code += "                return canEdit [columnIndex];\n";
        code += "            }\n";
        code += "        });\n";
        code += "        tTable.addMouseListener(new java.awt.event.MouseAdapter() {\n";
        code += "            public void mouseClicked(java.awt.event.MouseEvent evt) {\n";
        code += "                tTableMouseClicked(evt);\n";
        code += "            }\n";
        code += "        });\n";
        code += "        sTable.setViewportView(tTable);\n";
        code += "        add(sTable, java.awt.BorderLayout.CENTER);\n";
        code += "        pButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));\n";
        code += "        bAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/toolbox/images/stock_new-16.png\"))); // NOI18N\n";
        code += "        bAdd.setMnemonic('N');\n";
        code += "        bAdd.setText(\"Adicionar\");\n";
        code += "        bAdd.addActionListener(new java.awt.event.ActionListener() {\n";
        code += "            public void actionPerformed(java.awt.event.ActionEvent evt) {\n";
        code += "                bAddActionPerformed(evt);\n";
        code += "            }\n";
        code += "        });\n";
        code += "        pButtons.add(bAdd);\n";
        code += "        bRem.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/toolbox/images/stock_delete-16.png\"))); // NOI18N\n";
        code += "        bRem.setMnemonic('R');\n";
        code += "        bRem.setText(\"Remover\");\n";
        code += "        bRem.addActionListener(new java.awt.event.ActionListener() {\n";
        code += "            public void actionPerformed(java.awt.event.ActionEvent evt) {\n";
        code += "                bRemActionPerformed(evt);\n";
        code += "            }\n";
        code += "        });\n";
        code += "        pButtons.add(bRem);\n";
        code += "        bEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/toolbox/images/stock_open-16.png\"))); // NOI18N\n";
        code += "        bEdit.setMnemonic('E');\n";
        code += "        bEdit.setText(\"Editar\");\n";
        code += "        bEdit.addActionListener(new java.awt.event.ActionListener() {\n";
        code += "            public void actionPerformed(java.awt.event.ActionEvent evt) {\n";
        code += "                bEditActionPerformed(evt);\n";
        code += "            }\n";
        code += "        });\n";
        code += "        pButtons.add(bEdit);\n";
        code += "        add(pButtons, java.awt.BorderLayout.SOUTH);\n";
        code += "    }// </editor-fold>//GEN-END:initComponents\n";
        
        code += "\n";
        code += "    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed\n";
        code += "        "+classBean.getName()+" "+classBean.getNameL()+" = new "+classBean.getName()+"();\n";
        code += "        "+classBean.getNameL()+" = new "+classBean.getName()+"Form(parent, connection, "+classBean.getNameL()+").showInputDialog();\n";
        code += "        if("+classBean.getNameL()+"!=null){\n";
        code += "            try{\n";
        code += "                if("+classOwner.getNameL()+"Owner.get"+classOwner.getPrimaryKey().getNameU()+"()!=null) "+classBean.getName()+"DAO.insert(connection, "+classBean.getNameL()+", "+classOwner.getNameL()+"Owner);\n";
        code += "                list.add("+classBean.getNameL()+");\n";
        code += "                refresh();\n";
        code += "            } catch(java.sql.SQLException e){\n";
        code += "                e.printStackTrace();\n";
        code += "                javax.swing.JOptionPane.showMessageDialog(this, \"Falha na gravação do banco de dados!\\n\"+e.getMessage(),\"Aviso\",javax.swing.JOptionPane.ERROR_MESSAGE);\n";
        code += "            }\n";
        code += "        }\n";
        code += "    }//GEN-LAST:event_bAddActionPerformed\n";
        
        code += "\n";
        code += "    private void bRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemActionPerformed\n";
        code += "        if(tTable.getSelectedRow()>=0){\n";
        code += "            "+classBean.getName()+" "+classBean.getNameL()+" = list.get(tTable.getSelectedRow());\n";
        code += "            if(javax.swing.JOptionPane.showConfirmDialog(this, \"Deseja excluir o "+classBean.getName()+" \"+"+classBean.getNameL()+".get"+classBean.getPrimaryKey().getNameU()+"()+\"?\",\"Questão\",javax.swing.JOptionPane.OK_CANCEL_OPTION)==javax.swing.JOptionPane.OK_OPTION){\n";
        code += "                try{\n";
        code += "                    if("+classOwner.getNameL()+"Owner.get"+classOwner.getPrimaryKey().getNameU()+"()!=null) "+classBean.getName()+"DAO.delete(connection, "+classBean.getNameL()+");\n";
        code += "                    list.remove(tTable.getSelectedRow());\n";
        code += "                    refresh();\n";
        code += "                } catch(java.sql.SQLException e){\n";
        code += "                    e.printStackTrace();\n";
        code += "                    javax.swing.JOptionPane.showMessageDialog(this, \"Falha na exclusão do banco de dados!\\n\"+e.getMessage(),\"Aviso\",javax.swing.JOptionPane.ERROR_MESSAGE);\n";
        code += "                }\n";
        code += "            }\n";
        code += "        }\n";
        code += "    }//GEN-LAST:event_bRemActionPerformed\n";
        
        code += "\n";
        code += "    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed\n";
        code += "        if(tTable.getSelectedRow()>=0){\n";
        code += "            "+classBean.getName()+" "+classBean.getNameL()+" = list.get(tTable.getSelectedRow());\n";
        code += "            "+classBean.getNameL()+" = new "+classBean.getName()+"Form(parent, connection, "+classBean.getNameL()+").showInputDialog();\n";
        code += "            if("+classBean.getNameL()+"!=null){\n";
        code += "                try{\n";
        code += "                    if("+classOwner.getNameL()+"Owner.get"+classOwner.getPrimaryKey().getNameU()+"()!=null) "+classBean.getName()+"DAO.update(connection, "+classBean.getNameL()+", "+classOwner.getNameL()+"Owner);\n";
        code += "                    refresh();\n";
        code += "                } catch(java.sql.SQLException e){\n";
        code += "                    e.printStackTrace();\n";
        code += "                    javax.swing.JOptionPane.showMessageDialog(this, \"Falha na gravação do banco de dados!\\n\"+e.getMessage(),\"Aviso\",javax.swing.JOptionPane.ERROR_MESSAGE);\n";
        code += "                }\n";
        code += "            }\n";
        code += "        }\n";
        code += "    }//GEN-LAST:event_bEditActionPerformed\n";
        
        code += "\n";
        code += "    private void tTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTableMouseClicked\n";
        code += "        if(evt.getClickCount()>=2){\n";
        code += "            bEditActionPerformed(null);\n";
        code += "        }\n";
        code += "    }//GEN-LAST:event_tTableMouseClicked\n";
        
        code += "\n";
        code += "    // Variables declaration - do not modify//GEN-BEGIN:variables\n";
        code += "    private javax.swing.JButton bAdd;\n";
        code += "    private javax.swing.JButton bEdit;\n";
        code += "    private javax.swing.JButton bRem;\n";
        code += "    private javax.swing.JPanel pButtons;\n";
        code += "    private javax.swing.JScrollPane sTable;\n";
        code += "    private javax.swing.JTable tTable;\n";
        code += "    // End of variables declaration//GEN-END:variables\n";
        code += "}\n";
        
        return code;
    }
}
