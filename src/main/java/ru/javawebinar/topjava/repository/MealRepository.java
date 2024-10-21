package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealRepository {
    private final List<Meal> meals = new ArrayList<>();

    public void add(Meal meal) {
        synchronized (meals) {
            meals.add(meal);
        }
    }

    public List<Meal> findAll() {
        synchronized (meals) {
            return meals;
        }
    }

    public Meal findById(long id) {
        synchronized (meals) {
            return meals.stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);
        }
    }

    public void delete(long id) {
        synchronized (meals) {
            meals.removeIf(meal -> meal.getId() == id);
        }
    }

    public void update(Meal meal) {
        synchronized (meals) {
            meals.set(meals.indexOf(findById(meal.getId())), meal);
        }
    }
}
