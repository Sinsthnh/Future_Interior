package com.spring.main.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "don_hang")
public class DonHang implements Serializable{
	@Id
	@Column(name = "id_don_hang")
	String idDonHang ;
	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_tao")
	Date ngayTao = new Date();
	
	
	@Column(name = "phuong_thuc_thanh_toan")
	boolean phuongThucThanhToan ;
	@Column(name = "trang_thai_thanh_toan")
	boolean trangThaiThanhToan ;
	@Column(name = "trang_thai_don_hang")
	String trangThaiDonHang;
	
	@ManyToOne
	@JoinColumn(name = "tai_khoan_mua")
	TaiKhoan taiKhoanMuaHang;
	
	@OneToOne(mappedBy = "donHangCPDH")
	ChiPhiDonHang chiPhiDonHang;
	
	@OneToOne(mappedBy = "donHangTTGH")
	ThongTinGiaoHang thongTinGiaoHang;
	
	@OneToOne(mappedBy = "donHangTTTT")
	ThongTinThanhToan thongTinThanhToan;
	
	@OneToMany(mappedBy = "donHangDHCT")
	List<DonHangChiTiet> ListDHCT;
}
