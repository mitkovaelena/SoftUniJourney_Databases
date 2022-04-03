package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "location_name")
    private String locationName;

    @OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sale> salesInStore;

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public Location() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<Sale> getSalesInStore() {
        return salesInStore;
    }

    public void setSalesInStore(Set<Sale> salesInStore) {
        this.salesInStore = salesInStore;
    }
}
