package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "dispatch",
        urlPatterns = {"/"}
)
public class DispatcherServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURL = req.getRequestURI();
        String url = requestURL.substring(1);
        HttpServlet httpServlet = null;
        if ("order".equalsIgnoreCase(url)) {
            httpServlet = new OrderServlet();
        } else if("special/order".equalsIgnoreCase(url)) {
            httpServlet = new SpecialOrderServlet();
        }
        if(httpServlet != null){
            httpServlet.service(req,resp);
            req.getRequestDispatcher("/WEB-INF/"+url+".jsp").forward(req, resp);
        }
    }
}