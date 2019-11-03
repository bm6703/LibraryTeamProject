package com.library.vo;

public class VO_Booklend_reserve {
	private String reservenum; // 예약 번호
	private String bookno; // 책번호
	private String reserveid; // 예약자 아이디
	private String reservedate; // 순번
	// 추가
	private String bookimg; // 책 이미지이름
	private String bookname; // 책 이름
	private String reservestaus; // 예약 상태

	public String getReservestaus() {
		return reservestaus;
	}

	public void setReservestaus(String reservestaus) {
		this.reservestaus = reservestaus;
	}

	public String getBookimg() {
		return bookimg;
	}

	public void setBookimg(String bookimg) {
		this.bookimg = bookimg;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getReservenum() {
		return reservenum;
	}

	public void setReservenum(String reservenum) {
		this.reservenum = reservenum;
	}

	public String getBookno() {
		return bookno;
	}

	public void setBookno(String bookno) {
		this.bookno = bookno;
	}

	public String getReserveid() {
		return reserveid;
	}

	public void setReserveid(String reserveid) {
		this.reserveid = reserveid;
	}

	public String getReservedate() {
		return reservedate;
	}

	public void setReservedate(String reservedate) {
		this.reservedate = reservedate;
	}

}
