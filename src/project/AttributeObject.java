package project;

/**
 *
 * @author marcos morise
 */
public class AttributeObject extends Attribute{
    private String classRef;
    private String attribLookUp;

    /**
     * Construtor
     */
    public AttributeObject() {
        classRef = "";
        attribLookUp = "";
    }

    /**
     * @return clone AttributeObject
     */
    @Override
    public Attribute clone(){
        AttributeObject clone = new AttributeObject();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setClassRef(this.getClassRef().toString());
        clone.setAttribLookUp(this.getAttribLookUp().toString());
        return clone;
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.OBJECT;
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
     * @return the attribLookUp
     */
    public String getAttribLookUp() {
        return attribLookUp;
    }
    /**
     * @return the attribLookUp
     */
    public String getAttribLookUpU() {
        return (attribLookUp.isEmpty() ? "" : attribLookUp.substring(0,1).toUpperCase()+attribLookUp.substring(1,attribLookUp.length()));
    }

    /**
     * @param attribLookUp the attribLookUp to set
     */
    public void setAttribLookUp(String attribLookUp) {
        this.attribLookUp = attribLookUp;
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"OBJECT,"+(description.isEmpty()?" ":description)+","+(classRef.isEmpty()?" ":classRef)+","+(attribLookUp.isEmpty()?" ":attribLookUp)+"\n";
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
        this.attribLookUp = tokens.nextToken().trim();
    }
}
