/*
 * ProjectGen.java
 *
 * Created on 02/08/2011, 22:43:24
 */
package gui;

/**
 *
 * @author marcos
 */
public class ProjectGen extends javax.swing.JDialog {

    private project.Project projectEdit;
    private String currentDirectory;

    /**
     * Creates new form ProjectGen 
     * @param projectEdit 
     */
    public ProjectGen(java.awt.Window parent, project.Project projectEdit, String currentDirectory) {
        super(parent);
        this.projectEdit = projectEdit;
        this.currentDirectory = currentDirectory;
        initComponents();
        refreshTree();
        setVisible(true);
    }

    /**
     * Atualiza árvore de códigos
     */
    private void refreshTree(){
        //Pega o indice do elemento selecionado
        javax.swing.tree.DefaultMutableTreeNode treeNodeSel = (javax.swing.tree.DefaultMutableTreeNode)tProject.getLastSelectedPathComponent();
        int idx = 0;
        int idxSel = 0;
        if(treeNodeSel!=null){
            switch(treeNodeSel.getLevel()){
                case(1):
                    idx = treeNodeSel.getParent().getIndex(treeNodeSel)+1;
                    idxSel = treeNodeSel.getParent().getIndex(treeNodeSel)+1;
                    break;
                case(2):
                    idx = treeNodeSel.getParent().getParent().getIndex(treeNodeSel.getParent())+1;
                    idxSel = treeNodeSel.getParent().getParent().getIndex(treeNodeSel.getParent())+1;
                    idxSel += treeNodeSel.getParent().getIndex(treeNodeSel)+1;
                    break;
            }
        }

        //Atualiza a árvore
        tProject.removeAll();
        javax.swing.tree.DefaultMutableTreeNode root = new javax.swing.tree.DefaultMutableTreeNode("src");
        for(project.ClassBean classBean : projectEdit.getClassBeans()){
            if(!classBean.isDependent(projectEdit)){
                addNode(root, classBean);
            }
        }
        tProject.setModel(new javax.swing.tree.DefaultTreeModel(root));
        tProject.treeDidChange();

        tProject.collapseRow(0);
        tProject.expandRow(0);

        //Expande a arvore e seleciona o item que estava selecionado
        if(treeNodeSel!=null){
            tProject.expandRow(idx);
            tProject.setSelectionRow(idxSel);
        }
    }

    private void addNode(javax.swing.tree.DefaultMutableTreeNode root, project.ClassBean classBean){
        javax.swing.tree.DefaultMutableTreeNode node = new javax.swing.tree.DefaultMutableTreeNode(classBean.getPackage(projectEdit));
        node.add(new javax.swing.tree.DefaultMutableTreeNode(classBean.getName()+".java"));
        node.add(new javax.swing.tree.DefaultMutableTreeNode(classBean.getName()+"DAO.java"));
        node.add(new javax.swing.tree.DefaultMutableTreeNode(classBean.getName()+"Form.java"));
        node.add(new javax.swing.tree.DefaultMutableTreeNode(classBean.getName()+"Form.form"));
        node.add(new javax.swing.tree.DefaultMutableTreeNode(classBean.getName()+"Tab.java"));
        node.add(new javax.swing.tree.DefaultMutableTreeNode(classBean.getName()+"Tab.form"));
        root.add(node);
        java.util.List<project.Attribute> attList = classBean.getAttributesOfType(project.Attribute.Type.OBJECT_LIST);
        for(project.Attribute att : attList){
            project.AttributeObjectList attObjList = (project.AttributeObjectList) att;
            project.ClassBean classRef = projectEdit.getClassBean(attObjList.getClassRef());
            addNode(root, classRef);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sProject = new javax.swing.JScrollPane();
        tProject = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        cProjectType = new javax.swing.JComboBox();
        cDatabaseType = new javax.swing.JComboBox();
        bGenerate = new javax.swing.JButton();
        sCode = new javax.swing.JScrollPane();
        tCode = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        tProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tProjectMouseClicked(evt);
            }
        });
        tProject.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tProjectKeyReleased(evt);
            }
        });
        sProject.setViewportView(tProject);

        getContentPane().add(sProject, java.awt.BorderLayout.LINE_START);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        cProjectType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aplicativo Java Swing", "Aplicativo Java JSP" }));
        cProjectType.setBorder(javax.swing.BorderFactory.createTitledBorder("Projeto"));
        jPanel1.add(cProjectType);

        cDatabaseType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Apache Derby", "HSQLDB" }));
        cDatabaseType.setBorder(javax.swing.BorderFactory.createTitledBorder("Banco de Dados"));
        jPanel1.add(cDatabaseType);

        bGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/cup.png"))); // NOI18N
        bGenerate.setText("Gerar projeto NetBeans");
        bGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGenerateActionPerformed(evt);
            }
        });
        jPanel1.add(bGenerate);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        tCode.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tCode.setRows(5);
        sCode.setViewportView(tCode);

        getContentPane().add(sCode, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(640, 480));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tProjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProjectMouseClicked
        javax.swing.tree.DefaultMutableTreeNode treeNodeSel = (javax.swing.tree.DefaultMutableTreeNode)tProject.getLastSelectedPathComponent();
        if(treeNodeSel!=null){
            tCode.setText("");
            if(treeNodeSel.getLevel()==2){
                project.ClassBean classGen;
                switch(treeNodeSel.getParent().getIndex(treeNodeSel)){
                    case 0:
                        tCode.setText(new gen.classbean.ClassBeanGen(projectEdit,projectEdit.getClassBean(treeNodeSel.toString().replace(".java", ""))).getJavaCode());
                        break;
                    case 1:
                        tCode.setText(new gen.dao.ClassDAOGen(projectEdit,projectEdit.getClassBean(treeNodeSel.toString().replace("DAO.java", ""))).getJavaCode());
                        break;
                    case 2:
                        tCode.setText(new gen.gui.ClassFormGen(projectEdit,projectEdit.getClassBean(treeNodeSel.toString().replace("Form.java", ""))).getJavaCode());
                        break;
                    case 3:
                        tCode.setText(new gen.gui.XMLFormGen(projectEdit,projectEdit.getClassBean(treeNodeSel.toString().replace("Form.form", ""))).getXMLCode());
                        break;
                    case 4:
                        classGen = projectEdit.getClassBean(treeNodeSel.toString().replace("Tab.java", ""));
                        if(!classGen.isDependent(projectEdit))
                            tCode.setText(new gen.gui.ClassTabViewGen(projectEdit,classGen).getJavaCode());
                        else
                            tCode.setText(new gen.gui.ClassTabDependentGen(projectEdit,classGen).getJavaCode());
                        break;
                    case 5:
                        classGen = projectEdit.getClassBean(treeNodeSel.toString().replace("Tab.form", ""));
                        if(!classGen.isDependent(projectEdit))
                            tCode.setText(new gen.gui.XMLTabViewGen(projectEdit,projectEdit.getClassBean(treeNodeSel.toString().replace("Tab.form", ""))).getXMLCode());
                        else
                            tCode.setText(new gen.gui.XMLTabDependentGen(projectEdit,projectEdit.getClassBean(treeNodeSel.toString().replace("Tab.form", ""))).getXMLCode());
                        break;
                }
            }
        }
    }//GEN-LAST:event_tProjectMouseClicked

    private void tProjectKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tProjectKeyReleased
        tProjectMouseClicked(null);
    }//GEN-LAST:event_tProjectKeyReleased

    private void bGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGenerateActionPerformed
        javax.swing.JFileChooser browser = new javax.swing.JFileChooser();
        browser.setDialogTitle("Selecione o diretório para criação do projeto:");
        browser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        browser.setMultiSelectionEnabled(false);
        if(currentDirectory==null || currentDirectory.length()==0)
            currentDirectory="./";
        browser.setCurrentDirectory(new java.io.File(currentDirectory));

        if (browser.showSaveDialog(this) == javax.swing.JFileChooser.CANCEL_OPTION) {
            if(currentDirectory==null || currentDirectory.length()==0)
                currentDirectory="./";
            return;
        }
        if (browser.getSelectedFile().isDirectory()) {
            try{
                switch(cDatabaseType.getSelectedIndex()){
                    case 0: new gen.nbproject.nbProjectGen(projectEdit, browser.getSelectedFile().getPath()); break;
                    case 1: new gen.nbproject.nbProjectGen_hsqldb(projectEdit, browser.getSelectedFile().getPath()); break;
                }
                
                javax.swing.JOptionPane.showMessageDialog(this, "Projeto "+projectEdit.getName()+" gerado com sucesso em:\n"+browser.getSelectedFile().getPath(), "Mensagem", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_bGenerateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bGenerate;
    private javax.swing.JComboBox cDatabaseType;
    private javax.swing.JComboBox cProjectType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane sCode;
    private javax.swing.JScrollPane sProject;
    private javax.swing.JTextArea tCode;
    private javax.swing.JTree tProject;
    // End of variables declaration//GEN-END:variables
}
