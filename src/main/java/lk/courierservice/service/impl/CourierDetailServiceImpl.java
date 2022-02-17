package lk.courierservice.service.impl;

import lk.courierservice.entity.CourierCompany;
import lk.courierservice.exception.NotFoundException;
import lk.courierservice.reposiotry.CourierDetailRepo;
import lk.courierservice.service.CourierDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Thathsara Dananjaya <thathsaradananjaya@gmail.com>
 * @since 2/14/2022
 **/
@Service
@Transactional
public class CourierDetailServiceImpl implements CourierDetailService {

    @Autowired
    private CourierDetailRepo courierDetailRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CourierCompany getCourierCompanyByCode(String code) throws NotFoundException {
        Optional<CourierCompany> company = courierDetailRepo.findById(code);
        if (company.isEmpty()){
            throw new NotFoundException("Records not found");
        }
        return mapper.map(company, CourierCompany.class);
    }
}
