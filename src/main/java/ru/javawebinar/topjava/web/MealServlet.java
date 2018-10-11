package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.dao.DaoMealImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final DaoMeal dao;

    public MealServlet() {
        super();
        dao = new DaoMealImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("users.jsp");
//        super.doGet(req, resp);
        String action = getAction(req);

        switch (action) {
            case ("list"):
                list(req, resp);
                break;
            case ("delete"):
                delete(req, resp);
                break;
            case ("edit"):
                edit(req, resp);
                break;
            default:
                list(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        switch (action) {
            case ("edit"):
                editMeal(req, resp);
                break;
            default:
                resp.sendRedirect("/topjava/meal");
        }
//        super.doPost(req, resp);
    }

    private void editMeal(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("mealId"));
        String description = req.getParameter("description");
        Integer calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal = dao.get(id);
        meal.setDescription(description);
        meal.setCalories(calories);
        log.debug("dispatcherPost:editMeal");
        resp.sendRedirect("/topjava/meal");
    }

    private String getAction(HttpServletRequest req) throws UnsupportedEncodingException {
        String path = req.getRequestURI();
        String action = path.substring(path.lastIndexOf("/") + 1);
        log.debug("action:" + action);
        req.setCharacterEncoding("UTF-8");
        return action;
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("meals", dao.getAllMeals());
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = Integer.parseInt(req.getParameter("mealId"));
        req.setAttribute("mealId", id);
        req.setAttribute("meal", dao.get(id));
        req.getRequestDispatcher("/mealEdit.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = Integer.parseInt(req.getParameter("mealId"));
        log.debug("Id:" + id);
        Meal meal = dao.get(id);
        dao.delete(meal);
        resp.sendRedirect("/topjava/meal");
    }
}
