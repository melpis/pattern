package servlet;

import command.Command;
import util.StringUtils;

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
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String requestURL = req.getRequestURI();
        String method = req.getMethod().toLowerCase();
        String url = requestURL.substring(1);
        HandlerMapping handlerMapping = new HandlerMapping(method, url);
        final Command command = handlerMapping.getHandler();
        if (command != null) {
            HandlerAdapter handlerAdapter = new HandlerAdapter();
            handlerAdapter.handle(req, resp, command);
            viewResolver(req, resp, url);
        }
    }

    private void viewResolver(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/" + url + ".jsp").forward(req, resp);
    }


    private class HandlerMapping {
        private String method;
        private String url;

        public HandlerMapping(String method, String url) {
            this.method = method;
            this.url = StringUtils.convertUrl(url);
        }

        public Command getHandler() {
            Command command = null;
            try {
                command = (Command) Class.forName("command." + StringUtils.firstCharacterUpper(method) + url + "Command").newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return command;
        }
    }

    private class HandlerAdapter {
        public void handle(HttpServletRequest req, HttpServletResponse resp, Command command) {
            command.service(req, resp);
        }
    }
}