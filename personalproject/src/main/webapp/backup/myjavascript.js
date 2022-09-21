$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	});
//join.jsp

function check_join() {
	if('${sessionScope.userid}' != ""){
		if(confirm("로그아웃 후 가입 가능합니다.로그아웃하시겠습니까?")){
			location.href="${path}/member_servlet/logout.do";
			return false;
		}else{
			return false;
		}
	}
	//아이디
	var userid=$("#userid").val();
	if(userid==""){
		alert("아이디를 입력하세요.");
		$("#userid").focus();
		return;
	}
	var exp1 = /^[A-Za-z0-9]{4,10}$/; 
	if(!exp1.test(userid)){
		alert("아이디는 영문자, 숫자를 사용해서 4~10자리로 입력하세요.");
		$("#userid").focus();
		return;
	}
	//비밀번호
	var passwd=$("#passwd").val(); 
	if(passwd==""){
		alert("비밀번호를 입력하세요.");
		$("#passwd").focus();
		return;
	}
	var exp2 = /(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*\d){8,12}/; 
	if(!exp2.test(passwd)){
		alert("비밀번호는 영문대소문자,숫자,특수기호(!@#$%^*+=-)를 모두 사용해서 8~12자리로 입력하세요");
		$("#passwd").focus();
		return;
	}
	var pwcheck=$("#pwcheck").val();
	if(passwd!=pwcheck){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pwcheck").focus();
		return;
	}
	//이름
	var name=$("#name").val();
	if(name==""){
		alert("이름을 입력하세요.");
		$("#name").focus();
		return;
	}
	var exp3 = /^[가-힣|a-z|A-Z]+$/;
	if(!exp3.test(name)){
		alert("이름은 한글 또는 영문만 가능합니다.");
		$("#name").focus();
		return;
	}
	//이메일
	var email=$("#email").val();
	if(email==""){
		alert("이메일을 입력해주세요.");
		$("#email").focus();
		return;
	}
	var exp4 = /^[a-z0-9]{2,}@[a-z0-9]{2,}\.[a-z]{2,}$/;
	if(!exp4.test(email)){
		alert("이메일 형식이 잘못되었습니다. ex) abc@abc.com");
		$("#email").focus();
		return;
	}
	//전화번호
	var hp = $("#hp").val();
	if(hp==""){
		alert("전화번호를 입력해주세요.");
		$("#hp").focus();
		return;
	}
	var exp5 = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$/;
	if(!exp5.test(hp)){  
		alert("전화번호 형식이 잘못되었습니다. ex) 010-###(#)-####");
		$("#hp").focus();
		return;
	}
	//필수 확인 사항(checkbox)
	var check;
	for(i=1; i<=3; i++){
		check=document.querySelector('#check'+i).checked;
		console.log(check);
		if(!check){
			alert("모든 필수 확인 사항에 체크해주세요.");
			return;
		}
	} 
	return true;	
}

function join(){
	document.form2.action="${path}/member_servlet/join.do";
	document.form2.submit();  
}
//login.jsp
function loginCheck(){
	var userid=$("#userid").val();
	var passwd=$("#passwd").val();
	if(userid==""){
		alert("아이디를 입력해주세요.");
		$("#userid").focus();
		return false;
	}
	if(passwd==""){
		alert("비밀번호를 입력해주세요.");
		$("#passwd").focus();
		return false;
	}
	return true;
}

//btnLogout
function logoutConfirm(path){
	var check=confirm("로그아웃하시겠습니까?");
	if(check){
		location.href=path+"/member_servlet/logout.do";
	}
}

//admin_result.jsp
function adminDelete(){
	var message='선택한 회원이 강제 탈퇴됩니다. 진행하시겠습니까?';
	if(confirm(message)){
		document.form4.action="${path}/member_servlet/adminDelete.do";
		document.form4.submit();
	}
}
function adminList(){
	document.form4.action="${path}/member_servlet/adminList.do";
	document.form4.submit();
}
function list(curPage){
	var param="curPage="+curPage;
	document.form4.action="${path}/member_servlet/adminList.do?"+param;
	document.form4.submit();
}
function memberDelete(){
	var message='정말 회원 탈퇴하시겠습니까?';
	if(confirm(message)){
		document.form3.action="${path}/member_servlet/memberDelete.do";
		document.form3.submit();
	}
}

function memberUpdate(){
	var message='수정하시겠습니까?';
	if(confirm(message)){
		if(check_update()){
			document.form3.action="${path}/member_servlet/memberUpdate.do";
			document.form3.submit();
		}
	}
}

function check_update() {
	var passwd=$("#passwd").val(); 
	if(passwd==""){
		alert("비밀번호를 입력하세요.");
		$("#passwd").focus();
		return;
	}
	var exp2 = /(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*\d){8,12}/; 
	if(!exp2.test(passwd)){
		alert("비밀번호는 영문대소문자,숫자,특수기호(!@#$%^*+=-)를 모두 사용해서 8~12자리로 입력하세요");
		$("#passwd").focus();
		return;
	}
	var pwcheck=$("#pwcheck").val();
	if(passwd!=pwcheck){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pwcheck").focus();
		return;
	}
	var name=$("#name").val();
	if(name==""){
		alert("이름을 입력하세요.");
		$("#name").focus();
		return;
	}
	var exp3 = /^[가-힣|a-z|A-Z]+$/;
	if(!exp3.test(name)){
		alert("이름은 한글 또는 영문만 가능합니다.");
		$("#name").focus();
		return;
	}
	var email=$("#email").val();
	if(email==""){
		alert("이메일을 입력해주세요.");
		$("#email").focus();
		return;
	}
	var exp4 = /^[a-z0-9]{2,}@[a-z0-9]{2,}\.[a-z]{2,}$/;
	if(!exp4.test(email)){
		alert("이메일 형식이 잘못되었습니다. ex) abc@abc.com");
		$("#email").focus();
		return;
	}
	var hp = $("#hp").val();
	if(hp==""){
		alert("전화번호를 입력해주세요.");
		$("#hp").focus();
		return;
	}
	var exp5 = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$/;
	if(!exp5.test(hp)){  
		alert("전화번호 형식이 잘못되었습니다. ex) 010-###(#)-####");
		$("#hp").focus();
		return;
	}
	return true;	
}

function cafeList(curPage){
	var param="curPage="+curPage;
	document.form5.action="${path}/cafe_servlet/cafeList.do?"+param;
	document.form5.submit();
}

//cafeDetail.jsp
function updateStars(cafe_num){
	alert('감사합니다.');
	document.form6.action="${path}/cafe_servlet/stars.do?cafe_num="+cafe_num;
	document.form6.submit();
}

//reviewMain.jsp
function boardList(curPage){
	var param="curPage="+curPage;
	document.form7.action="${path}/reviewboard_servlet/boardList.do?"+param;
	document.form7.submit();
}

function reviewDetail(num){
	var param="num="+num;
	document.form7.action="${path}/reviewboard_servlet/reviewDetail.do?"+param;
	document.form7.submit();
}

//reviewPost.jsp
function reviewPost(){
	if(postCheck()){
	document.form8.action="${path}/reviewboard_servlet/insertReview.do"; 
	document.form8.submit();
	}
}
function postCheck(){
	var title=$("#title").val();
	var content=$("#content").val();
	if(title==""){
		alert('제목을 입력하세요');
		$("#title").focus();
		history.back();
		return;
	}
	if(content==""){
		alert('내용을 입력하세요');
		$("#content").focus();
		history.back();
		return;
	}
	return true;
}

//reviewDetail.jsp
function download(num){
	var param="num="+num;
	document.form9.action="${path}/reviewboard_servlet/download.do?"+param;
	document.form9.submit();
}



function insertComment(){
	document.form9.action="${path}/comment_servlet/insertComment.do";
	document.form9.submit();
}

function deleteComment(comment_num){
	var param="comment_num="+comment_num;
	document.form9.action="${path}/comment_servlet/deleteComment.do?"+param;
	document.form9.submit();
	}
function replyComment(comment_num){
	var param="comment_num="+comment_num;
	document.form9.action="${path}/comment_servlet/replyComment.do?"+param;
	document.form9.submit();
	}
//reviewUpdate.jsp
function reviewUpdate(){
	document.form1.action="${path}/reviewboard_servlet/reviewUpdate.do";
	document.form1.submit();
}

//reviewReply.jsp
function replyPost(){
	document.form1.action="${path}/reviewboard_servlet/replyReview.do";
	document.form1.submit();
}


