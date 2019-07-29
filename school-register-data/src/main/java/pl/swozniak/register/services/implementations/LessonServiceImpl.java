package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.model.Lesson;
import pl.swozniak.register.repositories.LessonRepository;
import pl.swozniak.register.services.LessonService;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public Lesson save(Lesson object) {
        return lessonRepository.save(object);
    }

    @Override
    public void delete(Lesson object) {
        lessonRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }
}
