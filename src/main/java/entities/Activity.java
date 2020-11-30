package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "work_name")
    private String workName;

    private double price;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public Activity() {
    }

    public Activity(String workName, double price) {
        this.workName = workName;
        this.price = price;
    }

    public Activity(String workName, double price, Building building) {
        this.workName = workName;
        this.price = price;
        this.building = building;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id &&
                Double.compare(activity.price, price) == 0 &&
                Objects.equals(workName, activity.workName) &&
                Objects.equals(building, activity.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workName, price, building);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", workName='" + workName + '\'' +
                ", price=" + price +
                '}';
    }
}


