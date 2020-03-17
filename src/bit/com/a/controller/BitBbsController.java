package bit.com.a.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.model.GetLikeParam;
import bit.com.a.model.LikeParam;
import bit.com.a.model.MemberDto;
import bit.com.a.service.BbsService;

@Controller
public class BitBbsController {

	@Autowired
	BbsService bbsService;
	
	@RequestMapping(value = "bbslist.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String bbslist(Model model, BbsParam param) {
		model.addAttribute("doc_title", "글목록");
		
		// paging 처리
		int sn = param.getPageNumber();	// 0 1 2	현재 페이지
		int start = sn * param.getRecordCountPerPage() + 1; // 1, 11, 21
		int end = (sn + 1) * param.getRecordCountPerPage();	// 10, 20, 30
		
		param.setStart(start);
		param.setEnd(end);
				
		List<BbsDto> bbslist = bbsService.getBbsList(param);
		
		// 글의 총수
		int totalRecordCount = bbsService.getBbsCount(param);
		
		model.addAttribute("bbslist", bbslist);
		
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		return "bbslist.tiles";
	}	
	
	@RequestMapping(value = "bbswrite.do", method = {RequestMethod.GET,	RequestMethod.POST})
	public String bbswrite(Model model) {		
		model.addAttribute("doc_title", "글쓰기");
		
		return "bbswrite.tiles";
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.POST)
	public String bbswriteAf(BbsDto bbs, Model model) throws Exception {
		if(bbs.getContent().equals("") || bbs.getTitle().equals("")){
			return "bbswrite.tiles";
		}
		bbsService.writeBbs(bbs);
		return "redirect:/bbslist.do";
	}
	
	@RequestMapping(value = "bbsdetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String bbsdetail(int seq, Model model) {
		model.addAttribute("doc_title", "상세글 보기");
		
		GetLikeParam getLike = bbsService.getLike(seq);
	//	System.out.println("getLike는 " + getLike.toString());
		
		BbsDto bbs=bbsService.getBbs(seq);
		model.addAttribute("bbs", bbs);
		
		model.addAttribute("likeCount", getLike);
	//	model.addAttribute("dislikeCount", getLike.getDislike());
		
		return "bbsdetail.tiles";
	}
	
	@RequestMapping(value = "bbsupdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String bbsupdate(int seq, Model model){
		
		BbsDto bbs=bbsService.getBbs(seq);
		model.addAttribute("bbs", bbs);
		return "bbsupdate.tiles";
	}
	
	@RequestMapping(value = "bbsupdateAf.do", 
			method = RequestMethod.POST)
	public String bbsupdateAf(BbsDto bbs,Model model) throws Exception {
		bbsService.updateBbs(bbs);
		return "redirect:/bbslist.do";
	}
	
	@RequestMapping(value = "bbsdelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteBbs(int seq, Model model) {
		bbsService.deleteBbs(seq);
		return "redirect:/bbslist.do";
	}
	
	@ResponseBody
	@RequestMapping(value="like.do", method=RequestMethod.POST)
	public String like(int seq, HttpServletRequest req, HttpSession session) {
	//	String id = (String)req.getSession().getAttribute("login");
		MemberDto dto = (MemberDto)session.getAttribute("login");
		String id = dto.getId();
		
	//	System.out.println("세션 아이디는 " + id);
		
		LikeParam Lparam = new LikeParam(seq, id);
		
		int count = bbsService.like(Lparam);
		// int count = bbsService.like(new LikeParam(seq, id));
		
		String msg = "";
		if(count > 0) {
			msg = "YES";
		}else {
			msg = "NO";
		}
		return msg;
	}
}












