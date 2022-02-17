package lk.courierservice.service.impl;

import lk.courierservice.dto.EmailDto;
import lk.courierservice.dto.OrderDetailDto;
import lk.courierservice.dto.OrderDetailDtoV2;
import lk.courierservice.entity.CourierCompany;
import lk.courierservice.entity.OrderDetail;
import lk.courierservice.entity.OrderDetailV2;
import lk.courierservice.exception.NotFoundException;
import lk.courierservice.exception.ValidateException;
import lk.courierservice.reposiotry.CourierDetailRepo;
import lk.courierservice.reposiotry.OrderDetailRepoV2;
import lk.courierservice.service.EmailProducerService;
import lk.courierservice.service.OrderDetailServiceV2;
import org.hibernate.JDBCException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/

@Service
@Transactional
public class OrderDetailServiceImplV2 implements OrderDetailServiceV2 {

    @Autowired
    private OrderDetailRepoV2 orderDetailRepoV2;

    @Autowired
    private CourierDetailRepo courierDetailRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmailProducerService emailProducerService;

    @Override
    public ArrayList<OrderDetailDtoV2> getAllOrders() throws NotFoundException {
        List<OrderDetailV2> allOrderDetails = orderDetailRepoV2.findAll();
        if (allOrderDetails.isEmpty()){
            throw new NotFoundException("Records not found");
        }
        return mapper.map(allOrderDetails,new TypeToken<ArrayList<OrderDetailDtoV2>>(){}.getType());
    }

    @Override
    public OrderDetailDtoV2 addOrder(OrderDetailDtoV2 orderDetailDtoV2) throws MappingException, ValidateException, NotFoundException, JDBCException {
        if (orderDetailDtoV2.getCourierCompany().getCourierCode().isEmpty()){
            throw new ValidateException("Courier Code cannot be empty");
        }
        CourierCompany courierCompany =  courierDetailRepo.findById(orderDetailDtoV2.getCourierCompany().getCourierCode()).orElseThrow(() -> {
            throw new NotFoundException("No Courier Company by this Code");
        });
        try {
            orderDetailRepoV2.save(mapper.map(orderDetailDtoV2, OrderDetailV2.class));
            String subject = "Order For Ship";
            String body = "Please pickup this order from "+orderDetailDtoV2.getSenderName()+" at "+orderDetailDtoV2.getSenderAddress()+" of "+orderDetailDtoV2.getParcelType()+" that weigh "+orderDetailDtoV2.getParcelWeight()+" and deliver to "+orderDetailDtoV2.getReceiverName()+" at "+orderDetailDtoV2.getReceiverAddress() ;
            EmailDto emailDto = new EmailDto(courierCompany.getEmail(),subject,body);
            emailProducerService.sendEmail(emailDto);
        }catch (MappingException e) {
            List<ErrorMessage> messages = new ArrayList<>(e.getErrorMessages());
            throw new MappingException(messages);
        }catch (JDBCException e){
            throw new JDBCException(e.getMessage(),e.getSQLException());
        }
        return orderDetailDtoV2;
    }

    @Override
    public OrderDetailDtoV2 updateOrder(OrderDetailDtoV2 orderDetailDtoV2) throws NotFoundException, ValidateException, MappingException {
        if (orderDetailDtoV2.getCourierCompany().getCourierCode().isEmpty()){
            throw new ValidateException("Courier Code cannot be empty");
        }
        courierDetailRepo.findById(orderDetailDtoV2.getCourierCompany().getCourierCode()).orElseThrow(() -> {
            throw new NotFoundException("No Courier Company by this Code");
        });
        orderDetailRepoV2.findById(orderDetailDtoV2.getOrderId()).orElseThrow(() -> {
            throw new NotFoundException("Record Not Found");
        });
        try {
            orderDetailRepoV2.save(mapper.map(orderDetailDtoV2,OrderDetailV2.class));
        }catch (MappingException e) {
            List<ErrorMessage> messages = new ArrayList<>(e.getErrorMessages());
            throw new MappingException(messages);
        }
        return orderDetailDtoV2;
    }

    @Override
    public void deleteOrder(String orderId) throws NotFoundException, NumberFormatException {
        try {
            Long id = Long.parseLong(orderId);
            orderDetailRepoV2.findById(id).orElseThrow(() -> {
                throw new NotFoundException("Record Not Found");
            });
        }catch (NumberFormatException e){
            throw new NumberFormatException(e.getLocalizedMessage());
        }

        orderDetailRepoV2.deleteById(Long.parseLong(orderId));
    }
}
