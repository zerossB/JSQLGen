/*
  project/AttributeObjectList.java é parte do programa JSQLGen

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
package project;

/**
 *
 * @author marcos morise
 */
public class AttributeObjectList extends Attribute{
    private String classRef;

    /**
     * Construtor
     */
    public AttributeObjectList() {
        classRef = "";
    }

    /**
     * @return clone AttributeObjectList
     */
    @Override
    public Attribute clone(){
        AttributeObjectList clone = new AttributeObjectList();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setClassRef(this.getClassRef().toString());
        return clone;
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.OBJECT_LIST;
    }

    /**
     * @return the classRef
     */
    public String getClassRef() {
        return classRef;
    }

    /**
     * @param classRef the classRef to set
     */
    public void setClassRef(String classRef) {
        this.classRef = classRef;
    }


    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"OBJ_LIST,"+(description.isEmpty()?" ":description)+","+(classRef.isEmpty()?" ":classRef)+"\n";
        return string;
    }

    /**
     * @param string to convert
     */
    @Override
    public void fromString(String string){
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(string,":");
        String name = tokens.nextToken();
        String content = tokens.nextToken();
        tokens = new java.util.StringTokenizer(content,",");
        this.name = name;
        tokens.nextToken();
        this.description = tokens.nextToken().trim();
        
        this.classRef = tokens.nextToken().trim();
    }
}
