package pro.sky.socks_warehouse_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.socks_warehouse_app.entity.Sock;


public interface SockRepository extends JpaRepository<Sock, Long> {
    Sock findByColorAndCottonPart(String color, Integer cottonPart);

    @Query(value = "SELECT SUM(quantity) FROM sock WHERE color = :color AND cotton_part = :cottonPart", nativeQuery = true)
    Integer findByCottonPartEquals(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query(value = "SELECT SUM(quantity) FROM sock WHERE color = :color AND cotton_part > :cottonPart", nativeQuery = true)
    Integer findByCottonPartMoreThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query(value = "SELECT SUM(quantity) FROM sock WHERE color = :color AND cotton_part < :cottonPart", nativeQuery = true)
    Integer findByCottonPartLessThan(@Param("color") String color, @Param("cottonPart") int cottonPart);


}
