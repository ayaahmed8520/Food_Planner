package favorite.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.lifecycle.LifecycleOwner;

import java.util.List;

import favorite.model.FavRepo;
import favorite.view.FavoriteFragmentIn;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import dp.MealDetails;

public class FavPresenter {
    static FavoriteFragmentIn favoriteFragment;

    public static void addMeal(MealDetails detailedMeal, Context context){
        FavRepo.getInstance(context).insertMeal(detailedMeal);
    }
    @SuppressLint("CheckResult")
    public static void getMeals(LifecycleOwner owner, Context context, FavoriteFragmentIn favoriteFragmentIn){
        favoriteFragment= favoriteFragmentIn;
        Observable<List<MealDetails>> myFavoriteMeals= FavRepo.getInstance(context).getAllStoredMeals();
        myFavoriteMeals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                            favoriteFragment.showMeals(meals);
                        },
                        throwable -> {
                            favoriteFragment.failedToDisplay(throwable.getCause().getMessage());
                        }

                );

    }
    public static void removeFromFav(MealDetails detailedMeal, Context context){
        FavRepo.getInstance(context).deleteMeal(detailedMeal);
    }
}
