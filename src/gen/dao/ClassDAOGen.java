package gen.dao;

/**
 *
 * @author marcos
 */
public class ClassDAOGen {

    protected project.Project proj;
    protected project.ClassBean classBean;

    /**
     * Construtor
     * @param proj
     * @param classBean
     */
    public ClassDAOGen(project.Project proj, project.ClassBean classBean){
        this.proj = proj;
        this.classBean = classBean;
    }

    /**
     *
     * @param att
     * @return
     */
    protected AttributeGen getAttributeGen(project.Attribute att){
        switch(att.getType()){
            case IDENTITY    : return new AttributeIdentityGen(classBean, att);
            case INTEGER     : return new AttributeIntegerGen(classBean, att);
            case FLOAT       : return new AttributeFloatGen(classBean, att);
            case BOOLEAN     : return new AttributeBooleanGen(classBean, att);
            case STRING      : return new AttributeStringGen(classBean, att);
            case DATE        : return new AttributeDateGen(classBean, att);
            case TIME        : return new AttributeTimeGen(classBean, att);
            case IMAGE       : return new AttributeImageGen(classBean, att);
            case FIXED_LIST  : return new AttributeFixedListGen(classBean, att);
            case OBJECT      : return new AttributeObjectGen(proj, classBean, att);
            case OBJECT_LIST : return new AttributeObjectListGen(proj, classBean, att);
            default: return null;
        }
    }

    public String getJavaCode(){
        String code = "";
        code += "package "+classBean.getPackage(proj) +";\n";
        code += "\n";
        code += "/**\n";
        code += " * Class "+classBean.getName()+"DAO responsável pela \n";
        code += " * leitura/escrita de objetos "+classBean.getName()+" no BD\n";
        code += " *\n";
        code += " * @author JSQLGen\n";
        code += " */\n";
        code += "public final class "+classBean.getName()+"DAO extends dbaccess.DAO {\n";
        code += "\n";
        code += "    //*****************************************\n";
        code += "    //CREATE TABLE\n";
        code += "    //*****************************************\n";
        code += "\n";

        //***********************
        //gera createTable
        //***********************
        code += "    /** createTable - Cria a tabela "+classBean.getName()+" no BD\n";
        code += "     * @param connection Conexão com BD\n";
        code += "     * @throws java.sql.SQLException\n";
        code += "     */\n";
        code += "    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {\n";
        code += "        String sql = \"CREATE TABLE "+classBean.getName()+" (\"\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            if(classOwner.getPrimaryKey().getType()==project.Attribute.Type.STRING){
                project.AttributeString att = (project.AttributeString)classOwner.getPrimaryKey();
                code += "                   + \""+classOwner.getNameL()+"Owner VARCHAR("+att.getLength()+") NOT NULL,\"\n";
            } else {
                code += "                   + \""+classOwner.getNameL()+"Owner INT NOT NULL,\"\n";
            }
        }
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genCreateTable();
        }
        code += "                   + \"CONSTRAINT PK_"+classBean.getName()+" PRIMARY KEY ("+classBean.getPrimaryKey().getName()+"),\"\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "                   + \"CONSTRAINT FKC_"+classBean.getName()+"_"+classOwner.getName()+"Owner FOREIGN KEY ("+classOwner.getNameL()+"Owner) REFERENCES "+classOwner.getName()+" ON DELETE CASCADE,\"\n";
        }
        java.util.List<project.Attribute> listAtt = classBean.getAttributesOfType(project.Attribute.Type.OBJECT);
        for(project.Attribute att : listAtt){
            project.AttributeObject attObj = (project.AttributeObject)att;
            code += "                   + \"CONSTRAINT FKA_"+classBean.getName()+"_"+attObj.getNameU()+" FOREIGN KEY ("+attObj.getName()+") REFERENCES "+attObj.getClassRef()+" ON DELETE CASCADE,\"\n";
        }
        code = code.substring(0, code.length()-3)+"\"\n";
        code += "                   + \")\";\n";
        code += "        java.sql.PreparedStatement statement = connection.prepareStatement(sql);\n";
        code += "        statement.executeUpdate();\n";
        code += "        statement.close();\n";
        
        //Cria índices
        for(project.Attribute att : classBean.getAttributes()){
            if(att.isIndexed()){
                code += "\n        sql = \"CREATE INDEX IDX_"+classBean.getName()+"_"+att.getName()+" ON "+classBean.getName()+" ("+att.getName()+")\";\n";
                code += "        statement = connection.prepareStatement(sql);\n";
                code += "        statement.executeUpdate();\n";
                code += "        statement.close();\n";
            }
        }        
        //Cria tabelas dependentes por composição
        listAtt = classBean.getAttributesOfType(project.Attribute.Type.OBJECT_LIST);
        for(project.Attribute att : listAtt){
            project.AttributeObjectList attObjList = (project.AttributeObjectList)att;
            project.ClassBean classRef = proj.getClassBean(attObjList.getClassRef());
            code += "\n        "+classRef.getPackage(proj) +"."+classRef.getName()+"DAO.createTable(connection);\n";
        }
        code += "    }\n";
        code += "\n";

        code += "    //*****************************************\n";
        code += "    //UPDATE\n";
        code += "    //*****************************************\n";
        //***********************
        //obj2stmt
        //***********************
        code += "\n";
        code += "    /** obj2stmt - Transfere o Objeto para o PreparedStatement.\n";
        code += "     * @param connection Conexão com BD\n";
        code += "     * @param "+classBean.getNameL()+"Save "+classBean.getName()+" a ser armazenado\n";
        code += "     * @param statement PreparedStatement contendo SQL\n";
        code += "     * @throws java.sql.SQLException\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "     * @param "+classOwner.getNameL()+"Owner "+classOwner.getName()+" owner\n";
            code += "     */\n";
            code += "    private static void obj2stmt("+classBean.getName()+" "+classBean.getNameL()+"Save, "+classOwner.getPackage(proj)+"."+classOwner.getName()+" "+classOwner.getNameL()+"Owner, java.sql.PreparedStatement statement) throws java.sql.SQLException {\n";
        } else {
            code += "     */\n";
            code += "    private static void obj2stmt("+classBean.getName()+" "+classBean.getNameL()+"Save, java.sql.PreparedStatement statement) throws java.sql.SQLException {\n";
        }
        int idx = 1;
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            if(classOwner.getPrimaryKey().getType()== project.Attribute.Type.STRING)
                code += "        statement.setString("+idx+", "+classOwner.getNameL()+"Owner.get"+classOwner.getPrimaryKey().getNameU()+"());\n";
            else
                code += "        statement.setInt("+idx+", "+classOwner.getNameL()+"Owner.get"+classOwner.getPrimaryKey().getNameU()+"());\n";
            idx++;
        }
        for(project.Attribute att : classBean.getAttributes()){
            String codeTmp = getAttributeGen(att).genStatementSet(idx, classBean.getNameL()+"Save");
            if(!codeTmp.isEmpty()){
                code += codeTmp;
                idx++;
            }
        }
        code += "    }\n";

        //***********************
        //insert
        //***********************
        code += "\n";
        code += "    /** insert - Este método insere no BD o objeto passado como parâmetro.\n";
        code += "     * @param connection Conexão com BD\n";
        code += "     * @param "+classBean.getNameL()+"Insert "+classBean.getName()+" a ser inserido\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "     * @param "+classOwner.getNameL()+"Owner "+classOwner.getName()+" owner\n";
        }
        code += "     * @throws java.sql.SQLException\n";
        code += "     */\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "    public static void insert(java.sql.Connection connection, "+classBean.getName()+" "+classBean.getNameL()+"Insert, "+classOwner.getPackage(proj)+"."+classOwner.getName()+" "+classOwner.getNameL()+"Owner) throws java.sql.SQLException {\n";
        } else {
            code += "    public static void insert(java.sql.Connection connection, "+classBean.getName()+" "+classBean.getNameL()+"Insert) throws java.sql.SQLException {\n";
        }

        String values = "";
        code += "        String sql = \"INSERT INTO "+classBean.getName()+" (";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += classOwner.getNameL()+"Owner,";
            values += "?,";
        }
        for(project.Attribute att : classBean.getAttributes()){
            String codeTmp = getAttributeGen(att).genInsert();
            if(!codeTmp.isEmpty()){
                code += codeTmp;
                values += "?,";
            }
        }
        code = code.substring(0, code.length()-1);
        values = values.substring(0, values.length()-1);
        code += ") \"\n";
        code += "                   + \"VALUES ("+values+")\";\n";
        code += "        java.sql.PreparedStatement statement = connection.prepareStatement(sql);\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "        obj2stmt("+classBean.getNameL()+"Insert, "+classOwner.getNameL()+"Owner, statement);\n";
        } else {
            code += "        obj2stmt("+classBean.getNameL()+"Insert, statement);\n";
        }
        code += "        statement.executeUpdate();\n";
        code += "        statement.close();\n";

        listAtt = classBean.getAttributesOfType(project.Attribute.Type.IDENTITY);
        if(!listAtt.isEmpty()){
            code += "        sql = \"SELECT IDENTITY_VAL_LOCAL() FROM "+classBean.getName()+"\";\n";
            code += "        statement = connection.prepareStatement(sql);\n";
            code += "        java.sql.ResultSet resultSet = statement.executeQuery();\n";
            code += "        if (resultSet.next()) {\n";
            code += "            "+classBean.getNameL()+"Insert.set"+listAtt.get(0).getNameU()+"(resultSet.getInt(1));\n";
            code += "        }\n";
            code += "        statement.close();\n";
        }

        listAtt = classBean.getAttributesOfType(project.Attribute.Type.OBJECT_LIST);
        for(project.Attribute att : listAtt){
            project.AttributeObjectList attObjList = (project.AttributeObjectList)att;
            project.ClassBean classRef = proj.getClassBean(attObjList.getClassRef());
            code += "        for ("+classRef.getPackage(proj) +"."+attObjList.getClassRef()+" "+attObjList.getClassRef().toLowerCase()+"Insert : "+classBean.getNameL()+"Insert.get"+att.getNameU()+"()) {\n";
            code += "            "+classRef.getPackage(proj) +"."+attObjList.getClassRef()+"DAO.insert(connection, "+attObjList.getClassRef().toLowerCase()+"Insert, "+classBean.getNameL()+"Insert);\n";
            code += "        }\n";
        }

        code += "    }\n";

        //***********************
        //update
        //***********************

        code += "\n";
        code += "    /** update Este método atualiza no BD o objeto passado como parâmetro.\n";
        code += "     * @param connection Conexão com BD\n";
        code += "     * @param "+classBean.getNameL()+"Update "+classBean.getName()+" a ser atualizado\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "     * @param "+classOwner.getNameL()+"Owner "+classOwner.getName()+" owner\n";
        }
        code += "     * @throws java.sql.SQLException\n";
        code += "     */\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "    public static void update(java.sql.Connection connection, "+classBean.getName()+" "+classBean.getNameL()+"Update, "+classOwner.getPackage(proj)+"."+classOwner.getName()+" "+classOwner.getNameL()+"Owner) throws java.sql.SQLException {\n";
        } else {
            code += "    public static void update(java.sql.Connection connection, "+classBean.getName()+" "+classBean.getNameL()+"Update) throws java.sql.SQLException {\n";
        }
        code += "        String sql = \"UPDATE "+classBean.getName()+" SET \"\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "                   + \""+classOwner.getNameL()+"Owner = ?,\"\n";
        }
        for(project.Attribute att : classBean.getAttributes()){
            String codeTmp = getAttributeGen(att).genUpdate();
            if(!codeTmp.isEmpty()){
                code += codeTmp;
            }
        }
        code = code.substring(0, code.length()-3)+" \"\n";
        code += "                   + \"WHERE "+classBean.getPrimaryKey().getName()+" = ?\";\n";
        code += "        java.sql.PreparedStatement statement = connection.prepareStatement(sql);\n";
        if(classBean.isDependent(proj)){
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "        obj2stmt("+classBean.getNameL()+"Update, "+classOwner.getNameL()+"Owner, statement);\n";
        } else {
            code += "        obj2stmt("+classBean.getNameL()+"Update, statement);\n";
        }

        if(classBean.getPrimaryKey().getType()== project.Attribute.Type.STRING)
            code += "        statement.setString("+idx+", "+classBean.getNameL()+"Update.get"+classBean.getPrimaryKey().getNameU()+"());\n";
        else
            code += "        statement.setInt("+idx+", "+classBean.getNameL()+"Update.get"+classBean.getPrimaryKey().getNameU()+"());\n";

        code += "        statement.executeUpdate();\n";
        code += "        statement.close();\n";
        code += "    }\n";

        //***********************
        //delete
        //***********************
        code += "\n";
        code += "    /** delete - Este método apaga do BD o objeto passado como parâmetro.\n";
        code += "     * @param connection Conexão com BD\n";
        code += "     * @param "+classBean.getNameL()+"Delete "+classBean.getName()+" a ser apagado\n";
        code += "     * @throws java.sql.SQLException\n";
        code += "     */\n";
        code += "    public static void delete(java.sql.Connection connection, "+classBean.getName()+" "+classBean.getNameL()+"Delete) throws java.sql.SQLException {\n";
        code += "        String sql = \"DELETE FROM "+classBean.getName()+" WHERE "+classBean.getPrimaryKey().getName()+" = ?\";\n";
        code += "        java.sql.PreparedStatement statement = connection.prepareStatement(sql);\n";
        if(classBean.getPrimaryKey().getType()== project.Attribute.Type.STRING)
            code += "        statement.setString(1, "+classBean.getNameL()+"Delete.get"+classBean.getPrimaryKey().getNameU()+"());\n";
        else
            code += "        statement.setInt(1, "+classBean.getNameL()+"Delete.get"+classBean.getPrimaryKey().getNameU()+"());\n";
        code += "        statement.executeUpdate();\n";
        code += "        statement.close();\n";
        code += "    }\n";

        code += "\n";
        code += "    //*****************************************\n";
        code += "    //QUERY private\n";
        code += "    //*****************************************\n";
        //***********************
        //rs2query
        //***********************
        code += "\n";
        code += "    /**\n";
        code += "     * rs2obj - Transfere do ResultSet da Query SQL para um novo objeto\n";
        code += "     * @param connection\n";
        code += "     * @param resultSet to parse\n";
        code += "     * @return novo objeto\n";
        code += "     * @throws java.sql.SQLException \n";
        code += "     */\n";
        code += "    private static "+classBean.getName()+" rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {\n";
        code += "        "+classBean.getName()+" "+classBean.getNameL()+"Load = new "+classBean.getName()+"();\n";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genRs2Obj();
        }
        code += "        return "+classBean.getNameL()+"Load;\n";
        code += "    }\n";

        //***********************
        //load
        //***********************
        code += "\n";
        code += "    /** load - Este método carrega o objeto com o seu identificador\n";
        code += "     * @param connection Conexão com BD\n";
        code += "     * @param condition Condição WHERE\n";
        code += "     * @return objeto "+classBean.getName()+" || null caso não encontrar\n";
        code += "     * @throws java.sql.SQLException\n";
        code += "     */\n";
        code += "    private static "+classBean.getName()+" load(java.sql.Connection connection, String condition) throws java.sql.SQLException {\n";
        code += "        if(!condition.isEmpty()){\n";
        code += "            String sql = \"SELECT ";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genSelect();
        }
        code = code.substring(0, code.length()-1);
        code += " \"\n";
        code += "                       + \"FROM "+classBean.getName()+" \"\n";
        code += "                       + \"WHERE \"+condition;\n";
        code += "            java.sql.PreparedStatement statement = connection.prepareStatement(sql);\n";
        code += "            java.sql.ResultSet resultSet = statement.executeQuery();\n";
        code += "            "+classBean.getName()+" "+classBean.getNameL()+"Load;\n";
        code += "            if (resultSet.next()) {\n";
        code += "                "+classBean.getNameL()+"Load = rs2obj(connection, resultSet);\n";
        code += "            } else {\n";
        code += "                "+classBean.getNameL()+"Load = null;\n";
        code += "            }\n";
        code += "            statement.close();\n";
        code += "            return "+classBean.getNameL()+"Load;\n";
        code += "        } else {\n";
        code += "            return null;\n";
        code += "        }\n";
        code += "    }\n";

        //***********************
        //loadList
        //***********************
        code += "\n";
        code += "    /** loadList - Carrega lista de objetos "+classBean.getName()+"\n";
        code += "     * @param connection Conexão com BD\n";
        code += "     * @param condition Condição WHERE\n";
        code += "     * @return List contendo a tabela\n";
        code += "     */\n";
        code += "    private static java.util.List<"+classBean.getName()+"> loadList(java.sql.Connection connection, String condition) {\n";
        code += "        java.util.List<"+classBean.getName()+"> list = new java.util.ArrayList<"+classBean.getName()+">();\n";
        code += "        try {\n";
        code += "            String sql = \"SELECT ";
        for(project.Attribute att : classBean.getAttributes()){
            code += getAttributeGen(att).genSelect();
        }
        code = code.substring(0, code.length()-1);
        code += " \"\n";
        code += "                       + \"FROM "+classBean.getName()+" \"\n";
        code += "                       + (condition.isEmpty()?\"\":\"WHERE \"+condition);\n";
        code += "            java.sql.PreparedStatement statement = connection.prepareStatement(sql);\n";
        code += "            java.sql.ResultSet resultSet = statement.executeQuery();\n";
        code += "            while (resultSet.next()) {\n";
        code += "                "+classBean.getName()+" "+classBean.getNameL()+"Load = rs2obj(connection, resultSet);\n";
        code += "                list.add("+classBean.getNameL()+"Load);\n";
        code += "            }\n";
        code += "            statement.close();\n";
        code += "        } catch (java.sql.SQLException sqlex) {\n";
        code += "            System.out.println(\"Falha na leitura do banco de dados !\");\n";
        code += "            sqlex.printStackTrace();\n";
        code += "        }\n";
        code += "        return list;\n";
        code += "    }\n";

        //***********************
        //loadView
        //***********************
        code += "\n";
        code += "    /** loadView - Carrega visão de atributos de objetos "+classBean.getName()+"\n";
        code += "     * @param connection Conexão com BD\n";
        code += "     * @param attributesList Atributos listados\n";
        code += "     * @param condition condição WHERE\n";
        code += "     * @param order Ordem da lista\n";
        code += "     * @return lista de atributo\n";
        code += "     */\n";
        code += "    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {\n";
        code += "        String sql = \"SELECT \" + attributesList + \" \"\n";
        code += "                   + \"FROM "+classBean.getName()+" \"\n";
        code += "                   + (condition.isEmpty() ? \"\" : \"WHERE \" + condition)\n";
        code += "                   + (order.isEmpty() ? \"\" : \"ORDER BY \" + order);\n";
        code += "        return execQueryF(connection, sql);\n";
        code += "    }\n";
        code += "\n";
        code += "    //*****************************************\n";
        code += "    //QUERY public\n";
        code += "    //*****************************************\n";

        //***********************
        //loadBy
        //***********************
        listAtt = classBean.getUnique();
        for(project.Attribute att : listAtt){
            code += "\n";
            code += "    /** loadBy"+att.getNameU()+" - Este método carrega o objeto com o seu identificador\n";
            code += "     * @param connection Conexão com BD\n";
            code += "     * @param "+att.getName()+" campo identificador de "+classBean.getName()+"\n";
            code += "     * @return objeto "+classBean.getName()+" || null caso não encontrar\n";
            code += "     * @throws java.sql.SQLException\n";
            code += "     */\n";

            if(att.getType() == project.Attribute.Type.STRING){
                code += "    public static "+classBean.getName()+" loadBy"+att.getNameU()+"(java.sql.Connection connection, String "+att.getName()+") throws java.sql.SQLException {\n";
                code += "        return load(connection, \""+att.getName()+" = '\"+"+att.getName()+"+\"'\");\n";
            } else {
                code += "    public static "+classBean.getName()+" loadBy"+att.getNameU()+"(java.sql.Connection connection, Integer "+att.getName()+") throws java.sql.SQLException {\n";
                code += "        return load(connection, \""+att.getName()+" = \"+"+att.getName()+");\n";
            }
            code += "    }\n";
        }
        //***********************
        //loadList
        //***********************
        if(!classBean.isDependent(proj)){
            code += "\n";
            code += "    /** loadList - Carrega lista de objetos "+classBean.getName()+"\n";
            code += "     * @param connection Conexão com BD\n";
            code += "     * @return List contendo a tabela\n";
            code += "     */\n";
            code += "    public static java.util.List<"+classBean.getName()+"> loadList(java.sql.Connection connection) {\n";
            code += "        return loadList(connection, \"\");\n";
            code += "    }\n";
        } else {
            project.ClassBean classOwner = proj.getClassBean(classBean.getOwner(proj));
            code += "\n";
            code += "    /** loadList - Retorna Lista de objetos "+classBean.getName()+" por "+classOwner.getName()+"\n";
            code += "     * @param connection Conexão com BD\n";
            code += "     * @param "+classOwner.getNameL()+"Owner "+classOwner.getName()+"\n";
            code += "     * @return List contendo a lista\n";
            code += "     */\n";
            code += "    public static java.util.List<"+classBean.getName()+"> loadList(java.sql.Connection connection, "+classOwner.getPackage(proj) +"."+classOwner.getName()+" "+classOwner.getNameL()+"Owner) {\n";
            if(classOwner.getPrimaryKey().getType() == project.Attribute.Type.STRING)
                code += "        return loadList(connection, \""+classOwner.getNameL()+"Owner = '\" + "+classOwner.getNameL()+"Owner.get"+classOwner.getPrimaryKey().getNameU()+"()+\"'\");\n";
            else
                code += "        return loadList(connection, \""+classOwner.getNameL()+"Owner = \" + "+classOwner.getNameL()+"Owner.get"+classOwner.getPrimaryKey().getNameU()+"());\n";
            code += "    }\n";
        }
        //***********************
        //loadAttList
        //***********************
        listAtt = classBean.getUnique();
        for(project.Attribute att : listAtt){
            code += "\n";
            code += "    /** load"+att.getNameU()+"List - Carrega lista de "+att.getName()+" de objetos "+classBean.getName()+"\n";
            code += "     * @param connection Conexão com BD\n";
            code += "     * @return lista contendo "+att.getNameU()+"\n";
            code += "     */\n";
            code += "    public static java.util.List<String> load"+att.getNameU()+"List(java.sql.Connection connection) {\n";
            code += "        return loadView(connection, \""+att.getName()+"\", \"\", \""+att.getName()+"\");\n";
            code += "    }\n";
        }
        
        //***********************
        //loadView
        //***********************
        if(!classBean.isDependent(proj)){
            code += "\n";
            code += "    /** loadView - Carrega visão de atributos de objetos "+classBean.getName()+"\n";
            code += "     * @param connection Conexão com BD\n";
            code += "     * @return lista com visão de atributos\n";
            code += "     */\n";
            code += "    public static java.util.List loadView(java.sql.Connection connection) {\n";
            //Coloca campos na cláusula SELECT
            code += "        String sql = \"SELECT \"\n";
            for(project.Attribute att : classBean.getAttributes()){
                if(att.getType() != project.Attribute.Type.OBJECT_LIST){
                    code += "                   + \"";
                    code += getAttributeGen(att).genView();
                    code += "\"\n";
                }
            }
            code = code.substring(0, code.length()-3);
            code += " \"\n";
            code += "                   + \"FROM "+classBean.getName()+" \";\n";

            //Relacionamentos
            java.util.List<project.Attribute> attList = classBean.getAttributesOfType(project.Attribute.Type.OBJECT);
            if(!attList.isEmpty()){
                code = code.substring(0, code.length()-4);
                //Coloca classes relacionadas na cláusula FROM
                for(project.Attribute att : attList){
                    project.AttributeObject attObj = (project.AttributeObject)att;
                    code += ", "+attObj.getClassRef();
                }
                code += " \"\n";
                
                //Coloca condições na cláusula WHERE
                code += "                   + \"WHERE ";
                for(project.Attribute att : attList){
                    project.AttributeObject attObj = (project.AttributeObject)att;
                    project.ClassBean classRef = proj.getClassBean(attObj.getClassRef());
                    code += classBean.getName()+"."+attObj.getName()+" = "+attObj.getClassRef()+"."+classRef.getPrimaryKey().getName()+" \"\n";
                    code += "                   + \"AND ";
                }
                code = code.substring(0, code.length()-27);
                code += ";\n";
                
            }
            
            code += "        return execQueryF(connection, sql);\n";
            code += "    }\n";
        }
        //***********************
        //***********************
        code += "\n";
        code += "}\n";
        return code;
    }
}
