package pl.swozniak.register.services;

import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Parent;

import java.util.List;

public interface ParentService extends CrudService<Parent, ParentDTO, Long> {
}
