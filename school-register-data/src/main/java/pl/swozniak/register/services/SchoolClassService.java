package pl.swozniak.register.services;

import pl.swozniak.register.dtos.SchoolClassDTO;

import java.util.List;

public interface SchoolClassService{
    List<SchoolClassDTO> findAll();
    SchoolClassDTO findById(Long id);
}
