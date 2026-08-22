package com.irving.cursoKubernetes.infraestructure.helper;

import com.irving.cursoKubernetes.api.models.responses.CustomerResponseDto;
import com.irving.cursoKubernetes.domain.entities.HotelEntity;
import com.irving.cursoKubernetes.domain.entities.ReservationEntity;
import com.irving.cursoKubernetes.domain.repositories.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
//    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;

    public Set<TicketEntity> createTickets(Set<FlyEntity> flights, CustomerEntity customer) {
        var response = new HashSet<TicketEntity>(flights.size());
        flights.forEach(flight -> {
            var ticket = TicketEntity.builder()
                    .id(UUID.randomUUID())
                    .fly(flight).customer(customer)
                    .price(calculatePriceWithTax(flight.getPrice()))
                    .purchaseDate(LocalDate.now())
                    .arrivalDate(LocalDateTime.now(ZoneId.of("America/Mexico_City")))
                    .departureDate(LocalDateTime.now(ZoneId.of("America/Mexico_City")))
                    .build();
            response.add(this.ticketRepository.save(ticket));

        });
        return response;
    }

    public Set<ReservationEntity> createReservations(HashMap<HotelEntity, Integer> hotels, CustomerResponseDto customer) {
        var response = new HashSet<ReservationEntity>(hotels.size());
        hotels.forEach((hotel, days) -> {
            var reservation = ReservationEntity.builder()
                    .id(UUID.randomUUID())
                    .dateTimeReservation(LocalDateTime.now(ZoneId.of("America/Mexico_City")))
                    .dateStart(LocalDate.now())
                    .dateEnd(LocalDate.now().plusDays(days))
                    .totalDays(days)
                    .price(calculatePriceWithTax(hotel.getPrice())).hotel(hotel)
                    .customerId(customer.getDni()).build();
            response.add(this.reservationRepository.save(reservation));

        });
        return response;
    }


    private BigDecimal calculatePriceWithTax(BigDecimal basePrice) {
        return basePrice.add(basePrice.multiply(BigDecimal.valueOf(0.25)));
    }


}
