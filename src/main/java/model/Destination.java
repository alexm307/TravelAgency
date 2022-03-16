package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Package> packages;

    public Destination(String name) {
        this.name = name;
        this.packages = new ArrayList<Package>();
    }

    public Destination() {

    }

    public String getName() {
        return name;
    }

    public List<Package> getPackages() {
        return packages;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                '}';
    }
}