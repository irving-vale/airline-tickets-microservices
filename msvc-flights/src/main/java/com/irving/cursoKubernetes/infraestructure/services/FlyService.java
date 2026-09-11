package com.irving.cursoKubernetes.infraestructure.services;

import com.irving.cursoKubernetes.api.models.request.FlyLessPriceRequestDto;
import com.irving.cursoKubernetes.api.models.request.FlySearchOriginDestinyDto;
import com.irving.cursoKubernetes.api.models.request.PageableRequestDto;
import com.irving.cursoKubernetes.api.models.request.PriceRangeRequestDto;
import com.irving.cursoKubernetes.api.models.responses.ApiResponseDto;
import com.irving.cursoKubernetes.api.models.responses.FlyResponseDto;
import com.irving.cursoKubernetes.api.models.responses.Meta;
import com.irving.cursoKubernetes.domain.mappers.FlyMapper;
import com.irving.cursoKubernetes.domain.repositories.FlyRepository;
import com.irving.cursoKubernetes.infraestructure.abstract_services.IFlyService;
import com.irving.cursoKubernetes.utils.SortType;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class FlyService implements IFlyService {

    private final FlyRepository flyRepository;
    private final FlyMapper flyMapper;

    @Override
    public ApiResponseDto<List<FlyResponseDto>> findAllPagination(Integer page, Integer size, SortType sortType) {
        Sort sortValue = sort(sortType);

        Pageable pageable = PageRequest.of(page - 1, size, sortValue);

        Page<FlyResponseDto> flyToDto = flyRepository.findAllJoinFetch(pageable).map(flyMapper::toDto);
        return ApiResponseDto.<List<FlyResponseDto>>builder()
                .status("success")
                .message(flyToDto.isEmpty() ? "No Flys found" : "Flys retrieved successfully")
                .data(flyToDto.getContent())
                .statusCode(200)
                .meta(Meta.builder()
                        .totalItems(flyToDto.getTotalElements())
                        .totalPages(flyToDto.getTotalPages())
                        .currentPage(page)
                        .pageSize(flyToDto.getSize())
                        .build())
                .build();


    }

    @Override
    public ApiResponseDto<List<FlyResponseDto>> readLessPrice(Integer page, Integer size, SortType sortType, BigDecimal price) {
        Sort sortValue = sort(sortType);
        Pageable pageable = PageRequest.of(page - 1, size, sortValue);

        Page<FlyResponseDto> flyToDto = flyRepository.findByPriceLessThanEqual(pageable, price).map(flyMapper::toDto);
        return ApiResponseDto.<List<FlyResponseDto>>builder()
                .status("success")
                .message(flyToDto.isEmpty() ? "No Fly's found with price "
                        : "Fly's retrieved successfully with price ")
                .data(flyToDto.getContent())
                .statusCode(200)
                .meta(Meta.builder()
                        .totalItems(0L)
                        .totalPages(0)
                        .currentPage(page - 1)
                        .pageSize(size)
                        .build())
                .build();
    }

    @Override
    public ApiResponseDto<List<FlyResponseDto>> readBetweenPrice(BigDecimal min, BigDecimal max) {

        List<FlyResponseDto> flyToDto = flyRepository.findByPriceBetween(min, max).stream()
                .map(flyMapper::toDto)
                .toList();
        return ApiResponseDto.<List<FlyResponseDto>>builder()
                .status("success")
                .message(flyToDto.isEmpty() ?"No Flys found between prices "
                        : "Flys retrieved successfully between prices ")
                .data(flyToDto)
                .statusCode(200)
                .meta(Meta.builder()
                        .totalItems(0L)
                        .totalPages(0)
                        .currentPage(0)
                        .pageSize(0)
                        .build())
                .build();
    }


    @Override
    public ApiResponseDto<Set<FlyResponseDto>> readByOriginDestiny(String origin, String destiny) {

        var flyToDto = flyRepository
                .findByOriginNameAndDestinyName(origin,destiny)
                .stream().map(flyMapper::toDto)
                .collect(Collectors.toSet());
        boolean isEmpty = flyToDto.isEmpty();
        int totalItems = flyToDto.size();

        return ApiResponseDto.<Set<FlyResponseDto>>builder()
                .status("success")
                .message(flyToDto.isEmpty()
                        ? "No fly found between origin destiny"
                        : "Fly found between origin and destiny")
                .data(flyToDto)
                .statusCode(200)
                .meta(Meta.builder()
                        .totalItems((long) totalItems)
                        .totalPages(isEmpty ? 0 : 1)
                        .currentPage(isEmpty ? 0 : 1)
                        .pageSize(totalItems)
                        .build())
                .build();

    }

    @Override
    public ApiResponseDto<FlyResponseDto> readById(Long id) {
        var flyToDto = flyRepository.findById(id).map(flyMapper::toDto)
                .orElseThrow(()-> new EntityNotFoundException("Fly not found"));

        return ApiResponseDto.<FlyResponseDto>builder()
                .status("success")
                .message("Fly found successfully")
                .data(flyToDto)
                .statusCode(200)
                .meta(null)
                .build();

    }


    private Sort sort(SortType sortType) {
        if (sortType == null) {
            throw new IllegalArgumentException("sortType must not be null");
        }
        Sort base = Sort.by("price");
        return switch (sortType) {
            case LOWER -> base.ascending();
            case UPPER -> base.descending();
            case NONE -> Sort.unsorted();
        };
    }
}