package service;

import model.Package;
import model.Status;
import model.User;
import repository.PackageRepository;
import repository.UserRepository;

import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserRepository userRepository;

    private PackageRepository packageRepository;

    private Validator validator;

    public UserService(EntityManagerFactory entityManagerFactory) {
        this.userRepository = new UserRepository(entityManagerFactory);
        this.validator = new Validator();
        this.packageRepository = new PackageRepository(entityManagerFactory);
    }

    //#1 - registering
    public boolean createUser(User user) {
        if (validator.isUserValid(user))
            userRepository.insertUser(user);
        else
            return false;
        return true;
    }

    //#2 - log in
    public User login(String username, String password) {
        return userRepository.findUser(username, password);
    }

    //#3 - view all
    public List<Package> viewAll() {

        return packageRepository.getPackages();
    }

    //#4 - filter
    public List<Package> filter(int selection, String dataToSearch, Date fromDate, Date toDate) {               //need to verify date and data according to selection
        List<Package> packages = packageRepository.getPackages();
        return filterPackages(packages, selection, dataToSearch, fromDate, toDate);
    }


    public List<Package> filterPackages (List<Package> packages, int selection, String dataToSearch, Date fromDate, Date toDate) {
        List<Package> finalList = null;
        System.out.println(dataToSearch + "   " + selection);
        switch (selection) {
            case 0:
                finalList = packages.stream().filter(item -> item.getName().contains(dataToSearch)).collect(Collectors.toList());
                break;
            case 1:
                finalList = packages.stream().filter(item -> item.getPrice() <= Double.parseDouble(dataToSearch)).collect(Collectors.toList());
                break;
            case 2:
                finalList = packages.stream().filter(item -> item.getFrom().after(fromDate)).collect(Collectors.toList());
                break;
            case 3:
                finalList = packages.stream().filter(item -> item.getTo().before(toDate)).collect(Collectors.toList());
                break;
            default:
                return null;
        }
        return finalList;
    }

    //#5 - book
    public boolean bookPackage(User user, Package p) {
        if (p.getStatus() != Status.BOOKED && p.getCurrentSpots() < p.getSpots()) {

            p.setCurrentSpots(p.getCurrentSpots() + 1);
            if (p.getStatus() == Status.NOT_BOOKED)
                p.setStatus(Status.IN_PROGRESS);

            if (p.getCurrentSpots() == p.getSpots())
                p.setStatus(Status.BOOKED);
            List<User> updatedUsers = p.getUsers();
            updatedUsers.add(user);
            p.setUsers(updatedUsers);

            List<Package> updatedPackages = user.getPackages();
            updatedPackages.add(p);
            user.setPackages(updatedPackages);

            userRepository.updateUser(user);
            packageRepository.updatePackage(p);
        }
        else
            return false;
        return true;
    }

    //#6 - view booked
    public List<Package> viewBooked(User user) {
        return user.getPackages();
    }
}