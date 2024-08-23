package favorite.model;

import android.content.Context;


import java.util.List;

import dp.AppDataBase;
import dp.MealDAO;


import io.reactivex.rxjava3.core.Observable;
import dp.MealDetails;

public class FavRepo {
    private MealDAO dao;
    private static FavRepo favRepo = null;
    private Observable<List<MealDetails>> storedMeals;


    private FavRepo(Context context){
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        dao = db.mealDAO();
        storedMeals = dao.getAllMeals();
    }

    public static FavRepo getInstance(Context context){
        if(favRepo == null){
            favRepo = new FavRepo(context);
        }
        return favRepo;
    }

    public  void insertMeal(MealDetails detailedMeal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(detailedMeal);
            }
        }).start();

    }


    public void deleteMeal(MealDetails detailedMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delete(detailedMeal);
            }
        }).start();
    }


    public Observable<List<MealDetails>> getAllStoredMeals() {
        return storedMeals;
    }
}
