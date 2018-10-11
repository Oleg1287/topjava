package ru.javawebinar.topjava.dao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.model.Meal;

public class DaoMealImpl implements DaoMeal{
    private LocalTime startTime = LocalTime.of(7, 0);
    private LocalTime endTime = LocalTime.of(23, 0);
    private List<Meal> meals;

    public DaoMealImpl() {
        meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
    }

    public List<MealWithExceed> getAllMeals(){
        return MealsUtil.getFilteredWithExceeded(meals, startTime, endTime, 2000);
    }

    public void delete(Meal meal) {
        meals = meals.stream()
                .filter(m -> !m.equals(meal))
                .collect(Collectors.toList());
    }

    public Meal get(Integer id){
        return meals.get(id);
    }
}
