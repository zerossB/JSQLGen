/*
  gui/ProjectPanel.java é parte do programa JSQLGen

 (c)Copyright 2005~2011 Marcos Morise.

    JSQLGen é um software livre; você pode redistribui-lo e/ou
    modifica-lo dentro dos termos da Licença Pública Geral GNU como
    publicada pela Fundação do Software Livre (FSF); na versão 3 da
    Licença.

    Este programa é distribuido na esperança que possa ser util,
    mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO
    a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
    Licença Pública Geral GNU para maiores detalhes.

    Você deve ter recebido uma cópia da Licença Pública Geral GNU
    junto com este programa, se não, veja em:
    <http://www.gnu.org/licenses/>
 */
package gui;

/**
 *
 * @author marcos morise
 */
public final class ProjectPanel extends javax.swing.JDesktopPane {

    private java.awt.Window parent;
    private project.Project projectEdit;
    private int xMouse, yMouse;
    private int cellHeight = 17;
    private int frameWidth = cellHeight * 10;
    private String currentDirectory = "./";
    private boolean edited = false;

    /** Creates new form ProjectPanel */
    public ProjectPanel(java.awt.Window parent, project.Project projectEdit) {
        super();
        this.parent = parent;
        this.projectEdit = projectEdit;
        initComponents();
        this.setComponentPopupMenu(popupMenuDesktop);
        refreshFrames();
        refreshCoordinates();
    }

    /** @return the projectEdit */
    public project.Project getProjectEdit() { return projectEdit; }

    /** @param projectEdit the projectEdit to set */
    public void setProjectEdit(project.Project projectEdit) { this.projectEdit = projectEdit; }

    /** @return the currentDirectory */
    public String getCurrentDirectory() { return currentDirectory; }

    /** @param currentDirectory the currentDirectory to set */
    public void setCurrentDirectory(String currentDirectory) { this.currentDirectory = currentDirectory; }

    /** @return the isEdited */
    public boolean isEdited() { return edited; }

    /** @param isEdited the isEdited to set */
    public void setEdited(boolean edited) { this.edited = edited; }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        //background
        g.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, cellHeight/2));
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //relationship lines
        if(this.getAllFrames().length>0){
            //pega altura da barra de titulos
            javax.swing.plaf.basic.BasicInternalFrameUI frameUI = (javax.swing.plaf.basic.BasicInternalFrameUI) this.getAllFrames()[0].getUI();
            int titleSize = frameUI.getNorthPane().getPreferredSize().height;
            int x0;
            int y0;
            int x1;
            int y1;

            //Desenha linhas do relacionamento
            for (project.ClassBean classBean : projectEdit.getClassBeans()) {
                //OBJECT relationship lines
                java.util.List<project.Attribute> listAttObject = classBean.getAttributesOfType(project.Attribute.Type.OBJECT);
                for (project.Attribute att : listAttObject) {
                    project.AttributeObject attObject = (project.AttributeObject) att;
                    project.ClassBean classRef = projectEdit.getClassBean(attObject.getClassRef());
                    project.Attribute attRef = classRef.getAttribute(attObject.getAttribLookUp());

                    x0 = classBean.getX();
                    x1 = classRef.getX();
                    y0 = classBean.getY() + (classBean.getAttributes().indexOf(att)+1) * 17 + titleSize;
                    y1 = classRef.getY() + (classRef.getAttributes().indexOf(attRef)+1) * 17 + titleSize;
                    int heightX0 = cellHeight;
                    int heightX1 = cellHeight;
                    if(x0+frameWidth/2<x1){
                        x0 = x0+frameWidth;
                        heightX0 = heightX0 * -1;
                    } else {
                        if(x1+frameWidth/2<x0){
                            x1 = x1+frameWidth;
                            heightX1 = heightX1 * -1;
                        }
                    }
                    g.setColor(java.awt.Color.BLACK);
                    g.drawLine(x0-heightX0, y0, x0, y0);
                    g.drawLine(x0-heightX0, y0, x1-heightX1, y1);
                    g.drawLine(x1-heightX1, y1, x1, y1);
                    g.setColor(java.awt.Color.WHITE);
                    g.fillOval(x0+((classBean.getX()+frameWidth/2)<x1?0:-heightX0/2), y0-cellHeight/4, cellHeight/2, cellHeight/2);
                    g.setColor(java.awt.Color.BLACK);
                    g.drawOval(x0+((classBean.getX()+frameWidth/2)<x1?0:-heightX0/2), y0-cellHeight/4, cellHeight/2, cellHeight/2);
                    g.drawString("1", x0-heightX0/2, y0-cellHeight/3);
                    g.drawString("1", x1-heightX1/2, y1-cellHeight/3);
                }

                //OBJECT LIST relationship lines
                java.util.List<project.Attribute> listAttObjectList = classBean.getAttributesOfType(project.Attribute.Type.OBJECT_LIST);
                for (project.Attribute att : listAttObjectList) {
                    project.AttributeObjectList attObject = (project.AttributeObjectList) att;
                    project.ClassBean classRef = projectEdit.getClassBean(attObject.getClassRef());
                    x0 = classBean.getX();
                    x1 = classRef.getX();
                    y0 = classBean.getY() + (classBean.getAttributes().indexOf(att)+1) * 17 + titleSize;
                    y1 = classRef.getY() + titleSize;
                    int heightX0 = cellHeight;
                    int heightX1 = cellHeight;
                    if(x0+frameWidth/2<x1){
                        x0 = x0+frameWidth;
                        heightX0 = heightX0 * -1;
                    } else {
                        if(x1+frameWidth/2<x0){
                            x1 = x1+frameWidth;
                            heightX1 = heightX1 * -1;
                        }
                    }
                    g.setColor(java.awt.Color.BLACK);
                    g.drawLine(x0-heightX0, y0, x0, y0);
                    g.drawLine(x0-heightX0, y0, x1-heightX1, y1);
                    g.drawLine(x1-heightX1, y1, x1, y1);
                    g.fillOval(x0+((classBean.getX()+frameWidth/2)<x1?0:-heightX0/2), y0-cellHeight/4, cellHeight/2, cellHeight/2);
                    g.drawString("1", x0-heightX0/2, y0-cellHeight/3);
                    g.drawString("*", x1-heightX1/2, y1-cellHeight/3);
                }

            }
        }
    }

    /**
     * Atualiza frames
     */
    protected void refreshFrames() {
        this.removeAll();

        for (project.ClassBean classBean : projectEdit.getClassBeans()) {
            javax.swing.JInternalFrame frame = new javax.swing.JInternalFrame();
            javax.swing.plaf.basic.BasicInternalFrameUI frameUI = (javax.swing.plaf.basic.BasicInternalFrameUI) frame.getUI();

            frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            frame.setResizable(false);
            frame.setBounds(classBean.getX(), classBean.getY(), frameWidth, cellHeight*(3+classBean.getAttributes().size()));
            frame.setLayout(new java.awt.BorderLayout());
            frame.setTitle(classBean.getName());

            if(classBean.getUnique().size() > 0){
                frame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/pill"+(!classBean.isDependent(projectEdit)?".png":"_go.png")))); // NOI18N
                frameUI.getNorthPane().setToolTipText(classBean.getPackage(projectEdit)+"."+classBean.getName());
            } else {
                frame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/pill_delete.png"))); // NOI18N
                frameUI.getNorthPane().setToolTipText(classBean.getPackage(projectEdit)+"."+classBean.getName()+" - A classe deve ter pelo menos um atributo com chave única");
            }
            frameUI.getNorthPane().setComponentPopupMenu(popupMenuFrame);
            frame.add(new ClassBeanPanel(parent, this, projectEdit, classBean, cellHeight));
            frame.addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentMoved(java.awt.event.ComponentEvent evt) {
                    frameComponentMoved(evt);
                }
            });
            frame.setVisible(true);
            this.add(frame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        }
        repaint();
    }

    /**
     * Ajusta coordenadas dos classBean
     */
    protected void refreshCoordinates() {
        int maxX = 0;
        int maxY = 0;
        int maxAtt = 0;
        
        if (projectEdit.getClassBeans().size() == this.getAllFrames().length) {
            for (project.ClassBean classBean : projectEdit.getClassBeans()) {
                javax.swing.JInternalFrame frame = getFrame(classBean.getName());
                classBean.setX(frame.getX());
                classBean.setY(frame.getY());
                frame.setLocation(classBean.getX(), classBean.getY());
                if(frame.getX()>maxX) maxX = frame.getX();
                if(frame.getY()>maxY) maxY = frame.getY();
                if(classBean.getAttributes().size()>maxAtt) maxAtt = classBean.getAttributes().size();
            }
            this.setPreferredSize(new java.awt.Dimension( maxX+(frameWidth*2), maxY+((cellHeight*(3+maxAtt))*2) ));
            parent.repaint();
        }
    }

    /**
     * Retorna Frame com o Título passado como parâmetro
     */
    private javax.swing.JInternalFrame getFrame(String name) {
        javax.swing.JInternalFrame[] frames = this.getAllFrames();
        int i = 0;
        while (i < frames.length) {
            if (frames[i].getTitle().equals(name)) {
                return frames[i];
            } else {
                i++;
            }
        }
        return null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuDesktop = new javax.swing.JPopupMenu();
        mNewBean = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mPaste = new javax.swing.JMenuItem();
        popupMenuFrame = new javax.swing.JPopupMenu();
        mRenameBean = new javax.swing.JMenuItem();
        mDeleteBean = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mDuplicate = new javax.swing.JMenuItem();
        mCopy = new javax.swing.JMenuItem();
        mCut = new javax.swing.JMenuItem();

        mNewBean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/pill_add.png"))); // NOI18N
        mNewBean.setText("Nova classe");
        mNewBean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mNewBeanActionPerformed(evt);
            }
        });
        popupMenuDesktop.add(mNewBean);
        popupMenuDesktop.add(jSeparator1);

        mPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_paste-16.png"))); // NOI18N
        mPaste.setText("Colar");
        mPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPasteActionPerformed(evt);
            }
        });
        popupMenuDesktop.add(mPaste);

        mRenameBean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/pill_go.png"))); // NOI18N
        mRenameBean.setText("Renomear classe");
        mRenameBean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRenameBeanActionPerformed(evt);
            }
        });
        popupMenuFrame.add(mRenameBean);

        mDeleteBean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/pill_delete.png"))); // NOI18N
        mDeleteBean.setText("Apagar classe");
        mDeleteBean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mDeleteBeanActionPerformed(evt);
            }
        });
        popupMenuFrame.add(mDeleteBean);
        popupMenuFrame.add(jSeparator2);

        mDuplicate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/pill_clone.png"))); // NOI18N
        mDuplicate.setText("Duplicar");
        mDuplicate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mDuplicateActionPerformed(evt);
            }
        });
        popupMenuFrame.add(mDuplicate);

        mCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_copy-16.png"))); // NOI18N
        mCopy.setText("Copiar");
        mCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCopyActionPerformed(evt);
            }
        });
        popupMenuFrame.add(mCopy);

        mCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_cut-16.png"))); // NOI18N
        mCut.setText("Recortar");
        mCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCutActionPerformed(evt);
            }
        });
        popupMenuFrame.add(mCut);

        setAutoscrolls(true);
        setDoubleBuffered(true);
        setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_formMouseMoved

    private void mNewBeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mNewBeanActionPerformed
        String beanName = javax.swing.JOptionPane.showInputDialog(this, "Nome da nova classe:", "Nova classe", javax.swing.JOptionPane.PLAIN_MESSAGE);
        if (beanName != null && beanName.length() > 0 && !projectEdit.isClassBeanExists(beanName)) {
            project.ClassBean newBean = new project.ClassBean();
            newBean.setName(beanName);
            newBean.setX(xMouse);
            newBean.setY(yMouse);
            projectEdit.getClassBeans().add(newBean);
            refreshFrames();
            refreshCoordinates();
            setEdited(true);
        } else {
            if(projectEdit.isClassBeanExists(beanName)){
                javax.swing.JOptionPane.showMessageDialog(this, "Nome de classe inválido ou já existente.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_mNewBeanActionPerformed

    private void mDeleteBeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mDeleteBeanActionPerformed
        project.ClassBean classDel = projectEdit.getClassBean(this.getSelectedFrame().getTitle());
        if (javax.swing.JOptionPane.showConfirmDialog(this, "Deseja apagar a classe "+classDel.getName()+"?", "Confirmação", javax.swing.JOptionPane.OK_CANCEL_OPTION) == javax.swing.JOptionPane.OK_OPTION) {
            if(!classDel.isUsed(projectEdit)){
                projectEdit.getClassBeans().remove(classDel);
                refreshFrames();
                refreshCoordinates();
                setEdited(true);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Não foi possível apagar.\nEsta classe está sendo utilizada por outras.\nRemova todas dependências e tente novamente.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_mDeleteBeanActionPerformed

    private void mRenameBeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRenameBeanActionPerformed
        String beanName = this.getSelectedFrame().getTitle();
        beanName = javax.swing.JOptionPane.showInputDialog(this, "Nome da classe:", beanName);
        if (beanName != null && beanName.length() > 0  && !projectEdit.isClassBeanExists(beanName)) {
            beanName = beanName.substring(0,1).toUpperCase()+beanName.substring(1,beanName.length());
            project.ClassBean classRen = projectEdit.getClassBean(this.getSelectedFrame().getTitle());
            //renomeia classe
            classRen.rename(projectEdit, beanName);
            this.getSelectedFrame().setTitle(classRen.getName());
            setEdited(true);
        }else {
            javax.swing.JOptionPane.showMessageDialog(this, "Nome de classe inválido ou já existente.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mRenameBeanActionPerformed

    private void mDuplicateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mDuplicateActionPerformed
        copy();
        paste();
    }//GEN-LAST:event_mDuplicateActionPerformed

    private void mCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCopyActionPerformed
        copy();
    }//GEN-LAST:event_mCopyActionPerformed

    private void mPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPasteActionPerformed
        paste();
    }//GEN-LAST:event_mPasteActionPerformed

    private void mCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCutActionPerformed
        cut();
    }//GEN-LAST:event_mCutActionPerformed

    private void frameComponentMoved(java.awt.event.ComponentEvent evt) {
        this.getSelectedFrame().setLocation((this.getSelectedFrame().getLocation().x/cellHeight)*cellHeight, (this.getSelectedFrame().getLocation().y/cellHeight)*cellHeight);
        //this.getSelectedFrame().setLocation(this.getSelectedFrame().getLocation().x, this.getSelectedFrame().getLocation().y);
        refreshCoordinates();
        repaint();
        setEdited(true);
    }

    public void copy(){
        if(this.getSelectedFrame()!=null){
            project.ClassBean classCopy = projectEdit.getClassBean(this.getSelectedFrame().getTitle());
            if(classCopy != null){
                java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ClassBeanTransferable(classCopy.toString()),null);
            }
        }
    }

    public void cut(){
        if(this.getSelectedFrame()!=null){
            project.ClassBean classCut = projectEdit.getClassBean(this.getSelectedFrame().getTitle());
            if(classCut != null && !classCut.isUsed(projectEdit)){
                java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ClassBeanTransferable(classCut.toString()),null);
                projectEdit.getClassBeans().remove(classCut);
                refreshFrames();
                refreshCoordinates();
                setEdited(true);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Não foi possível recortar.\nEsta classe está sendo utilizada por outras.\nRemova todas dependências e tente novamente.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void paste(){
        try {
            String beanPaste = (String)java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor);
            if(beanPaste.contains("JSQLGen")){
                project.ClassBean destBean = new project.ClassBean();
                destBean.fromString(beanPaste);
                String beanName = javax.swing.JOptionPane.showInputDialog(this, "Nome da nova classe:",destBean.getName());
                if (beanName != null && beanName.length() > 0 && !projectEdit.isClassBeanExists(beanName)) {

                    destBean.setName(beanName);
                    destBean.setX(destBean.getX()+cellHeight*2);
                    destBean.setY(destBean.getY()+cellHeight*2);

                    //Remove referencias invalidas de objetos
                    String errorMessage = "";
                    java.util.List<project.Attribute> attlist;
                    attlist = destBean.getAttributesOfType(project.Attribute.Type.OBJECT);
                    for(project.Attribute att : attlist){
                        project.AttributeObject attObj = (project.AttributeObject)att;
                        project.ClassBean classRef = projectEdit.getClassBean(attObj.getClassRef());
                        if(classRef==null || classRef.getAttribute(attObj.getAttribLookUp())==null){
                            destBean.getAttributes().remove(att);
                            errorMessage += att.getName()+"\n";
                        }
                    }
                    //Remove referencias invalidas de lista de objetos
                    attlist = destBean.getAttributesOfType(project.Attribute.Type.OBJECT_LIST);
                    for(project.Attribute att : attlist){
                        project.AttributeObjectList attObj = (project.AttributeObjectList)att;
                        project.ClassBean classRef = projectEdit.getClassBean(attObj.getClassRef());
                        if(classRef==null || classRef.isDependent(projectEdit)){
                            destBean.getAttributes().remove(att);
                            errorMessage += att.getName()+"\n";
                        }
                    }
                    if(!errorMessage.isEmpty())
                        javax.swing.JOptionPane.showMessageDialog(this, "Os seguintes atributos foram removidos para evitar problemas de referência!\n"+errorMessage,"Aviso",javax.swing.JOptionPane.WARNING_MESSAGE);
                    projectEdit.getClassBeans().add(destBean);
                    refreshFrames();
                    refreshCoordinates();
                    setEdited(true);
                }
            }
        } catch(java.awt.datatransfer.UnsupportedFlavorException e){
            javax.swing.JOptionPane.showMessageDialog(this, "O Conteúdo da Área de Transferência Inválido!","Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch(java.io.IOException e){
            javax.swing.JOptionPane.showMessageDialog(this, "Erro de E / S !","Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch(java.awt.HeadlessException e){
            javax.swing.JOptionPane.showMessageDialog(this, "Recurso de Área de Transferência não suportada!","Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem mCopy;
    private javax.swing.JMenuItem mCut;
    private javax.swing.JMenuItem mDeleteBean;
    private javax.swing.JMenuItem mDuplicate;
    private javax.swing.JMenuItem mNewBean;
    private javax.swing.JMenuItem mPaste;
    private javax.swing.JMenuItem mRenameBean;
    private javax.swing.JPopupMenu popupMenuDesktop;
    private javax.swing.JPopupMenu popupMenuFrame;
    // End of variables declaration//GEN-END:variables


    /******************************
     * String Selection p/ utilizar no clipboard
     */
    protected static class ClassBeanTransferable implements java.awt.datatransfer.Transferable {

        private String classBean;

        public ClassBeanTransferable(String classBean) {
            this.classBean = classBean;
        }

        // Returns supported flavors
        @Override
        public java.awt.datatransfer.DataFlavor[] getTransferDataFlavors() {
            return new java.awt.datatransfer.DataFlavor[]{java.awt.datatransfer.DataFlavor.stringFlavor};
        }

        // Returns true if flavor is supported
        @Override
        public boolean isDataFlavorSupported(java.awt.datatransfer.DataFlavor flavor) {
            return java.awt.datatransfer.DataFlavor.stringFlavor.equals(flavor);
        }

        // Returns image
        @Override
        public Object getTransferData(java.awt.datatransfer.DataFlavor flavor) throws java.awt.datatransfer.UnsupportedFlavorException, java.io.IOException {
            if (!java.awt.datatransfer.DataFlavor.stringFlavor.equals(flavor)) {
                throw new java.awt.datatransfer.UnsupportedFlavorException(flavor);
            }
            return classBean.toString();
        }
    }
}
