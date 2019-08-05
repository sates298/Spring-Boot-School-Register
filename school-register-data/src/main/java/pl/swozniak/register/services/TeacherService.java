package pl.swozniak.register.services;

import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.model.Teacher;

public interface TeacherService extends CrudService<Teacher, TeacherDTO, Long> {
}