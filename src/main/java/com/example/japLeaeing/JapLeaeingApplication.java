package com.example.japLeaeing;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@SpringBootApplication
public class JapLeaeingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JapLeaeingApplication.class, args);
    }

    @Bean
    public JPAQueryFactory initFactory(EntityManager entityManager) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        return jpaQueryFactory;
    }

}
