package javase.jndi.junitjndi.example1;

import javax.naming.Context;
import javax.naming.NamingException;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import junitjndi.JndiRule;

public class JndiServer extends JndiRule {
    @Override
    protected void bind(Context context) throws NamingException {
        Context cx = context.createSubcontext("java:/comp/env");
        
        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("javase_jndi_junitjndi_example1");
               
        cx.bind("jdbc/mysql", dataSource);
    }
}
