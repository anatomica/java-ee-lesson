package ru.geekbrains;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Строка вида “/something/*” привязывает к сервлету все URL в которых на месте
// звездочки произвольный текст (“/something/first”, “/something/second”)
// Строка, которая начинается символами “*.” используется для привязки по
// расширению (аналогично расширению файла). Например шаблону “*.html” будут
// удовлетворять все URL, которые заканчиваются на “.html” (“/one.html”, “/one/two.html”,
// “/one/two/free.html”)
// Пустая строка привязывает сервлет к корневому URL веб-приложения.
// Строка из одного символа “/” привязывает сервлет ко всем URL приложения, для
// которых нет какой-либо другой привязки. По умолчанию к ней привязан т.н.
// default-сервлет. Данную привязку следует использовать с осторожностью, т.к.
// default-сервлет ответственен за привязку к URL статических ресурсов таких как css и
// js файлы. Если его подменить, то статические ресурсы (файлы) из папки webapp
// перестанут быть доступны.
// Все остальные строки будут проверяться на строгое соответствие.

@WebServlet(name = "FirstHttpServlet", urlPatterns = "/httpservlet/*")
public class FirstHttpServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(FirstHttpServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New GET request");
        getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
        //resp.getWriter().println("<h1>Hello from HTTP servlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New POST request");
        resp.getWriter().printf("<h1>New POST request</h1>");
    }

}
