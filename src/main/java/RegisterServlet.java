import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String company = request.getParameter("company");
            String job = request.getParameter("job");
            String money = request.getParameter("money");
            String region = request.getParameter("region");
            String phone = request.getParameter("phone");

            if (company.isBlank() || job.isBlank() || region.isBlank() || phone.isBlank() || money.isBlank()) {
                response.sendRedirect("/register");
            } else {
                // 유효성 검증 시 로직, DB 엑세스
                System.out.println(company);
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            response.sendRedirect("/register");
        }
    }
}
