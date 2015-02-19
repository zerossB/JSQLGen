/*
 * ClassBeanPanel.java
 *
 * Created on 10/06/2011, 22:50:51
 */
package gui;

/**
 *
 * @author marcos
 */
public class ClassBeanPanel extends javax.swing.JPanel {

    private java.awt.Window parent;
    private ProjectPanel projectPanel;
    private project.Project projectEdit;
    private project.ClassBean classBean;

    /** Creates new form ClassBeanPanel */
    public ClassBeanPanel(java.awt.Window parent, ProjectPanel projectPanel, project.Project projectEdit, project.ClassBean classBean, Integer cellHeight) {
        this.parent = parent;
        this.projectPanel = projectPanel;
        this.projectEdit = projectEdit;
        this.classBean = classBean;
        initComponents();
        lAttributes.setComponentPopupMenu(popupMenu);
        lAttributes.setFixedCellHeight(cellHeight);
        refresh();
    }

    private void refresh() {
        java.util.List<String> attList = new java.util.ArrayList<String>();
        for (project.Attribute attribute : classBean.getAttributes()) {
            String name = "";
            if(attribute.isUnique()) name += "!";
            if(attribute.isIndexed()) name += "@";
            name += attribute.getName()+" : "+attribute.getType();
            attList.add(name);
        }
        lAttributes.setListData(attList.toArray());
        lAttributes.revalidate();
        lAttributes.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        mAddAttribute = new javax.swing.JMenuItem();
        mRemAttribute = new javax.swing.JMenuItem();
        mEditAttribute = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mMoveTop = new javax.swing.JMenuItem();
        mMoveUp = new javax.swing.JMenuItem();
        mMoveDown = new javax.swing.JMenuItem();
        mMoveBotton = new javax.swing.JMenuItem();
        sAttributes = new javax.swing.JScrollPane();
        lAttributes = new javax.swing.JList();

        mAddAttribute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/textfield_add.png"))); // NOI18N
        mAddAttribute.setText("Adicionar Atributo");
        mAddAttribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAddAttributeActionPerformed(evt);
            }
        });
        popupMenu.add(mAddAttribute);

        mRemAttribute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/textfield_delete.png"))); // NOI18N
        mRemAttribute.setText("Remover Atributo");
        mRemAttribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRemAttributeActionPerformed(evt);
            }
        });
        popupMenu.add(mRemAttribute);

        mEditAttribute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/textfield_rename.png"))); // NOI18N
        mEditAttribute.setText("Editar Atributo");
        mEditAttribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mEditAttributeActionPerformed(evt);
            }
        });
        popupMenu.add(mEditAttribute);
        popupMenu.add(jSeparator1);

        mMoveTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/bullet_arrow_top.png"))); // NOI18N
        mMoveTop.setMnemonic('C');
        mMoveTop.setText("Mover para o topo");
        mMoveTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mMoveTopActionPerformed(evt);
            }
        });
        popupMenu.add(mMoveTop);

        mMoveUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/bullet_arrow_up.png"))); // NOI18N
        mMoveUp.setMnemonic('C');
        mMoveUp.setText("Mover acima");
        mMoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mMoveUpActionPerformed(evt);
            }
        });
        popupMenu.add(mMoveUp);

        mMoveDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/bullet_arrow_down.png"))); // NOI18N
        mMoveDown.setMnemonic('B');
        mMoveDown.setText("Mover abaixo");
        mMoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mMoveDownActionPerformed(evt);
            }
        });
        popupMenu.add(mMoveDown);

        mMoveBotton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/bullet_arrow_bottom.png"))); // NOI18N
        mMoveBotton.setMnemonic('B');
        mMoveBotton.setText("Mover para o fim");
        mMoveBotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mMoveBottonActionPerformed(evt);
            }
        });
        popupMenu.add(mMoveBotton);

        setLayout(new java.awt.BorderLayout());

        lAttributes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lAttributes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lAttributesMouseClicked(evt);
            }
        });
        sAttributes.setViewportView(lAttributes);

        add(sAttributes, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void mAddAttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAddAttributeActionPerformed
        project.Attribute att = new project.Attribute();
        att = new AttributeForm(parent, projectEdit, classBean, att).showInputDialog();
        if (att != null) {
            classBean.getAttributes().add(att);
            refresh();
            projectPanel.refreshFrames();
            projectPanel.setEdited(true);
        }
    }//GEN-LAST:event_mAddAttributeActionPerformed

    private void mRemAttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRemAttributeActionPerformed
        int selRow = lAttributes.getSelectedIndex();
        if(selRow>=0){
            project.Attribute att = classBean.getAttributes().get(selRow);
            if (javax.swing.JOptionPane.showConfirmDialog(projectPanel, "Deseja apagar o atributo " + att.getName() + "?", "Confirmação", javax.swing.JOptionPane.OK_CANCEL_OPTION) == javax.swing.JOptionPane.OK_OPTION) {
                if(!att.isUsed(projectEdit, classBean)){
                    classBean.getAttributes().remove(selRow);
                    refresh();
                    projectPanel.repaint();
                    projectPanel.setEdited(true);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Este atributo está sendo utilizado por outra classe.\nApague todas dependências e tente novamente.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_mRemAttributeActionPerformed

    private void mEditAttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEditAttributeActionPerformed
        int selRow = lAttributes.getSelectedIndex();
        if(selRow>=0){
            project.Attribute att = classBean.getAttributes().get(selRow);
            att = new AttributeForm(parent, projectEdit, classBean, att).showInputDialog();
            if (att != null) {
                classBean.getAttributes().remove(selRow);
                classBean.getAttributes().add(selRow, att);
                refresh();
                projectPanel.refreshFrames();
                projectPanel.setEdited(true);
            }
        }
    }//GEN-LAST:event_mEditAttributeActionPerformed

    private void lAttributesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lAttributesMouseClicked
        if(evt.getClickCount()>=2){
            mEditAttributeActionPerformed(null);
        }
    }//GEN-LAST:event_lAttributesMouseClicked

    private void mMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mMoveUpActionPerformed
        int selRow = lAttributes.getSelectedIndex();
        if(selRow > 0){
            project.Attribute attPrior = classBean.getAttributes().get(selRow-1);
            project.Attribute att = classBean.getAttributes().get(selRow);
            classBean.getAttributes().set(selRow-1, att);
            classBean.getAttributes().set(selRow, attPrior);
            refresh();
            projectPanel.refreshFrames();
            projectPanel.setEdited(true);
        }
        lAttributes.setSelectedIndex(selRow);
    }//GEN-LAST:event_mMoveUpActionPerformed

    private void mMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mMoveDownActionPerformed
        int selRow = lAttributes.getSelectedIndex();
        if(selRow >= 0 && selRow<classBean.getAttributes().size()-1){
            project.Attribute attNext = classBean.getAttributes().get(selRow+1);
            project.Attribute att = classBean.getAttributes().get(selRow);
            classBean.getAttributes().set(selRow+1, att);
            classBean.getAttributes().set(selRow, attNext);
            refresh();
            projectPanel.refreshFrames();
            projectPanel.setEdited(true);
        }
        lAttributes.setSelectedIndex(selRow);
    }//GEN-LAST:event_mMoveDownActionPerformed

    private void mMoveTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mMoveTopActionPerformed
        int selRow = lAttributes.getSelectedIndex();
        if(selRow > 0){
            project.Attribute att = classBean.getAttributes().get(selRow);
            classBean.getAttributes().remove(att);
            classBean.getAttributes().add(0, att);
            refresh();
            projectPanel.refreshFrames();
            projectPanel.setEdited(true);
        }
        lAttributes.setSelectedIndex(0);
    }//GEN-LAST:event_mMoveTopActionPerformed

    private void mMoveBottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mMoveBottonActionPerformed
        int selRow = lAttributes.getSelectedIndex();
        if(selRow < classBean.getAttributes().size()-1){
            project.Attribute att = classBean.getAttributes().get(selRow);
            classBean.getAttributes().remove(att);
            classBean.getAttributes().add(classBean.getAttributes().size(), att);
            refresh();
            projectPanel.refreshFrames();
            projectPanel.setEdited(true);
        }
        lAttributes.setSelectedIndex(classBean.getAttributes().size()-1);
    }//GEN-LAST:event_mMoveBottonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JList lAttributes;
    private javax.swing.JMenuItem mAddAttribute;
    private javax.swing.JMenuItem mEditAttribute;
    private javax.swing.JMenuItem mMoveBotton;
    private javax.swing.JMenuItem mMoveDown;
    private javax.swing.JMenuItem mMoveTop;
    private javax.swing.JMenuItem mMoveUp;
    private javax.swing.JMenuItem mRemAttribute;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane sAttributes;
    // End of variables declaration//GEN-END:variables
}
