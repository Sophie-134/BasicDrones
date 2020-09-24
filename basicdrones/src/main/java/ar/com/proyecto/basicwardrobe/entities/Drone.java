package ar.com.proyecto.basicwardrobe.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Drone {
    
    @Id
    @Column(name= "Drone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int droneId;
    private String name;
    private int propellers;
    private double maxSpeed;

    private List<Drone> drones = new ArrayList<>();

    public int getDroneId() {
        return droneId;
    }

    public void setDroneId(int droneId) {
        this.droneId = droneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPropellers() {
        return propellers;
    }

    public void setPropellers(int propellers) {
        this.propellers = propellers;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void setDrones(List<Drone> drones) {
        this.drones = drones;
    }
    public enum ResultadoDroneEnum{
        VELOCIDAD_NO_VALIDA,
        ID_NO_VALIDO,
        DRONE_INEXISTENTE,
        INICIADA;
    }
}
