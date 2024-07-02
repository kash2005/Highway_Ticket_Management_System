package lk.ijse.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleDTO {
    private String vehicleId;
    private String vehicleName;
    private String vehicleType;
    private String vehicleNumber;
    private String vehicleModel;
    private String userId;
}
