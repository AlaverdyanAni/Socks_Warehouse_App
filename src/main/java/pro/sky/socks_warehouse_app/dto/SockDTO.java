package pro.sky.socks_warehouse_app.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


/**
 * Сущность  для прихода и расхода носков
 */

@Data
@Schema(description = "Сущность для носков")
public class SockDTO {

    /**
     * Цвет носков
     */

    @Schema(description = "Цвет")
    @NotNull(message = "Color не должно быть null")
    private String color;

    /**
     * Процент хлопка в составе носков
     */

    @Schema(description = "Процент хлопка")
    @NotNull(message = "Cotton part не должно быть null")
    @Min(value = 0, message = "Минимальное число должно быть не меньше 0")
    @Max(value = 100, message = "Максимальное число должно быть не больше 100")
    private Integer cottonPart;

    /**
     * Количество носков
     */

    @Schema(description = "Количество")
    @NotNull(message = "Quantity не должно быть null")
    @Min(value = 1, message = "Минимальное целое число должно быть не меньше 0")
    @Max(value = Integer.MAX_VALUE, message = "Максимальное число должно быть не больше Integer.MAX_VALUE")
    private Integer quantity;

}

