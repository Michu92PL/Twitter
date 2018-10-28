package twitter.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {

    public static EntityManagerFactory instance;

    public static EntityManagerFactory getInstance() {
        if(instance == null){
            instance = Persistence.createEntityManagerFactory("asdf");
        }
        return instance;

    }
}
