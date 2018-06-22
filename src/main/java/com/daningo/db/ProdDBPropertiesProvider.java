package com.daningo.db;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Created by naing on 6/20/18.
 */
public class ProdDBPropertiesProvider  implements DBPropertiesProvider {
    private static final Logger logger = Logger.getLogger(ProdDBPropertiesProvider.class.getSimpleName());

    public Properties getDBProperties() {
        Properties myBatisProperties = new Properties();

        myBatisProperties.setProperty("mybatis.environment.id", "production");
        myBatisProperties.setProperty("JDBC.host", "localhost");
        myBatisProperties.setProperty("JDBC.port", "3306");
        myBatisProperties.setProperty("JDBC.schema", "advice");
        myBatisProperties.setProperty("JDBC.username", "root");
        myBatisProperties.setProperty("JDBC.password", "yannaing");
        myBatisProperties.setProperty("JDBC.autoCommit", "false");
        myBatisProperties.setProperty("JDBC.autoReconnect", "true");
        myBatisProperties.setProperty("c3p0.acquireIncrement","1");
        myBatisProperties.setProperty("c3p0.initialPoolSize", "2");
        myBatisProperties.setProperty("c3p0.maxPoolSize", "10");
        myBatisProperties.setProperty("c3p0.maxIdleTime", "1800");

        logger.log(Level.INFO, "DB Properties", myBatisProperties);
        return myBatisProperties;
    }
}
