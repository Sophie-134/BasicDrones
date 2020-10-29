package ar.com.proyecto.basicwardrobe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.proyecto.basicwardrobe.entities.Drone;
import ar.com.proyecto.basicwardrobe.entities.Drone.ResultadoDroneEnum;
import ar.com.proyecto.basicwardrobe.models.responses.GenericResponse;
import ar.com.proyecto.basicwardrobe.models.responses.request.DroneRequest;
import ar.com.proyecto.basicwardrobe.services.DroneService;

@RestController
public class DroneController {

    @Autowired
    DroneService droneService;

    @PostMapping("/drones")
    public ResponseEntity<GenericResponse> create(@RequestBody DroneRequest req) {
        GenericResponse r = new GenericResponse();

        ResultadoDroneEnum resultado = droneService.validarCreate(req.name, req.maxSpeed, req.propellers);

        if (resultado != ResultadoDroneEnum.INICIADA) {
            r.isOk = false;
            r.message = "No se pudo crear el drone" + resultado;
            return ResponseEntity.badRequest().body(r);// error 400
        }

        Drone drone = droneService.create(req.name, req.maxSpeed, req.propellers);

        r.isOk = true;
        r.id = drone.getDroneId();
        r.message = "Se ha creado el drone " + drone.getName();
        return ResponseEntity.ok(r);

    }

    @GetMapping("/drones")
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(droneService.listAll());
    }

    @GetMapping("/drones/name/{name}")
    public ResponseEntity<?> listName(@PathVariable String name) {
        Drone drone = new Drone();
        drone = droneService.buscarDroneName(name);
        if (drone != null) {

            return ResponseEntity.ok(drone);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/drones/{droneId}")
    public ResponseEntity<?> listId(@PathVariable int droneId) {
        Drone drone = droneService.findId(droneId);
        if (drone != null) {

            return ResponseEntity.ok(drone);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/drones/{droneId}")
    public ResponseEntity<GenericResponse> editDrone(@PathVariable int droneId, @RequestBody Drone req) {
        GenericResponse r = new GenericResponse();

        Drone drone = droneService.findId(droneId);
        if (drone == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = droneService.update(drone, req);

        if(resultado){
        r.isOk = true;
        r.id = drone.getDroneId();
        r.message = "Se ha actualizado el drone " + drone.getDroneId();
        return ResponseEntity.ok(r);
    }else {

        r.isOk = false;
        r.message = "No se pudo actualizar el drone.";

        return ResponseEntity.badRequest().body(r);
    }
}
@DeleteMapping(value ="/drones/{droneId}")
public ResponseEntity<GenericResponse> deleteDrone (@PathVariable int droneId) {
    GenericResponse resp = new GenericResponse();

    Drone drone = droneService.findId(droneId);

    if (drone != null) {

        droneService.deleteDrone(droneId);
        

    resp.isOk = true;
    resp.id = drone.getDroneId();
    resp.message = "Drone: " + drone.getName()+ " eliminado, felicidades.";

    return ResponseEntity.ok(resp);
}
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}