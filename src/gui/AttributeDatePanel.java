package gui;

/**
 *
 * @author marcos
 */
public class AttributeDatePanel extends AttributePanel {

    /** Creates new form AttributeDatePanel */
    public AttributeDatePanel() {
        initComponents();
    }

    @Override
    public void setAtribute(project.Attribute attribute){
        project.AttributeDate attDate = (project.AttributeDate)attribute;
        cIndexed.setSelected(attDate.isIndexed());
        cUseCurrentDate.setSelected(attDate.isUseCurrentDate());
    }
    
    @Override
    public project.AttributeDate getAtribute(){
        project.AttributeDate att = new project.AttributeDate();
        att.setIndexed(cIndexed.isSelected());
        att.setUseCurrentDate(cUseCurrentDate.isSelected());
        return att;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pLabels = new javax.swing.JPanel();
        lName = new javax.swing.JLabel();
        lUseCurrentDate = new javax.swing.JLabel();
        pFields = new javax.swing.JPanel();
        cIndexed = new javax.swing.JCheckBox();
        cUseCurrentDate = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout(10, 0));

        pLabels.setPreferredSize(new java.awt.Dimension(150, 51));
        pLabels.setLayout(new java.awt.GridLayout(2, 0, 0, 5));

        lName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lName.setText("Tipo de Chave");
        pLabels.add(lName);

        lUseCurrentDate.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lUseCurrentDate.setText("Usar data atual");
        pLabels.add(lUseCurrentDate);

        add(pLabels, java.awt.BorderLayout.WEST);

        pFields.setLayout(new java.awt.GridLayout(2, 0, 0, 5));

        cIndexed.setText("Indexado");
        pFields.add(cIndexed);
        pFields.add(cUseCurrentDate);

        add(pFields, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cIndexed;
    private javax.swing.JCheckBox cUseCurrentDate;
    private javax.swing.JLabel lName;
    private javax.swing.JLabel lUseCurrentDate;
    private javax.swing.JPanel pFields;
    private javax.swing.JPanel pLabels;
    // End of variables declaration//GEN-END:variables
}
