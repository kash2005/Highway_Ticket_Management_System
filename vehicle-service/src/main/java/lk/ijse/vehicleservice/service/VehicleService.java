package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    void updateVehicle(VehicleDTO vehicleDTO);

    VehicleDTO getVehicle(String vehicleId);

    List<VehicleDTO> getAllVehicles();

    void deleteVehicle(String vehicleId);

    List<VehicleDTO> getVehicleByUserId(String userId);
}
