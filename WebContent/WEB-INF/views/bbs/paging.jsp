<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- paging 연산 -->
<%
/*		[1]~[10] -> [11]~[20] 
	처음					  끝페이지
	<<  < [1][2][3][4] > >>
*/

	int totalRecordCount;	// 전체 글의 수		23 -> 3
	int pageNumber;			// 현재 페이지		0 ~ 9 -> [1] ~ [10]	
	int pageCountPerScreen; // 스크린당 페이지수	[1]~[10], [1]~[5]	-> 10
	int recordCountPerPage;	// 페이지당 글의 수   [1] -> 10
	
	String st1 = request.getParameter("totalRecordCount");
	if(st1 == null) totalRecordCount = 0;
	else	totalRecordCount = Integer.parseInt(st1);

	String st2 = request.getParameter("pageNumber");
	if(st2 == null) pageNumber = 0;
	else	pageNumber = Integer.parseInt(st2);
	
	String st3 = request.getParameter("pageCountPerScreen");
	if(st3 == null) pageCountPerScreen = 0;
	else 	pageCountPerScreen = Integer.parseInt(st3);
	
	String st4 = request.getParameter("recordCountPerPage");
	if(st4 == null) recordCountPerPage = 0;
	else    recordCountPerPage = Integer.parseInt(st4);

	// 총 페이지의 수	[1][2][3] -> 3
	int totalPageCount = totalRecordCount / recordCountPerPage;
	//		2				23					10
	if((totalRecordCount % recordCountPerPage) != 0){
		totalPageCount++;
	}
	
	// 시작페이지	[1]	[11] [21]
	int screenStartPageIndex = ((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen;		
	//			0			 =			0			 10						10			
	
	// 끝 페이지     [10] [20] [30]
	int screenEndPageIndex = (((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen) + pageCountPerScreen;	
	//			10					0				10                       10 				10
	
	// 끝 페이지는 다시 계산	-> [1]~[10]	[1]~[3]
	if(screenEndPageIndex > totalPageCount){
		screenEndPageIndex = totalPageCount;
	}
	
	// 0 ~ 9 -> 1 ~ 10
	if((pageNumber + 1) % pageCountPerScreen == 0){
		screenStartPageIndex = (((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen) - pageCountPerScreen;
		screenEndPageIndex = pageNumber + 1;	// 0 -> 1 		9 -> 10
	}
	
	System.out.println("screenStartPageIndex:" + screenStartPageIndex);
	System.out.println("screenEndPageIndex:" + screenEndPageIndex);
%>

<!-- paging 출력 -->
<div style="float: left;width: 96%; text-align: center;">
	<!-- << -->
	<a href="#none" title="처음페이지" onclick="goPage('0')">
		<img alt="" src="./image/arrow_first.gif" style="width: 9px; height: 9px">
	</a>
	
	<!-- < [11] ~ [20] -> [1] ~ [10] -->
	<%
	if(screenStartPageIndex > 1){	// 총 글의 수가 100개 넘어야 보인다
		%>
		<a href="#none" title="이전페이지" onclick="goPage('<%=screenStartPageIndex-1 %>')">
			<img alt="" src="./image/arrow_back.gif" style="width: 9px; height: 9px">
		</a>
		
		<%
	}	
	%>

	<!-- [1] 2 [3] --> 
	<%
	for(int i = screenStartPageIndex; i < screenEndPageIndex; i++){
		if(i == pageNumber){	// 현재 페이지	2
			%>
			<span style="font-size: 9pt; color: #000000; font-weight: bold;">
				<%=i+1 %>			
			</span>			
			<%
		}else{					// 그 외의 페이지 [1] 2 [3][4]
			%>
			<a href="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)"
				style="font-size: 7.5pt; color: #000000; font-weight: normal;">
				[<%=i+1 %>]
			</a>	
			<%
		}		
	}		
	%>

	<!-- > 1 ~ 10 -> 11 ~ 20	-->
	<%
	if(screenEndPageIndex < totalPageCount){	// [11][12][13] 
		%>
		<a href="#none" title="다음페이지" onclick="goPage(<%=screenEndPageIndex %>)">
			<img alt="" src="./image/arrow_next.gif" style="width: 9px; height: 9px">			
		</a>	
		<%
	}
	int end_page = 0;
	if(totalPageCount > 0){
		end_page = totalPageCount - 1;
	}	
	%>		
	
	<!-- >> -->
	<a href="#none" title="마지막페이지" onclick="goPage(<%=end_page %>)">
		<img alt="" src="./image/arrow_end.gif" style="width: 9px;height: 9px;">
	</a>
	
</div>











