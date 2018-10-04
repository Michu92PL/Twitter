package twitter.db;

import twitter.model.Author;
import twitter.model.AuthorRepository;
import twitter.model.AuthorRepositoryException;
import twitter.model.Tweet;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class JPAAuthorRepository implements AuthorRepository {
    private EntityManager entityManager;

    @Override
    public void save(Author author) throws AuthorRepositoryException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Author> authorQuery = entityManager.createQuery("SELECT a FROM Author a WHERE a.id = :id",Author.class);
        authorQuery.setParameter("id", author.getId());
        Author existingAuthor = authorQuery.getSingleResult();

        //authorQuery.getResultStream().forEach(au -> au.equals(author));

    }

    @Override
    public void findAllTweets() throws AuthorRepositoryException {

    }
}
