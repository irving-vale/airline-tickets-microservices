package com.irving.cursoKubernetes.api.controllers;

import com.irving.cursoKubernetes.api.models.request.TicketRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.TicketResponseDto;
import com.irving.cursoKubernetes.infraestructure.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {

	private final TicketService ticketService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponseDto<TicketResponseDto>> createTicket(@RequestBody TicketRequestDto request) {
		return ResponseEntity.ok(ticketService.create(request));
	}

	@GetMapping("/read")
	public ResponseEntity<ApiResponseDto<TicketResponseDto>> readTicket(@RequestParam UUID uuid) {
		return ResponseEntity.ok(ticketService.read(uuid));
	}

	@PutMapping("/update/{uuid}")
	public ResponseEntity<ApiResponseDto<TicketResponseDto>> updateTicket(@PathVariable UUID uuid,@RequestBody TicketRequestDto request) {
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
