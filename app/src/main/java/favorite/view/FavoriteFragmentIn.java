package favorite.view;

import java.util.List;

import dp.MealDetails;

public interface FavoriteFragmentIn {
    public void showMeals(List<MealDetails> meals);
    public void failedToDisplay(String error);
    public void removeMeal(MealDetails meal);

}
