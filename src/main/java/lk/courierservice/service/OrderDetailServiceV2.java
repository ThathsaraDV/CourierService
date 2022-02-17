package lk.courierservice.service;

import lk.courierservice.dto.OrderDetailDto;
import lk.courierservice.dto.OrderDetailDtoV2;
import lk.courierservice.exception.NotFoundException;
import lk.courierservice.exception.ValidateException;
import org.hibernate.JDBCException;
import org.modelmapper.MappingException;

import java.util.List;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/
public interface OrderDetailServiceV2 {

    List<OrderDetailDtoV2> getAllOrders() throws NotFoundException;

    OrderDetailDtoV2 addOrder(OrderDetailDtoV2 orderDetailDtoV2) throws MappingException, ValidateException, NotFoundException, JDBCException;

    OrderDetailDtoV2 updateOrder(OrderDetailDtoV2 orderDetailDtoV2) throws NotFoundException, ValidateException, MappingException;

    void deleteOrder(String orderId) throws NotFoundException, NumberFormatException;

}
