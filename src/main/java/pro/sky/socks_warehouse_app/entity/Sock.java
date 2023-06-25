package pro.sky.socks_warehouse_app.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity

@Table(name = "sock")
public class Sock  {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @Column(name = "color")
   private String color;
   @Column(name = "cotton_part", nullable=false)
   private Integer cottonPart;
   @Column(name = "quantity", nullable=false)
   private Integer quantity;

   public Sock(String color, Integer cottonPart, Integer quantity) {
   }


}
