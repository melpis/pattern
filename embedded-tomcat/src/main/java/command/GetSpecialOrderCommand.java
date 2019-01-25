package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSpecialOrderCommand implements Command {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("info","special");
    }
}
