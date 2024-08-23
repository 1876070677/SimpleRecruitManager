import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opt = request.getParameter("opt");
            String keyword = request.getParameter("keyword");
            if (opt == null) {
                // 그냥 전체 목록 조회, 공고 등록 순서대로
            } else if (opt.equals("company") || opt.equals("money")) {
                if (!keyword.isBlank()) {
                    // 회사 또는 봉급 순으로 정렬 할 때, 키워드가 있는 경우
                    response.sendRedirect("/");
                } else {
                    // 회사 또는 봉급 순으로 정렬만 하는 경우
                }
            } else {
                response.sendRedirect("/list");
            }
        } catch (Exception e) {
            response.sendRedirect("/list");
        }
    }
}
