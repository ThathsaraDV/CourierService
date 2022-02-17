package lk.courierservice.reposiotry;

import lk.courierservice.entity.OrderDetailV2;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/13/2022
 **/
public interface OrderDetailRepoV2 extends JpaRepository<OrderDetailV2, Long> {

}
