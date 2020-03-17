<%@page import="bit.com.a.model.GetLikeParam"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>

<div>

<form name="frmForm" id="_frmForm" method="post" action="bbsupdate.do">
	<input type="hidden" name="seq" value="${bbs.seq}">
<table class="list_table" style="width:85%;">


<colgroup>
<col style="width:200px;" />
<col style="width:auto;" />
</colgroup>

<tbody>	
	<tr class="id">
		<th>아이디</th>
		<td style="text-align: left">${bbs.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td style="text-align: left">${bbs.title}</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td style="text-align: left">${bbs.wdate}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td style="text-align: left"><textarea rows="10" cols="50" 
		name='content' id="_content">${bbs.content}</textarea></td>
	</tr>
	<tr>
		<td colspan="2" style="height:50px; text-align:center;">
		<span>
			<c:if test="${bbs.id eq login.id}">
			<a href="#none" id="_btnUpdate" title="글수정하기"><img src="image/bupdate.png" alt="수정하기" /></a>
			<a href="#none" id="_btnDel" title="삭제하기"><img src="image/del.png" alt="삭제하기" /></a>
			</c:if>
			<a href="#none" id="_btnReply" title="답글달기"><img src="image/breply.png" alt="답글달기" /></a>
		</span>
		</td>
	</tr>
</tbody>
</table>

</form>

</div>

<br>

<%-- <a href="#" id="_like" onclick="_like(); return false;">
	좋아요 ${likeCount.like }
</a> --%>

<button type="button" id="_like1">
	좋아요 ${likeCount.likeCount }
</button>
<%
GetLikeParam getLike = (GetLikeParam)request.getAttribute("likeCount");
//System.out.println("좋아요 갯수는 " + getLike.getLikeCount());
%>
<button type="button" id="_like1">
	좋아요 <%=getLike.getLikeCount() %>
</button>

<script type="text/javascript">

$("#_btnUpdate").click(function() {	
	alert('글수정하기');		
	$("#_frmForm").attr({ "target":"_self", "action":"bbsupdate.do" }).submit();
});
$("#_btnReply").click(function() {	
	alert('답글달기');	
	$("#_frmForm").attr({ "target":"_self", "action":"answer.do" }).submit();
});
$("#_btnDel").click(function() {			
	$("#_frmForm").attr({ "target":"_self", "action":"bbsdelete.do" }).submit();
});



$("#_like1").click(function() {
//	alert("_like1 클릭");
	
	$.ajax({
		url: "like.do",
		type: "post",
		data: {
			seq: ${bbs.seq }
		},
		success: function(msg) {
			alert("성공");
			
			if(msg == "YES") {
				alert("이미 좋아요나 싫어요를 누르셨습니다.");
			}else {
				alert("좋아요가 반영되었습니다.");
				$("#_like").html("좋아요 " + ${likeCount.likeCount} + 1);
			}
		},
		error: function() {
			alert("에러");
		}
	})
})

</script>






