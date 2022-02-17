package lk.courierservice.service;

import lk.courierservice.entity.CourierCompany;
import lk.courierservice.exception.NotFoundException;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/14/2022
 **/
public interface CourierDetailService {

    CourierCompany getCourierCompanyByCode(String code) throws NotFoundException;

}
