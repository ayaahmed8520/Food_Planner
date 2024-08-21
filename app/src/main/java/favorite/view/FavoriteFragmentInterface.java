package favorite.view;

import java.util.List;

import mealDetails.model.SingleMealDetails;

public interface FavoriteFragmentInterface {
    public void showData(List<SingleMealDetails> meals);
    public void showDataFailed(String error);
    public void removeMeal(SingleMealDetails meal);

}
