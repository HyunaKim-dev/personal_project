package config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
//요청과 서블릿,JSP 등의 최종 자원 사이에서 요청정보 및 요청결과를 알맞게 변경 및 재사용 가능한 코드가 필터
@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
	private String charset="utf-8";

	/* public EncodingFilter() {
        super();
    } */

	public void destroy() {
		System.out.println("필터가 제거되었습니다.");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		System.out.println("필터가 실행되었습니다.");
		request.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	}

	//필터를 웹컨테이너 내에 생성 후 초기화할 때 호출
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("필터가 초기화되었습니다.");
	}
}
