package twitter.db;

import twitter.model.Author;

import javax.persistence.EntityManager;

public class DatabasePopulator {

    public static void populateWithDefaultAuthors(){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("asdf");
        //EntityManager entityManager = factory.createEntityManager();
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
        JPAAuthorRepository rep = new JPAAuthorRepository(entityManager);
        Author author0 = new Author("defaultAuthor0","pass0");
        Author author1 = new Author("defaultAuthor1","pass1");
        Author author2 = new Author("defaultAuthor2","pass2");
        Author author3 = new Author("admin","admin");

        rep.save(author0);
        rep.save(author1);
        rep.save(author2);
        rep.save(author3);
    }
}
