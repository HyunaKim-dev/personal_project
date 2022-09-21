package reviewboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reviewboard.dao.ReviewBoardCommentDAO;
import reviewboard.dao.ReviewBoardDAO;
import reviewboard.dto.ReviewBoardCommentDTO;
import reviewboard.dto.ReviewBoardDTO;
import utils.JSFunction;

@WebServlet("/comment_servlet/*")
public class ReviewBoardCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uri=request.getRequestURI();
		System.out.println(uri);
		String context=request.getContextPath();
		ReviewBoardCommentDAO dao=new ReviewBoardCommentDAO();
		if(uri.indexOf("comment_list.do") != -1) {
			int reviewboard_num=0;
			if(request.getParameter("num") != null)
				reviewboard_num=Integer.parseInt(request.getParameter("num"));
			System.out.println("list-num:"+reviewboard_num);
			List<ReviewBoardCommentDTO> clist=new ArrayList<ReviewBoardCommentDTO>();
			clist=dao.getCommentList(reviewboard_num);
			ReviewBoardDAO bdao=new ReviewBoardDAO();
			ReviewBoardDTO dto=bdao.reviewDetail(reviewboard_num);
			request.setAttribute("dto", dto);
			request.setAttribute("clist", clist);
			String page=context+"/reviewboard_servlet/review_detail.do?num="+reviewboard_num;
			RequestDispatcher rd=request.getRequestDispatcher("");
			rd.forward(request, response);

		}else if(uri.indexOf("insert_comment.do") != -1) {
			int reviewboard_num=0;
			if(request.getParameter("num")!=null)
				reviewboard_num=Integer.parseInt(request.getParameter("num"));
			String writer=request.getParameter("userid");
			String content=request.getParameter("content");
			System.out.println("insert-num:"+reviewboard_num+"writer:"+writer+", content:"+content);
			ReviewBoardCommentDTO dto=new ReviewBoardCommentDTO();
			dto.setReviewboard_num(reviewboard_num);
			dto.setWriter(writer);
			dto.setContent(content);
			dao.insertComment(dto);
			String page=context+"/reviewboard_servlet/review_detail.do?num="+reviewboard_num;
			response.sendRedirect(page);
		}else if(uri.indexOf("delete_comment.do") != -1) { 
			int reviewboard_num=0;
			int comment_num=0;
			if(request.getParameter("num") != null)
				reviewboard_num=Integer.parseInt(request.getParameter("num"));
			if(request.getParameter("comment_num")!=null)
				comment_num=Integer.parseInt(request.getParameter("comment_num"));
			dao.deleteComment(comment_num);
			String page=context+"/reviewboard_servlet/review_detail.do?num="+reviewboard_num;
			response.sendRedirect(page);
		}else if(uri.indexOf("reply_comment.do") != -1) { 
			int reviewboard_num=0;
			int comment_num=0;
			if(request.getParameter("num") != null)
				reviewboard_num=Integer.parseInt(request.getParameter("num"));
			if(request.getParameter("comment_num") != null)
				comment_num=Integer.parseInt(request.getParameter("comment_num"));
			String writer=request.getParameter("userid");
			String content=request.getParameter("content");
			System.out.println("insert-num:"+reviewboard_num+"writer:"+writer+", content:"+content);
			ReviewBoardCommentDTO dto=new ReviewBoardCommentDTO();
			int group_num=dto.getGroup_num();
			int re_step=dto.getRe_step()+1;
			int re_level=dto.getRe_level()+1;
			dto.setReviewboard_num(reviewboard_num);
			dto.setWriter(writer);
			dto.setContent(content);
			dto.setGroup_num(group_num);
			dto.setRe_step(re_step);
			dto.setRe_level(re_level);
			dao.updateStep(group_num, re_step);
			dao.replyComment(dto);
			String page=context+"/reviewboard_servlet/review_detail.do?num="+reviewboard_num;
			response.sendRedirect(page);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
