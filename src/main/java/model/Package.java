package model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dest_id")
    private Destination destination;

    @ManyToMany(mappedBy = "packages", fetch = FetchType.EAGER)
    private List<User> users;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "fromDate")
    private Date from;

    @Column(name = "toDate")
    private Date to;

    @Column(name = "details")
    private String details;

    @Column(name = "spots")
    private int spots;

    @Column(name = "availableSpots")
    private int currentSpots;

    @Column(name = "status")
    private Status status;

    public Package () {
    }

    public Package(String name, double price, Date from, Date to, String details, int spots, Destination destination) {
        this.name = name;
        this.price = price;
        this.from = from;
        this.to = to;
        this.details = details;
        this.spots = spots;
        this.status = Status.NOT_BOOKED;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public Destination getDestination() {
        return destination;
    }

    public String getName() {
        return name;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public String getDetails() {
        return details;
    }

    public int getSpots() {
        return spots;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurrentSpots() {
        return currentSpots;
    }

    public void setCurrentSpots(int currentSpots) {
        this.currentSpots = currentSpots;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setFrom(Date from) {
        this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setSpots(int spots) {
        this.spots = spots;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Package{" +
                "destination=" + destination +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", from=" + from +
                ", to=" + to +
                ", details='" + details + '\'' +
                ", spots=" + spots +
                ", currentSpots=" + currentSpots +
                ", status=" + status +
                '}';
    }
}