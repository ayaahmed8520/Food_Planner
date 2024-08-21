package favorite.model;

import android.content.Context;


import java.util.List;

import dp.AppDataBase;
import dp.MealDAO;


import io.reactivex.rxjava3.core.Observable;
import mealDetails.model.SingleMealDetails;

public class FavoriteRepository {
    private MealDAO dao;
    private static FavoriteRepository favoriteRepository = null;
    private Observable<List<SingleMealDetails>> storedMeals;


    private FavoriteRepository(Context context){
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        dao = db.mealDAO();
        storedMeals = dao.getAllMeals();
    }

    public static FavoriteRepository getInstance(Context context){
        if(favoriteRepository == null){
            favoriteRepository = new FavoriteRepository(context);
        }
        return favoriteRepository;
    }

    public  void insertMeal(SingleMealDetails detailedMeal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(detailedMeal);
            }
        }).start();

    }


    public void deleteMeal(SingleMealDetails detailedMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delete(detailedMeal);
            }
        }).start();
    }


    public Observable<List<SingleMealDetails>> getAllStoredMeals() {
        return storedMeals;
    }
}
