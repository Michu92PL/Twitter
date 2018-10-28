package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.db.DatabasePopulator;
import twitter.db.JPAAuthorRepository;
import twitter.db.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabasePopulatorTest {

    private JPAAuthorRepository repository;

    @BeforeEach
    void before() {
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("asdf");
        //EntityManager entityManager = factory.createEntityManager();
        //EntityManagerFactorySingleton ems = new EntityManagerFactorySingleton();
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
        repository = new JPAAuthorRepository(entityManager);
    }

    @DisplayName("should populate database with 3 authors")
    @Test
    void test() throws Exception {
        // when
        DatabasePopulator.populateWithDefaultAuthors();

        // then
        assertThat(repository.findAll()).hasSize(4);
    }
}
