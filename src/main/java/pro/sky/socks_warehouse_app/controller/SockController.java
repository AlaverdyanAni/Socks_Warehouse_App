package pro.sky.socks_warehouse_app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.socks_warehouse_app.dto.SockDTO;
import pro.sky.socks_warehouse_app.service.SockService;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "SocksController", description = "Приход и расход носков на складе")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @Operation(
            summary = "Регистрация прихода носков",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сохраняет партию носков в базу данных"
                    )
            }
    )
    @PostMapping("/income")
    private void income(@RequestBody SockDTO sockDTO ) {
        sockService.addSock(sockDTO);
    }
    @Operation(
            summary = "Регистрация отпуска носков",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Уменьшает количество носков в базе данных"
                    )
            }
    )
    @PostMapping("/outcome")
    private void outcome(@RequestBody SockDTO sockDTO) {
        sockService.deleteSock(sockDTO);
    }


    @Operation(
            summary = "Получение общего колличества носков по указанным параметрам",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Возвращяет общее колличество носков по указанным параметрам"
                    )
            }
    )


    @GetMapping
    private ResponseEntity<Integer> getQuantity(@Parameter(description = "Введите цвет", example = "red")
                               @RequestParam (value = "color") String color,
                                                @Parameter(description = "Введите значение количества хлопка в составе носков" +
                                       " 'moreThan', 'lessThan', 'equal'", example = "equal")
                               @RequestParam (value = "operation") String operation,
                                                @Parameter(description = "Введите значение процента хлопка в составе носок", example = "50")
                               @RequestParam (value = "cottonPart") int cottonPart) {
        return ResponseEntity.ok().body(sockService.getQuantity(color, operation, cottonPart));
    }

}