package ru.akirakozov.sd.refactoring.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by Cawa on 21.10.2017.
 */
public class HTMLWriter {

    public static void putLine(Writer w, List<String> list) throws IOException {Boolean first = true;
        for (String s : list) {
            if (first) {
                w.write(s);
                first = false;
            } else {
                w.write( "\t" + s);
            }
        }
        w.write("</br>");
    }

    public static void putHeader(Writer w, String header) throws IOException{
        w.write("<h1>" + header +  "</h1>");
    }

    public static void openBody(Writer w) throws IOException{
        w.write("<html><body>");
    }

    public static void closeBody(Writer w) throws IOException{
        w.write("</html></body>");
    }
}
