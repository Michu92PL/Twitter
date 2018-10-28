package twitter.db;

import twitter.model.Author;
import twitter.model.AuthorRepositoryException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.stream.Stream;

public class JPAAuthorRepository {
    private EntityManager entityManager;

    public JPAAuthorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Author author) throws AuthorRepositoryException {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(author);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new AuthorRepositoryException("Can't create a new Author");
        }


    }

    public Stream<Author> findAll() throws AuthorRepositoryException {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultStream();

    }

    public boolean validateUser(String name, String password) {
        return findAll().anyMatch(
                author -> author.getName().equals(name) && author.getPassword().equals(password)
        );
    }
}
