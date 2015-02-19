/*
 project/Project.java é parte do programa JSQLGen

 (c)Copyright 2005~2014 Marcos Morise.

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
package project;

/**
  * @author marcos morise
 */
public class Project {
    private String name;
    private java.util.List<project.ClassBean> classBeans;

    public Project(){
        name = "";
        classBeans = new java.util.ArrayList<project.ClassBean>();
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        if(name.isEmpty()){
            this.name = "";
        } else {
            name = name.trim();
            name = name.replace('ç','c'); name = name.replace('Ç','C');
            name = name.replace('ã','a'); name = name.replace('Ã','A');
            name = name.replace('á','a'); name = name.replace('Á','A');
            name = name.replace('à','a'); name = name.replace('À','A');
            name = name.replace('â','a'); name = name.replace('Â','A');
            name = name.replace('é','e'); name = name.replace('É','E');
            name = name.replace('ê','e'); name = name.replace('Ê','E');
            name = name.replace('í','i'); name = name.replace('Í','I');
            name = name.replace('õ','o'); name = name.replace('Õ','O');
            name = name.replace('ó','o'); name = name.replace('Ó','O');
            name = name.replace('ô','o'); name = name.replace('Ô','O');
            name = name.replace('ú','u'); name = name.replace('Ú','U');
            name = name.replace('ü','u'); name = name.replace('Ü','U');
            this.name = "";
            for(char c:name.toCharArray()){
                if((c>='A' && c<='Z') || (c>='a' && c<='z') || (c>='0' && c<='9'))
                    this.name += c;
                else
                    this.name += '_';

            }
            name = this.name;
            this.name = name.substring(0,1).toLowerCase()+name.substring(1,name.length());
        }
    }

    /**
     * @return the classBeans
     */
    public java.util.List<project.ClassBean> getClassBeans() {
        return classBeans;
    }

    /**
     * @param classBeans the classBeans to set
     */
    public void setClassBeans(java.util.List<project.ClassBean> classBeans) {
        this.classBeans = classBeans;
    }

    /**
     * @param name nome da classe
     * @return classBean
     */
    public project.ClassBean getClassBean(String name){
        for(project.ClassBean classBean : classBeans){
            if(classBean.getName().compareToIgnoreCase(name)==0){
                return classBean;
            }
        }
        return null;
    }
    
    /**
     * @param name nome da classe
     * @return true se a classe já existe || false se a classe NÃO existe
     */
    public Boolean isClassBeanExists(String name){
        for(project.ClassBean classBean : classBeans){
            if(classBean.getName().compareToIgnoreCase(name)==0){
                return true;
            }
        }
        return false;
    }

    /**
     * Save Project to XML file
     * @param name
     * @throws java.io.FileNotFoundException 
     */
    public void saveXML(String name) throws java.io.FileNotFoundException{
        java.beans.XMLEncoder encoder = new java.beans.XMLEncoder(new java.io.BufferedOutputStream(new java.io.FileOutputStream(name)));
        encoder.writeObject(this);
        encoder.close();
    }

    /**
     * Load Project from XML file
     * @param name
     * @throws java.io.FileNotFoundException 
     */
    public void loadXML(String name) throws java.io.FileNotFoundException{
        java.beans.XMLDecoder decoder = new java.beans.XMLDecoder(new java.io.BufferedInputStream(new java.io.FileInputStream(name)));
        Project project = (Project)decoder.readObject();
        this.setName(project.getName());
        this.setClassBeans(project.getClassBeans());
        decoder.close();
    }
    
    /**
     * @return Max Hierarchy Level
     */
    public int getMaxHierarchyLevel() {
        int maxlevel = 0;
        for(ClassBean classBean : classBeans){
            int level = classBean.getLevel(this);
            if(level>maxlevel) maxlevel = level;
        }
        return maxlevel;
    }

    /**
     * Retorna 0 se todas as classes possuem uma chave primária 
     * para o projeto ser gerado corretamente.
     * @return 0 se todas as classes possuem uma chave primária || n classes que não possuem uma chave primária
     */
    public int isValid(){
        //Ordena classBeans
        java.util.List<project.ClassBean> classNotDependent = new java.util.ArrayList<project.ClassBean>();
        java.util.List<project.ClassBean> classDependent = new java.util.ArrayList<project.ClassBean>();
        for(project.ClassBean c : classBeans){
            if(c.getAttributesOfType(project.Attribute.Type.OBJECT).isEmpty()){
                classNotDependent.add(c);
            } else {
                classDependent.add(c);
            }
        }
        classBeans.removeAll(classBeans);
        classBeans.addAll(classNotDependent);
        classBeans.addAll(classDependent);

        //Verifica validade
        int n = 0;
        for(project.ClassBean c : classBeans){
            if(c.getUnique().size()==0){
                n++;
            }
        }
        return n;
    }

}

