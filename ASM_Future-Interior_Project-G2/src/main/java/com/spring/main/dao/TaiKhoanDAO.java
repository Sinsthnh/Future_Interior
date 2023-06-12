package com.spring.main.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.entity.TaiKhoan;


public interface TaiKhoanDAO extends JpaRepository<TaiKhoan, String>{
	TaiKhoan findByTenDangNhap(String tenDangNhap);
	
	@Query("SELECT t FROM TaiKhoan t WHERE t.tenDangNhap LIKE CONCAT('%', ?1, '%') and t.vaiTro = ?2")
	Page<TaiKhoan> findByTenDangNhap(String tenDangNhap,boolean vaiTro,Pageable pageable);
	
	
	
}