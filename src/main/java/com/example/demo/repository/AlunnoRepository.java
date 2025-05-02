package com.example.demo.repository;

import com.example.demo.entity.Alunno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunnoRepository extends JpaRepository<Alunno, Long> {
}
