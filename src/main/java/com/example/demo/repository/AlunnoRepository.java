package com.example.demo.repository;

import com.example.demo.entity.Alunno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AlunnoRepository extends JpaRepository<Alunno, Long> {

    @Query(value = "SELECT a FROM Alunno a WHERE a.citta = :citta ORDER BY a.id")
    List<Alunno> findByCitta(@Param("citta") String citta);

    @NativeQuery(value = "SELECT * FROM alunni WHERE voto >= 6.0 ORDER BY id;")
    List<Alunno> findAllPromossi();

}

