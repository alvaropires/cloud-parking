package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService{

    public static Map<String, Parking> parkingMap = new HashMap<>();

    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {

        Parking parking = parkingMap.get(id);
        if (parking == null){
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
       String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setLicense(parkingCreate.getLicense());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;

    }

    public Parking exit(String id) {
        Parking parking = findById(id);
        // TODO para fins de teste, foi alocado 7,0 horas de estacionamento
        //TODO preço foi estipulado em 5.0 por hora, deve ser lançado
        parking.setExitDate(LocalDateTime.now().plusHours(7));
        parking.setBill(valor(parking, 4.80));
        parkingMap.replace(id, parking);
        return parking;
    }

    public Double valor(Parking parking, Double pricePerHour){
        int timeParking = parking.getExitDate().getHour() - parking.getEntryDate().getHour();
        double valor;
        double valorPrimeiraHora = 5.0;
        double valorSegundaHora = 4.50;
        double valorDemaisHoras = 4.0;
        if (timeParking <= 1){
            valor = valorPrimeiraHora;
        } else if (timeParking > 1 && timeParking <= 2) {
            valor = valorPrimeiraHora + valorSegundaHora;
        } else {
            valor = valorPrimeiraHora + valorSegundaHora + valorDemaisHoras * (timeParking - 2);
        }
        return valor;
    }
}
