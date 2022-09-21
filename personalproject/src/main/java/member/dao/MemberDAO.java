package member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.dto.MemberDTO;
import sqlmap.MybatisManager;

public class MemberDAO {

	public String loginCheck(String userid, String passwd) {
		String name="";
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String, Object>map=new HashMap<>();
			map.put("userid", userid);
			map.put("passwd", passwd);
			name=session.selectOne("member.loginCheck", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return name;
	}

	public String joinIdCheck(String userid) {
		String result="";
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			result=session.selectOne("member.joinIdCheck", userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return result;
	}

	public void join(MemberDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("member.join", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}
	public MemberDTO getMemberDetail(String userid) {
		SqlSession session=null;
		MemberDTO dto=new MemberDTO();
		try {
			session=MybatisManager.getInstance().openSession();
			dto=session.selectOne("member.getMemberDetail",userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return dto;
	}

	public void updateMember(MemberDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("updateMember", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}
	public boolean adminIdCheck(String userid) {
		SqlSession session=null;
		boolean result=false;
		try {
			session=MybatisManager.getInstance().openSession();
			result=session.selectOne("member.adminIdCheck", userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return result;
	}

	public boolean adminKeyCheck(String userid, String key) {
		SqlSession session=null;
		boolean result=false;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("adminid", userid);
			map.put("adminkey", key);
			result=session.selectOne("member.adminKeyCheck", map);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return result;
	}

	public int getMemberCount() {
		SqlSession session=null;
		int count=0;
		try {
			session=MybatisManager.getInstance().openSession();
			count=session.selectOne("member.memberCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return count;
	}

	public List<MemberDTO> getMemberList(int pageBegin, int pageEnd) {
		SqlSession session=null;
		List<MemberDTO> list=null;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("start", pageBegin);
			map.put("end", pageEnd);
			list=session.selectList("member.getMemberList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	public void adminDelete(String[] userids) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			List<String> list=new ArrayList<String>();
			for(String s : userids) {
				list.add(s);
			}
			session.delete("adminDelete", list);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}

	}

	public void deleteMember(String userid) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.delete("member.deleteMember", userid);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
	}

	public List<MemberDTO> searchList(String searchOption, String searchKeyword, int pageBegin, int pageEnd) {
		SqlSession session=null;
		List<MemberDTO> list=new ArrayList<MemberDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String, Object> map=new HashMap<String, Object>();
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchOption", searchOption);
			map.put("searchKeyword", searchKeyword);
			map.put("start", pageBegin);
			map.put("end", pageEnd);
			list=session.selectList("member.searchList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	public List<MemberDTO> searchListAll(String searchKeyword, int pageBegin, int pageEnd) {
		SqlSession session=null;
		List<MemberDTO> list=new ArrayList<MemberDTO>();
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String, Object> map=new HashMap<String, Object>();
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchKeyword", searchKeyword);
			map.put("start", pageBegin);
			map.put("end", pageEnd);
			list=session.selectList("searchListAll", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	public int searchCountAll(String searchKeyword) {
		SqlSession session=null;
		int count=0;
		try {
			session=MybatisManager.getInstance().openSession();
			searchKeyword="%"+searchKeyword+"%";
			count=session.selectOne("member.searchCountAll", searchKeyword);
			System.out.println("count dao:" +count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return count;
	}

	public int searchCount(String searchOption, String searchKeyword) {
		SqlSession session=null;
		int count=0;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String, Object> map=new HashMap<String, Object>();
			searchKeyword="%"+searchKeyword+"%";
			map.put("searchOption", searchOption);
			map.put("searchKeyword", searchKeyword);
			count=session.selectOne("member.searchCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return count;
	}

	public MemberDTO getUserid(String email, String name) {
		SqlSession session=null;
		MemberDTO dto=new MemberDTO();
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("email", email);
			map.put("name", name);
			dto=session.selectOne("member.getUserid", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return dto;
	}

}
