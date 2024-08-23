import javax.servlet.*;
import java.io.IOException;
import java.util.logging.LogRecord;

public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void destroy() {
    }
    //클라이언트의 요청이 있을 때마다 실행. 필터 핵심 메소드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        chain.doFilter(request, response);

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}