package weakPlan.model;

import android.content.Context;

import java.util.List;

import dp.AppDataBase;
import io.reactivex.rxjava3.core.Observable;
import weakPlan.dp.WeeklyPlanMeal;
import weakPlan.dp.WeeklyPlanMealDao;

public class WeekPlanMealRepo {
    private WeeklyPlanMealDao weeklyPlanMealDao;
    private static WeekPlanMealRepo weekPlanMealRepo = null;
    private Observable<List<WeeklyPlanMeal>> plannedMeals;


    private WeekPlanMealRepo(Context context){
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        weeklyPlanMealDao = db.getWeeklyPlanMealDao();
        plannedMeals = weeklyPlanMealDao.getAllMeals();
    }

    public static WeekPlanMealRepo getInstance(Context context){
        if(weekPlanMealRepo == null){
            weekPlanMealRepo = new WeekPlanMealRepo(context);
        }
        return weekPlanMealRepo;
    }

    public  void insertMeal(WeeklyPlanMeal weeklyPlanMeal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                weeklyPlanMealDao.insertAll(weeklyPlanMeal);
            }
        }).start();

    }


    public void deleteMeal(WeeklyPlanMeal weeklyPlanMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                weeklyPlanMealDao.delete(weeklyPlanMeal);
            }
        }).start();
    }


    public Observable<List<WeeklyPlanMeal>> getAllStoredMeals() {
        return plannedMeals;
    }
}


