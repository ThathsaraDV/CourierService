package lk.courierservice.service.impl;

import lk.courierservice.dto.CourierCompanyDto;
import lk.courierservice.dto.OrderDetailDto;
import lk.courierservice.exception.NotFoundException;
import lk.courierservice.exception.ValidateException;
import lk.courierservice.service.OrderDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/14/2022
 **/

@SpringBootTest
@Transactional
public class OrderDetailServiceImplTest {

    @Autowired
    private OrderDetailService service;

    @Test
    void getAllOrders(){
        //get all orders
        Assertions.assertDoesNotThrow(() ->{
            service.getAllOrders();
        });
    }

    @Test
    void getAllOrders2(){
        //no orders in the database
        Assertions.assertThrows(NotFoundException.class, () -> service.getAllOrders());
    }

    @Test
    void addOrderDetail(){
        //add order detail
        CourierCompanyDto companyDto = new CourierCompanyDto();
        companyDto.setCourierCode("C001");
        OrderDetailDto detailDto = new OrderDetailDto(0L,"DT Store","A2, Galle Street, Colombo 7","Thathsara","A 156, Gurugoda, Horana",1.2,companyDto);

        Assertions.assertDoesNotThrow(() ->{
            service.addOrder(detailDto);
        });
    }

    @Test
    void addInvalidOrderDetail(){
        //add invalid order detail
        CourierCompanyDto companyDto = new CourierCompanyDto();
        companyDto.setCourierCode("C001");
        OrderDetailDto detailDto = new OrderDetailDto(0L,null,"A2, Galle Street, Colombo 7","Thathsara","A 156, Gurugoda, Horana",1.2,companyDto);

        Assertions.assertThrows(MappingException.class, () -> service.addOrder(detailDto));
    }

    @Test
    void addInvalidOrderDetail2(){
        //add empty courier code order detail
        CourierCompanyDto companyDto = new CourierCompanyDto();
        companyDto.setCourierCode("");
        OrderDetailDto detailDto = new OrderDetailDto(0L,"DT Store","A2, Galle Street, Colombo 7","Thathsara","A 156, Gurugoda, Horana",1.2,companyDto);

        Assertions.assertThrows(ValidateException.class, () -> service.addOrder(detailDto));
    }

    @Test
    void addInvalidOrderDetail3(){
        //add invalid courier code order detail
        CourierCompanyDto companyDto = new CourierCompanyDto();
        companyDto.setCourierCode("C005");
        OrderDetailDto detailDto = new OrderDetailDto(0L,"DT Store","A2, Galle Street, Colombo 7","Thathsara","A 156, Gurugoda, Horana",1.2,companyDto);

        Assertions.assertThrows(NotFoundException.class, () -> service.addOrder(detailDto));
    }

    @Test
    void updateOrderDetail(){
        //update order detail
        CourierCompanyDto companyDto = new CourierCompanyDto();
        companyDto.setCourierCode("C002");
        OrderDetailDto detailDto = new OrderDetailDto(1L,"DT Store","A2, Galle Street, Colombo 7","Dananjaya","A 156, Gurugoda, Horana",1.2,companyDto);

        Assertions.assertDoesNotThrow(() -> service.updateOrder(detailDto));
    }

    @Test
    void updateInvalidOrderIdOrderDetail(){
        //update order detail with invalid orderId
        CourierCompanyDto companyDto = new CourierCompanyDto();
        companyDto.setCourierCode("C002");
        //no order under orderId 2
        OrderDetailDto detailDto = new OrderDetailDto(2L,"DT Store","A2, Galle Street, Colombo 7","Dananjaya","A 156, Gurugoda, Horana",1.2,companyDto);

        Assertions.assertThrows(NotFoundException.class, () -> service.updateOrder(detailDto));
    }

    @Test
    void updateInvalidCourierCodeOrderDetail(){
        //update order detail with invalid courierCode
        CourierCompanyDto companyDto = new CourierCompanyDto();
        companyDto.setCourierCode("C005");
        OrderDetailDto detailDto = new OrderDetailDto(1L,"DT Store","A2, Galle Street, Colombo 7","Dananjaya","A 156, Gurugoda, Horana",1.2,companyDto);

        Assertions.assertThrows(NotFoundException.class, () -> service.updateOrder(detailDto));
    }

    @Test
    void updateWithNullDetailOrderDetail(){
        //update order detail with null detail
        CourierCompanyDto companyDto = new CourierCompanyDto();
        companyDto.setCourierCode("C002");
        OrderDetailDto detailDto = new OrderDetailDto(1L,null,"A2, Galle Street, Colombo 7","Dananjaya","A 156, Gurugoda, Horana",1.2,companyDto);

        Assertions.assertThrows(MappingException.class, () -> service.updateOrder(detailDto));
    }

    @Test
    void deleteOrderDetail(){
        //delete order detail
        Assertions.assertDoesNotThrow(() -> service.deleteOrder("1"));
    }

    @Test
    void deleteOrderDetail2(){
        //delete orderDetail not in the database
        //no record under orderId 2
        Assertions.assertThrows(NotFoundException.class, () -> service.deleteOrder("2"));
    }

    @Test
    void deleteOrderDetail3(){
        //delete orderDetail with invalid orderId
        Assertions.assertThrows(NumberFormatException.class, () -> service.deleteOrder("2abc"));
    }
}
