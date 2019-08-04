package pl.swozniak.register.mapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.swozniak.register.mapper.*;

@Configuration
public class MappersConfiguration {

    @Bean
    public StudentMapper getStudentMapper(){
        return StudentMapper.INSTANCE;
    }

    @Bean
    public SchoolClassMapper getSchoolClassMapper(){
        return SchoolClassMapper.INSTANCE;
    }

    @Bean
    public GradeMapper getGradeMapper(){
        return GradeMapper.INSTANCE;
    }

    @Bean
    public ParentMapper getParentMapper(){
        return ParentMapper.INSTANCE;
    }

    @Bean
    public TeacherMapper getTeacherMapper(){
        return TeacherMapper.INSTANCE;
    }

    @Bean
    public SubjectMapper getSubjectMapper(){
        return SubjectMapper.INSTANCE;
    }
}
