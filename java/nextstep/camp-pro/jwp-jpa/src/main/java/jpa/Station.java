package jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Station extends BaseEntity {
    @OneToMany(mappedBy = "station")
    private List<LineStation> lines = new ArrayList();

    @Column(unique = true)
    private String name;

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    public void add(LineStation line) {
        this.lines.add(line);
    }

    public String getName() {
        return name;
    }

    public List<Line> findLines() {
        List<Line> lines = this.lines.stream()
            .map(LineStation::getLine)
            .collect(toList());
        return Collections.unmodifiableList(lines);
    }
}
