package twitter.web;

import twitter.db.EntityManagerFactorySingleton;
import twitter.db.JPAAuthorRepository;
import twitter.model.Dashboard;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
        Dashboard dashboard = new Dashboard(entityManager);
        context.addServlet("TwitterServlet", new TwitterServlet(dashboard)).addMapping("/twitter");
        context.addServlet("LoginServlet", new LoginServlet(new JPAAuthorRepository(entityManager))).addMapping("/login");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
