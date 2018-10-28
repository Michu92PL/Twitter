package twitter.web;

import twitter.db.DatabasePopulator;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DatabasePopulatingListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DatabasePopulator.populateWithDefaultAuthors();

    }
}
