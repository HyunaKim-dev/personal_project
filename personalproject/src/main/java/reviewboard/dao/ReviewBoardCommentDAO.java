package reviewboard.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import reviewboard.dto.ReviewBoardCommentDTO;
import sqlmap.MybatisManager;

public class ReviewBoardCommentDAO {

	public List<ReviewBoardCommentDTO> getCommentList(int reviewboard_num) {
		SqlSession session=null;
		List<ReviewBoardCommentDTO> clist=new ArrayList<ReviewBoardCommentDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			clist=session.selectList("rbcomment.getCommentList", reviewboard_num);
			for(ReviewBoardCommentDTO dto : clist) {
				String content=dto.getContent();
				content=content.replace("<", "&lt;"); 
				content=content.replace(">", "&gt;");
				content=content.replace("  ", "&nbsp;&nbsp;");
				String writer=dto.getWriter();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return clist;
	}

	public void insertComment(ReviewBoardCommentDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("rbcomment.insertComment", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteComment(int comment_num) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("rbcomment.deleteComment", comment_num);
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
			session.update("rbccomment.updateStep", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public void replyComment(ReviewBoardCommentDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("rbccomment.replyComment", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

}
