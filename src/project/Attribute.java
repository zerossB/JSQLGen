/*
 project/Attribute.java é parte do programa JSQLGen

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
 * @author marcos
 */
public class Attribute {

    public static enum Type{
        NONE,
        IDENTITY,
        INTEGER,
        FLOAT,
        BOOLEAN,
        STRING,
        DATE,
        TIME,
        IMAGE,
        FIXED_LIST,
        OBJECT,
        OBJECT_LIST
    }

    protected String name;
    protected String description;
    /**
     * Construtor
     */
    public Attribute(){
        name = "";
        description = "";
    }

    /**
     * @return clone Attribute
     */
    @Override
    public Attribute clone(){
        Attribute clone = new Attribute();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        return clone;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return Type.NONE;
    }
    
    /**
     * @return the Java type 
     */
    public String getJavaType() {
        return "";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the name with the first char uppercase
     */
    public String getNameU() {
        return (name.isEmpty() ? "" : name.substring(0,1).toUpperCase()+name.substring(1,name.length()));
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
        //this.name = name;
    }

    /**
     * Renomeia Atributo alterando as referências,
     * deve ser sobrescrito somente em atributos que possibilitam chave UNIQUE
     * @param project
     * @param classBean
     * @param name
     */
    public void rename(Project project, ClassBean classBean, String name){
        this.setName(name);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description.trim();
    }

    /**
     * @return if is Useb by other classes
     */
    public Boolean isUsed(Project project, ClassBean classBean){
        //Verifica se outra classe possui referência
        if(isUnique()){
            Boolean isUsed = false;
            for(project.ClassBean classBeanVerif : project.getClassBeans()){
                java.util.List<project.Attribute> attList = classBeanVerif.getAttributesOfType(Attribute.Type.OBJECT);
                for(project.Attribute attVerif : attList){
                    project.AttributeObject attObj = (project.AttributeObject)attVerif;
                    if(attObj.getClassRef().equals(classBean.getName()) &&
                       attObj.getAttribLookUp().equals(getName())){
                        isUsed = true;
                    }
                }
            }
            return isUsed;
        } else {
            return false;
        }
    }

    /**
     * @return if is UNIQUE
     */
    public Boolean isUnique(){
        return false;
    }

    /**
     * @return if is INDEXED
     */
    public Boolean isIndexed(){
        return false;
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"NONE,"+(description.isEmpty()?" ":description)+"\n";
        return string;
    }

    /**
     * @param string to convert
     */
    public void fromString(String string){
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(string,":");
        String name = tokens.nextToken();
        String content = tokens.nextToken();
        tokens = new java.util.StringTokenizer(content,",");
        this.name = name;
        tokens.nextToken();
        this.description = tokens.nextToken().trim();
    }
}
