package net.sf.jsqlparser.test.show;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.show.ShowTables;
import org.junit.Test;

import static net.sf.jsqlparser.test.TestUtils.assertStatementCanBeDeparsedAs;

public class ShowTablesTest {
    @Test
    public void test() throws JSQLParserException {
        String sql = "SHOW TABLES";
        Statement stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
    }

    @Test
    public void testDatabase() throws JSQLParserException {
        String sql = "SHOW TABLES IN db";
        Statement stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getDatabase().getDatabaseName().equals("db");

        sql = "SHOW TABLES IN `db`";
        stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getDatabase().getDatabaseName().equals("`db`");

        sql = "SHOW TABLES FROM db";
        stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getDatabase().getDatabaseName().equals("db");

        sql = "SHOW TABLES FROM `db`";
        stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getDatabase().getDatabaseName().equals("`db`");
    }

    /**
     * something error !
     * dbName outside quote "" is illegal in mysql
     * but JSqlParser think it is legal even in select statement
     * @throws JSQLParserException
     */
    @Test
    public void testDatabase2() throws JSQLParserException {
        String sql = "SHOW TABLES IN \"db\"";
        Statement stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getDatabase().getDatabaseName().equals("\"db\"");

        sql = "SELECT * FROM \"db\".table";
        stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
    }

    @Test
    public void testLike() throws JSQLParserException {
        String sql = "SHOW TABLES LIKE 'pattern'";
        Statement stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getPattern().equals("'pattern'");

        sql = "SHOW TABLES LIKE \"pattern\"";
        stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getPattern().equals("\"pattern\"");

        //illegal in mysql!
        //more to do
        sql = "SHOW TABLES LIKE `pattern`";
        stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getPattern().equals("`pattern`");

        sql = "SHOW TABLES IN db LIKE 'pattern'";
        stmt = CCJSqlParserUtil.parse(sql);
        assertStatementCanBeDeparsedAs(stmt, sql);
        assert ((ShowTables) stmt).getDatabase().getDatabaseName().equals("db");
        assert ((ShowTables) stmt).getPattern().equals("'pattern'");
    }

    @Test(expected = JSQLParserException.class)
    public void testLike2() throws JSQLParserException {
        String sql = "SHOW TABLES LIKE pattern";
        Statement stmt = CCJSqlParserUtil.parse(sql);
    }
}
