package lk.courierservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourierCompany {
    @Id
    @Column(nullable = false)
    private String courierCode;
    private String name;
    private String email;
    @OneToMany(mappedBy = "courierCompany", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;
}
