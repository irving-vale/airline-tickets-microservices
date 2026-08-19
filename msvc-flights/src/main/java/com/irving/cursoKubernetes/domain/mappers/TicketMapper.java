package com.irving.cursoKubernetes.domain.mappers;

import com.irving.cursoKubernetes.api.models.responses.TicketResponseDto;
import com.irving.cursoKubernetes.domain.entities.TicketEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FlyMapper.class})
public interface TicketMapper {
	TicketResponseDto toTicketResponseDto(TicketEntity ticket);
	TicketEntity toTicketEntity(TicketResponseDto ticketResponseDto);
}
