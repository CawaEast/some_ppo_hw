package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpResponse resp = new HttpResponse(response);
        try {
            DBClient conn = new DBClient();
            resp.writeBodyTable(conn.getAll());
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.setOk();
    }
}
