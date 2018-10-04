package twitter.db.executor;

import org.apache.commons.io.IOUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlScriptExecutor {
    private DataSource dataSource;

    public SqlScriptExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void execute(String pathToFile) throws SqlScriptExecutorException {
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {

            InputStream sqlScriptInputStream = getClass().getResourceAsStream(pathToFile);

            try {
                connection.setAutoCommit(false);

                Scanner scanner = new Scanner(sqlScriptInputStream, "UTF-8");
                scanner.useDelimiter(";");

                while (scanner.hasNext()) {
                    String sqlStatement = scanner.next().trim();
                    if (!sqlStatement.isEmpty()) {
                        statement.execute(sqlStatement);
                    }
                }
                connection.commit();
            }
            catch (SQLException e) {
                connection.rollback();
                throw new SqlScriptExecutorException("Failed to execute a script");
            }
            finally {
                connection.setAutoCommit(true);
            }
        }
        catch (SQLException e) {
            throw new SqlScriptExecutorException("Failed to execute a script");
        }
    }
}
