package weakPlan.WeeklyPlanMealDetails.model;

public class WeeklyPlanDetailsIngredientMeasure {

    private final String weeklyPlanIngredientName;
    private final String weeklyPlanIngredientMeasure;

    public WeeklyPlanDetailsIngredientMeasure(String weeklyPlanIngredientName, String weeklyPlanIngredientMeasure) {
        this.weeklyPlanIngredientName = weeklyPlanIngredientName;
        this.weeklyPlanIngredientMeasure = weeklyPlanIngredientMeasure;
    }

    public String getWeeklyPlanIngredientName() {
        return weeklyPlanIngredientName;
    }

    public String getWeeklyPlanIngredientMeasure() {
        return weeklyPlanIngredientMeasure;
    }
}
