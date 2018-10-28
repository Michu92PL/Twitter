package twitter.web;

import twitter.db.JPAAuthorRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private JPAAuthorRepository rep;

    public LoginServlet(JPAAuthorRepository rep) {
        this.rep = rep;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");

        if (rep.validateUser(user, pass)) {
            HttpSession session = req.getSession();
            session.setAttribute(SessionKeys.CURRENT_USER, user);
            resp.sendRedirect("/twitter");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
