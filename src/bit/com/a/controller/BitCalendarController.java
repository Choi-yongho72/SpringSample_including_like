package bit.com.a.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.model.CalParam;
import bit.com.a.model.CalendarDto;
import bit.com.a.model.MemberDto;
import bit.com.a.service.BitCalendarService;
import bit.com.a.util.CalendarUtil;

@Controller
public class BitCalendarController {
	@Autowired
	BitCalendarService service;
	
	@RequestMapping(value = "calendarlist.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String calendarlist(Model model, HttpSession session, CalParam jcal) {
		model.addAttribute("doc_title", "일정");
		
		jcal.calculate();
		
		// id취득
		String id = ((MemberDto)session.getAttribute("login")).getId();
		
		String yyyymm = CalendarUtil.yyyymm(jcal.getYear(), jcal.getMonth());
		
		CalendarDto fcal = new CalendarDto();
		fcal.setId(id);
		fcal.setRdate(yyyymm);
		
		List<CalendarDto> list = service.getCalendarList(fcal);
		
		model.addAttribute("flist", list);
		model.addAttribute("jcal", jcal);
		
		return "calendar.tiles";
	}
}
