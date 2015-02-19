package project;

/**
 *
 * @author marcos morise
 */
public class AttributeFloat extends Attribute{
    private Boolean indexed;
    private Float startValue;
    private String maskFormat;
    
    /**
     * Construtor
     */
    public AttributeFloat() {
        indexed = true;
        startValue = 0.0f;
        maskFormat = "#,##0.###";
    }

    /**
     * @return clone AttributeFloat
     */
    @Override
    public Attribute clone(){
        AttributeFloat clone = new AttributeFloat();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setIndexed(this.getIndexed().booleanValue());
        clone.setStartValue(this.getStartValue().floatValue());
        clone.setMaskformat(this.getMaskFormat().toString());
        return clone;
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.FLOAT;
    }
    /**
     * @return the Java type 
     */
    @Override
    public String getJavaType() {
        return "Float";
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
     * @return the startValue
     */
    public Float getStartValue() {
        return startValue;
    }
    /**
     * @return the startValue formatted
     */
    public String getStartValueF() {
        return new java.text.DecimalFormat("#,##0.###").format(startValue);
    }

    /**
     * @param startValue the startValue to set
     */
    public void setStartValue(Float startValue) {
        this.startValue = startValue;
    }
    /**
     * @param startValue the startValue formatted to set
     */
    public void setStartValueF(String startValue) {
        this.startValue = new java.text.DecimalFormat("#,##0.###").parse(startValue, new java.text.ParsePosition(0)).floatValue();
    }

    /**
     * @return the maskformat
     */
    public String getMaskFormat() {
        return maskFormat;
    }

    /**
     * @param maskFormat the maskformat to set
     */
    public void setMaskformat(String maskFormat) {
        this.maskFormat = maskFormat;
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"FLOAT,"+(description.isEmpty()?" ":description)+","+indexed+","+startValue+","+(maskFormat.isEmpty()?" ":maskFormat)+"\n";
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
        this.startValue = Float.parseFloat(tokens.nextToken());
        this.maskFormat = tokens.nextToken().trim();
    }
}
