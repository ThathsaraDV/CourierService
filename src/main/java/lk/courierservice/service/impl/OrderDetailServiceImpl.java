package lk.courierservice.service.impl;

import lk.courierservice.dto.EmailDto;
import lk.courierservice.dto.OrderDetailDto;
import lk.courierservice.entity.CourierCompany;
import lk.courierservice.entity.OrderDetail;
import lk.courierservice.exception.NotFoundException;
import lk.courierservice.exception.ValidateException;
import lk.courierservice.reposiotry.CourierDetailRepo;
import lk.courierservice.reposiotry.OrderDetailRepo;
import lk.courierservice.service.EmailProducerService;
import lk.courierservice.service.OrderDetailService;
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
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private CourierDetailRepo courierDetailRepo;

    @Autowired
    private EmailProducerService emailProducerService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ArrayList<OrderDetailDto> getAllOrders() throws NotFoundException {
        List<OrderDetail> allOrderDetails = orderDetailRepo.findAll();
        if (allOrderDetails.isEmpty()){
            throw new NotFoundException("Records not found");
        }
        return mapper.map(allOrderDetails,new TypeToken<ArrayList<OrderDetailDto>>(){}.getType());
    }

    @Override
    public OrderDetailDto addOrder(OrderDetailDto orderDetailDto) throws MappingException, ValidateException, NotFoundException, JDBCException {
        if (orderDetailDto.getCourierCompany().getCourierCode().isEmpty()){
            throw new ValidateException("Courier Code cannot be empty");
        }
        CourierCompany courierCompany =  courierDetailRepo.findById(orderDetailDto.getCourierCompany().getCourierCode()).orElseThrow(() -> {
            throw new NotFoundException("No Courier Company by this Code");
        });
        try {
            orderDetailRepo.save(mapper.map(orderDetailDto, OrderDetail.class));
            String subject = "Order For Ship";
            String body = "Please pickup this order from "+orderDetailDto.getSenderName()+" at "+orderDetailDto.getSenderAddress()+" that weigh "+orderDetailDto.getParcelWeight()+" and deliver to "+orderDetailDto.getReceiverName()+" at "+orderDetailDto.getReceiverAddress() ;
            EmailDto emailDto = new EmailDto(courierCompany.getEmail(),subject,body);
            emailProducerService.sendEmail(emailDto);
        }catch (MappingException e) {
            List<ErrorMessage> messages = new ArrayList<>(e.getErrorMessages());
            throw new MappingException(messages);
        }catch (JDBCException e){
            throw new JDBCException(e.getMessage(),e.getSQLException());
        }
        return orderDetailDto;
    }

    @Override
    public OrderDetailDto updateOrder(OrderDetailDto orderDetailDto) throws NotFoundException, ValidateException, MappingException {
        if (orderDetailDto.getCourierCompany().getCourierCode().isEmpty()){
            throw new ValidateException("Courier Code cannot be empty");
        }
        courierDetailRepo.findById(orderDetailDto.getCourierCompany().getCourierCode()).orElseThrow(() -> {
            throw new NotFoundException("No Courier Company by this Code");
        });
        orderDetailRepo.findById(orderDetailDto.getOrderId()).orElseThrow(() -> {
            throw new NotFoundException("Record Not Found");
        });
        try {
            orderDetailRepo.save(mapper.map(orderDetailDto,OrderDetail.class));
        }catch (MappingException e) {
            List<ErrorMessage> messages = new ArrayList<>(e.getErrorMessages());
            throw new MappingException(messages);
        }
        return orderDetailDto;
    }

    @Override
    public void deleteOrder(String orderId) throws NotFoundException, NumberFormatException {
        try {
            Long id = Long.parseLong(orderId);
            orderDetailRepo.findById(id).orElseThrow(() -> {
                throw new NotFoundException("Record Not Found");
            });
        }catch (NumberFormatException e){
            throw new NumberFormatException(e.getLocalizedMessage());
        }

        orderDetailRepo.deleteById(Long.parseLong(orderId));
    }
}
