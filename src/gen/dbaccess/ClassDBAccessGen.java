package gen.dbaccess;

/**
 *
 * @author marcos
 */
public class ClassDBAccessGen {

    private project.Project projectEdit;

    /**
     * Construtor
     * @param projectEdit
     */
    public ClassDBAccessGen(project.Project projectEdit){
        this.projectEdit = projectEdit;
    }

    public String getJavaCode(){
        String code = "";
        code += "package dbaccess;\n";
        code += "/**\n";
        code += " * Classe responsável pela conexão e desconexão com o SGBD\n";
        code += " * @author jsqlgen\n";
        code += " */\n";
        code += "public class DBAccess {\n";
        code += "    private java.sql.Connection connection;\n";
        code += "    /** \n";
        code += "     * Construtor\n";
        code += "     * @throws ClassNotFoundException throws when JDBC Driver is not found\n";
        code += "     * @throws java.sql.SQLException throws when Driver Manager can't connect to Database\n";
        code += "     */\n";
        code += "    public DBAccess() throws ClassNotFoundException, java.sql.SQLException {\n";
        code += "        String iniFile = \"./db.ini\";\n";
        code += "        if (new java.io.File(iniFile).exists()) {\n";
        code += "            try {\n";
        code += "                java.util.Properties propFile = new java.util.Properties();\n";
        code += "                propFile.load(new java.io.FileInputStream(iniFile));\n";
        code += "                connect(propFile);\n";
        code += "            } catch(java.io.IOException e){\n";
        code += "                throw new java.sql.SQLException(\"File Ini doesn't exist.\");\n";
        code += "            }\n";
        code += "        } else {\n";
        code += "            String drv = \"org.apache.derby.jdbc.EmbeddedDriver\";\n";
        code += "            String url = \"jdbc:derby:./data;territory=pt_BR;create=TRUE\";\n";
        code += "            String user = \"admin\";\n";
        code += "            String password = \"admin\";\n";
        code += "            connect(drv, url, user, password);\n";
        code += "            createTables();\n";
        code += "        }\n";
        code += "    }\n";
        code += "\n";
        code += "    /**\n";
        code += "     * Conecta no Banco de Dados\n";
        code += "     * @param drv\n";
        code += "     * @param url\n";
        code += "     * @param user\n";
        code += "     * @param password\n";
        code += "     * @throws ClassNotFoundException throws when JDBC Driver is not found\n";
        code += "     * @throws java.sql.SQLException throws when Driver Manager can't connect to Database\n";
        code += "     */\n";
        code += "    private void connect(String drv, String url, String user, String password) throws ClassNotFoundException, java.sql.SQLException {\n";
        code += "        Class.forName(drv);\n";
        code += "        connection = java.sql.DriverManager.getConnection(url, user, password);\n";
        code += "    }\n";
        code += "\n";
        code += "    /**\n";
        code += "     * \n";
        code += "     * @param fileIni \n";
        code += "     * @throws ClassNotFoundException throws when JDBC Driver is not found\n";
        code += "     * @throws java.sql.SQLException throws when Driver Manager can't connect to Database\n";
        code += "     */\n";
        code += "    private void connect(java.util.Properties fileIni) throws ClassNotFoundException, java.sql.SQLException {\n";
        code += "        connect(fileIni.getProperty(\"driver\"), fileIni.getProperty(\"url\"), fileIni.getProperty(\"user\"), fileIni.getProperty(\"password\"));\n";
        code += "    }\n";
        code += "\n";
        code += "    /**\n";
        code += "     * Desconecta no Banco de Dados\n";
        code += "     * @throws java.sql.SQLException throws when Driver Manager can't disconnect from Database\n";
        code += "     */\n";
        code += "    public void disconnect() throws java.sql.SQLException {\n";
        code += "        connection.commit();\n";
        code += "        connection.close();\n";
        code += "    }\n";
        code += "\n";
        code += "    /** @return the connection */\n";
        code += "    public java.sql.Connection getConnection() {\n";
        code += "        return connection;\n";
        code += "    }\n";
        code += "    /** Cria tabelas \n";
        code += "     * @throws java.sql.SQLException throws when Driver Manager can't disconnect from Database\n";
        code += "     */\n";
        code += "    private void createTables() throws java.sql.SQLException {\n";
        code += "        String sql = \"SELECT COUNT(*) FROM SYS.SYSTABLES WHERE TABLETYPE='T'\";\n";
        code += "        java.sql.Statement statement = connection.createStatement();\n";
        code += "        java.sql.ResultSet resultSet = statement.executeQuery(sql);\n";
        code += "        if (resultSet.next()) {\n";
        code += "            if (resultSet.getInt(1) == 0) {\n";
        code += "                if (javax.swing.JOptionPane.showConfirmDialog(null, \"O sistema criará o banco de dados.\\nClique em <OK> para continuar.\", \"Aviso\", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE) == javax.swing.JOptionPane.OK_OPTION) {\n";
        for(project.ClassBean classBean : projectEdit.getClassBeans()){
            if(!classBean.isDependent(projectEdit)){
                code += "                    "+classBean.getName().toLowerCase()+"."+classBean.getName()+"DAO.createTable(connection);\n";
            }
        }
        code += "                } else {\n";
        code += "                    statement.close();\n";
        code += "                    this.disconnect();\n";
        code += "                    System.exit(1);\n";
        code += "                }\n";
        code += "            }\n";
        code += "        }\n";
        code += "        statement.close();\n";
        code += "    }\n";
        code += "}\n";
        return code;
    }
}
