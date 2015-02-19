package gen.classbean;

/**
 *
 * @author marcos
 */
public class ClassBeanGen {

    private project.Project proj;
    private project.ClassBean classBean;

    /**
     * Construtor
     * @param proj
     * @param classBean 
     */
    public ClassBeanGen(project.Project proj, project.ClassBean classBean){
        this.proj = proj;
        this.classBean = classBean;
    }
    
    /**
     * 
     * @param att
     * @return 
     */
    private AttributeGen getAttributeGen(project.Attribute att){
        switch(att.getType()){
            case IDENTITY   : return new AttributeIdentityGen(att);
            case INTEGER    : return new AttributeIntegerGen(att);
            case FLOAT      : return new AttributeFloatGen(att);
            case BOOLEAN    : return new AttributeBooleanGen(att);
            case STRING     : return new AttributeStringGen(att);
            case DATE       : return new AttributeDateGen(att);
            case TIME       : return new AttributeTimeGen(att);
            case IMAGE      : return new AttributeImageGen(att);
            case FIXED_LIST : return new AttributeFixedListGen(att);
            case OBJECT     : return new AttributeObjectGen(att,proj);
            case OBJECT_LIST: return new AttributeObjectListGen(att,proj);
            default: return null;
        }
    }
    
    public String getJavaCode(){
        String code = "";
        code += "package "+classBean.getPackage(proj)+";\n";
        code += "/**\n";
        code += " *\n";
        code += " * @author JSQLGen\n";
        code += " */\n";
        code += "public final class "+classBean.getName()+" {\n";
      
        java.util.List<project.Attribute> listFixedList = classBean.getAttributesOfType(project.Attribute.Type.FIXED_LIST);
        code += "\n";
        code += (listFixedList.isEmpty()?"":"    /** Atributos estaticos */\n");
        for(project.Attribute att : listFixedList){
            code += new AttributeFixedListGen(att).genStaticList();
        }

        code += "\n";
        code += "    /** Atributos */\n";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genDeclaration();
        }

        code += "\n";
        code += "    /** Construtor */\n";
        code += "    public "+classBean.getName()+"() {\n";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genInstance();
        }
        
        code += "    }\n";
        code += "    /** Metodos */\n";
        for(project.Attribute att : classBean.getAttributes()){
            code += "\n";
            code += getAttributeGen(att).genMethods();
        }
        code += "}\n";
        return code;
    }
}
