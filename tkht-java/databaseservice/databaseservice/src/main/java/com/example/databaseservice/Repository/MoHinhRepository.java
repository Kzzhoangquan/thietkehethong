package com.example.databaseservice.Repository;


import com.example.databaseservice.Model.MoHinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoHinhRepository extends JpaRepository<MoHinh, Long> {
    Optional<MoHinh> findByTen(String ten);
}
