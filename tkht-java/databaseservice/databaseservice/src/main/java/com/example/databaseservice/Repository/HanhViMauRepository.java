package com.example.databaseservice.Repository;

import com.example.databaseservice.Model.HanhVi;
import com.example.databaseservice.Model.HanhViMau;
import com.example.databaseservice.Model.Mau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HanhViMauRepository extends JpaRepository<HanhViMau, Long> {
    @Query("SELECT hvm.hanhVi.id, hvm.hanhVi.ten, hvm.hanhVi.mucdo, hvm.hanhVi.mota, COUNT(hvm.mau.id) " +
            "FROM HanhViMau hvm " +
            "WHERE hvm.mau.ngaytao BETWEEN :ngayBatDau AND :ngayKetThuc " +
            "GROUP BY hvm.hanhVi.id, hvm.hanhVi.ten, hvm.hanhVi.mucdo, hvm.hanhVi.mota")
    List<Object[]> countMauByHanhVi(@Param("ngayBatDau") Date ngayBatDau,
                                    @Param("ngayKetThuc") Date ngayKetThuc);

    @Query("SELECT m FROM HanhViMau hvm JOIN hvm.mau m " +
            "WHERE hvm.hanhVi = :hanhVi " +
            "AND m.ngaytao BETWEEN :ngayBatDau AND :ngayKetThuc " +
            "ORDER BY m.id ASC")
    List<Mau> findDsMauByHanhVi(
            @Param("hanhVi") HanhVi hanhVi,
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc);


}
