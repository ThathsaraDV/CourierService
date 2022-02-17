package lk.courierservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {
    private Long orderId;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    private double parcelWeight;
    private CourierCompanyDto courierCompany;

}
