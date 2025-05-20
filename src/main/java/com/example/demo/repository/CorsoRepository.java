package com.example.demo.repository;

import com.example.demo.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface CorsoRepository extends JpaRepository<Corso, Long> {

    Set<Corso> findByDocenteId(Long docenteId);


}
