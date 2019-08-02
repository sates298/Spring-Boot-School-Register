package pl.swozniak.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.swozniak.register.mapper.GradeMapper;
import pl.swozniak.register.mapper.SchoolClassMapper;
import pl.swozniak.register.mapper.StudentMapper;

@Configuration
public class BeanConfiguration {

    @Bean
    public StudentMapper getStudentMapper(){
        return StudentMapper.INSTANCE;
    }

    @Bean
    public SchoolClassMapper getSchoolClassMapper(){
        return SchoolClassMapper.INSTANCE;
    }

    @Bean
    public GradeMapper getGRadeMapper(){
        return GradeMapper.INSTANCE;
    }
}
