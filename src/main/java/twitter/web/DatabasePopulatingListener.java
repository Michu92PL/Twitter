package twitter.web;

import twitter.db.JPAAuthorRepository;
import twitter.model.Author;
import twitter.model.Dashboard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DatabasePopulator implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("asdf");
        EntityManager entityManager = factory.createEntityManager();
        JPAAuthorRepository rep = new JPAAuthorRepository(entityManager);
        Author author0 = new Author("defaultAuthor0","pass0");
        Author author1 = new Author("defaultAuthor1","pass1");
        Author author2 = new Author("defaultAuthor2","pass2");
        rep.save(author0);
        rep.save(author1);
        rep.save(author2);

    }
}
