package gen.dao;

/**
 *
 * @author marcos
 */
public abstract class AttributeGen {
    public abstract String genCreateTable();
    public abstract String genInsert();
    public abstract String genUpdate();
    public abstract String genStatementSet(int idx, String varName);
    public abstract String genRs2Obj();
    public abstract String genSelect();
    public abstract String genView();
}
