package com.example.databaseservice.Repository;

import com.example.databaseservice.Model.Mau;
import com.example.databaseservice.Model.MoHinh;
import com.example.databaseservice.Model.MoHinhMau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MoHinhMauRepository extends JpaRepository<MoHinhMau, Long> {
    @Query("SELECT mhm.mohinh.id, mhm.mohinh.ten, mhm.mohinh.phienban, mhm.mohinh.dochinhxac, COUNT(mhm) " +
            "FROM MoHinhMau mhm " +
            "WHERE mhm.mau.ngaytao BETWEEN :ngayBatDau AND :ngayKetThuc " +
            "GROUP BY mhm.mohinh.id, mhm.mohinh.ten, mhm.mohinh.phienban, mhm.mohinh.dochinhxac")
    List<Object[]> countMauByMoHinh(@Param("ngayBatDau") Date ngayBatDau,
                                    @Param("ngayKetThuc") Date ngayKetThuc);

    @Query("SELECT m FROM MoHinhMau mhm JOIN mhm.mau m " +
            "WHERE mhm.mohinh = :moHinh " +
            "AND m.ngaytao BETWEEN :ngayBatDau AND :ngayKetThuc " +
            "ORDER BY m.id ASC")
    List<Mau> findDsMauByMoHinh(
            @Param("moHinh") MoHinh moHinh,
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc);


}
