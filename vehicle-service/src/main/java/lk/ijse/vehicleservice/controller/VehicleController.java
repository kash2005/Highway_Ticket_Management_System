package lk.ijse.vehicleservice.controller;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.service.UserServiceClient;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.service.impl.UserServiceClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserServiceClient userServiceClient;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClientImpl.class);

    @GetMapping("/health")
    private String health(){
        return "Vehicle Service is running";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> saveVehicle(@RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        if (!userServiceClient.isExitsUser(vehicleDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }

        vehicleService.saveVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle saved successfully");
    }

    @PutMapping()
    private ResponseEntity<?> updateVehicle(@RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        vehicleService.updateVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle updated successfully");
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicle(@PathVariable ("vehicleId") String vehicleId){
        return ResponseEntity.ok(vehicleService.getVehicle(vehicleId));
    }

    @GetMapping
    public ResponseEntity<?> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable ("vehicleId") String vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVehiclesByUserId(@PathVariable ("userId") String userId){
        return ResponseEntity.ok(vehicleService.getVehicleByUserId(userId));
    }

    @GetMapping("/vehicleExists/{vehicleId}")
    public ResponseEntity<?> isVehicleExists(@PathVariable String vehicleId) {
        logger.info("Checking user existence with ID: {}", vehicleId);
        try {
            boolean isUserExists = vehicleService.isVehicleExists(vehicleId);
            logger.info("Vehicle Exists: {}", isUserExists);
            return ResponseEntity.ok(isUserExists);
        } catch (Exception exception) {
            logger.error("Error checking vehicle existence: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to check vehicle existence.\nMore Details\n" + exception);
        }
    }
}
