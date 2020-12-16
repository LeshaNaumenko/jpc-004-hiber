package entities;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "report_name")
    private String name;

    private double price;

    @Column(name = "order_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "report", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Building> buildings = new ArrayList<>();

    public Report() {
    }

    public Report(String name, double price, Date date, User user) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.user = user;
    }

    public Report(String name, double price, Date date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public Report(Integer id, String name, double price, Date date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id &&
                Double.compare(report.price, price) == 0 &&
                Objects.equals(name, report.name) &&
                Objects.equals(date, report.date) &&
                Objects.equals(user, report.user) &&
                Objects.equals(buildings, report.buildings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, date, user, buildings);
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void addBuilding(Building building) {
        if (!buildings.contains(building)) {
            buildings.add(building);
            building.setReport(this);
        }
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
