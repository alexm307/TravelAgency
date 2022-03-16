package service;

import model.Destination;
import model.Package;
import repository.DestinationRepository;
import repository.PackageRepository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AgencyService {                                                                                // NEED TO DO VALIDATIONS HERE AS WELL

    private PackageRepository packageRepository;

    private DestinationRepository destinationRepository;

    public AgencyService(EntityManagerFactory entityManagerFactory) {
        this.packageRepository = new PackageRepository(entityManagerFactory);
        this.destinationRepository = new DestinationRepository(entityManagerFactory);
    }

    //#1
    public void createDestination(Destination destination) {
        destinationRepository.insertDestination(destination);
    }

    //#2
    public void createPackage(Package p) {
        packageRepository.insertPackage(p);
    }

    //#3
    public void editPackage(Package updatedPackage) {
        packageRepository.updatePackage(updatedPackage);
    }

    //#4
    public void deletePackage(Package p) {
        packageRepository.deletePackage(p);
    }

    //#5
    public List<Package> viewPackages() {
        return packageRepository.getPackages();
    }

    public List<Destination> viewDestinations() {
        return destinationRepository.viewDestinations();
    }

    //#6
    public void deleteDestination(Destination destination) {
        destinationRepository.deleteDestination(destination);
    }
}