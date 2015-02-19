package project;

/**
 *
 * @author marcos morise
 */
public class AttributeIdentity extends Attribute {

    private Integer initialValue;
    private Integer incrementValue;

    /**
     * Construtor
     */
    public AttributeIdentity() {
        initialValue = 1;
        incrementValue = 1;
    }

    /**
     * @return clone AttributeIdentity
     */
    @Override
    public Attribute clone(){
        AttributeIdentity clone = new AttributeIdentity();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setInitialValue(this.getInitialValue().intValue());
        clone.setIncrementValue(this.getIncrementValue().intValue());
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
        for(ClassBean classSeek : project.getClassBeans()){
            java.util.List<Attribute> attsObj = classSeek.getAttributesOfType(Attribute.Type.OBJECT);
            for(Attribute att : attsObj){
                AttributeObject attObj = (AttributeObject)att;
                if(attObj.getClassRef().compareTo(classBean.getName())==0 && attObj.getAttribLookUp().compareTo(this.getName())==0){
                    attObj.setAttribLookUp(name);
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
        return Type.IDENTITY;
    }
    
    /**
     * @return the Java type 
     */
    @Override
    public String getJavaType() {
        return "Integer";
    }

    /**
     * @return the initialValue
     */
    public Integer getInitialValue() {
        return initialValue;
    }

    /**
     * @param initialValue the initialValue to set
     */
    public void setInitialValue(Integer initialValue) {
        this.initialValue = initialValue;
    }

    /**
     * @return the incrementValue
     */
    public Integer getIncrementValue() {
        return incrementValue;
    }

    /**
     * @param incrementValue the incrementValue to set
     */
    public void setIncrementValue(Integer incrementValue) {
        this.incrementValue = incrementValue;

    }

    /**
     * @return if is Useb by other classes
     */
    @Override
    public Boolean isUsed(Project project, ClassBean classBean){
        //Verifica se outra classe possui referência
        Boolean isUsed = super.isUsed(project, classBean);
        if(!isUsed){
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
     * @return the Unique
     */
    @Override
    public Boolean isUnique() {
        return true;
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"IDENTITY,"+(description.isEmpty()?" ":description)+","+initialValue+","+incrementValue+"\n";
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
        
        this.initialValue = Integer.parseInt(tokens.nextToken());
        this.incrementValue = Integer.parseInt(tokens.nextToken());
    }
}
