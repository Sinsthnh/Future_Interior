package com.spring.main.entity;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "thong_tin_tai_khoan")
public class ThongTinTaiKhoan implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_thong_tin_tai_khoan")
	int idThongTinTaiKhoan ;
	@Column(name = "ho_ten")
	String hoTen ;
	@Column(name = "gioi_tinh")
	boolean gioiTinh ;
	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_sinh")
	Date ngaySinh = new Date() ;
	@Column(name = "so_dien_thoai")
	String soDienThoai ;
	@Column(name = "anh_dai_dien")
	String anhDaiDien ;
	
	
//	Quan hệ 1 - 1	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ten_dang_nhap")
	TaiKhoan taiKhoanTTTK;
}
