package twitter.web;

import twitter.db.JPAAuthorRepository;
import twitter.db.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/twitter")
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //EntityManagerFactorySingleton ems = new EntityManagerFactorySingleton();
        EntityManager entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
        JPAAuthorRepository rep = new JPAAuthorRepository(entityManager);

        String user = req.getParameter("user");
        String pass = req.getParameter("pass");

        if (rep.validateUser(user, pass)) {
            HttpSession session = req.getSession();
            session.setAttribute("author", user);
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("login.jsp");
        }
    }

}
