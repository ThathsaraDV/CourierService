package lk.courierservice.reposiotry;

import lk.courierservice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {

}
