package reviewboard.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import reviewboard.dto.ReviewBoardCommentDTO;
import reviewboard.dto.ReviewBoardDTO;
import sqlmap.MybatisManager;

public class ReviewBoardDAO {

	public int countBoard(String searchOption, String searchKeyword) {
		SqlSession session=null;
		int count=0;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("searchOption", searchOption);
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchKeyword", searchKeyword);
			count=session.selectOne("reviewboard.countBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return count;
	}

	public List<ReviewBoardDTO> getBoardList(String searchOption, String searchKeyword
			, String orderOption, int pageBegin, int pageEnd) {
		SqlSession session=null;
		List<ReviewBoardDTO> list=new ArrayList<ReviewBoardDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("searchOption", searchOption);
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchKeyword", searchKeyword);
			map.put("orderOption", orderOption);
			map.put("start", pageBegin);
			map.put("end", pageEnd);
			list=session.selectList("reviewboard.getBoardList", map);
			for(ReviewBoardDTO dto : list) {
				String title=dto.getTitle();
				title=title.replace("  ", "&nbsp;&nbsp;");
				title=title.replace("<", "&lt;"); 
				title=title.replace(">", "&gt;");
				dto.setTitle(title);	
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close(); 
		}
		return list;
	}

	public void insertReview(ReviewBoardDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("insertReview", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public void updateReadCount(int num, HttpSession httpsession) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			long read_time=0;
			if(httpsession.getAttribute("read_time"+num) != null)
				read_time=(long)httpsession.getAttribute("read_time"+num);
			long cur_time=System.currentTimeMillis();
			long limit=24*60*60*1000;
			if((cur_time-read_time) > limit) {
				session.update("reviewboard.updateReadCount", num);
				session.commit();
				httpsession.setAttribute("read_time"+num, cur_time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public ReviewBoardDTO reviewDetail(int num) {
		SqlSession session=null;
		ReviewBoardDTO dto=new ReviewBoardDTO();
		try {
			session=MybatisManager.getInstance().openSession();
			dto=session.selectOne("reviewboard.reviewDetail", num);
			String title=dto.getTitle();
			title=title.replace("  ", "&nbsp;&nbsp;");
			title=title.replace("<", "&lt;"); 
			title=title.replace(">", "&gt;");
			String content=dto.getContent();
			content=content.replace("  ", "&nbsp;&nbsp;");
			content=content.replace("<", "&lt;"); 
			content=content.replace(">", "&gt;");
			content=content.replace("\n", "<br>");
			dto.setTitle(title);
			dto.setContent(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public String getFileName(int num) {
		SqlSession session=null;
		String filename="";
		try {
			session=MybatisManager.getInstance().openSession();
			filename=session.selectOne("reviewboard.getFileName", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return filename;
	}

	public void updateDownload(int num) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("reviewboard.updateDownload", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public void updateStars(int stars, int prev_stars, int cafe_num, int prev_cafe_num, int prev_count) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("stars", stars);
			map.put("prev_stars", prev_stars);
			map.put("cafe_num", cafe_num);
			map.put("prev_cafe_num", prev_cafe_num);
			map.put("prev_count", prev_count);
			session.update("reviewboard.updateStars", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public void updateReview(ReviewBoardDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("reviewboard.updateReview", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public void deleteReview(int num) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("reviewboard.deleteReview", num); //update
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public void deleteStars(int stars, int cafe_num, int prev_count) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("stars", stars);
			map.put("cafe_num", cafe_num);
			map.put("prev_count", prev_count);
			session.update("reviewboard.deleteStars", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}

	}

	public void updateStep(int group_num, int re_step) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("group_num", group_num);
			map.put("re_step", re_step);
			session.update("reviewboard.updateStep", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public void replyReview(ReviewBoardDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("reviewboard.replyReview", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}

	}

	public void updateGoodCount(int num) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("reviewboard.updateGoodCount", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public void deleteCommentAll(int num) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("reviewboard.deleteCommentAll", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
				
		
	}

	public int countMyList(String searchOption, String searchKeyword, String writer) {
		SqlSession session=null;
		int count=0;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("searchOption", searchOption);
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchOption", searchOption);
			map.put("searchKeyword", searchKeyword);
			map.put("writer", writer);
			count=session.selectOne("reviewboard.countMyList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		return count;
	}

	public List<ReviewBoardDTO> getMyList(String searchOption, String searchKeyword, 
			String orderOption, int pageBegin, int pageEnd, String writer) {
		SqlSession session=null;
		List<ReviewBoardDTO> list=new ArrayList<ReviewBoardDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("searchOption", searchOption);
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchKeyword", searchKeyword);
			map.put("orderOption", orderOption);
			map.put("start", pageBegin);
			map.put("end", pageEnd);
			map.put("writer", writer);
			list=session.selectList("reviewboard.getMyList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ReviewBoardDTO> gethomeList() {
		SqlSession session=null;
		List<ReviewBoardDTO> list=new ArrayList<ReviewBoardDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			list=session.selectList("reviewboard.gethomeList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	public ReviewBoardDTO updateDetail(int num) {
		SqlSession session=null;
		ReviewBoardDTO dto=new ReviewBoardDTO();
		try {
			session=MybatisManager.getInstance().openSession();
			dto=session.selectOne("reviewboard.reviewDetail", num);
			String title=dto.getTitle();
			title=title.replace("&nbsp;&nbsp;", "  ");
			title=title.replace("&lt;", "<"); 
			title=title.replace("&gt;", ">");
			String content=dto.getContent();
			content=content.replace("  ", "&nbsp;&nbsp;");
			content=content.replace("<", "&lt;"); 
			content=content.replace(">", "&gt;");
			content=content.replace("<br>", "\n");
			dto.setTitle(title);
			dto.setContent(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public String numCheck(int num) {
		SqlSession session=null;
		String result="";
		try {
			session=MybatisManager.getInstance().openSession();
			List<ReviewBoardDTO> list=new ArrayList<ReviewBoardDTO>();
			list=session.selectList("reviewboard.numCheck");
			int min=list.get(0).getNum();
			int max=list.get(1).getNum();
			System.out.println("min:"+min+"max:"+max);
			if(num==min) result="min";
			if(num==max) result="max";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return result;
	}
}
