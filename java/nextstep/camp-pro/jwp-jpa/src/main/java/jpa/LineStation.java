package jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineStation extends BaseEntity {

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
