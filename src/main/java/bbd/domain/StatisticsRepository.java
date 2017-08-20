package bbd.domain;

import org.springframework.data.repository.CrudRepository;

public interface StatisticsRepository extends CrudRepository<Statistics, Long>{
	Statistics[] findByByer (User byer);
}
