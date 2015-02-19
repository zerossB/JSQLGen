package project;

/**
 *
 * @author marcos morise
 */
public class AttributeTime extends Attribute {

    private Boolean indexed;
    private Boolean useCurrentTime;

    /**
     * Construtor
     */
    public AttributeTime() {
        indexed = false;
        useCurrentTime = false;
    }
    /**
     * @return clone AttributeTime
     */
    @Override
    public Attribute clone(){
        AttributeTime clone = new AttributeTime();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setIndexed(this.getIndexed().booleanValue());
        clone.setUseCurrentTime(this.getUseCurrentTime().booleanValue());
        return clone;
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.TIME;
    }
    
    /**
     * @return the Java type 
     */
    @Override
    public String getJavaType() {
        return "java.util.Date";
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
     * @return the useCurrentTime
     */
    public Boolean isUseCurrentTime() {
        return useCurrentTime;
    }
    public Boolean getUseCurrentTime() {
        return useCurrentTime;
    }

    /**
     * @param useCurrentTime the useCurrentTime to set
     */
    public void setUseCurrentTime(boolean useCurrentTime) {
        this.useCurrentTime = useCurrentTime;
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"TIME,"+(description.isEmpty()?" ":description)+","+indexed+","+useCurrentTime+"\n";
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
        this.useCurrentTime = Boolean.parseBoolean(tokens.nextToken());
    }
}
