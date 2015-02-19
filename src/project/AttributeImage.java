package project;

/**
 *
 * @author marcos morise
 */
public class AttributeImage extends Attribute{
    private Boolean fixedWidth;
    private Integer width;

    /**
     * Construtor
     */
    public AttributeImage() {
        fixedWidth = true;
        width = 300;
    }


    /**
     * @return clone AttributeImage
     */
    @Override
    public Attribute clone(){
        AttributeImage clone = new AttributeImage();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setFixedWidth(this.getFixedWidth().booleanValue());
        clone.setWidth(this.getWidth().intValue());
        return clone;
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.IMAGE;
    }
    
    /**
     * @return the Java type 
     */
    @Override
    public String getJavaType() {
        return "javax.swing.ImageIcon";
    }

    /**
     * @return the fixedWidth
     */
    public Boolean isFixedWidth() {
        return fixedWidth;
    }
    public Boolean getFixedWidth() {
        return fixedWidth;
    }

    /**
     * @param fixedWidth the fixedWidth to set
     */
    public void setFixedWidth(Boolean fixedWidth) {
        this.fixedWidth = fixedWidth;
    }

    /**
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }


    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"IMAGE,"+(description.isEmpty()?" ":description)+","+fixedWidth+","+width+"\n";
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
        
        this.fixedWidth = Boolean.parseBoolean(tokens.nextToken());
        this.width = Integer.parseInt(tokens.nextToken());
    }
}
