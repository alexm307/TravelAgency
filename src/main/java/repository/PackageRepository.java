package repository;

import model.Package;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PackageRepository {

    private EntityManagerFactory entityManagerFactory;

    public PackageRepository (EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void insertPackage (Package p) {                                                         //C
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public List<Package> getPackages() {                                                            //R
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Package> packages;
        em.getTransaction().begin();
        packages = em.createQuery("SELECT p FROM Package p", Package.class)
                .getResultList();
        em.getTransaction().commit();
        em.close();

        return packages;
    }

    public void updatePackage(Package p) {                                                          //U
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }

    public void deletePackage(Package p) {                                                          //D
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(p) ? p : em.merge(p));
        em.getTransaction().commit();
        em.close();
    }

}