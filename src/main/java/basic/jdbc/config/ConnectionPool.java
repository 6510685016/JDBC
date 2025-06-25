package basic.jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionPool {
    private static final HikariDataSource ds;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DatabaseConfig.URL);
        config.setUsername(DatabaseConfig.USER);
        config.setPassword(DatabaseConfig.PASSWORD);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setPoolName("MyJDBCPool");

        ds = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
