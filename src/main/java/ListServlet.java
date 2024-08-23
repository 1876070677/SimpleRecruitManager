import DTO.Recruit;

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
import java.util.ArrayList;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource ds = null;

    private static final String GET_ALL_LIST = "select * from recruit";
    private static final String GET_LIST_ORDER_BY_COMPANY_OR_MONEY = "select * from recruit order by ";
    private static final String GET_LIST_ORDER_BY_COMPANY_OR_MONEY_MATCHING_KEYWORD = "select * from recruit where company like ? order by ";
    private static final String GET_LIST_MATCHING_KEYWORD = "select * from recruit where company like ?";

    @Override
    public void init() throws ServletException{
        super.init();
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String opt = request.getParameter("opt");
            String keyword = request.getParameter("keyword");

            // 데이터베이스 커넥션 가져오기
            conn = ds.getConnection();
            if (opt == null && keyword == null) {
                ps = conn.prepareStatement(GET_ALL_LIST);
                rs = ps.executeQuery();
            } else if (opt != null && (opt.equals("company") || opt.equals("money"))) {
                if (keyword != null) {
                    // 회사 또는 봉급 순으로 정렬 할 때, 키워드가 있는 경우
                    String SQL = GET_LIST_ORDER_BY_COMPANY_OR_MONEY_MATCHING_KEYWORD + opt + " DESC";
                    ps = conn.prepareStatement(SQL);
                    ps.setString(1, '%' + keyword + '%');
                    rs = ps.executeQuery();
                } else {
                    // 회사 또는 봉급 순으로 정렬만 하는 경우
                    String SQL = GET_LIST_ORDER_BY_COMPANY_OR_MONEY + opt + " DESC";
                    ps = conn.prepareStatement(SQL);
                    rs = ps.executeQuery();
                }
            } else if (keyword != null && !keyword.isBlank()){
                System.out.println(keyword);
                ps = conn.prepareStatement(GET_LIST_MATCHING_KEYWORD);
                ps.setString(1, '%' + keyword + '%');
                rs = ps.executeQuery();
            }

            // resultSet에 담긴 결과를 DTO에 담아서 뷰로 전달할 것
            ArrayList<Recruit> list = new ArrayList<>();
            if (rs != null) {
                while (rs.next()) {
                    Recruit r = new Recruit(rs.getString("company"), rs.getString("job"), rs.getInt("money"), rs.getString("region"), rs.getString("phone"));
                    list.add(r);
                }
            }
            request.setAttribute("list", list);
            request.setAttribute("keyword", keyword);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/");
        } finally {
            // 모든 객체 close 해주기
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {}
            }

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
