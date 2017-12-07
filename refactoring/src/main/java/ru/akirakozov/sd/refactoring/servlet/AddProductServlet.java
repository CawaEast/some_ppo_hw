package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));
        try {
            DBClient conn = new DBClient();
            conn.put(name, price);
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HttpResponse resp = new HttpResponse(response);
        resp.writeOk();
    }
}
