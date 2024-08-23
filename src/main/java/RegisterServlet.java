import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource ds = null;

    private static final String INSERT_RECRUIT = "INSERT INTO recruit(company, job, money, region, phone) VALUES(?, ?, ?, ?, ?)";

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String company = request.getParameter("company");
            String job = request.getParameter("job");
            int money = Integer.parseInt(request.getParameter("money"));
            String region = request.getParameter("region");
            String phone = request.getParameter("phone");

            if (company.isBlank() || job.isBlank() || region.isBlank() || phone.isBlank() || money < 1) {
                request.setAttribute("err", "입력 정보가 부족합니다.");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
                rd.forward(request, response);
            } else {
                // 유효성 검증 시 로직, DB 엑세스
                conn = ds.getConnection();
                ps = conn.prepareStatement(INSERT_RECRUIT);
                ps.setString(1, company);
                ps.setString(2, job);
                ps.setInt(3, money);
                ps.setString(4, region);
                ps.setString(5, phone);

                ps.executeUpdate();
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("err", "등록 과정에서 에러가 발생했습니다.");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
            rd.forward(request, response);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {}
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {}
            }
        }
    }
}
