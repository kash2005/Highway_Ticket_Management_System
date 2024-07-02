package lk.ijse.vehicleservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.vehicleservice.conversion.ConversionData;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.VehicleEntity;
import lk.ijse.vehicleservice.repostory.VehicleServiceDAO;
import lk.ijse.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceIMPL implements VehicleService {
    @Autowired
    private ConversionData conversionData;
    @Autowired
    private VehicleServiceDAO vehicleServiceDAO;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleServiceDAO.save(conversionData.mapTo(vehicleDTO, VehicleEntity.class));
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleServiceDAO.existsById(vehicleDTO.getVehicleId())) {
            return;
        }
        vehicleServiceDAO.save(conversionData.mapTo(vehicleDTO, VehicleEntity.class));
    }

    @Override
    public VehicleDTO getVehicle(String vehicleId) {
        return conversionData.mapTo(vehicleServiceDAO.findById(vehicleId).get(), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return conversionData.mapTo(vehicleServiceDAO.findAll(), VehicleDTO.class);
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        vehicleServiceDAO.deleteById(vehicleId);
    }

    @Override
    public List<VehicleDTO> getVehicleByUserId(String userId) {
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();
        List<VehicleEntity> vehicles = vehicleServiceDAO.findAllByUserId(userId);
        for (VehicleEntity vehicleEntity : vehicles) {
            vehicleDTOs.add(conversionData.mapTo(vehicleEntity, VehicleDTO.class));
        }
        return vehicleDTOs;
    }


}
