package lk.courierservice.controller;

import lk.courierservice.dto.OrderDetailDto;
import lk.courierservice.exception.NotFoundException;
import lk.courierservice.exception.ValidateException;
import lk.courierservice.service.OrderDetailService;
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
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderDetailApi {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderDetailService.getAllOrders());
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("System Error");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addOrder(@RequestBody OrderDetailDto orderDetailDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.addOrder(orderDetailDto));
        }catch (ValidateException | MappingException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (JDBCException e){
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("System Error");
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDetailDto orderDetailDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderDetailService.updateOrder(orderDetailDto));
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
            orderDetailService.deleteOrder(orderId);
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
