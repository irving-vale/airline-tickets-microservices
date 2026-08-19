package com.irving.cursoKubernetes.api.controllers;

import com.irving.cursoKubernetes.api.models.request.FlyLessPriceRequestDto;
import com.irving.cursoKubernetes.api.models.request.FlySearchOriginDestinyDto;
import com.irving.cursoKubernetes.api.models.request.PageableRequestDto;
import com.irving.cursoKubernetes.api.models.request.PriceRangeRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.FlyResponseDto;
import com.irving.cursoKubernetes.infraestructure.services.FlyService;
import com.irving.cursoKubernetes.utils.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/fly")
@RequiredArgsConstructor
public class FlyController {

	private final FlyService flyService;

	@GetMapping("/findAll")
	@PreAuthorize("hasAuthority('SCOPE_read')")
	public ResponseEntity<ApiResponseDto<List<FlyResponseDto>>> findAllPagination(
			@RequestBody PageableRequestDto pageableRequestDto) {
		if (pageableRequestDto.getSortType() == null) {
			pageableRequestDto.setSortType(SortType.NONE);
		}
		return ResponseEntity.ok(flyService.findAllPagination(pageableRequestDto));
	}

	@GetMapping("/readLessPrice")
	@PreAuthorize("hasAuthority('SCOPE_read')")
	public ResponseEntity<ApiResponseDto<List<FlyResponseDto>>> readLessPrice(@RequestBody FlyLessPriceRequestDto lessPrice) {
		if (lessPrice.getSortType() == null) {
			lessPrice.setSortType(SortType.NONE);
		}
		return ResponseEntity.ok(flyService.readLessPrice(lessPrice));
	}

	@GetMapping("/readBetweenPrice")
	@PreAuthorize("hasAuthority('SCOPE_read')")
	public ResponseEntity<ApiResponseDto<List<FlyResponseDto>>> readBetweenPrice(@RequestBody PriceRangeRequestDto priceRange) {
		return ResponseEntity.ok(flyService.readBetweenPrice(priceRange));
	}

	@GetMapping("/readByOriginDestiny")
	@PreAuthorize("hasAuthority('SCOPE_read')")
	public ResponseEntity<ApiResponseDto<Set<FlyResponseDto>>> readByOriginDestiny(@RequestBody FlySearchOriginDestinyDto flySearchOriginDestinyDto) {
		return ResponseEntity.ok(flyService.readByOriginDestiny(flySearchOriginDestinyDto));
	}
}
