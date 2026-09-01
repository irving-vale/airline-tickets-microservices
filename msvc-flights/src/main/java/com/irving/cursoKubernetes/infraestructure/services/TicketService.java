package com.irving.cursoKubernetes.infraestructure.services;


import com.irving.cursoKubernetes.api.models.request.TicketRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.TicketResponseDto;
import com.irving.cursoKubernetes.domain.entities.TicketEntity;
import com.irving.cursoKubernetes.domain.mappers.TicketMapper;
import com.irving.cursoKubernetes.domain.repositories.FlyRepository;
import com.irving.cursoKubernetes.domain.repositories.TicketRepository;
import com.irving.cursoKubernetes.infraestructure.abstract_services.ITicketService;
import com.irving.cursoKubernetes.infraestructure.client.CustomerClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService implements ITicketService {

    private final FlyRepository flyRepository;
    private final TicketRepository ticketRepository;
//    private final CustomerRepository customerRepository;
    private final TicketMapper ticketMapper;
    private final CustomerClient customerClient;


    @Override
    public ApiResponseDto<TicketResponseDto> create(TicketRequestDto request) throws ServiceUnavailableException {
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow(() -> new EntityNotFoundException("Fly not found"));
//        var customer = customerRepository.findById(request.getIdClient()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        var customerFindById = getCustomerFindById(request);

        var ticket = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customerId(customerFindById)
                .price(fly.getPrice()
                        .multiply(BigDecimal.valueOf(0.25)))
                .purchaseDate(LocalDate.now())
                .arrivalDate(LocalDateTime.now())
                .departureDate(LocalDateTime.now()).build();
        ticketRepository.save(ticket);

        TicketResponseDto ticketToDto = ticketMapper.toTicketResponseDto(ticket);
        return ApiResponseDto.<TicketResponseDto>builder().status("success").message("Ticket created successfully").statusCode(200).data(ticketToDto).meta(null).build();
    }


    @Override
    public ApiResponseDto<TicketResponseDto> read(UUID uuid) {
        var ticket = ticketRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        var ticketResponseDto = ticketMapper.toTicketResponseDto(ticket);
        return ApiResponseDto.<TicketResponseDto>builder().status("success").message("Ticket found successfully").statusCode(200).data(ticketResponseDto).meta(null).build();
    }

    @Override
    public ApiResponseDto<TicketResponseDto> update(UUID uuid, TicketRequestDto request) throws ServiceUnavailableException {
        TicketEntity ticketEntity = ticketRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));

        var flyUpdate = flyRepository.findById(request.getIdFly()).orElseThrow(() -> new EntityNotFoundException("Fly not found"));

//        var customerUpdate = customerRepository.findById(request.getIdClient()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        var customerFindById = getCustomerFindById(request);

        var TicketUpdate = TicketEntity.builder().id(ticketEntity.getId()).fly(flyUpdate).customerId(customerFindById).price(flyUpdate.getPrice().multiply(BigDecimal.valueOf(0.25))).purchaseDate(LocalDate.now()).arrivalDate(LocalDateTime.now()).departureDate(LocalDateTime.now()).build();
        ticketRepository.save(TicketUpdate);
        var ticketToDto = ticketMapper.toTicketResponseDto(TicketUpdate);
        return ApiResponseDto.<TicketResponseDto>builder().status("success").message("Ticket updated successfully").statusCode(200).data(ticketToDto).meta(null).build();
    }

    @Override
    public ApiResponseDto<Void> delete(UUID uuid) {
        TicketEntity ticketEntity = ticketRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        ticketRepository.delete(ticketEntity);
        log.info("Ticket deleted: {}", ticketEntity);
        return ApiResponseDto.<Void>builder().status("success").message("Ticket deleted successfully").statusCode(200).data(null).meta(null).build();
    }


    @Override
    public ApiResponseDto<BigDecimal> flyByPrice(Long idFly) {
        var fly = flyRepository.findById(idFly).orElseThrow(() -> new EntityNotFoundException("Fly not found"));
        var extraPrice = fly.getPrice().multiply(BigDecimal.valueOf(0.25));
        var finalPrice = fly.getPrice().add(extraPrice);
        return ApiResponseDto.<BigDecimal>builder().status("success").message("Fly found successfully").statusCode(200).data(finalPrice).meta(null).build();


    }


    private @NonNull String getCustomerFindById(TicketRequestDto request) throws ServiceUnavailableException {
        var customer = this.customerClient.getCustomerById(request.getIdClient());
        if (customer != null && "degraded".equals(customer.getStatus())) {
            throw new ServiceUnavailableException(STR."Customer not found with ID: \{request.getIdClient()}");
        }

        if (customer == null || customer.getData() == null || customer.getData().getDni() == null) {
            throw new EntityNotFoundException(STR."Customer not found with ID: \{request.getIdClient()}");
        }
        var customerFindById = customer.getData().getDni();
        return customerFindById;
    }
}
