package twitter.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Dashboard {

    private EntityManager entityManager;

    public Dashboard(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Tweet create(String msg, String author) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Author> authorQuery = entityManager.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class);
        authorQuery.setParameter("name", author);
        Tweet tweet = new Tweet(msg);
        try {
            Author existingAuthor = authorQuery.getSingleResult();
            existingAuthor.addTweets(tweet);
        } catch (NoResultException e) {
            Author newAuthor = new Author(author);
            newAuthor.addTweets(tweet);
            entityManager.persist(newAuthor);
        }
        transaction.commit();
        return tweet;
    }

    public Stream<Tweet> load() {
        List<Tweet> listOfAllTweets = new ArrayList<>();

        TypedQuery<Author> authorQuery = entityManager.createQuery("SELECT a from Author a", Author.class);
        List<Author> listOfAuthors = authorQuery.getResultList();

        for (Author a : listOfAuthors) {
            listOfAllTweets.addAll(a.getTweets());
        }

        return listOfAllTweets.stream();
    }
}
