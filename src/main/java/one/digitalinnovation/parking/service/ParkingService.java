package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService{


    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional
    @ReadOnlyProperty
    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional
    @ReadOnlyProperty
    public Parking findById(String id) {

        return parkingRepository.findById(id).orElseThrow(()->
                new ParkingNotFoundException(id));

    }

    @Transactional
    public Parking create(Parking parkingCreate) {
       String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setLicense(parkingCreate.getLicense());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setColor(parkingCreate.getColor());
        parkingRepository.save(parking);
        return parking;

    }

    @Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        // TODO para fins de teste, foi alocado 7,0 horas de estacionamento
        //TODO pre??o foi estipulado em 5.0 por hora, deve ser lan??ado
        parkingRepository.save(parking);
        return parking;
    }

}
