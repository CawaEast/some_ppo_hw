package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        HttpResponse resp = new HttpResponse(response);
        try {
            DBClient conn = new DBClient();
            switch (command) {
                case "max":
                    resp.writeBodyTable("Product with max price: ", conn.getMax());
                    break;
                case "min":
                    resp.writeBodyTable("Product with min price: ", conn.getMin());
                    break;
                case "sum":
                    resp.writeBodyLine("Summary price: " + conn.getSum());
                    break;
                case "count":
                    resp.writeBodyLine("Number of products: " + conn.getCount());
                    break;
                default:
                    resp.writeBodyLine("Unknown command: " + command);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.setOk();
    }

}
