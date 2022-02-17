package lk.courierservice.reposiotry;

import lk.courierservice.entity.CourierCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/14/2022
 **/
public interface CourierDetailRepo extends JpaRepository<CourierCompany, String> {

}
