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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public ApiResponseDto<List<FlyResponseDto>> findAllPagination(PageableRequestDto pageableRequestDto) {
        Sort sortValue = sort(pageableRequestDto.getSortType());

        Pageable pageable = PageRequest.of(pageableRequestDto.getPage() - 1, pageableRequestDto.getSize(), sortValue);

        Page<FlyResponseDto> flyToDto = flyRepository.findAllJoinFetch(pageable).map(flyMapper::toDto);
        return ApiResponseDto.<List<FlyResponseDto>>builder()
                .status("success")
                .message(flyToDto.isEmpty() ? "No Flys found" : "Flys retrieved successfully")
                .data(flyToDto.getContent())
                .statusCode(200)
                .meta(Meta.builder()
                        .totalItems(flyToDto.getTotalElements())
                        .totalPages(flyToDto.getTotalPages())
                        .currentPage(pageableRequestDto.getPage())
                        .pageSize(flyToDto.getSize())
                        .build())
                .build();


    }

    @Override
    public ApiResponseDto<List<FlyResponseDto>> readLessPrice(FlyLessPriceRequestDto lessPrice) {
        Sort sortValue = sort(lessPrice.getSortType());
        Pageable pageable = PageRequest.of(lessPrice.getPage() - 1, lessPrice.getSize(), sortValue);

        Page<FlyResponseDto> flyToDto = flyRepository.findByPriceLessThanEqual(pageable, lessPrice.getPrice()).map(flyMapper::toDto);
        return ApiResponseDto.<List<FlyResponseDto>>builder()
                .status("success")
                .message(flyToDto.isEmpty() ? STR."No Flys found with price less than or equal to \{lessPrice.getPrice()}"
                        : STR."Flys retrieved successfully with price less than or equal to \{lessPrice.getPrice()}")
                .data(flyToDto.getContent())
                .statusCode(200)
                .meta(Meta.builder()
                        .totalItems(0L)
                        .totalPages(0)
                        .currentPage(lessPrice.getPage() - 1)
                        .pageSize(lessPrice.getSize())
                        .build())
                .build();
    }

    @Override
    public ApiResponseDto<List<FlyResponseDto>> readBetweenPrice(PriceRangeRequestDto priceRange) {

        List<FlyResponseDto> flyToDto = flyRepository.findByPriceBetween(priceRange.getMin(), priceRange.getMax()).stream()
                .map(flyMapper::toDto)
                .toList();
        return ApiResponseDto.<List<FlyResponseDto>>builder()
                .status("success")
                .message(flyToDto.isEmpty() ? STR."No Flys found between prices \{priceRange.getMin()} and \{priceRange.getMax()}"
                        : STR."Flys retrieved successfully between prices \{priceRange.getMin()} and \{priceRange.getMax()}")
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
    public ApiResponseDto<Set<FlyResponseDto>> readByOriginDestiny(FlySearchOriginDestinyDto flySearchOriginDestinyDto) {

        var flyToDto = flyRepository.findByOriginNameAndDestinyName(flySearchOriginDestinyDto.getOrigin(),flySearchOriginDestinyDto.getDestiny())
                .stream().map(flyMapper::toDto)
                .collect(Collectors.toSet());
        return ApiResponseDto.<Set<FlyResponseDto>>builder()
                .status("success")
                .message(flyToDto.isEmpty()? "No fly found between origin destiny"
                        : "Fly found between origin and destiny")
                .data(flyToDto)
                .statusCode(200)
                .meta(Meta.builder()
                        .totalItems((long)flyToDto.size())
                        .totalPages(1)
                        .currentPage(1)
                        .pageSize(1)
                        .build())
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