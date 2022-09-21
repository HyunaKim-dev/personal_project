package member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Pager;
import member.dao.MemberDAO;
import member.dto.MemberDTO;
import utils.CookieManager1;
import utils.JSFunction;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String uri=request.getRequestURI();
		String context=request.getContextPath();
		MemberDAO dao=new MemberDAO();

		if(uri.indexOf("login.do") != -1) {
			String message=""; 
			String userid=request.getParameter("userid");
			String passwd=request.getParameter("passwd");
			String save_id=request.getParameter("save_id");
			System.out.println(save_id);
			String name=dao.loginCheck(userid, passwd);
			if(name!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("userid", userid);
				message=name+"님 환영합니다";
				if(save_id != null && save_id.equals("on"))
					CookieManager1.makeCookie(response, "save_id", userid, 86400*7);
				else
					CookieManager1.deleteCookie(response, "save_id");
				if(dao.adminIdCheck(userid))
					session.setAttribute("admin", "admin");
				String page=context+"/index.jsp";
				JSFunction.alertLocation(response, message, page);
			}else{
				message="아이디 또는 비밀번호를 확인해주세요";
				JSFunction.alertBack(response, message);
			}
		}else if(uri.indexOf("logout.do") != -1){
			HttpSession session=request.getSession();
			session.invalidate();
			String message="로그아웃되었습니다.";
			String page=context+"/index.jsp";
			JSFunction.alertLocation(response, message, page);
		}else if(uri.indexOf("join.do") != -1) {
			String userid=request.getParameter("userid");
			String result=dao.joinIdCheck(userid);
			if(result!=null) {
				String message=userid+"는 사용 중인 아이디입니다.";
				JSFunction.alertBack(response, message);
			}else {
				String passwd=request.getParameter("passwd");
				String name=request.getParameter("name");
				String email=request.getParameter("email");
				String hp=request.getParameter("hp");
				String address=request.getParameter("address");
				String birthday=request.getParameter("birthday");
				MemberDTO dto=new MemberDTO();
				dto.setUserid(userid);
				dto.setPasswd(passwd);
				dto.setName(name);
				dto.setHp(hp);
				dto.setEmail(email);
				dto.setAddress(address);
				dto.setBirthday(birthday);
				dao.join(dto);
				String message="가입을 축하합니다. 로그인 페이지로 이동합니다.";
				String page=context+"/login.jsp";
				JSFunction.alertLocation(response, message, page);
			}
		}else if(uri.indexOf("member_detail.do") != -1){
			String userid=request.getParameter("userid");
			String passwd=request.getParameter("passwd");
			String name=dao.loginCheck(userid, passwd);
			if(name!=null) {
				MemberDTO dto=new MemberDTO();
				dto=dao.getMemberDetail(userid);
				request.setAttribute("dto", dto);
				String page="/memberDetail.jsp";
				RequestDispatcher rd=request.getRequestDispatcher(page);
				rd.forward(request, response);
			}else {
				String message="error";
				String page=context+"/memberDetail.jsp?message="+message;
				response.sendRedirect(page);
			}
		}else if(uri.indexOf("member_update.do") != -1) {
			String userid=request.getParameter("userid");
			String passwd=request.getParameter("passwd");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String hp=request.getParameter("hp");
			String address=request.getParameter("address");
			String birthday=request.getParameter("birthday");
			MemberDTO dto=new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			dto.setName(name);
			dto.setHp(hp);
			dto.setEmail(email);
			dto.setAddress(address);
			dto.setBirthday(birthday);
			dao.updateMember(dto);
			String message="수정되었습니다.";
			String page = "/member_servlet/member_detail.do";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(uri.indexOf("member_delete.do") != -1) {
			String userid=request.getParameter("userid");
			dao.deleteMember(userid);
			HttpSession session=request.getSession();
			session.invalidate();
			String message="탈퇴되었습니다. 이용해주셔서 감사합니다.";
			String page=context+"/index.jsp";
			JSFunction.alertLocation(response, message, page);
		}
		else if(uri.indexOf("admin_check.do") != -1) {
			HttpSession session=request.getSession();
			String userid=(String)session.getAttribute("userid");
			String adminkey=request.getParameter("adminkey");
			if(dao.adminKeyCheck(userid, adminkey)) {
				String page=context+"/member_servlet/admin_list.do";
				response.sendRedirect(page);
			}
			else {
				String message="error";
				String page=context+"/admin.jsp?message="+message;
				response.sendRedirect(page);
			}
		}else if(uri.indexOf("admin_list.do") != -1) {
			int curPage=1;
			Pager pager=null;
			List<MemberDTO> list=new ArrayList<MemberDTO>();
			if(request.getParameter("curPage") != null)
				curPage=Integer.parseInt(request.getParameter("curPage"));
			String searchOption=request.getParameter("searchOption");
			String searchKeyword=request.getParameter("searchKeyword");
			if(searchOption!=null) {
				if(searchOption.equals("all")) {
					int count=dao.searchCountAll(searchKeyword);
					pager=new Pager(count, curPage);
					int pageBegin=pager.getPageBegin();
					int pageEnd=pager.getPageEnd();
					list=dao.searchListAll(searchKeyword, pageBegin, pageEnd);
					request.setAttribute("searchOption", searchOption);
					request.setAttribute("searchKeyword", searchKeyword);
				}else {
					int count=dao.searchCount(searchOption, searchKeyword);
					pager=new Pager(count, curPage);
					int pageBegin=pager.getPageBegin();
					int pageEnd=pager.getPageEnd();
					list=dao.searchList(searchOption, searchKeyword, pageBegin, pageEnd);
					request.setAttribute("searchOption", searchOption);
					request.setAttribute("searchKeyword", searchKeyword);
				}
			}else {
				int count=dao.getMemberCount();
				pager=new Pager(count, curPage);
				int pageBegin=pager.getPageBegin();
				int pageEnd=pager.getPageEnd();
				list = dao.getMemberList(pageBegin, pageEnd);
			}
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			String page = "/adminResult.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(uri.indexOf("admin_delete.do") != -1) {
			String[] userids=request.getParameterValues("checkbox");
			dao.adminDelete(userids);
			String message="삭제되었습니다.";
			String page=context+"/member_servlet/admin_list.do";
			JSFunction.alertLocation(response, message, page);
		}else if(uri.indexOf("search_id") != -1) {
			String email=request.getParameter("email");
			String name=request.getParameter("name");
			System.out.println(email+","+name);
			MemberDTO dto=new MemberDTO();
			dto=dao.getUserid(email, name);
			System.out.println(dto.getUserid());

			if(dto.getUserid()==null) {
				String message="일치하는 회원이 없습니다. 다시 확인해주세요."; 
				JSFunction.alertBack(response, message);
			}else {
				String message=name+"님의 아이디는 "+dto.getUserid()+" 입니다.";
				String page=context+"/login.jsp"; 
				JSFunction.alertLocation(response, message, page);
			}
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
