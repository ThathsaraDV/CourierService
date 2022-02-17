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
public class CourierCompanyDto {
    private String courierCode;
    private String name;
    private String email;
}
