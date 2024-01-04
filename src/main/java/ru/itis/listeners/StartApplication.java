package ru.itis.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import ru.itis.repository.DBConnection;
import ru.itis.service.VisitorService;


@WebListener
public class StartApplication implements ServletContextListener {

    private DBConnection dbConnection;

    public void contextInitialized(ServletContextEvent sce) {
        DBConnection dbConnection = DBConnection.getInstance();
        sce.getServletContext().setAttribute("VisitorService",new VisitorService());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        dbConnection.destroyConnection();
    }
}