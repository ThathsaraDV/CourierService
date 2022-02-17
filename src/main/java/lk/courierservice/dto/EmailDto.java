package lk.courierservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/16/2022
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDto {

    private String to;
    private String subject;
    private String body;
}
