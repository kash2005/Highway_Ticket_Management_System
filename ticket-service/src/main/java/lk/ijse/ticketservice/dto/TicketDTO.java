package lk.ijse.ticketservice.dto;

import lk.ijse.ticketservice.ENUM.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketDTO {
    private String ticketId;
    private LocalDate ticketIssueDate;
    private TicketStatus ticketStatus;
    private String vehicleId;
    private String userId;
}
