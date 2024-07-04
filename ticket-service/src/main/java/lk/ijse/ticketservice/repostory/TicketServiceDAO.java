package lk.ijse.ticketservice.repostory;

import lk.ijse.ticketservice.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketServiceDAO extends JpaRepository<TicketEntity,String> {
        List<TicketEntity> findAllByUserId(String userId);
        List<TicketEntity> findAllByVehicleId(String vehicleId);
}
