package gen.dbaccess;

/**
 *
 * @author marcos
 */
public class ClassDBAccessGen_hsqldb extends ClassDBAccessGen {

    /**
     * Construtor
     * @param projectEdit
     */
    public ClassDBAccessGen_hsqldb(project.Project projectEdit){
        super(projectEdit);
    }

    @Override
    public String getJavaCode(){
        String code = super.getJavaCode();
        code = code.replace("String drv = \"org.apache.derby.jdbc.EmbeddedDriver\";",             "String drv = \"org.hsqldb.jdbcDriver\";");
        code = code.replace("String url = \"jdbc:derby:./data;territory=pt_BR;create=TRUE\";",    "String url = \"jdbc:hsqldb:file:dados/dados;shutdown=TRUE\";");
        code = code.replace("String user = \"admin\";",                                           "String user = \"sa\";");
        code = code.replace("String password = \"admin\";",                                       "String password = \"\";");
        code = code.replace("SELECT COUNT(*) FROM SYS.SYSTABLES WHERE TABLETYPE='T'", "SELECT COUNT(*) FROM INFORMATION_SCHEMA.SYSTEM_TABLES WHERE TABLE_TYPE='TABLE'");
        return code;
    }
}
