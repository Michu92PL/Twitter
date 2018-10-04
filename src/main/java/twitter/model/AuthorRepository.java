package twitter.model;

public interface AuthorRepository {

    void save(Author author) throws AuthorRepositoryException;
    void findAllTweets() throws AuthorRepositoryException;
}
