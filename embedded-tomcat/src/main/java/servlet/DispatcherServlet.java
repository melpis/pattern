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
        try {
            httpServlet = handlerMapping(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(httpServlet != null){
            httpServlet.service(req,resp);
            viewResolver(req, resp, url);
        }
    }

    private void viewResolver(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/"+url+".jsp").forward(req, resp);
    }

    private HttpServlet handlerMapping(String url) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String[] urlToken = url.split("\\/");
        if(url.contains("/")){
            StringBuilder stringBuilder = new StringBuilder();
            for(String className:urlToken){
                stringBuilder.append(className.substring(0, 1).toUpperCase()).append(className.substring(1));
            }
            url = stringBuilder.toString();
        }else{
            url = url.substring(0,1).toUpperCase()+url.substring(1);
        }

        return (HttpServlet)Class.forName("servlet."+url+"Servlet").newInstance();
    }
}