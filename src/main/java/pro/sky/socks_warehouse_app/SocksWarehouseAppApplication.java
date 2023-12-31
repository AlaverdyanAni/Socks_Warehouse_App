package pro.sky.socks_warehouse_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SocksWarehouseAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocksWarehouseAppApplication.class, args);
    }

}
