package lk.ijse.ticketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.ticketservice.ENUM.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketEntity {
    @Id
    private String ticketId;
    private LocalDate ticketIssueDate;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    private String vehicleId;
    private String userId;
}
