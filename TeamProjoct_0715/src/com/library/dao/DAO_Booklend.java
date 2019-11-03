package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.util.DBConn;
import com.library.vo.VO_Booklend;
import com.library.vo.VO_Booklend_reserve;

public class DAO_Booklend {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private DAO_Booklend() {
	}

	private static DAO_Booklend instance = new DAO_Booklend();

	public static DAO_Booklend getInstance() {
		return instance;
	}

	// get connection
	private Connection getConnection() throws Exception {
		conn = DBConn.getConnection();
		return conn;
	}

	// 책 대여 번호 메소드
	public String booklend_seq() {
		String sql = "SELECT SEQ_BOOKLEND.NEXTVAL seq FROM DUAL";
		String lend_seq = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lend_seq = Integer.toString(rs.getInt("seq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return lend_seq;
	}

	// 책 대여 메소드
	public int booklend_insert(VO_Booklend vo, String seq) {
		String sql = "INSERT INTO booklend(LENDNO,BOOKNO,LENDID,LENDDATE,LENDDUE) VALUES(?,?,?,SYSDATE,?)";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			pstmt.setString(2, vo.getBookno());
			pstmt.setString(3, vo.getLendid());
			pstmt.setString(4, vo.getLenddue());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// 책 대여 목록 메소드
	public List<VO_Booklend> booklend_list(String memberid) {
		String sql = "select l.bookno bookno, l.bookimg bookimg, l.bookname bookname, d.lendno lendno, d.lendid lendid, d.lenddate lenddate, d.lenddue lenddue, d.returndate returndate from booklist l, booklend d where l.bookno = d.bookno and d.lendid = '"
				+ memberid + "' and d.returndate is null order by lenddate desc";
		List<VO_Booklend> list = new ArrayList<VO_Booklend>();
		VO_Booklend vo = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new VO_Booklend();
				vo.setLendno(rs.getString("lendno"));
				vo.setBookno(rs.getString("bookno"));
				vo.setBookname(rs.getString("bookname"));
				vo.setBookimg(rs.getString("bookimg"));
				vo.setLendid(rs.getString("lendid"));
				vo.setLenddate(rs.getString("lenddate"));
				vo.setLenddue(rs.getString("lenddue"));
				vo.setReturndate(rs.getString("returndate"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	// 책 대여수
	public int booklend_count(String memberid) {
		String sql = "select COUNT(*) count from booklend where returndate is null and lendid = '" + memberid + "'";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// sms send 메소드
	public String[] sms_lend(String memberid) {
		String sql = "select * from (select  m.membername, l.bookname, b.lenddate, b.lenddue, m.phone "
				+ "from booklist l, booklend b, member m " + "where l.bookno = b.bookno and b.lendid = '" + memberid
				+ "' and m.memberid = b.lendid " + "order by b.lenddate desc) where rownum = 1";
		String[] sms = new String[5];
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sms[0] = rs.getString("membername");
				sms[1] = rs.getString("bookname");
				sms[2] = rs.getString("lenddate");
				sms[3] = rs.getString("lenddue");
				sms[4] = rs.getString("phone");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return sms;
	}

	// 반납 메소드
	public int booklend_return(String lendno) {
		String sql = "UPDATE BOOKLEND SET returndate=SYSDATE where lendno = '" + lendno + "'";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// 연장 메소드
	public String booklend_renewal(String lendno) {
		String sql = "select lenddue+7 lenddue from booklend where lendno = '" + lendno + "'";
		String lenddue = "";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lenddue = rs.getString("lenddue");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return lenddue.substring(0, 10);
	}

	// 도서 반납 메세지 메소드
	public String[] sms_return(String lendno) {
		String[] sms = new String[5];
		String sql = "select mem.membername,blst.bookname,bl.returndate, (case when bl.returndate-bl.lenddue < 0 then 0 else bl.returndate-bl.lenddue end) as overdue, mem.phone "
				+ "from booklend bl, member mem , booklist blst "
				+ "where bl.lendid=mem.memberid and blst.bookno = bl.bookno and  bl.lendno = '" + lendno + "'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sms[0] = rs.getString("membername");
				sms[1] = rs.getString("bookname");
				sms[2] = rs.getString("returndate");
				sms[3] = rs.getString("overdue");
				sms[4] = rs.getString("phone");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return sms;
	}

	// 도서 연장 메소드
	public int booklend_renewal_update(String lendno, String lenddue) {
		String sql = "update booklend set lenddue='" + lenddue + "' where lendno='" + lendno + "'";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// 연장 여부 가지고 오기
	public String booklend_renewal_search(String lendno) {
		String renewal = "";
		String sql = "SELECT RENEWAL FROM BOOKLEND WHERE lendno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lendno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				renewal = rs.getString("renewal");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return renewal;
	}

	// 연장 여부 수정
	public void booklend_renewal_update(String lendno) {
		String sql = "UPDATE BOOKLEND SET renewal=? where lendno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "1");
			pstmt.setString(2, lendno);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// 연장 여부 가지고 오기 (booklist_view)
	public String booklend_renewal_search_booklist_view(String lendno) {
		String renewal = "";
		String sql = "SELECT RENEWAL FROM BOOKLEND WHERE lendno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lendno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				renewal = rs.getString("renewal");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return renewal;
	}

	// 예약 번호 생성
	public String booklend_reservenum() {
		String sql = "SELECT seq_reserve.NEXTVAL SEQ FROM DUAL";
		String seq_reserve = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				seq_reserve = Integer.toString(rs.getInt("seq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return seq_reserve;
	}

	// 예약 메소드
	public int booklend_reserve(String seq, String bookno, String memberid) {
		String sql = "INSERT INTO BOOKRESERVE(RESERVENUM, BOOKNO, RESERVEID, RESERVEDATE) VALUES(?,?,?,SYSDATE)";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			pstmt.setString(2, bookno);
			pstmt.setString(3, memberid);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// 예약 번호 가지고 오기
	public List<VO_Booklend_reserve> booklend_reservenum(String bookno) {
		List<VO_Booklend_reserve> list = new ArrayList<VO_Booklend_reserve>();
		String sql = "select reservenum from BookReserve where bookno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				VO_Booklend_reserve vo = new VO_Booklend_reserve();
				vo.setReservenum(rs.getString("reservenum"));
				vo.setBookno(rs.getString("bookno"));
				vo.setReserveid(rs.getString("reserveid"));
				vo.setReservedate(rs.getString("reservedate"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	// 예약번호 확인
	public int booklend_reserve_check(String bookno) {
		String sql = "select reservenum from BookReserve where bookno = ?";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookno);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// 예약 리스트 가지고오기(출력용)
	public List<VO_Booklend_reserve> booklend_reserve_list(String memberid) {
		List<VO_Booklend_reserve> list = new ArrayList<VO_Booklend_reserve>();
		String sql = "select r.reservenum, l.bookno, l.bookimg, l.bookname, r.reserveid, r.reservedate, r.reservestaus\r\n"
				+ "from bookreserve r, booklist l\r\n" + "where r.bookno = l.bookno\r\n" + "and r.reserveid = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				VO_Booklend_reserve vo = new VO_Booklend_reserve();
				vo.setReservenum(rs.getString("reservenum"));
				vo.setBookno(rs.getString("bookno"));
				vo.setBookimg(rs.getString("bookimg"));
				vo.setBookname(rs.getString("bookname"));
				vo.setReserveid(rs.getString("reserveid"));
				vo.setReservedate(rs.getString("reservedate"));
				vo.setReservestaus(rs.getString("reservestaus"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	// 예약 리스트 가지고오기
	public List<VO_Booklend_reserve> booklend_reserve_list_check(String memberid) {
		List<VO_Booklend_reserve> list = new ArrayList<VO_Booklend_reserve>();
		String sql = "select *\r\n" + "from bookreserve r, booklist l\r\n" + "where r.bookno = l.bookno\r\n"
				+ "and r.reserveid = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				VO_Booklend_reserve vo = new VO_Booklend_reserve();
				vo.setBookimg(rs.getString("bookimg"));
				vo.setBookname(rs.getString("bookname"));
				vo.setReserveid(rs.getString("reserveid"));
				vo.setReservedate(rs.getString("reservedate"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	// 같은 책 예약 방지 메소드
	public String booklend_reserve_brake(String memberid, String bookno) {
		String sql = "select r.bookno\r\n" + "from bookreserve r, booklend l\r\n" + "where r.bookno = l.bookno\r\n"
				+ "and r.reserveid = ? and r.bookno = ? and returndate is null";
		String reserve_bookno = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberid);
			pstmt.setString(2, bookno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reserve_bookno = rs.getString("bookno");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return reserve_bookno;
	}

	// 예약시 대여 목록에 같은 책이 있는지 확인하는 메소드
	public String booklend_reserve_check_booklendlist(String bookno, String memberid) {
		String sql = "select bookno\r\n" + "from booklend\r\n"
				+ "where bookno = ? and lendid = ? and returndate is null";
		String check_bookno = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookno);
			pstmt.setString(2, memberid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check_bookno = rs.getString("bookno");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return check_bookno;
	}

	// 예약 삭제 메소드
	public int booklend_reserve_delete(String reservenum) {
		String sql = "DELETE FROM bookreserve where reservenum = ?";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservenum);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// 반납시 첫 번째 예약자 찾기 메소드
	public String booklend_reservenum_find(String bookno) {
		String sql = "SELECT reservenum FROM(SELECT * FROM bookreserve ORDER BY reservedate DESC) WHERE BOOKNO = ? AND ROWNUM <=1";
		String reservenum = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reservenum = rs.getString("reservenum");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return reservenum;
	}

	// sms 예약번호로 예약자 정보 찾기
	public String[] booklend_reserve_booker(String reservenum) {
		String sql = "select mem.membername, bl.bookname, mem.phone from\r\n" + 
				"(select bookno, reserveid from bookreserve where reservenum = ?) re,\r\n" + 
				"member mem, booklist bl\r\n" + 
				"where mem.memberid = re.reserveid  \r\n" + 
				"    and re.bookno = bl.bookno";
		String[] sms = new String[3];
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservenum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sms[0] = rs.getString("membername");
				sms[1] = rs.getString("bookname");
				sms[2] = rs.getString("phone");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return sms;
	}

	// 반납시 첫 번째 예약자 찾기 메소드
	public int booklend_reservenum_first_find(String bookno) {
		String sql = "SELECT reservenum FROM bookreserve WHERE BOOKNO = ?";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				row = 1;
			} else {
				row = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// 예약이 있는 책 반납시 예약테이블 예약상태 변경
	public void booklend_reservestaus(String reservenum) {
		String sql = "UPDATE bookreserve SET reservestaus=? WHERE reservenum = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "1");
			pstmt.setString(2, reservenum);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// 예약 상태 가져오기
	public VO_Booklend_reserve booklend_reservestaus_impormation(String memberid, String bookno) {
		String sql = "SELECT reservestaus\r\n" + "FROM bookreserve\r\n" + "WHERE reserveid = ? and bookno = ?";
		VO_Booklend_reserve vo = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberid);
			pstmt.setString(2, bookno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new VO_Booklend_reserve();
				vo.setReservestaus(rs.getString("reservestaus"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return vo;
	}

	// 예약자 예약번호 가져오기
	public String booklend_reservenum(String memberid, String bookno) {
		String sql = "select reservenum\r\n" + "from bookreserve\r\n" + "where reserveid = ? and bookno = ?";
		String reservenum = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberid);
			pstmt.setString(2, bookno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reservenum = rs.getString("reservenum");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return reservenum;
	}

	// 예약자 책을 반납시 예약리스트에서 삭제
	public void booklend_reserveid_delete(String reservenum) {
		String sql = "delete from bookreserve where reservenum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservenum);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// 삭제전 예약번호 확인
	public int booklend_delete_reservenum_search(String bookno, String memberid) {
		String sql = "select reservenum\r\n" + "from bookreserve\r\n" + "where bookno = ? and reserveid = ?";
		int row = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookno);
			pstmt.setString(2, memberid);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return row;
	}

	// 예약취소시 대출상태 변경
	public void booklend_delete_reserve_lend(String bookno) {
		String sql = "update booklist SET lend=? where bookno = '" + bookno + "'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// close all
	private void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}