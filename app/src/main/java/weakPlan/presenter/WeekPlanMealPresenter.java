package weakPlan.presenter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import weakPlan.view.WeekPlanInterface;
import weakPlan.dp.WeeklyPlanMeal;
import weakPlan.model.WeekPlanMealRepo;

public class WeekPlanMealPresenter {

    static WeekPlanInterface weekPlanInterface;

    public static void addMeal(WeeklyPlanMeal weeklyPlanMeal, Context context){

        WeekPlanMealRepo.getInstance(context).insertMeal(weeklyPlanMeal);
    }

    @SuppressLint("CheckResult")
    public static void getMeals(LifecycleOwner owner, Context context, WeekPlanInterface weekPlanIn) {

        weekPlanInterface= weekPlanIn;

        Observable<List<WeeklyPlanMeal>> myPlannedMeals= WeekPlanMealRepo.getInstance(context).getAllStoredMeals();

        myPlannedMeals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                            weekPlanInterface.showPlannedMeals(meals);
                        },
                        throwable -> {
                            weekPlanInterface.failedToDisplayPlanMeals(throwable.getCause().getMessage());
                        }

                );

    }
    public static void removeFromPlan(WeeklyPlanMeal weeklyPlanMeal, Context context){
        WeekPlanMealRepo.getInstance(context).deleteMeal(weeklyPlanMeal);
    }
}
