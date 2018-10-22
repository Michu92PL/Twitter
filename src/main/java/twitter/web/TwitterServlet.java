package twitter.web;

import twitter.model.Dashboard;
import twitter.model.Tweet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterServlet extends HttpServlet {

    private Dashboard dashboard;

    public TwitterServlet(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tweet> tweetList = dashboard.load().collect(Collectors.toList());
        req.setAttribute("tweets",tweetList);
        req.getRequestDispatcher("twitter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String author = req.getParameter("author");
        dashboard.create(message,author);
        resp.sendRedirect("twitter");
    }
}
