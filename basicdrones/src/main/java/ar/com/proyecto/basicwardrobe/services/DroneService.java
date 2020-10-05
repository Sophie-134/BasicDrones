package ar.com.proyecto.basicwardrobe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.proyecto.basicwardrobe.entities.Drone;
import ar.com.proyecto.basicwardrobe.entities.Drone.ResultadoDroneEnum;
import ar.com.proyecto.basicwardrobe.repositories.DroneRepository;

@Service
public class DroneService {

    @Autowired
    DroneRepository droneRepo;

    public Drone create(String name, double maxSpeed, int propellers) {

        Drone drone = new Drone();
        drone.setName(name);
        drone.setMaxSpeed(maxSpeed);
        drone.setPropellers(propellers);

        droneRepo.save(drone);
        return drone;
    }

    public ResultadoDroneEnum validarCreate(String name, double maxSpeed, int propellers/* , Drone droneId */) {
        if (name == null) {
            return ResultadoDroneEnum.DRONE_INEXISTENTE;
        }
        if (maxSpeed < 0) {
            return ResultadoDroneEnum.VELOCIDAD_NO_VALIDA;
        }
        /*
         * if(droneId == null){ return ResultadoDroneEnum.ID_NO_VALIDO; }
         */
        return ResultadoDroneEnum.INICIADA;
    }

    public List<Drone> listAll() {
        return droneRepo.findAll();
    }

    public Drone buscarDroneName(String name) {
     
        return droneRepo.findByName(name);
    }
}
