package project;

/**
 *
 * @author marcos morise
 */
public class AttributeString extends Attribute {

    public static enum KeyType {
        NONE,
        UNIQUE,
        INDEXED
    }
    
    private KeyType key;
    private String startValue;
    private Integer length;
    private String maskFormat;

    /**
     * Construtor
     */
    public AttributeString() {
        key = KeyType.NONE;
        startValue = "";
        length = 1;
        maskFormat = "";
    }

    /**
     * @return clone AttributeString
     */
    @Override
    public Attribute clone(){
        AttributeString clone = new AttributeString();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setKey(this.getKey());
        clone.setStartValue(this.getStartValue().toString());
        clone.setLength(this.getLength().intValue());
        clone.setMaskFormat(this.getMaskFormat().toString());
        return clone;
    }

    /**
     * @param project
     * @param classBean
     * @param name 
     */
    @Override
    public void rename(Project project, ClassBean classBean, String name){
        name = name.substring(0,1).toLowerCase()+name.substring(1,name.length());
        if(this.getKey()==KeyType.UNIQUE){
            for(ClassBean classSeek : project.getClassBeans()){
                java.util.List<Attribute> attsObj = classSeek.getAttributesOfType(Attribute.Type.OBJECT);
                for(Attribute att : attsObj){
                    AttributeObject attObj = (AttributeObject)att;
                    if(attObj.getClassRef().compareTo(classBean.getName())==0 && attObj.getAttribLookUp().compareTo(this.getName())==0){
                        attObj.setAttribLookUp(name);
                    }
                }
            }
        }
        this.setName(name);
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.STRING;
    }
    
    /**
     * @return the Java type 
     */
    @Override
    public String getJavaType() {
        return "String";
    }

    /**
     * @return the key
     */
    public KeyType getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(KeyType key) {
        this.key = key;
    }

    /**
     * @return the startValue
     */
    public String getStartValue() {
        return startValue;
    }

    /**
     * @param startValue the startValue to set
     */
    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    /**
     * @return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return the maskFormat
     */
    public String getMaskFormat() {
        return maskFormat;
    }

    /**
     * @param maskFormat the maskFormat to set
     */
    public void setMaskFormat(String maskFormat) {
        this.maskFormat = maskFormat;
    }

    /**
     * @return if is Unique
     */
    @Override
    public Boolean isUnique() {
        if(getKey()==KeyType.UNIQUE)
            return true;
        else
            return false;
    }
    /**
     * @return if is Indexed
     */
    @Override
    public Boolean isIndexed() {
        if(getKey()==KeyType.INDEXED)
            return true;
        else
            return false;
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"STRING,"+(description.isEmpty()?" ":description)+","+key.ordinal()+","+(startValue.isEmpty()?" ":startValue)+","+length+","+(maskFormat.isEmpty()?" ":maskFormat)+"\n";
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
        
        this.key = KeyType.values()[Integer.parseInt(tokens.nextToken())];
        this.startValue = tokens.nextToken().trim();
        this.length = Integer.parseInt(tokens.nextToken());
        this.maskFormat = tokens.nextToken().trim();
    }
}
