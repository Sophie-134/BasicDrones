package ar.com.proyecto.basicwardrobe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.proyecto.basicwardrobe.entities.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Integer>{

    //@Query("select name from drone")
    Drone findByName(String name);
}
