package lk.ijse.vehicleservice.repostory;

import lk.ijse.vehicleservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleServiceDAO extends JpaRepository<VehicleEntity,String> {
    List<VehicleEntity> findAllByUserId(String userId);
}
