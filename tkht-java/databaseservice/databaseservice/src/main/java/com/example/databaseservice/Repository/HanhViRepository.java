package com.example.databaseservice.Repository;


import com.example.databaseservice.Model.HanhVi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HanhViRepository extends JpaRepository<HanhVi, Long> {
    Optional<HanhVi> findByTen(String ten);
}
