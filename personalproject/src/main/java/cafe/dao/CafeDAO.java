package cafe.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cafe.dto.CafeDTO;
import sqlmap.MybatisManager;

public class CafeDAO {

	public List<CafeDTO> cafeList(int pageBegin, int pageEnd) {
		SqlSession session=null;
		List<CafeDTO> list=new ArrayList<CafeDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("start", pageBegin);
			map.put("end", pageEnd);
			list=session.selectList("cafe.cafeList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	public int cafeCount() {
		SqlSession session=null;
		int count=0;
		try {
			session=MybatisManager.getInstance().openSession();
			count=session.selectOne("cafe.cafeCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return count;
	}

	public List<CafeDTO> searchList(String searchOption, String searchKeyword, String orderOption, int pageBegin, int pageEnd) {
		SqlSession session=null;
		List<CafeDTO> list=new ArrayList<CafeDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>(); 
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchOption", searchOption);
			map.put("searchKeyword", searchKeyword);
			map.put("orderOption", orderOption);
			map.put("start", pageBegin);
			map.put("end", pageEnd);
			list=session.selectList("cafe.searchList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	public int searchCount(String searchOption, String searchKeyword) {
		SqlSession session=null;
		int count=0;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>(); 
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchOption", searchOption);
			map.put("searchKeyword", searchKeyword);
			count=session.selectOne("cafe.searchCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return count;
	}

	public List<CafeDTO> searchList(String searchKeyword, String orderOption, int pageBegin, int pageEnd) {
		SqlSession session=null;
		List<CafeDTO> list=new ArrayList<CafeDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>(); 
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchKeyword", searchKeyword);
			map.put("orderOption", orderOption);
			map.put("start", pageBegin);
			map.put("end", pageEnd);
			list=session.selectList("cafe.searchList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	public int searchCount(String searchKeyword) {
		SqlSession session=null;
		int count=0;
		try {
			session=MybatisManager.getInstance().openSession();
			searchKeyword="%"+searchKeyword+"%";
			count=session.selectOne("cafe.searchCount", searchKeyword);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return count;
	}

	public CafeDTO getCafeDetail(int cafe_num) {
		SqlSession session=null;
		CafeDTO dto=null;
		try {
			session=MybatisManager.getInstance().openSession();
			dto=session.selectOne("cafe.getCafeDetail", cafe_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return dto;
	}

	public void updateStar(int star, int cafe_num) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("star", star);
			map.put("cafe_num", cafe_num);
			session.update("cafe.updateStar", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public CafeDTO getnewCafe() {
		SqlSession session=null;
		CafeDTO dto=null;
		try {
			session=MybatisManager.getInstance().openSession();
			dto=session.selectOne("cafe.getnewCafe");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return dto;
	}

}
