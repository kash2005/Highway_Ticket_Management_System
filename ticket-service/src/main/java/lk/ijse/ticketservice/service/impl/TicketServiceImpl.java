package lk.ijse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticketservice.ENUM.TicketStatus;
import lk.ijse.ticketservice.conversion.ConversionData;
import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.entity.TicketEntity;
import lk.ijse.ticketservice.repostory.TicketServiceDAO;
import lk.ijse.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    @Autowired
    private ConversionData conversionData;

    @Autowired
    private TicketServiceDAO ticketServiceDAO;

    @Override
    public void saveTicket(TicketDTO ticketDTO) {
        ticketServiceDAO.save(conversionData.mapTo(ticketDTO, TicketEntity.class));
    }

    @Override
    public TicketDTO getTicket(String ticketId) {
        return conversionData.mapTo(ticketServiceDAO.findById(ticketId).get(), TicketDTO.class);
    }

    @Override
    public Object getAllTickets() {
        return conversionData.mapTo(ticketServiceDAO.findAll(), TicketDTO.class);
    }

    @Override
    public void updateTicket(TicketDTO ticketDTO) {
        if (!ticketServiceDAO.existsById(ticketDTO.getTicketId())){
            return;
        }
        ticketServiceDAO.save(conversionData.mapTo(ticketDTO, TicketEntity.class));
    }

    @Override
    public void deleteTicket(String ticketId) {
        ticketServiceDAO.deleteById(ticketId);
    }

    @Override
    public List<TicketDTO> getTicketsByUserId(String userId) {
        List<TicketDTO> tickeDTOs = new ArrayList<>();
        List<TicketEntity> tickets = ticketServiceDAO.findAllByUserId(userId);
        for (TicketEntity ticketEntity : tickets) {
            tickeDTOs.add(conversionData.mapTo(ticketEntity, TicketDTO.class));
        }
        return tickeDTOs;
    }

    @Override
    public List<TicketDTO> getTicketsByVehicleId(String vehicleId) {
        List<TicketDTO> tickeDTOs = new ArrayList<>();
        List<TicketEntity> tickets = ticketServiceDAO.findAllByVehicleId(vehicleId);
        for (TicketEntity ticketEntity : tickets) {
            tickeDTOs.add(conversionData.mapTo(ticketEntity, TicketDTO.class));
        }
        return tickeDTOs;
    }

    @Override
    public boolean isTicketExists(String ticketId) {
        if (ticketServiceDAO.existsById(ticketId)){
            TicketDTO ticket = getTicket(ticketId);
            ticket.setTicketStatus(TicketStatus.PAID);
            updateTicket(ticket);
            return true;
        }
        return false;
    }

}
