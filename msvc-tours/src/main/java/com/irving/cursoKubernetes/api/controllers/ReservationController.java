package com.irving.cursoKubernetes.api.controllers;

import com.irving.cursoKubernetes.api.models.request.ReservationRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.ReservationResponseDto;
import com.irving.cursoKubernetes.infraestructure.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
@AllArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponseDto<ReservationResponseDto>> create(@RequestBody ReservationRequestDto request) {
		return ResponseEntity.ok(reservationService.create(request));
	}

	@GetMapping("/read")
	public ResponseEntity<ApiResponseDto<ReservationResponseDto>> read(@RequestParam UUID uuid) {
		return ResponseEntity.ok(reservationService.read(uuid));
	}

	@PutMapping ("/update/{uuid}")
	public ResponseEntity<ApiResponseDto<ReservationResponseDto>> update(@PathVariable UUID uuid,@RequestBody ReservationRequestDto request) {
		return ResponseEntity.ok(reservationService.update(uuid,request));
	}

	@DeleteMapping("/delete/{uuid}")
	public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable UUID uuid) {
		return ResponseEntity.ok(reservationService.delete(uuid));
	}
}
