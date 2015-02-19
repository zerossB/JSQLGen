package project;

/**
 *
 * @author marcos morise
 */
public class AttributeDate extends Attribute {

    private Boolean indexed;
    private Boolean useCurrentDate;

    /**
     * Construtor
     */
    public AttributeDate() {
        indexed = false;
        useCurrentDate = false;
    }

    /**
     * @return clone AttributeDate
     */
    @Override
    public Attribute clone(){
        AttributeDate clone = new AttributeDate();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setIndexed(this.getIndexed().booleanValue());
        clone.setUseCurrentDate(this.getUseCurrentDate().booleanValue());
        return clone;
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.DATE;
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
     * @return the useCurrentDate
     */
    public Boolean isUseCurrentDate() {
        return useCurrentDate;
    }
    public Boolean getUseCurrentDate() {
        return useCurrentDate;
    }
    /**
     * @param useCurrentDate the useCurrentDate to set
     */
    public void setUseCurrentDate(Boolean useCurrentDate) {
        this.useCurrentDate = useCurrentDate;
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"DATE,"+(description.isEmpty()?" ":description)+","+indexed+","+useCurrentDate+"\n";
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
        this.useCurrentDate = Boolean.parseBoolean(tokens.nextToken());
    }
}
