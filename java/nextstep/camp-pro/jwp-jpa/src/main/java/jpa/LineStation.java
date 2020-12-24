package jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class LineStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;

    public LineStation() {
    }

    public LineStation(Line line, Station station) {
        this.line = line;
        this.station = station;

        line.add(this);
        station.add(this);
    }

    public Station getStation() {
        return station;
    }

    public Line getLine() {
        return line;
    }
}
