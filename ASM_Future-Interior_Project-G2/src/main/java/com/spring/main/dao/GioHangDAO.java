package com.spring.main.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.entity.GioHang;
import com.spring.main.entity.TaiKhoan;

public interface GioHangDAO extends JpaRepository<GioHang, Integer> {

	List<GioHang> findByTaiKhoanGH(TaiKhoan taiKhoanGH);

	@Query("Update GioHang gh set gh.soLuong = ?1 where gh.idGioHang = ?2")
	@Modifying
	public void updateSoLuong(Integer soLuong, Integer idGioHang);
}
