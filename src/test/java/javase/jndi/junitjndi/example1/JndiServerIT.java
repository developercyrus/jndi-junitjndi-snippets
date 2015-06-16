package javase.jndi.junitjndi.example1;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

import org.junit.ClassRule;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class JndiServerIT {
    @ClassRule
    public static JndiServer s = new JndiServer();
    
    @Test
    public void test1() throws NamingException, SQLException {
    	Context ctx = (Context) new InitialContext().lookup("java:/comp/env");
        ConnectionPoolDataSource ds = (MysqlConnectionPoolDataSource) ctx.lookup("jdbc/mysql");
        PooledConnection pConn = ds.getPooledConnection();
        Connection conn = pConn.getConnection();        
        Statement stmt = conn.createStatement();        
        ResultSet rs = stmt.executeQuery("select symbol from language where id = 1");
        if (rs.next()) {
        	String actual = rs.getString("symbol");        	
            System.out.println(actual);
            assertEquals("EN", actual);
        }
    }

}