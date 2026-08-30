package com.irving.cursoKubernetes.infraestructure.helper;

import com.irving.cursoKubernetes.domain.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.loader.ast.spi.EntityLoader;
import org.springframework.stereotype.Component;

@Transactional
@Component
@AllArgsConstructor
public class CustumerIncreasTicketsAndReservations {

    private final CustomerRepository customerRepository;
    private final CustumerLoader customerLoader;

    public void increaseTicketsAndReservations(String customerId, Class<?> entityType) {
       var custumerUpdate = this.customerLoader.findCustumerEntityById(customerId);
       switch (entityType.getSimpleName()) {
           case "TourService" -> custumerUpdate.setTotalTours(custumerUpdate.getTotalTours() + 1);
           case "TicketService" -> custumerUpdate.setTotalFlights(custumerUpdate.getTotalFlights() + 1);
           case "ReservationService" -> custumerUpdate.setTotalLodgings(custumerUpdate.getTotalLodgings() + 1);
           default -> throw new IllegalArgumentException("Unsupported entity type: " + entityType.getSimpleName());
       }
         this.customerRepository.save(custumerUpdate);


    }
}
