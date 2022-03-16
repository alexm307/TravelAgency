package repository;

import model.Destination;
import model.Package;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DestinationRepository {



    private EntityManagerFactory entityManagerFactory;

    public DestinationRepository (EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void insertDestination (Destination destination) {                                       //C
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }

    /*
    public List<Package> getPackages() {
        List<Package> packages;
        em.getTransaction().begin();
        packages = em.createQuery("SELECT p FROM Package p", Package.class)
                .getResultList();
        em.getTransaction().commit();
        em.close();

        return packages;
    }
     */

    public List<Destination> viewDestinations() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Destination> destinations;
        em.getTransaction().begin();
        destinations = em.createQuery("SELECT d FROM Destination d", Destination.class)
                .getResultList();
        em.getTransaction().commit();
        em.close();

        return destinations;
    }

    public void deleteDestination(Destination destination) {
        EntityManager em = entityManagerFactory.createEntityManager();                    //D
        em.getTransaction().begin();
        em.remove(em.contains(destination) ? destination : em.merge(destination));
        em.getTransaction().commit();
        em.close();
    }
}