package cafe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.dao.CafeDAO;
import cafe.dto.CafeDTO;
import common.CafePager;
import reviewboard.dao.ReviewBoardDAO;
import reviewboard.dto.ReviewBoardDTO;

@WebServlet("/cafe_servlet/*")
public class CafeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uri=request.getRequestURI();
		CafeDAO dao=new CafeDAO();
		String context=request.getContextPath();
		if(uri.indexOf("homelist.do") != -1) {
			CafeDTO cdto=new CafeDTO();
			List<ReviewBoardDTO> list=new ArrayList<ReviewBoardDTO>();
			ReviewBoardDAO bdao=new ReviewBoardDAO(); 
			cdto=dao.getnewCafe();
			list=bdao.gethomeList();
			request.setAttribute("cdto", cdto);
			request.setAttribute("list", list);
			String page="/index_home.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(uri.indexOf("cafe_list.do") != -1) {
			int curPage=1;
			CafePager pager=null;
			List<CafeDTO> list=new ArrayList<CafeDTO>();
			if(request.getParameter("curPage") != null)
				curPage=Integer.parseInt(request.getParameter("curPage"));
			String searchOption=request.getParameter("searchOption");
			String searchKeyword=request.getParameter("searchKeyword");
			String orderOption=request.getParameter("orderOption");
			System.out.println("searchOption:"+searchOption+", searchKey:"+searchKeyword+", orderOp:"+orderOption);
			if(searchOption!=null) {
				if(searchOption.equals("all")) {
					int count=dao.searchCount(searchKeyword);
					pager=new CafePager(count, curPage);
					int pageBegin=pager.getPageBegin();
					int pageEnd=pager.getPageEnd();
					list=dao.searchList(searchKeyword, orderOption, pageBegin, pageEnd);
				}
				else {
					int count=dao.searchCount(searchKeyword, searchKeyword);
					pager=new CafePager(count, curPage);
					int pageBegin=pager.getPageBegin();
					int pageEnd=pager.getPageEnd();
					list=dao.searchList(searchOption, searchKeyword, orderOption, pageBegin, pageEnd);
				}
				request.setAttribute("searchOption", searchOption);
				request.setAttribute("searchKeyword", searchKeyword);
			}else {
				int count=dao.cafeCount();
				pager=new CafePager(count, curPage);
				int pageBegin=pager.getPageBegin();
				int pageEnd=pager.getPageEnd();
				list=dao.cafeList(pageBegin, pageEnd);
			}
			request.setAttribute("orderOption", orderOption);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			String page="/brownstars.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(uri.indexOf("cafe_detail.do") != -1) {
			int cafe_num=0;
			if(request.getParameter("cafe_num") != null)
				cafe_num=Integer.parseInt(request.getParameter("cafe_num"));
			CafeDTO dto=new CafeDTO();
			dto=dao.getCafeDetail(cafe_num);
			request.setAttribute("dto", dto);
			String page="/cafeDetail.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(uri.indexOf("stars.do") != -1) {
			int star=0;
			int cafe_num=0;
			if(request.getParameter("star")!=null && request.getParameter("cafe_num")!=null) {
				star=Integer.parseInt(request.getParameter("star"));
				cafe_num=Integer.parseInt(request.getParameter("cafe_num"));
			}
			dao.updateStar(star, cafe_num);
			request.setAttribute("cafe_num", cafe_num);
			String page=context+"/cafe_servlet/cafe_detail.do?cafe_num="+cafe_num;
			response.sendRedirect(page);
//			RequestDispatcher rd=request.getRequestDispatcher(page);
//			rd.forward(request, response);
		}
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
