package com.spring.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.entity.PhanNhomLoai;


public interface PhanNhomLoaiDAO extends JpaRepository<PhanNhomLoai, Integer> {
	PhanNhomLoai findByIdPhanNhomLoai(int idPhanNhomLoai);
	
}
