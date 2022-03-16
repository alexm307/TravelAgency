import controller.AgencyController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public static void main(String[] args) {

        AgencyController agencyController = new AgencyController(entityManagerFactory);

    }
}
