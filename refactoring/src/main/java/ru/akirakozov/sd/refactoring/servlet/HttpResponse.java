package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cawa on 21.10.2017.
 */
public class HttpResponse {
    private HttpServletResponse response;
    private PrintWriter w;

    HttpResponse(HttpServletResponse r)throws IOException{
        response = r;
        response.setContentType("text/html");
        w = response.getWriter();
    }

    void openBody() throws IOException{
        HTMLWriter.openBody(w);
    }

    void putVals(List<String> list) throws IOException{
        HTMLWriter.putLine(w, list);
    }

    void closeBody() throws IOException{
        HTMLWriter.closeBody(w);
    }

    void setOk(){
        response.setStatus(HttpServletResponse.SC_OK);
    }

    void writeOk() throws IOException{
        response.getWriter().println("OK");
        setOk();
    }

    void writeBodyTable(LinkedList<LinkedList<String>> list) throws IOException {
        openBody();
        for (LinkedList<String> vals : list) {
            putVals(vals);
        }
        closeBody();
    }

    void writeBodyTable(String header, LinkedList<LinkedList<String>> list) throws IOException {
        openBody();
        HTMLWriter.putHeader(w, header);
        for (LinkedList<String> vals : list) {
            putVals(vals);
        }
        closeBody();
    }

    void writeBodyLine(String line) throws IOException {
        openBody();
        w.print(line);
        closeBody();
    }
}
