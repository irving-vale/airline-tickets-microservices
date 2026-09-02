package com.irving.cursoKubernetes.api.controllers;

import com.irving.cursoKubernetes.api.models.request.TicketRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.TicketResponseDto;
import com.irving.cursoKubernetes.infraestructure.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
public class TicketController {

	private final TicketService ticketService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponseDto<TicketResponseDto>> createTicket(@RequestBody TicketRequestDto request) throws ServiceUnavailableException {
		return ResponseEntity.ok(ticketService.create(request));
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<ApiResponseDto<TicketResponseDto>> readTicket(@PathVariable("uuid") UUID uuid) {
		return ResponseEntity.ok(ticketService.read(uuid));
	}

	@PutMapping("/update/{uuid}")
	public ResponseEntity<ApiResponseDto<TicketResponseDto>> updateTicket(@PathVariable UUID uuid,@RequestBody TicketRequestDto request) throws ServiceUnavailableException {
		return ResponseEntity.ok(ticketService.update(uuid, request));
	}

	@DeleteMapping("/delete/{uuid}")
	public ResponseEntity<ApiResponseDto<Void>> deleteTicket(@PathVariable UUID uuid) {
		return ResponseEntity.ok(ticketService.delete(uuid));
	}
	@GetMapping("/flyByPrice")
	public ResponseEntity<ApiResponseDto<BigDecimal>> readById(@RequestParam Long idFly) {
		return ResponseEntity.ok(ticketService.flyByPrice(idFly));
	}

}
