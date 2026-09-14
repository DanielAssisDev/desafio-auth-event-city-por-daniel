package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<CityDTO> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable).map(CityDTO::new);
    }

    @Transactional
    public CityDTO insert(CityDTO cityDTO){
        City city = new City();
        city.setName(cityDTO.getName());
        return new CityDTO(cityRepository.save(city));
    }
}
