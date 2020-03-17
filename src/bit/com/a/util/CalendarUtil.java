package bit.com.a.util;

import java.util.List;

import bit.com.a.model.CalendarDto;

public class CalendarUtil {
	// 1 -> 01
	public static String two(int tt) {
		return (tt + "").length() > 1 ? (tt + "") : "0" + tt;
	}
	
	// 1 ->01
	public static String two(String msg) {
		return msg.trim().length() < 2 ? "0" + msg.trim():msg.trim();
	}
	
	// 20203 -> 202003
	public static String yyyymm(int year, int month) {
		return "" + year + two(month);
	}
	
	// 202032 -> 20200302
	public static String yyyymm(int year, int month, int day) {
		return "" + year + two(month) + two(day);
	}
	
	// 03(String) -> 3(int)
	public static int toOne(String tt) {
		if(tt.charAt(0) == '0') {
			return Integer.parseInt("" + tt.charAt(1));
		}else {
			return Integer.parseInt(tt);
		}
	}
	
	// 날짜를 클릭하면, 그날의 일정이 모두 보이게 하는 cal_list.jsp로 이동하는 함수
	public static String cal_list(int year, int month, int day) {
		String str = "";
		
		str += String.format("<a href='%s?year=%d&month=%d&day%d'>", "cal_list.do", year, month, day);
		str += String.format("%2d", day);		// <a href="">날짜</a>
		str += "</a>";
			// <a href='cal_list.jsp?year=2020&month=02&day=05'>_5</a>
		
		return str;
	}
	
	// 일정을 기입하기 위해서 pen이미지를 클릭하면, calwrite.jsp로 넘어가
	public static String showPen(int year, int month, int day) {
		String str ="";
		
		String image = "<img src='./image/pen2.png' width='18px', height='18px'>";
		str = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>", "calwrite.do", year, month, day, image);
		
		return str;
	}
	
	public static String makeTable(int year, int month, int day, List<CalendarDto> list) {
		String str = "";
		
		// 2020 2 5 -> 20200205
		String dates = (year + "") + two(month + "") + two(day + "");
		
		str += "<table>";
		str += "<col width='98'>";
		
		for(CalendarDto dto : list) {
			if(dto.getRdate().substring(0, 8).equals(dates)) {
				str += "<tr bgcolor='transparent'>";
				str += "<td style='border:hidden'";
				str += "<a href='caldetail.do?seq=" + dto.getSeq() + "'>";
				str += "<font style='font-size:6px; color: red'>";
				str += dot3(dto.getTitle());
				str += "</font>";
				str += "</a>";
				str += "</td>";
				str += "</tr>";
			}
		}
		
		str += "</table>";
		
		return str;
	}
	
	// 일정 제목이 너무 길때 ...으로 처리해
	public static String dot3(String msg) {
		String str = "";
		if(msg.length() >= 6) {
			str = msg.substring(0, 6);
			str += "...";
		}else {
			str = msg.trim();
		}
		
		return str;
	}
}
