package douglas.Planets.controller;


import douglas.Planets.domain.Planet;
import douglas.Planets.dto.PlanetDTO;
import douglas.Planets.repository.PlanetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetController {
    private PlanetRepository planetRepository;

    public PlanetController(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @PostMapping("/planets")
    public ResponseEntity<String> savePlanet(@RequestBody PlanetDTO planetDTO){
        planetRepository.save(new Planet(planetDTO.id(), planetDTO.name(), planetDTO.climate(), planetDTO.ground()));
        return ResponseEntity.status(HttpStatus.CREATED).body("Planet " + planetDTO.name() + " created sucessfully!");
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlanetDTO> findPlanetById(@PathVariable("id") Long id){
        Optional<Planet> planetFound = this.planetRepository.findById(id);

        if (planetFound.isPresent()){
            Planet planet = planetFound.get();
            PlanetDTO planetDTO = new PlanetDTO(planet.getId(), planet.getName(), planet.getClimate(), planet.getGround());
            return ResponseEntity.ok(planetDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
   /* @GetMapping("/name/{name}")
    public ResponseEntity<PlanetDTO> findPlanetByName(@PathVariable("name") String name){
        Optional<Planet> planetFound = this.planetRepository.findByName(name);

        if (planetFound.isPresent()){
            Planet planet = planetFound.get();
            PlanetDTO planetDTO = new PlanetDTO(planet.getName(), planet.getClimate(), planet.getGround());
            return ResponseEntity.ok(planetDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    } */
    @GetMapping("/list_planets")
    public ResponseEntity<List<PlanetDTO>> listAllPlanets(){
        List<Planet> planets = planetRepository.findAll();
        List<PlanetDTO> planetDTOs = new ArrayList<>();
        if (!planets.isEmpty()){
            for (Planet planet : planets){

                planetDTOs.add(new PlanetDTO(planet.getId(), planet.getName(), planet.getClimate(), planet.getGround()));
            }
            return ResponseEntity.ok(planetDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deletePlanetById(@PathVariable Long id){
        Optional<Planet> planetFound = planetRepository.findById(id);

        if (planetFound.isPresent()){
            Planet planet = planetFound.get();
            planetRepository.delete(planet);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Planet " + planet.getName() + " deleted sucessfully!");

        }
        else{
            throw new RuntimeException("Planet not found.");
        }
    }
}