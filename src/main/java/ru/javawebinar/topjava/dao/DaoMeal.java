package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface DaoMeal {
    public List<MealWithExceed> getAllMeals();
    public void delete(Meal meal);
    public Meal get(Integer id);
}
