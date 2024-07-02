package lk.ijse.ticketservice.repostory;

import lk.ijse.ticketservice.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketServiceDAO extends JpaRepository<TicketEntity,String> {
        List<TicketEntity> findAllByUserId(String userId);
        List<TicketEntity> findAllByVehicleId(String vehicleId);
}
