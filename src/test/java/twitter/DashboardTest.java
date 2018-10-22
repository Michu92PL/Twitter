package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.model.Dashboard;
import twitter.model.Tweet;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class DashboardTest {

    private Dashboard dashboard;

    @BeforeEach
    void beforeEach() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("asdf");
        EntityManager entityManager = factory.createEntityManager();
        dashboard = new Dashboard(entityManager);
    }

    @DisplayName("author should be able to create a new tweet")
    @Test
    void test0() {
        // given
        String msg = "content";
        String author = "goobar";

        // when
        Tweet tweet = dashboard.create(msg, author);

        // then
        assertThat(tweet.getMessage()).isEqualTo(msg);
    }

    @DisplayName("should load created tweet from the dashboard")
    @Test
    void test1() {
        // given
        String msg = "content";
        String author = "goobar";
        Tweet tweet = dashboard.create(msg, author);

        // when
        Stream<Tweet> allTweets = dashboard.load();

        // then
        assertThat(allTweets).containsExactlyInAnyOrder(tweet);
    }
}