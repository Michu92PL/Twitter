package twitter.web;

import twitter.model.Dashboard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("asdf");
        EntityManager entityManager = factory.createEntityManager();
        Dashboard dashboard = new Dashboard(entityManager);

        context.addServlet("TwitterServlet", new TwitterServlet(dashboard)).addMapping("/twitter");


    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
