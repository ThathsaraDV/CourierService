package lk.courierservice.service;

import lk.courierservice.dto.EmailDto;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/16/2022
 **/
public interface EmailService {

    void sendEmail(EmailDto emailDto);

}
