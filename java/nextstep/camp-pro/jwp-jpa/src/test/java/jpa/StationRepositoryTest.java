package jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class StationRepositoryTest {
    @Autowired
    private StationRepository stations;

    @Autowired
    private LineRepository lines;

    @Autowired
    private LineStationRepository lineStations;

    @Test
    void 역을_추가한다() {
        Station expected = new Station("잠실역");
        Station actual = stations.save(expected);
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getName()).isEqualTo(expected.getName())
        );
    }

    @Test
    void 역_이름으로_조회한다() {
        Station expected = 역을_생성한다("잠실역");

        Station actual = stations.findByName(expected.getName());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 노선_조회_시_속한_지하철역을_볼_수_있다() {
        Line line = 노선을_생성한다("2호선");
        Station station = 역을_생성한다("잠실역");
        노선에_역을_등록한다(line, station);

        List<Station> stations = line.findStations();
        assertThat(stations).contains(station);
    }

    @Test
    void 지하철역_조회_시_어느_노선에_속한지_볼_수_있다() {
        Line line = 노선을_생성한다("2호선");
        Station station = 역을_생성한다("잠실역");
        노선에_역을_등록한다(line, station);

        List<Line> lines = station.findLines();
        assertThat(lines).contains(line);
    }

    private LineStation 노선에_역을_등록한다(Line line, Station station) {
        LineStation lineStation = new LineStation(line, station);

        return lineStations.save(lineStation);
    }

    private Station 역을_생성한다(String name) {
        return stations.save(new Station(name));
    }

    private Line 노선을_생성한다(String name) {
        return lines.save(new Line(name));
    }
}
