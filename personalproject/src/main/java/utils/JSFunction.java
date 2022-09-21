package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class JSFunction {

	public static void alertLocation(HttpServletResponse resp, String message, String url) {
		try {
			//서블릿에서 내용 출력위해 setContentType으로 콘텐츠 타입 지정 필요
			resp.setContentType("text/html;charset=UTF-8");
			//서블릿으로 HTML을 웹에 표현하기 위해 출력스트림 생성
			PrintWriter writer= resp.getWriter();
			String script = "<script>"
					+ "	alert('"+message+"');"
					+ "	location.href='"+url+"';"
					+ "	</script>";
			writer.print(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void alertBack(HttpServletResponse resp, String message) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer= resp.getWriter();
			String script = "<script>"
					+ "alert('"+message+"');"
					+ "	history.back();"
					+ "</script>";
			writer.print(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}