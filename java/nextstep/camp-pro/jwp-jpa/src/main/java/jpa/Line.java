package jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Line extends BaseEntity {
    private String color;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "line")
    private List<LineStation> stations = new ArrayList();

    public Line() {
    }

    public Line(String name) {
        this.name = name;
    }

    public void add(LineStation station) {
        this.stations.add(station);
    }

    public List<Station> findStations() {
        List<Station> stations = this.stations.stream()
            .map(LineStation::getStation)
            .collect(toList());
        return Collections.unmodifiableList(stations);
    }
}
