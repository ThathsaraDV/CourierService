package lk.courierservice.controller;

import lk.courierservice.dto.OrderDetailDtoV2;
import lk.courierservice.exception.NotFoundException;
import lk.courierservice.exception.ValidateException;
import lk.courierservice.service.OrderDetailServiceV2;
import org.hibernate.JDBCException;
import org.modelmapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/

@RestController
@RequestMapping("/api/v2/order")
@CrossOrigin
public class OrderDetailApiV2 {

    @Autowired
    private OrderDetailServiceV2 orderDetailServiceV2;

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderDetailServiceV2.getAllOrders());
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("System Error");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addOrder(@RequestBody OrderDetailDtoV2 orderDetailDtoV2){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailServiceV2.addOrder(orderDetailDtoV2));
        }catch (ValidateException | MappingException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (JDBCException e){
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("System Error");
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDetailDtoV2 orderDetailDtoV2){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderDetailServiceV2.updateOrder(orderDetailDtoV2));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (ValidateException | MappingException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("System Error");
        }
    }

    @DeleteMapping(params = {"orderId"})
    public ResponseEntity<?> deleteOrder(@RequestParam String orderId){
        try {
            orderDetailServiceV2.deleteOrder(orderId);
            return ResponseEntity.status(HttpStatus.OK).body(orderId);
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("System Error");
        }
    }

}
