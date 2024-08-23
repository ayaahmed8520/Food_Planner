package weakPlan.dp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "Weekly_plan" , primaryKeys = {"date", "mealType"})
public class WeeklyPlanMeal {


    @ColumnInfo(name = "idMeal")
    private String idMeal;

    @NonNull
    @ColumnInfo(name = "date")
    String date;

    @NonNull
    @ColumnInfo(name = "mealType")
    String mealType;

    @ColumnInfo(name = "mealName")
    String mealName;

    @ColumnInfo(name = "mealImage")
    String mealThump;


    public WeeklyPlanMeal(String idMeal, @NonNull String date, @NonNull String mealType, String mealName, String mealThump) {
        this.idMeal = idMeal;
        this.date = date;
        this.mealType = mealType;
        this.mealName = mealName;
        this.mealThump = mealThump;
    }



    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getMealType() {
        return mealType;
    }

    public void setMealType(@NonNull String mealType) {
        this.mealType = mealType;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealThump() {
        return mealThump;
    }

    public void setMealThump(String mealThump) {
        this.mealThump = mealThump;
    }
}
