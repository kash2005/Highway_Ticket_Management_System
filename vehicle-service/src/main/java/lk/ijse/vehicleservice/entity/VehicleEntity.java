package lk.ijse.vehicleservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleEntity {
    @Id
    private String vehicleId;
    private String vehicleName;
    private String vehicleType;
    private String vehicleNumber;
    private String vehicleModel;
    private String userId;
}
