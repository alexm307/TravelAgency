package controller;

import model.Destination;
import model.Package;
import service.AgencyService;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AgencyController {

    private AgencyService agencyService;

    private GeneralView generalView;
    private AgencyView agencyView;
    private UserView userView;

    public AgencyController(EntityManagerFactory entityManagerFactory) {
        this.agencyService = new AgencyService(entityManagerFactory);
        this.generalView = new GeneralView(this, entityManagerFactory);
        this.agencyView = new AgencyView(this);
        this.userView = new UserView(entityManagerFactory);

        this.generalView.setVisible(true);
    }

    public void login() {
        generalView.setVisible(false);
        userView.setUser(generalView.getUser());
        userView.setVisible(true);
    }

    public void startAgency() {
        generalView.setVisible(false);
        agencyView.setVisible(true);
    }

    public void createDestination(Destination d) {
        agencyService.createDestination(d);
    }

    public void createPackage(Package p) {
        agencyService.createPackage(p);
    }

    public List<Package> viewPackages() {
        return agencyService.viewPackages();
    }

    public List<Destination> viewDestinations() {
        return agencyService.viewDestinations();
    }

    public void editPackage(Package p) {
        agencyService.editPackage(p);
    }

    public void deletePackage(Package p) {
        agencyService.deletePackage(p);
    }

    public void deleteDestination(Destination destination) {
        agencyService.deleteDestination(destination);
    }
}
