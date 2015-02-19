package gen.dao;

/**
 *
 * @author marcos
 */
public class AttributeImageGen extends AttributeGen{

    private project.ClassBean classBean;
    private project.AttributeImage att;
    
    public AttributeImageGen(project.ClassBean classBean, project.Attribute att){
        this.classBean = classBean;
        this.att = (project.AttributeImage)att;
    }
    
    @Override
    public String genCreateTable(){
        String code = "";
        code += "                   + \""+att.getName()+" BLOB NOT NULL,\"\n";
        return code;
    }
    @Override
    public String genInsert(){
        String code = "";
        code += att.getName()+",";
        return code;
    }
    @Override
    public String genUpdate(){
        String code = "";
        code += "                   + \""+att.getName()+" = ?,\"\n";
        return code;
    }
    @Override
    public String genStatementSet(int idx, String varName){
        String code = "";
        code += "        statement.setBlob("+idx+", "+varName+".get"+att.getNameU()+"(\"PNG\"));\n";
        return code;
    }
    @Override
    public String genRs2Obj(){
        String code = "";
        code += "        try {\n";
        code += "            "+classBean.getNameL()+"Load.set"+att.getNameU()+"(new javax.swing.ImageIcon(javax.imageio.ImageIO.read(resultSet.getBlob(\""+att.getName()+"\").getBinaryStream())));\n";
        code += "        } catch (java.io.IOException e) {\n";
        code += "            "+classBean.getNameL()+"Load.set"+att.getNameU()+"(new javax.swing.ImageIcon());\n";
        code += "        } catch (NullPointerException e) {\n";
        code += "            "+classBean.getNameL()+"Load.set"+att.getNameU()+"(new javax.swing.ImageIcon());\n";
        code += "        }\n";
        return code;
    }
    @Override
    public String genSelect(){
        String code = "";
        code += att.getName()+",";
        return code;
    }
    @Override
    public String genView(){
        String code = "";
        code +=  classBean.getName()+"."+att.getName()+",";
        return code;
    }
}
