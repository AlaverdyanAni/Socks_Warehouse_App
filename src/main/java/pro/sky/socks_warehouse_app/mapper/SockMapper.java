package pro.sky.socks_warehouse_app.mapper;

import pro.sky.socks_warehouse_app.dto.SockDTO;
import org.mapstruct.*;
import pro.sky.socks_warehouse_app.model.Sock;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SockMapper {
    @Mapping(target = "color", expression = "java(sockDTO.getColor().toLowerCase())")
    Sock toEntity(SockDTO sockDTO);

    SockDTO toDto(Sock sock);

    @Mapping(target = "id", ignore = true)
    void enrichSock(SockDTO sockDTO, @MappingTarget Sock sock);

}

