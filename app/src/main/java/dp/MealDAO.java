package dp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import mealDetails.model.SingleMealDetails;

@Dao
public interface MealDAO {
    @Query("SELECT * From MealDetails")
    Observable<List<SingleMealDetails>> getAllMeals();

//    @Query("SELECT * FROM singleMeal WHERE name LIKE :first " + "LIMIT 1")
//    Movie findMovieByName(String first);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(SingleMealDetails meal);

    @Delete
    void delete(SingleMealDetails meal);
}