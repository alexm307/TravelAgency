package controller;

import model.Package;
import model.User;
import service.UserService;

import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class UserController {

    private UserService userService;

    public UserController(EntityManagerFactory entityManagerFactory) {
        this.userService = new UserService(entityManagerFactory);
    }

    public void createUser(String username, String mail, String password) {
        User newUser = new User(username, mail, password);

        userService.createUser(newUser);
    }

    public User login(String username, String password) {

        return userService.login(username, password);
    }

    public List<Package> viewAllPackages() {
        return userService.viewAll();                      // aici e lista cu package-uri pe care le afisam
    }

    public List<Package> viewBooked(User user) {
        return userService.viewBooked(user);
    }

    public Boolean bookPackage(User user, Package p) {
        return userService.bookPackage(user, p);
    }

    public List<Package> filterPackages(int selection, String data, Integer day, Integer month) {

        Date date = new Date(); // will be either from or to, no need to create two different structures
        date.setDate(day);
        date.setMonth(month);

        return userService.filterPackages(userService.viewAll(), selection, data, date, date);
    }
}
