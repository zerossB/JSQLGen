package project;

/**
 *
 * @author marcos morise
 */
public class AttributeBoolean extends Attribute{
    public enum KeyType{
        NONE, 
        INDEXED_FIELD
    }

    private Boolean indexed;
    private Boolean initialValue;
    
    /**
     * Construtor
     */
    public AttributeBoolean() {
        indexed = false;
        initialValue = false;
    }

    /**
     * @return clone AttributeBoolean
     */
    @Override
    public Attribute clone(){
        AttributeBoolean clone = new AttributeBoolean();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setIndexed(this.getIndexed().booleanValue());
        clone.setInitialValue(this.getInitialValue().booleanValue());
        return clone;
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
    
    /**
     * @return the Java type 
     */
    @Override
    public String getJavaType() {
        return "Boolean";
    }
    
    /**
     * @return the Indexed
     */
    @Override
    public Boolean isIndexed() {
        return indexed;
    }
    public Boolean getIndexed() {
        return indexed;
    }

    /**
     * @param indexed true is indexed | false isn't indexed
     */
    public void setIndexed(Boolean indexed) {
        this.indexed = indexed;
    }

    /**
     * @return the initialValue
     */
    public Boolean getInitialValue() {
        return initialValue;
    }

    /**
     * @param initialValue the initialValue to set
     */
    public void setInitialValue(Boolean initialValue) {
        this.initialValue = initialValue;
    }
    
    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"BOOLEAN,"+(description.isEmpty()?" ":description)+","+indexed+","+initialValue+"\n";
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
        
        this.indexed = Boolean.parseBoolean(tokens.nextToken());
        this.initialValue = Boolean.parseBoolean(tokens.nextToken());
    }

}
