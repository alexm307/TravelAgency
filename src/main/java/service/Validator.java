package service;


import model.Package;
import model.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;
import java.util.Date;

public class Validator {

    public boolean isUserValid(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty())
            return false;
        if (user.getEmail() == null || user.getEmail().isEmpty())
            return false;
        return user.getPassword() != null && !user.getPassword().isEmpty();
    }

    public boolean isPackageValid(Package p) {
        if (p.getDetails() == null || p.getDetails().isEmpty())
            return false;
        if (p.getName() == null || p.getName().isEmpty())
            return false;
        if (p.getFrom() == null)
            return false;
        if (p.getTo() == null)
            return false;
        if (p.getSpots() == 0)
            return false;
        if (p.getStatus() == null)
            return false;
        if (p.getFrom().before(Date.from(Instant.now())))
            return false;
        if (p.getTo().before(p.getFrom()) || p.getTo().before(Date.from(Instant.now())))
            return false;
        return true;
    }
}
