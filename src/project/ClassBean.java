/*
 project/ClassBean.java é parte do programa JSQLGen

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
 *
 * @author marcos morise
 */
public class ClassBean {
    private String name;
    private Integer x,y;
    private java.util.List<Attribute> attributes;

    public ClassBean(){
        name = "";
        attributes = new java.util.ArrayList<Attribute>();
    }

    /**
     * @return clone ClassBean
     */
    @Override
    public ClassBean clone(){
        ClassBean clone = new ClassBean();
        clone.setName(name);
        clone.setX(x);
        clone.setY(y);
        for(Attribute att : attributes){
            if(att.getType()!=Attribute.Type.OBJECT_LIST)
                clone.getAttributes().add(att.clone());
        }
        return clone;
    }

    /**
     * Retorna pacote da classe
     * @param project
     * @return the package
     */
    public String getPackage(Project project){
        String pkg = "";
        if(isDependent(project))
            pkg += project.getClassBean(getOwner(project)).getPackage(project) +".";
        pkg += getName().toLowerCase();
        return pkg;
    }

    /**
     * Retorna o nível de hierarquia
     * @param project
     * @return the hierarchy level
     */
    public int getLevel(Project project){
        return new java.util.StringTokenizer(getPackage(project),".").countTokens();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the name with the first char lowercase
     */
    public String getNameL() {
        return (name.isEmpty() ? "" : name.substring(0,1).toLowerCase()+name.substring(1,name.length()));
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
            this.name = name.substring(0,1).toUpperCase()+name.substring(1,name.length());
        }
    }

    /**
     * Renomeia considerando as dependências
     * @param name the name to set
     * @param project projeto que pertence a classe
     */
    public void rename(Project project, String name) {
        if(!name.isEmpty()){
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

            String nameTemp = "";
            for(char c:name.toCharArray()){
                if((c>='A' && c<='Z') || (c>='a' && c<='z') || (c>='0' && c<='9'))
                    nameTemp += c;
                else
                    nameTemp += '_';
            }

            name = nameTemp.substring(0,1).toUpperCase()+nameTemp.substring(1,nameTemp.length());

            //se dependente renomeia classe de referência, referência de composição
            if(isDependent(project)){
                project.ClassBean classOwner = project.getClassBean(getOwner(project));
                java.util.List<project.Attribute> attList = classOwner.getAttributesOfType(Attribute.Type.OBJECT_LIST);
                for(project.Attribute att : attList){
                    project.AttributeObjectList attObjList = (project.AttributeObjectList)att;
                    if(attObjList.getClassRef().compareTo(getName())==0){
                        attObjList.setClassRef(name);
                    }
                }
            }

            //Verifica se outra classe possui referência por agregação
            for(project.ClassBean classBean : project.getClassBeans()){
                java.util.List<project.Attribute> attList = classBean.getAttributesOfType(Attribute.Type.OBJECT);
                for(project.Attribute att : attList){
                    project.AttributeObject attObj = (project.AttributeObject)att;
                    if(attObj.getClassRef().compareTo(getName())==0){
                        attObj.setClassRef(name);
                    }
                }
            }
            this.name = name;
        }
    }

    /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(Integer x) {
        this.x = (x>34?x:34);
    }

    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Integer y) {
        this.y = (y>17?y:17);
    }

    /**
     * @return the attributes
     */
    public java.util.List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(java.util.List<Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the attributes
     */
    public java.util.List<Attribute> getAttributesOfType(project.Attribute.Type type) {
        java.util.List<project.Attribute> list = new java.util.ArrayList<project.Attribute>();
        for(Attribute attribute : attributes)
            if(attribute.getType().equals(type))
                list.add(attribute);
        return list;
    }

    /**
     * @param name nome do atributo
     * @return atributo
     */
    public project.Attribute getAttribute(String name){
        for(Attribute attribute : attributes){
            if(attribute.getName().compareTo(name)==0){
                return attribute;
            }
        }
        return null;
    }

    /**
     * @return lista com atributos Primary Key
     */
    public Attribute getPrimaryKey(){
        java.util.List<Attribute> list = getUnique();
        if(!list.isEmpty()) return list.get(0);
        else return null;
    }

    /**
     * @return lista com atributos UNIQUE
     */
    public java.util.List<project.Attribute> getUnique(){
        java.util.List<project.Attribute> list = new java.util.ArrayList<project.Attribute>();
        for(Attribute attribute : attributes){
            if(attribute.isUnique()){
                list.add(attribute);
            }
        }
        return list;
    }

    /**
     * @return lista com atributos INDEX
     */
    public java.util.List<project.Attribute> getIndexed(){
        java.util.List<project.Attribute> list = new java.util.ArrayList<project.Attribute>();
        for(Attribute attribute : attributes){
            if(attribute.isIndexed()){
                list.add(attribute);
            }
        }
        return list;
    }

    /**
     * @param project projeto que pertence a classe
     * @return se é dependente de alguma classe
     */
    public String getOwner(Project project){
        String owner = "";
        boolean find = false;
        int i=0;
        while(!find && i < project.getClassBeans().size()){
            int j = 0;
            java.util.List<Attribute> list = project.getClassBeans().get(i).getAttributesOfType(Attribute.Type.OBJECT_LIST);
            while(!find && j < list.size()){
                AttributeObjectList att = (AttributeObjectList)list.get(j);
                if(att.getClassRef().compareTo(this.getName())==0){
                    find = true;
                    owner = project.getClassBeans().get(i).getName();
                }
                j++;
            }
            i++;
        }
        return owner;
    }

    /**
     * @param project projeto que pertence a classe
     * @return se é dependente de alguma classe
     */
    public boolean isDependent(Project project){
        boolean find = false;
        int i=0;
        while(!find && i < project.getClassBeans().size()){
            int j = 0;
            java.util.List<Attribute> attList = project.getClassBeans().get(i).getAttributesOfType(Attribute.Type.OBJECT_LIST);
            while(!find && j < attList.size()){
                AttributeObjectList att = (AttributeObjectList)attList.get(j);
                if(att.getClassRef().compareTo(this.getName())==0)
                    find = true;
                j++;
            }
            i++;
        }
        return find;
    }

    /**
     * @param project projeto que pertence a classe
     * @return se é utilizada como objeto ou lista de objeto de alguma outra classe
     */
    public boolean isUsed(Project project){
        //Verifica se outra classe possui referência a essa
        Boolean isUsed = false;
        for(project.ClassBean classBean : project.getClassBeans()){
            java.util.List<project.Attribute> attList = classBean.getAttributesOfType(Attribute.Type.OBJECT);
            for(project.Attribute att : attList){
                project.AttributeObject attObj = (project.AttributeObject)att;
                if(attObj.getClassRef().compareTo(getName())==0){
                    isUsed = true;
                }
            }
            attList = classBean.getAttributesOfType(Attribute.Type.OBJECT_LIST);
            for(project.Attribute att : attList){
                project.AttributeObjectList attObj = (project.AttributeObjectList)att;
                if(attObj.getClassRef().compareTo(getName())==0){
                    isUsed = true;
                }
            }
        }
        return isUsed;
    }
    /**
     * @param project projeto que pertence a classe
     * @return lista de classes que utilizam a classe
     */
    public java.util.List<project.ClassBean> getClassesUses(Project project){
        //Verifica se outra classe possui referência a essa
        java.util.List<project.ClassBean> classesUses = new java.util.ArrayList<project.ClassBean>();
        for(project.ClassBean bean : project.getClassBeans()){
            java.util.List<project.Attribute> attList = bean.getAttributesOfType(Attribute.Type.OBJECT);
            for(project.Attribute att : attList){
                project.AttributeObject attObj = (project.AttributeObject)att;
                if(attObj.getClassRef().compareTo(getName())==0){
                    classesUses.add(bean);
                }
            }
        }
        return classesUses;
    }

    /**
     * @param name nome do atributo
     * @return o classBean já possui o atributo
     */
    public boolean hasAttribute(String name){
        for(Attribute att : attributes){
            if(att.getName().compareToIgnoreCase(name)==0){
                return true;
            }
        }
        return false;
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = "\n";
        string += name+"{\n";
        string += x+"\n";
        string += y+"\n";
        for(Attribute att : attributes){
            string += att.toString();
        }
        string += "}JSQLGen";
        return string;
    }

    /**
     * @param string to convert
     */
    public void fromString(String string){
        if(string.contains("JSQLGen")){
            java.util.StringTokenizer tokens = new java.util.StringTokenizer(string.trim(),"{");
            this.name = tokens.nextToken().trim();
            String content = tokens.nextToken();
            tokens = new java.util.StringTokenizer(content,"\n");
            this.x = Integer.parseInt(tokens.nextToken());
            this.y = Integer.parseInt(tokens.nextToken());
            while(tokens.hasMoreTokens()){
                content = tokens.nextToken();
                Attribute att = null;
                if(content.contains("IDENTITY"))    { att = new AttributeIdentity(); }
                if(content.contains("INTEGER"))     { att = new AttributeInteger(); }
                if(content.contains("FLOAT"))       { att = new AttributeFloat(); }
                if(content.contains("BOOLEAN"))     { att = new AttributeBoolean(); }
                if(content.contains("STRING"))      { att = new AttributeString(); }
                if(content.contains("DATE"))        { att = new AttributeDate(); }
                if(content.contains("TIME"))        { att = new AttributeTime(); }
                if(content.contains("IMAGE"))       { att = new AttributeImage(); }
                if(content.contains("FIXED_LIST"))  { att = new AttributeFixedList(); }
                if(content.contains("OBJECT"))      { att = new AttributeObject(); }
                if(content.contains("OBJ_LIST")) { att = new AttributeObjectList(); }
                if(att!=null){
                    att.fromString(content);
                    this.attributes.add(att);
                }
            }
        }
    }
}
