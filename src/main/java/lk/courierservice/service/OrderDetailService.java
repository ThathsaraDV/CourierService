package lk.courierservice.service;

import lk.courierservice.dto.OrderDetailDto;
import lk.courierservice.exception.NotFoundException;
import lk.courierservice.exception.ValidateException;
import org.hibernate.JDBCException;
import org.modelmapper.MappingException;

import java.util.List;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/
public interface OrderDetailService {

    List<OrderDetailDto> getAllOrders() throws NotFoundException;

    OrderDetailDto addOrder(OrderDetailDto orderDetailDto) throws MappingException, ValidateException, NotFoundException, JDBCException;

    OrderDetailDto updateOrder(OrderDetailDto orderDetailDto) throws NotFoundException, ValidateException, MappingException;

    void deleteOrder(String orderId) throws NotFoundException, NumberFormatException;

}
