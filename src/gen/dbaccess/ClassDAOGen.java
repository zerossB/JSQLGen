package gen.dbaccess;

/**
 *
 * @author marcos
 */
public class ClassDAOGen {

    /**
     * Construtor
     */
    public ClassDAOGen(){
    }
    
    public String getJavaCode(){
        String code = "";
        code += "package dbaccess;\n";
        code += "/**\n";
        code += " *\n";
        code += " * @author jsqlgen\n";
        code += " */\n";
        code += "public abstract class DAO {\n";
        code += "    /**\n";
        code += "     * \n";
        code += "     * @param connection\n";
        code += "     * @param query\n";
        code += "     * @return \n";
        code += "     */\n";
        code += "    protected static java.util.List execQuery(java.sql.Connection connection, String query) {\n";
        code += "        try {\n";
        code += "            java.sql.Statement statement = connection.createStatement();\n";
        code += "            java.sql.ResultSet rs = statement.executeQuery(query);\n";
        code += "            java.util.List result = new java.util.ArrayList();\n";
        code += "            while (rs.next()) {\n";
        code += "                java.util.List line = new java.util.ArrayList();\n";
        code += "                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {\n";
        code += "                    switch (rs.getMetaData().getColumnType(i)) {\n";
        code += "                        case java.sql.Types.DATE:\n";
        code += "                            line.add(rs.getDate(i));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.TIME:\n";
        code += "                            line.add(rs.getTime(i));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.BOOLEAN:\n";
        code += "                        case java.sql.Types.BIT:\n";
        code += "                            line.add(rs.getBoolean(i));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.NUMERIC:\n";
        code += "                        case java.sql.Types.DECIMAL:\n";
        code += "                        case java.sql.Types.FLOAT:\n";
        code += "                        case java.sql.Types.DOUBLE:\n";
        code += "                        case java.sql.Types.REAL:\n";
        code += "                            line.add(new Float(rs.getFloat(i)));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.INTEGER:\n";
        code += "                            line.add(new Integer(rs.getInt(i)));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.TINYINT:\n";
        code += "                            line.add(new Byte(rs.getByte(i)));\n";
        code += "                            break;\n";
        code += "                        default:\n";
        code += "                            line.add(rs.getString(i));\n";
        code += "                            break;\n";
        code += "                    }\n";
        code += "                }\n";
        code += "                if (line.size() == 1) {\n";
        code += "                    result.add(line.get(0));\n";
        code += "                } else {\n";
        code += "                    result.add(line);\n";
        code += "                }\n";
        code += "            }\n";
        code += "            statement.close();\n";
        code += "            return (result);\n";
        code += "        } catch (java.sql.SQLException e) {\n";
        code += "            System.out.println(e.getMessage());\n";
        code += "            System.out.println(\"\\nFalha na leitura do banco de dados !\");\n";
        code += "            return (null);\n";
        code += "        }\n";
        code += "    }\n";
        code += "    /**\n";
        code += "     * @param connection\n";
        code += "     * @param query\n";
        code += "     * @return \n";
        code += "     */\n";
        code += "    protected static java.util.List execQueryF(java.sql.Connection connection, String query) {\n";
        code += "        try {\n";
        code += "            java.sql.Statement statement = connection.createStatement();\n";
        code += "            java.sql.ResultSet rs = statement.executeQuery(query);\n";
        code += "            java.util.List result = new java.util.ArrayList();\n";
        code += "            while (rs.next()) {\n";
        code += "                java.util.List line = new java.util.ArrayList();\n";
        code += "                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {\n";
        code += "                    switch (rs.getMetaData().getColumnType(i)) {\n";
        code += "                        case java.sql.Types.DATE:\n";
        code += "                            line.add(new java.text.SimpleDateFormat(\"dd/MM/yyyy\").format(rs.getDate(i)));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.TIME:\n";
        code += "                            line.add(new java.text.SimpleDateFormat(\"HH:mm:ss\").format(rs.getTime(i)));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.BOOLEAN:\n";
        code += "                        case java.sql.Types.BIT:\n";
        code += "                            line.add(rs.getBoolean(i));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.NUMERIC:\n";
        code += "                        case java.sql.Types.DECIMAL:\n";
        code += "                        case java.sql.Types.FLOAT:\n";
        code += "                        case java.sql.Types.DOUBLE:\n";
        code += "                        case java.sql.Types.REAL:\n";
        code += "                            line.add(new java.text.DecimalFormat(\"#,##0.00\").format(new Float(rs.getFloat(i))));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.INTEGER:\n";
        code += "                            line.add(new Integer(rs.getInt(i)));\n";
        code += "                            break;\n";
        code += "                        case java.sql.Types.TINYINT:\n";
        code += "                            line.add(new Byte(rs.getByte(i)));\n";
        code += "                            break;\n";
        code += "                        default:\n";
        code += "                            line.add(rs.getString(i));\n";
        code += "                            break;\n";
        code += "                    }\n";
        code += "                }\n";
        code += "                if (line.size() == 1) {\n";
        code += "                    result.add(line.get(0));\n";
        code += "                } else {\n";
        code += "                    result.add(line);\n";
        code += "                }\n";
        code += "            }\n";
        code += "            statement.close();\n";
        code += "            return (result);\n";
        code += "        } catch (java.sql.SQLException e) {\n";
        code += "            System.out.println(e.getMessage());\n";
        code += "            System.out.println(\"\\nFalha na leitura do banco de dados !\");\n";
        code += "            return (null);\n";
        code += "        }\n";
        code += "    }\n";
        code += "    \n";
        code += "}\n";
        return code;
    }        
}
