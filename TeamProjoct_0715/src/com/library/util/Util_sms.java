package com.library.util;

import java.util.*;
import java.security.*;
import java.io.*;
import java.net.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util_sms {

	// 회원가입시 메시지 (메소드를 콜 하는 부분) 대출자 성명, 책이름, 반납일자, 연체일(없으면 0), 전화번호
	public void authenticSMS(String authenticNum, String tel, HttpServletRequest request,
			HttpServletResponse response) {
		String subject = "JSL도서관 본인인증";
		String contents = "";
		contents = "[JSL도서관] 본인확인 인증번호 " + "[" + authenticNum + "]를 입력해주세요:)";
		sendSMS(request, response, tel, contents, subject);
	}

	// 반납시 메시지 (메소드를 콜 하는 부분) 대출자 성명, 책이름, 반납일자, 연체일(없으면 0), 전화번호
	public void returnSMS(String name, String bookname, String returndate, String overdue, String tel,
			HttpServletRequest request, HttpServletResponse response) {
		String subject = "JSL도서관 반납안내";
		String contents = "";
		contents += name + "님 [" + bookname + "] 도서가 " + datefrm(returndate) + "에  반납되었습니다.";
		if (!overdue.equals("0"))
			contents += "\n총 " + overdue + "일 연체되었습니다.";
		sendSMS(request, response, tel, contents, subject);
	}

	// 대출시 메시지 (메소드를 콜 하는 부분)
	public void lendSMS(String name, String bookname, String lenddate, String lenddue, String tel,
			HttpServletRequest request, HttpServletResponse response) {
		String subject = "JSL도서관 대출안내";
		String contents = "";
		contents += name + "님 [" + bookname + "] 도서가 " + datefrm(lenddate) + "에  대출되었습니다. \n반납 기한은 " + datefrm(lenddue)
				+ " 입니다.";
		sendSMS(request, response, tel, contents, subject);
	}
	
	// 반납시 예약자 메시지 (메소드를 콜 하는 부분) 대출자 성명, 책이름, 반납일자, 전화번호
		public void reserveSMS(String name, String bookname, String tel,
				HttpServletRequest request, HttpServletResponse response) {
			String subject = "JSL도서관 예약안내";
			String contents = "";
			contents += name + "님  예약하신[" + bookname + "] 도서가  반납되어 대출이 가능합니다.";

			sendSMS(request, response, tel, contents, subject);
		}

	// 날짜 형식을 바꿔줌 2019-08-02 -> 2019년 8월 2일
	private String datefrm(String date) {
		String result = "";
		String subDate = "";
		if (date.length() > 10)
			subDate = date.substring(0, 11);
		if (date != null) {
			String[] strArr = subDate.split("-");
			if (strArr.length == 3) {
				result += strArr[0] + "년 " + strArr[1] + "월 " + strArr[2] + "일 ";
			} else {
				result = date;
			}
		}
		return result;
	}

	// sms 송신부분. 건들지마세요
	private void sendSMS(HttpServletRequest request, HttpServletResponse response, String tel, String contents,
			String inputSubject) {
		String charsetType = "UTF-8"; // EUC-KR 또는 UTF-8
		try {
			request.setCharacterEncoding(charsetType);
			response.setCharacterEncoding(charsetType);
			// String action = nullcheck(request.getParameter("action"), "");
			String sms_url = "";
			sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // SMS 전송요청 URL
			String user_id = base64Encode("shss2216"); // SMS아이디
			String secure = base64Encode("9d6c3ca2e87a1884209e5cda1b1f6d26 ");// 인증키
			String inputmsg = contents;
			String inputrphone = "010-5131-3577";
			String inputsphone1 = "010";
			String inputsphone2 = "5131";
			String inputsphone3 = "3577";
			String msg = base64Encode(nullcheck(inputmsg, ""));
			String rphone = base64Encode(nullcheck(inputrphone, ""));
			String sphone1 = base64Encode(nullcheck(inputsphone1, ""));
			String sphone2 = base64Encode(nullcheck(inputsphone2, ""));
			String sphone3 = base64Encode(nullcheck(inputsphone3, ""));
			String rdate = base64Encode("");
			String rtime = base64Encode("");
			// out.print(msg);
			String mode = base64Encode("1");
			String subject = base64Encode(inputSubject);
			/*
			 * if (nullcheck(request.getParameter("smsType"), "").equals("L")) { subject =
			 * base64Encode(nullcheck(request.getParameter("subject"), "")); }
			 */
			String testflag = base64Encode("");
			String destination = base64Encode(nullcheck(tel, ""));
			String repeatFlag = base64Encode("");
			String repeatNum = base64Encode("");
			String repeatTime = base64Encode("");
			String returnurl = "";
			String nointeractive = "";
			String smsType = base64Encode("L");
			String[] host_info = sms_url.split("/");
			String host = host_info[2];
			String path = "/" + host_info[3];
			int port = 80;
			// 데이터 맵핑 변수 정의
			String arrKey[] = new String[] { "user_id", "secure", "msg", "rphone", "sphone1", "sphone2", "sphone3",
					"rdate", "rtime", "mode", "testflag", "destination", "repeatFlag", "repeatNum", "repeatTime",
					"smsType", "subject" };
			String valKey[] = new String[arrKey.length];
			valKey[0] = user_id;
			valKey[1] = secure;
			valKey[2] = msg;
			valKey[3] = rphone;
			valKey[4] = sphone1;
			valKey[5] = sphone2;
			valKey[6] = sphone3;
			valKey[7] = rdate;
			valKey[8] = rtime;
			valKey[9] = mode;
			valKey[10] = testflag;
			valKey[11] = destination;
			valKey[12] = repeatFlag;
			valKey[13] = repeatNum;
			valKey[14] = repeatTime;
			valKey[15] = smsType;
			valKey[16] = subject;
			String boundary = "";
			Random rnd = new Random();
			String rndKey = Integer.toString(rnd.nextInt(32000));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytData = rndKey.getBytes();
			md.update(bytData);
			byte[] digest = md.digest();
			for (int i = 0; i < digest.length; i++) {
				boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
			}
			boundary = "---------------------" + boundary.substring(0, 11);
			// 본문 생성
			String data = "";
			String index = "";
			String value = "";
			for (int i = 0; i < arrKey.length; i++) {
				index = arrKey[i];
				value = valKey[i];
				data += "--" + boundary + "\r\n";
				data += "Content-Disposition: form-data; name=\"" + index + "\"\r\n";
				data += "\r\n" + value + "\r\n";
				data += "--" + boundary + "\r\n";
			}
			// out.println(data);
			InetAddress addr = InetAddress.getByName(host);
			Socket socket = new Socket(host, port);
			// 헤더 전송
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
			wr.write("POST " + path + " HTTP/1.0\r\n");
			wr.write("Content-Length: " + data.length() + "\r\n");
			wr.write("Content-type: multipart/form-data, boundary=" + boundary + "\r\n");
			wr.write("\r\n");
			// 데이터 전송
			wr.write(data);
			wr.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String nullcheck(String str, String Defaultvalue) throws Exception {
		String ReturnDefault = "";
		if (str == null) {
			ReturnDefault = Defaultvalue;
		} else if (str == "") {
			ReturnDefault = Defaultvalue;
		} else {
			ReturnDefault = str;
		}
		return ReturnDefault;
	}

	/**
	 * BASE64 Encoder
	 * 
	 * @param str
	 * @return
	 */
	private static String base64Encode(String str) throws java.io.IOException {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		byte[] strByte = str.getBytes();
		String result = encoder.encode(strByte);
		return result;
	}

	/**
	 * BASE64 Decoder
	 * 
	 * @param str
	 * @return
	 */
	private static String base64Decode(String str) throws java.io.IOException {
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		byte[] strByte = decoder.decodeBuffer(str);
		String result = new String(strByte);
		return result;
	}
}