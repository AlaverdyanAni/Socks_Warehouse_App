package pro.sky.socks_warehouse_app.mapper;

import pro.sky.socks_warehouse_app.dto.SockDTO;
import pro.sky.socks_warehouse_app.entity.Sock;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SockMapper {
    @Mapping(target = "color", expression = "java(sockDTO.getColor().toLowerCase())")
    @Mapping(target = "cottonPart", source = "cottonPart")
    @Mapping(target = "quantity", source = "quantity")
    Sock sockDtoToSock(SockDTO sockDTO);
}

