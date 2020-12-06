import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Cars {
    List<Car> cars = new ArrayList();

    public Cars(int count) {
        for(int i = 0; i < count; i++) {
            cars.add(new Car());
        }
    }

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public RoundResult racing() {
        for (Car car: cars) {
            car.racing(RandomUtils.run());
        }

        return new RoundResult(positions());
    }

    private List<Integer> positions() {
        return cars.stream()
            .map(Car::getPosition)
            .collect(toList());
    }
}
