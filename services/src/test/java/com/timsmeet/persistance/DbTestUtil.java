package com.timsmeet.persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public class DbTestUtil {

    private static final String PROPERTY_KEY_RESET_SQL_SEQUENCE = "test.reset.sequence.template";

    /**
     * Prevents instantiation.
     */
    private DbTestUtil() {}

    /**
     * This method reads the invoked SQL statement template from a properties file, creates
     * the invoked SQL statements, and invokes them.
     *
     * @param applicationContext    The application context that is used by our tests.
     * @param tableNames            The names of the database tables which auto-increment column will be reseted.
     * @throws SQLException         If a SQL statement cannot be invoked for some reason.
     */
    public static void resetAutoIncrementColumns(ApplicationContext applicationContext,
                                                 String... tableNames) throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        String resetSqlTemplate = getResetSqlTemplate(applicationContext);
        Connection dbConnection = null;
        try {
            dbConnection = dataSource.getConnection();
            //Create SQL statements that reset the auto-increment columns and invoke
            //the created SQL statements.
                    
            Statement statement = null;
            try {
                statement = dbConnection.createStatement();
                for (String resetSqlArgument: tableNames) {
                    String resetSql = String.format(resetSqlTemplate, resetSqlArgument);
                    statement.execute(resetSql);
                }
            } finally {
                if(statement != null) {
                    statement.close();
                }
            }
        } finally {
            if(dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static String getResetSqlTemplate(ApplicationContext applicationContext) {
        //Read the SQL template from the properties file
        Environment environment = applicationContext.getBean(Environment.class);
        return environment.getRequiredProperty(PROPERTY_KEY_RESET_SQL_SEQUENCE);
    }
}
