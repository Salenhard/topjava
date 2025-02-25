package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateFormater;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealServlet extends HttpServlet {
    private final MealRepository mealRepository = new MealRepository();
    private static final long MEAL_UUID = 1L;
    private static final String LIST_MEALS = "/meals.jsp";
    private static final DateFormater DateFormater = new DateFormater();

    {
        initMealsList();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        request.setAttribute("sdf", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                mealRepository.save(new Meal(LocalDateTime.now(), "Завтрак", 500));
                request.setAttribute("meals", MealsUtil.getFilteredWithExcess(mealRepository.findAll(), LocalTime.MIN, LocalTime.MAX, 2000));
                response.sendRedirect("meals");
                return;
            case "delete":
                mealRepository.delete(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("meals");
                return;
            case "update":
                forward = LIST_MEALS;
                Meal meal = mealRepository.findById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("meal", meal);
                break;
            default:
                forward = LIST_MEALS;
                request.setAttribute("meals", MealsUtil.getFilteredWithExcess(mealRepository.findAll(), LocalTime.MIN, LocalTime.MAX, 2000));
                break;
        }


        request.getRequestDispatcher(forward).forward(request, response);
    }

    public void initMealsList() {

    }

}
