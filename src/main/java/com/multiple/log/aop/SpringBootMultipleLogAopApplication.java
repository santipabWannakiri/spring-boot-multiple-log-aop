package com.multiple.log.aop;

import com.multiple.log.aop.model.Instructor;
import com.multiple.log.aop.model.InstructorDetail;
import com.multiple.log.aop.service.InstructorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootMultipleLogAopApplication {
    private InstructorService instructorService;

    public SpringBootMultipleLogAopApplication(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultipleLogAopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(String[] args) {
        return r -> {
            initialObject();
        };
    }

    private void initialObject() {
        InstructorDetail instructorDetailA = new InstructorDetail("https://youtube.com/somsak.s", "Cycling");
        Instructor instructorA = new Instructor("1309901022750", "SOMSAK", "SANDEE", "SOMSAK.S@gmail.com", instructorDetailA);

        InstructorDetail instructorDetailB = new InstructorDetail("https://youtube.com/somchai", "Singing");
        Instructor instructorB = new Instructor("1409988224308", "SOMCHAI", "RIT", "SOMCHAI.R@gmail.com", instructorDetailB);

        InstructorDetail instructorDetailC = new InstructorDetail("https://youtube.com/jirapon", "Tiktoker");
        Instructor instructorC = new Instructor("7509936482234", "JIRAPON", "KIM", "JIRAPON.K@gmail.com", instructorDetailC);

        instructorService.save(instructorA);
        instructorService.save(instructorB);
        instructorService.save(instructorC);
    }


}
