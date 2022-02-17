package lk.courierservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @NonNull
    private String senderName;
    @NonNull
    private String senderAddress;
    @NonNull
    private String receiverName;
    @NonNull
    private String receiverAddress;
    @NonNull
    private double parcelWeight;
    @ManyToOne
    private CourierCompany courierCompany;

}
