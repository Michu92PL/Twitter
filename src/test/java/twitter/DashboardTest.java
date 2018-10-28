package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.db.JPAAuthorRepository;
import twitter.model.Author;
import twitter.model.AuthorDoesntExistException;
import twitter.model.Dashboard;
import twitter.model.Tweet;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class DashboardTest {

    private Dashboard dashboard;
    private JPAAuthorRepository authorRepository;

    @BeforeEach
    void beforeEach() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("asdf");
        EntityManager entityManager = factory.createEntityManager();
        authorRepository = new JPAAuthorRepository(entityManager);
        dashboard = new Dashboard(entityManager);
    }

    @DisplayName("should throw exception when create a new tweet for non-existent author")
    @Test
    void test2() {
        // when
        Throwable e = catchThrowable(() -> dashboard.create("any msg", "anonymous"));

        // then
        assertThat(e).isInstanceOf(AuthorDoesntExistException.class);
    }

    @DisplayName("author should be able to create a new tweet with author property")
    @Test
    void test0() {
        // given
        String msg = "content";
        String author = "goobar";
        createAuthor(author);

        // when
        Tweet tweet = dashboard.create(msg, author);

        // then
        assertThat(tweet.getMessage()).isEqualTo(msg);
        assertThat(tweet.getAuthor()).isEqualTo(author);
    }

    @DisplayName("should load created tweet from the dashboard")
    @Test
    void test1() {
        // given
        String msg = "content";
        String author = "goobar";
        createAuthor(author);
        Tweet tweet = dashboard.create(msg, author);

        // when
        Stream<Tweet> allTweets = dashboard.load();

        // then
        assertThat(allTweets).containsExactlyInAnyOrder(tweet);
    }

    private void createAuthor(String authorName) {
        authorRepository.save(new Author(authorName));
    }
}