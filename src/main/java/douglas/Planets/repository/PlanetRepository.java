package douglas.Planets.repository;

import douglas.Planets.domain.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, Long> {
}
