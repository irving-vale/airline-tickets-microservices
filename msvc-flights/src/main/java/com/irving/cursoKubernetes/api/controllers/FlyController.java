package com.irving.cursoKubernetes.api.controllers;

import com.irving.cursoKubernetes.api.models.request.FlyLessPriceRequestDto;
import com.irving.cursoKubernetes.api.models.request.FlySearchOriginDestinyDto;
import com.irving.cursoKubernetes.api.models.request.PageableRequestDto;
import com.irving.cursoKubernetes.api.models.request.PriceRangeRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.FlyResponseDto;
import com.irving.cursoKubernetes.infraestructure.services.FlyService;
import com.irving.cursoKubernetes.utils.SortType;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/fly")
@RequiredArgsConstructor
@Validated
public class FlyController {

	private final FlyService flyService;

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto<FlyResponseDto>> findFlyById(
			@PathVariable("id") @NotNull @Positive Long id) {
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(flyService.readById(id));
    }

	@GetMapping("/findAll")
	public ResponseEntity<ApiResponseDto<List<FlyResponseDto>>> findAllPagination(
			@RequestParam @NotNull @Min(0) Integer page,
			@RequestParam @NotNull @Min(1) Integer size,
			@RequestParam(required = false) SortType sortType) {
		if (sortType == null) {
			sortType = SortType.NONE; // Default sort type if not provided
		}
		return ResponseEntity.ok(flyService.findAllPagination(page, size, sortType));
	}

	@GetMapping("/readLessPrice")
	public ResponseEntity<ApiResponseDto<List<FlyResponseDto>>> readLessPrice(
			@RequestParam @NotNull @Min(0) Integer page,
			@RequestParam @NotNull @Max(1) Integer size,
			@RequestParam(required = false) SortType sortType,
			@RequestParam @NotNull @Positive BigDecimal price) {
		if (sortType == null) sortType = SortType.NONE;
		return ResponseEntity.ok(flyService.readLessPrice(page,size,sortType,price));
	}

	@GetMapping("/readBetweenPrice")
	public ResponseEntity<ApiResponseDto<List<FlyResponseDto>>> readBetweenPrice(
			@RequestParam @NotNull @Positive BigDecimal min,
			@RequestParam @NotNull @Positive BigDecimal max) {
		return ResponseEntity.ok(flyService.readBetweenPrice(min,max));
	}

	@GetMapping("/readByOriginDestiny")
	public ResponseEntity<ApiResponseDto<Set<FlyResponseDto>>> readByOriginDestiny(
			@RequestParam @NotBlank String origin,
			@RequestParam @NotBlank String destiny) {
		return ResponseEntity.ok(flyService.readByOriginDestiny(origin,destiny));
	}
}
