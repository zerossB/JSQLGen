package project;

/**
 *
 * @author marcos morise
 */
public class AttributeInteger extends Attribute{

    public static enum KeyType {
        NONE,
        UNIQUE,
        INDEXED
    }

    private KeyType key;
    private Integer startValue;

    /**
     * Construtor
     */
    public AttributeInteger() {
        key = KeyType.NONE;
        startValue = 0;
    }

    /**
     * @return clone AttributeInteger
     */
    @Override
    public Attribute clone(){
        AttributeInteger clone = new AttributeInteger();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setKey(this.getKey());
        clone.setStartValue(this.getStartValue().intValue());
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
        return Type.INTEGER;
    }
    
    /**
     * @return the Java type 
     */
    @Override
    public String getJavaType() {
        return "Integer";
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
    public Integer getStartValue() {
        return startValue;
    }

    /**
     * @param startValue the startValue to set
     */
    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }

    /**
     * @return if is Useb by other classes
     */
    @Override
    public Boolean isUsed(Project project, ClassBean classBean){
        //Verifica se outra classe possui referência
        Boolean isUsed = super.isUsed(project, classBean);
        if(!isUsed && isUnique()){
            for(project.ClassBean classBeanVerif : project.getClassBeans()){
                java.util.List<project.Attribute> attList = classBeanVerif.getAttributesOfType(Attribute.Type.OBJECT_LIST);
                for(project.Attribute attVerif : attList){
                    project.AttributeObjectList attObj = (project.AttributeObjectList)attVerif;
                    if(attObj.getClassRef().equals(classBean.getName())){
                        isUsed = true;
                    }
                }
                attList = classBeanVerif.getAttributesOfType(Attribute.Type.OBJECT);
                for(project.Attribute attVerif : attList){
                    project.AttributeObject attObj = (project.AttributeObject)attVerif;
                    if(attObj.getClassRef().equals(classBean.getName())){
                        isUsed = true;
                    }
                }
            }
        }
        return isUsed;
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
        String string = name+":"+"INTEGER,"+(description.isEmpty()?" ":description)+","+key.ordinal()+","+startValue+"\n";
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
        this.startValue = Integer.parseInt(tokens.nextToken());
    }
}
