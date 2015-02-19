package project;

/**
 *
 * @author marcos morise
 */
public class AttributeFixedList extends Attribute{
    
    public static enum TypeStore{
        ITEM,
        INDEX,
    }
    
    private TypeStore typeStore;
    private String listOptions;

    /**
     * Construtor
     */
    public AttributeFixedList() {
        typeStore = TypeStore.ITEM;
        listOptions = "";
    }

    /**
     * @return clone AttributeFixedList
     */
    @Override
    public Attribute clone(){
        AttributeFixedList clone = new AttributeFixedList();
        clone.setName(this.getName().toString());
        clone.setDescription(this.getDescription().toString());
        clone.setTypeStore(this.getTypeStore());
        clone.setListOptions(this.getListOptions().toString());
        return clone;
    }

    /**
     * @return the type
     */
    @Override
    public Type getType() {
        return Type.FIXED_LIST;
    }

    /**
     * @return the Java type 
     */
    @Override
    public String getJavaType() {
        if(getTypeStore()==TypeStore.INDEX)
            return "Integer";
        else
            return "String";
    }

    /**
     * @return the maxLength
     */
    public Integer getMaxLength() {
        Integer maxLength = 0;
        java.util.StringTokenizer options = new java.util.StringTokenizer(listOptions,"\n");
        while(options.hasMoreTokens()){
            Integer tokenLen = options.nextToken().length();
            if(tokenLen>maxLength)
                maxLength = tokenLen;
        }
        return maxLength;
    }
    /**
     * @return the typeStore
     */
    public TypeStore getTypeStore() {
        return typeStore;
    }

    /**
     * @param typeStore the typeStore to set
     */
    public void setTypeStore(TypeStore typeStore) {
        this.typeStore = typeStore;
    }
    

    /**
     * @return the listOptions
     */
    public String getListOptions() {
        return listOptions;
    }

    /**
     * @param listOptions the listOptions to set
     */
    public void setListOptions(String listOptions) {
        this.listOptions = listOptions.replaceAll("\"","");
    }

    /**
     * @return Attribute to String
     */
    @Override
    public String toString(){
        String string = name+":"+"FIXED_LIST,"+(description.isEmpty()?" ":description)+","+typeStore.ordinal()+","+(listOptions.isEmpty()?" ":listOptions.replace('\n', ';'))+"\n";
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
        
        this.typeStore = TypeStore.values()[Integer.parseInt(tokens.nextToken())];
        this.listOptions = tokens.nextToken().replace(';','\n').trim();
    }
}
