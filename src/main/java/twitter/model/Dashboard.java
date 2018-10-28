package twitter.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Dashboard {

    private EntityManager entityManager;

    public Dashboard(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Creates a new tweet by the existing author.
     *
     * @param msg    a msg
     * @param author an existing author
     * @return the newly created tweet
     * @throws AuthorDoesntExistException if author doesn't exist
     */
    public Tweet create(String msg, String author) throws AuthorDoesntExistException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Author> authorQuery = entityManager.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class);
        authorQuery.setParameter("name", author);
        Tweet tweet = new Tweet(msg);
        try {
            Author existingAuthor = authorQuery.getSingleResult();
            tweet.setAuthor(existingAuthor.getName());
            existingAuthor.addTweets(tweet);
            transaction.commit();
            return tweet;
        } catch (NoResultException e) {
            transaction.rollback();
            throw new AuthorDoesntExistException(String.format("Author %s doesn't exist in the db.", author), e);
        }
    }

    public Stream<Tweet> load() {
        List<Tweet> listOfAllTweets = new ArrayList<>();

        TypedQuery<Author> authorQuery = entityManager.createQuery("SELECT a from Author a", Author.class);
        List<Author> listOfAuthors = authorQuery.getResultList();

        for (Author a : listOfAuthors) {
            Collection<Tweet> tweets = a.getTweets();
            tweets.forEach(t -> t.setAuthor(a.getName()));
            listOfAllTweets.addAll(tweets);
        }

        return listOfAllTweets.stream();
    }
}
