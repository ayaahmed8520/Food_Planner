package favorite.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.lifecycle.LifecycleOwner;

import java.util.List;

import favorite.model.FavRepo;
import favorite.view.FavoriteFragmentInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import mealDetails.model.SingleMealDetails;

public class FavPresenter {
    static FavoriteFragmentInterface favoriteFragment;

    public static void addMeal(SingleMealDetails detailedMeal, Context context){
        FavRepo.getInstance(context).insertMeal(detailedMeal);
    }
    @SuppressLint("CheckResult")
    public static void getMeals(LifecycleOwner owner, Context context, FavoriteFragmentInterface favoriteFragmentInterface){
        favoriteFragment=favoriteFragmentInterface;
        Observable<List<SingleMealDetails>> myFavoriteMeals= FavRepo.getInstance(context).getAllStoredMeals();
        myFavoriteMeals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                            favoriteFragment.showData(meals);
                        },
                        throwable -> {
                            favoriteFragment.showDataFailed(throwable.getCause().getMessage());
                        }

                );

    }
    public static void removeFromFav(SingleMealDetails detailedMeal,Context context){
        FavRepo.getInstance(context).deleteMeal(detailedMeal);
    }
}
