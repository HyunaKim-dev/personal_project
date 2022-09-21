package reviewboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import cafe.dao.CafeDAO;
import cafe.dto.CafeDTO;
import common.Constants;
import common.Pager;
import member.dao.MemberDAO;
import reviewboard.dao.ReviewBoardCommentDAO;
import reviewboard.dao.ReviewBoardDAO;
import reviewboard.dto.ReviewBoardCommentDTO;
import reviewboard.dto.ReviewBoardDTO;
import utils.JSFunction;

@WebServlet("/reviewboard_servlet/*")
public class ReviewBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uri=request.getRequestURI();
		String context=request.getContextPath();
		ReviewBoardDAO dao=new ReviewBoardDAO();
		if(uri.indexOf("board_list.do") != -1) {
			int curPage=1;
			Pager pager=null;
			List<ReviewBoardDTO> list=new ArrayList<ReviewBoardDTO>();
			if(request.getParameter("curPage") != null)
				curPage=Integer.parseInt(request.getParameter("curPage"));
			String searchOption=request.getParameter("searchOption");
			String searchKeyword=request.getParameter("searchKeyword");
			String orderOption=request.getParameter("orderOption");
			if(searchOption==null) {
				searchOption="all";
				searchKeyword="";
				orderOption="reg_date";
			}
			//System.out.println("searchOption:"+searchOption+", searchKey:"+searchKeyword+", orderOp:"+orderOption);
			int count=dao.countBoard(searchOption, searchKeyword);
			//System.out.println("count:"+count);
			pager=new Pager(count, curPage);
			int pageBegin=pager.getPageBegin();
			int pageEnd=pager.getPageEnd();
			list=dao.getBoardList(searchOption, searchKeyword, orderOption, pageBegin, pageEnd);
			//System.out.println("list size:"+list.size());
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			request.setAttribute("searchOption", searchOption);
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("orderOption", orderOption);
			String page="/reviewMain.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(uri.indexOf("select_cafe.do") != -1) {
			List<CafeDTO> list=new ArrayList<CafeDTO>();
			CafeDAO cdao=new CafeDAO();
			int count=cdao.cafeCount();
			list=cdao.cafeList(1, count);
			request.setAttribute("list", list);
			String page="/reviewPost.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(uri.indexOf("insert_review.do") != -1) {
			File uploadDir=new File(Constants.UPLOAD_PATH);
			if(!uploadDir.exists()) uploadDir.mkdir();
			MultipartRequest multi=new MultipartRequest(request, 
					Constants.UPLOAD_PATH, Constants.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy());
			String writer=multi.getParameter("userid");
			String title=multi.getParameter("title");
			String content=multi.getParameter("content");
			int stars=0;
			int cafe_num=0;
			if(multi.getParameter("stars") != null)
				stars=Integer.parseInt(multi.getParameter("stars"));
			if(multi.getParameter("cafe_num") != null)
				cafe_num=Integer.parseInt(multi.getParameter("cafe_num"));
			String ip=request.getRemoteAddr();
			if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
				InetAddress inetAddress=InetAddress.getLocalHost();
				ip=inetAddress.getHostAddress(); //ip를 string으로
			}
			String filename="";
			int filesize=0;
			try {
				Enumeration files=multi.getFileNames();
				while(files.hasMoreElements()) {
					String fileElement=(String)files.nextElement();
					filename=multi.getFilesystemName(fileElement);
					File file1=multi.getFile(fileElement);
					if(file1 != null) {
						filesize=(int)file1.length();
						System.out.println("fileElement:"+fileElement+", filename:"+filename+", filesize:"+filesize);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(filename ==null || filename.trim().equals("")) {
				filename="-";
			}
			ReviewBoardDTO dto=new ReviewBoardDTO();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setStars(stars);
			dto.setIp(ip);
			dto.setFilename(filename);
			dto.setFilesize(filesize);
			dto.setCafe_num(cafe_num);
			CafeDAO cdao=new CafeDAO();
			cdao.updateStar(stars, cafe_num);
			dao.insertReview(dto);
			System.out.println(dto.toString());
			String message="등록되었습니다.";
			String page=context+"/reviewboard_servlet/board_list.do";
			JSFunction.alertLocation(response, message, page);
		}else if(uri.indexOf("review_detail.do") != -1) {
			System.out.println(uri);
			int num=0;
			if(request.getParameter("num")!=null)
				num=Integer.parseInt(request.getParameter("num"));
			HttpSession session=request.getSession();
			dao.updateReadCount(num, session);
			ReviewBoardDTO dto=new ReviewBoardDTO();
			dto=dao.reviewDetail(num); //기존 정보
			if(dto.getshow_op().equals("n")) {
				String message="삭제된 게시물입니다.";
				String page=context+"/reviewboard_servlet/board_list.do";
				JSFunction.alertLocation(response, message, page);
			}else {
				List<ReviewBoardCommentDTO> clist=new ArrayList<ReviewBoardCommentDTO>();
				clist=new ReviewBoardCommentDAO().getCommentList(num);
				String result=dao.numCheck(num);
				request.setAttribute("result", result);
				request.setAttribute("dto", dto);
				request.setAttribute("clist", clist);
				String page="/reviewDetail.jsp";
				RequestDispatcher rd=request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		}else if(uri.indexOf("download.do") != -1) {
			int num=0;
			if(request.getParameter("num") != null)
				num=Integer.parseInt(request.getParameter("num"));
			String filename=dao.getFileName(num);
			String filepath=Constants.UPLOAD_PATH+filename;
			System.out.println("filepath:"+filepath);
			byte b[]=new byte[4096];
			FileInputStream fis=new FileInputStream(filepath);
			String mimeType=getServletContext().getMimeType(filepath);
			System.out.println("mimeType:"+mimeType);
			if(mimeType==null)
				mimeType="application/octet-stream;charset=utf-8";
			filename=new String(filename.getBytes("utf-8"), "8859_1");
			response.setHeader("Content-Disposition", "attachment;filename="+filename);
			ServletOutputStream out=response.getOutputStream();
			int readNum;
			while(true) {
				readNum=fis.read(b, 0, b.length);
				if(readNum == -1) break;
				out.write(b, 0, readNum);
			}
			out.flush();
			out.close();
			fis.close();
			dao.updateDownload(num);
		}else if(uri.indexOf("update_page.do") != -1) {
			int num=0;
			if(request.getParameter("num") != null)
				num=Integer.parseInt(request.getParameter("num"));
			String message=request.getParameter("message"); //reviewUpdate.jsp
			List<CafeDTO> list=new ArrayList<CafeDTO>();
			CafeDAO cdao=new CafeDAO();
			int count=cdao.cafeCount();
			list=cdao.cafeList(1, count);
			ReviewBoardDTO dto=dao.updateDetail(num);
			request.setAttribute("list", list);
			request.setAttribute("dto", dto);
			request.setAttribute("message", message);
			RequestDispatcher rd=
					request.getRequestDispatcher("/reviewUpdate.jsp");
			rd.forward(request, response);
		}else if(uri.indexOf("review_update.do") != -1) {
			//form enctype="multipart/form-data"
			MultipartRequest multi=new MultipartRequest(request, 
					Constants.UPLOAD_PATH, Constants.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy());
			int num=0;
			if(multi.getParameter("num") != null)
				num=Integer.parseInt(multi.getParameter("num"));
			String userid=multi.getParameter("userid");
			String passwd=multi.getParameter("passwd");
			MemberDAO mdao=new MemberDAO();
			String name=mdao.loginCheck(userid, passwd);
			ReviewBoardDTO dto=dao.reviewDetail(num);
			ReviewBoardDTO dto2=dao.reviewDetail(num); //기존 정보
			if(name==null || name.equals("")) {
				JSFunction.alertBack(response, "비밀번호가 틀렸습니다.");
			}else {
				String writer=multi.getParameter("userid");
				String title=multi.getParameter("title");
				String content=multi.getParameter("content");
				int stars=0;
				int cafe_num=0;
				if(multi.getParameter("stars") != null)
					stars=Integer.parseInt(multi.getParameter("stars"));
				if(multi.getParameter("cafe_num") != null)
					cafe_num=Integer.parseInt(multi.getParameter("cafe_num"));
				String ip=request.getRemoteAddr();
				if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
					InetAddress inetAddress=InetAddress.getLocalHost();
					ip=inetAddress.getHostAddress(); //ip를 string으로
				}

				File uploadDir=new File(Constants.UPLOAD_PATH);
				if(!uploadDir.exists()) uploadDir.mkdir();
				String filename=" ";
				int filesize=0;
				try {
					Enumeration files=multi.getFileNames();
					while(files.hasMoreElements()) {
						String fileElement=(String)files.nextElement();
						filename=multi.getFilesystemName(fileElement);
						File file1=multi.getFile(fileElement);
						if(file1 != null) {
							filesize=(int)file1.length();
						}
						System.out.println("fileElement:"+fileElement+", filename:"+filename+", filesize:"+filesize);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(!(filename==null || filename.trim().equals(""))) { //신규파일 있음
					//기존파일 삭제
					String filenameDel=dao.getFileName(num);
					File f=new File(Constants.UPLOAD_PATH+filenameDel);
					f.delete();
					dto.setFilename("-");
					dto.setFilesize(0);
					dto.setDownload(0);
					//신규파일 저장
					dto.setFilename(filename);
					dto.setFilesize(filesize);
				}else { //신규파일 없음
					String fileDel=multi.getParameter("fileDel");
					if(fileDel!=null && fileDel.equals("on")) {//Delon
						//기존 파일 삭제
						String filenameDel=dao.getFileName(num);
						File f=new File(Constants.UPLOAD_PATH+filenameDel);
						f.delete();
						dto.setFilename("-");
						dto.setFilesize(0);
						dto.setDownload(0);
					}else {//del off
						//기존파일 정보 불러오기
						String filename2=dto2.getFilename();
						int filesize2=dto2.getFilesize();
						int download2=dto2.getDownload();
						dto.setFilename(filename2);
						dto.setFilesize(filesize2);
						dto.setDownload(download2);
					}
				}//신규파일없음
				//나머지 set
				dto.setNum(num);
				dto.setTitle(title);
				dto.setContent(content);
				dto.setIp(ip);
				dto.setStars(stars);
				dto.setCafe_num(cafe_num);
				dao.updateReview(dto);
				//별점처리
				int prev_stars=dto2.getStars(); //기존 평점
				int prev_cafe_num=dto2.getCafe_num();
				int prev_count=new CafeDAO().getCafeDetail(prev_cafe_num).getStars_count();
				dao.updateStars(stars, prev_stars, cafe_num, prev_cafe_num, prev_count); //cafe 테이블의 평점 데이터 수정
				//				System.out.println("prev_stars:"+prev_stars+", prev_cafe_num:"+prev_cafe_num
				//						+", cafe_num:"+cafe_num+", stars:"+stars+", prev_count:" + prev_count); 
				//페이지 이동
				String page=context+"/reviewboard_servlet/board_list.do";
				response.sendRedirect(page);
			}
		}else if(uri.indexOf("delete_review.do") != -1) {
			//form enctype="multipart/form-data"
			int num=0;
			if(request.getParameter("num") != null)
				num=Integer.parseInt(request.getParameter("num"));
			String userid=request.getParameter("userid");
			String passwd=request.getParameter("passwd");
			MemberDAO mdao=new MemberDAO();
			String name=mdao.loginCheck(userid, passwd);
			if(name==null || name.equals("")) {
				JSFunction.alertBack(response, "비밀번호가 틀렸습니다.");
			}else {
				ReviewBoardDTO dto=dao.reviewDetail(num);
				String filenameDel=dao.getFileName(num);
				File f=new File(Constants.UPLOAD_PATH+filenameDel);
				f.delete();
				dto.setFilename("-");
				dto.setFilesize(0);
				dto.setDownload(0); 
				dao.deleteCommentAll(num);
				dao.deleteReview(num); //update
				int stars=dto.getStars();
				int cafe_num=dto.getCafe_num();
				if(cafe_num!=0) {
					int prev_count=new CafeDAO().getCafeDetail(cafe_num).getStars_count();
					dao.deleteStars(stars, cafe_num, prev_count);
				}
				String page=context+"/reviewboard_servlet/board_list.do";
				JSFunction.alertLocation(response, "삭제되었습니다.", page);
			}
		}else if(uri.indexOf("reply_page.do") != -1) {
			int num=0;
			if(request.getParameter("num") != null)
				num=Integer.parseInt(request.getParameter("num"));
			ReviewBoardDTO dto=dao.reviewDetail(num);
			dto.setContent("\n------본문------\n"+dto.getContent());
			request.setAttribute("dto", dto);
			RequestDispatcher rd=request.getRequestDispatcher("/reviewReply.jsp");
			rd.forward(request, response);
		}else if(uri.indexOf("reply_review.do") != -1) {
			File uploadDir=new File(Constants.UPLOAD_PATH);
			if(!uploadDir.exists()) uploadDir.mkdir();
			MultipartRequest multi=new MultipartRequest(request, 
					Constants.UPLOAD_PATH, Constants.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy());
			int oriNum=0;
			if(multi.getParameter("num") != null)
				oriNum=Integer.parseInt(multi.getParameter("num"));
			String writer=multi.getParameter("userid");
			String title=multi.getParameter("title");
			String content=multi.getParameter("content");
			String ip=request.getRemoteAddr();
			if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
				InetAddress inetAddress=InetAddress.getLocalHost();
				ip=inetAddress.getHostAddress();
			}
			String filename="";
			int filesize=0;
			try {
				Enumeration files=multi.getFileNames();
				while(files.hasMoreElements()) {
					String fileElement=(String)files.nextElement();
					filename=multi.getFilesystemName(fileElement);
					File file1=multi.getFile(fileElement);
					if(file1 != null) {
						filesize=(int)file1.length();
						System.out.println("fileElement:"+fileElement+", filename:"+filename+", filesize:"+filesize);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(filename ==null || filename.trim().equals("")) {
				filename="-";
			}
			ReviewBoardDTO dto=dao.reviewDetail(oriNum);
			int group_num=dto.getGroup_num();
			int re_step=dto.getRe_step()+1;
			int re_level=dto.getRe_level()+1;
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setGroup_num(group_num);
			dto.setRe_step(re_step);
			dto.setRe_level(re_level);
			dto.setIp(ip);
			dto.setFilename(filename);
			dto.setFilesize(filesize);
			dao.updateStep(group_num, re_step);
			dao.replyReview(dto);
			System.out.println(dto.toString());
			String message="답글이 등록되었습니다.";
			String page=context+"/reviewboard_servlet/board_list.do";
			JSFunction.alertLocation(response, message, page);
		}else if(uri.indexOf("update_goodcount.do") != -1) {
			int num=0;
			if(request.getParameter("num") != null)
				num=Integer.parseInt(request.getParameter("num"));
			dao.updateGoodCount(num);
			String page=context+"/reviewboard_servlet/review_detail.do?num="+num;
			response.sendRedirect(page);
		}else if(uri.indexOf("mylist.do") != -1) {
			int curPage=1;
			Pager pager=null;
			List<ReviewBoardDTO> list=new ArrayList<ReviewBoardDTO>();
			if(request.getParameter("curPage") != null)
				curPage=Integer.parseInt(request.getParameter("curPage"));
			HttpSession session=request.getSession();
			String writer=(String)session.getAttribute("userid");
			String searchOption=request.getParameter("searchOption");
			String searchKeyword=request.getParameter("searchKeyword");
			String orderOption=request.getParameter("orderOption");
			System.out.println("searchOption:"+searchOption+", searchKey:"+searchKeyword+", orderOp:"+orderOption);
			if(searchOption==null) {
				searchOption="all";
				searchKeyword="";
				orderOption="reg_date";
			}
			int count=dao.countMyList(searchOption, searchKeyword, writer);
			System.out.println("count: "+count);
			pager=new Pager(count, curPage);
			int pageBegin=pager.getPageBegin();
			int pageEnd=pager.getPageEnd();
			list=dao.getMyList(searchOption, searchKeyword, orderOption, pageBegin, pageEnd, writer);
			request.setAttribute("list", list);
			request.setAttribute("pager", pager);
			request.setAttribute("searchOption", searchOption);
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("orderOption", orderOption);
			String page="/myList.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
