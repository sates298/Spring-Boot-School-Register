package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.model.Lesson;
import pl.swozniak.register.repositories.LessonRepository;
import pl.swozniak.register.services.LessonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonServiceImplTest {

    public static final long ID = 1L;

    @Mock
    LessonRepository lessonRepository;

    @InjectMocks
    LessonServiceImpl service;

    private Lesson returnLesson;

    @BeforeEach
    void setUp() {
        returnLesson = Lesson.builder().id(ID).build();
    }

    @Test
    void findAll() {
        List<Lesson> returnLessonsList = new ArrayList<>();
        returnLessonsList.add(returnLesson);
        returnLessonsList.add(Lesson.builder().id(ID + 1).build());
        returnLessonsList.add(Lesson.builder().id(ID + 2).build());


        when(lessonRepository.findAll()).thenReturn(returnLessonsList);

        List<Lesson> lessons = service.findAll();

        assertNotNull(lessons);
        assertEquals(3, lessons.size());
    }

    @Test
    void findById() {
        when(lessonRepository.findById(anyLong())).thenReturn(Optional.of(returnLesson));

        Lesson lesson = service.findById(ID);
        assertNotNull(lesson);
    }

    @Test
    void findByIdNotFound(){
        when(lessonRepository.findById(anyLong())).thenReturn(Optional.empty());

        Lesson lesson = service.findById(ID);
        assertNull(lesson);
    }

    @Test
    void save() {
        Lesson lessonToSave = Lesson.builder().id(ID).build();

        when(lessonRepository.save(any())).thenReturn(returnLesson);

        Lesson saved = service.save(lessonToSave);

        assertNotNull(saved);
        verify(lessonRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnLesson);

        verify(lessonRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(lessonRepository, times(1)).deleteById(anyLong());
    }
}