package gen.classbean;

/**
 *
 * @author marcos
 */
public class AttributeImageGen extends AttributeGen{

    private project.AttributeImage att;
    
    public AttributeImageGen(project.Attribute att){
        this.att = (project.AttributeImage)att;
    }
    
    @Override
    public String genDeclaration(){
        String code = "";
        code += "    private javax.swing.ImageIcon "+att.getName()+";\n";
        return code;
    }
    @Override
    public String genInstance(){
        String code = "";
        code += "        "+att.getName()+" = new javax.swing.ImageIcon();\n";
        return code;
    }
    @Override
    public String genMethods(){
        String code = "";
        code += "    /**\n";
        code += "     * @return "+att.getNameU()+"\n";
        code += "     */\n";
        code += "    public javax.swing.ImageIcon get"+att.getNameU()+"() { return "+att.getName()+"; }\n";
        code += "    /**\n";
        code += "     * @param formatName - a String containg the informal name of the format.(PNG,JPEG,GIF)\n";
        code += "     * @return the ByteArrayInputStream representing the "+att.getNameU()+".\n";
        code += "     */\n";
        code += "    public java.io.ByteArrayInputStream get"+att.getNameU()+"(String formatName) {\n";
        code += "        try {\n";
        code += "            java.awt.image.BufferedImage bf = new java.awt.image.BufferedImage("+att.getName()+".getIconWidth(),"+att.getName()+".getIconHeight(),java.awt.image.BufferedImage.TYPE_INT_ARGB);\n";
        code += "            bf.createGraphics().drawImage("+att.getName()+".getImage(), null, null);\n";
        code += "            java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();\n";
        code += "            //javax.imageio.ImageIO.write(bf,\"PNG\",output);\n";
        code += "            javax.imageio.ImageIO.write(bf,formatName,output);\n";
        code += "            return new java.io.ByteArrayInputStream(output.toByteArray());\n";
        code += "        } catch(java.io.IOException e){\n";
        code += "            return new java.io.ByteArrayInputStream(new byte[0]);\n";
        code += "        } catch(IllegalArgumentException  e){\n";
        code += "            return new java.io.ByteArrayInputStream(new byte[0]);\n";
        code += "        }\n";
        code += "    }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" "+att.getNameU()+" to set\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(javax.swing.ImageIcon "+att.getName()+") { this."+att.getName()+" = "+att.getName()+"; }\n";

        code += "    /**\n";
        code += "     * @param "+att.getName()+" "+att.getNameU()+" to set\n";
        code += "     * @param width "+att.getNameU()+" fixed width\n";
        code += "     */\n";
        code += "    public void set"+att.getNameU()+"(javax.swing.ImageIcon "+att.getName()+", int width) {\n";
        code += "        width = Math.abs(width);\n";
        code += "        if(width==0) width = "+att.getName()+".getIconWidth();\n";
        code += "        int height = (width*"+att.getName()+".getIconHeight()) / "+att.getName()+".getIconWidth();\n";
        code += "        java.awt.image.BufferedImage bf = new java.awt.image.BufferedImage(width, height,java.awt.image.BufferedImage.TYPE_INT_ARGB);\n";
        code += "        bf.createGraphics().drawImage("+att.getName()+".getImage(), 0, 0, width, height, null);\n";
        code += "        this."+att.getName()+" = new javax.swing.ImageIcon(bf);\n";
        code += "    }\n";

        code += "    /**\n";
        code += "     * Salva "+att.getNameU()+" em Arquivo\n";
        code += "     * @param format - JPG,PNG,GIF\n";
        code += "     * @throws java.io.IOException \n";
        code += "     */\n";
        code += "    public void save"+att.getNameU()+"(String path, String format) throws java.io.IOException{\n";
        code += "        java.awt.image.BufferedImage bf = new java.awt.image.BufferedImage("+att.getName()+".getIconWidth(),"+att.getName()+".getIconHeight(),java.awt.image.BufferedImage.TYPE_INT_ARGB);\n";
        code += "        bf.createGraphics().drawImage("+att.getName()+".getImage(), null, null);\n";
        code += "        java.io.File file = new java.io.File(path);\n";
        code += "        javax.imageio.ImageIO.write(bf,format,file);\n";
        code += "    }\n";
        return code;
    }
}
