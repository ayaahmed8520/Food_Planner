package dp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealDAO {
    @Query("SELECT * From MealDetails")
    Observable<List<MealDetails>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(MealDetails meal);

    @Delete
    void delete(MealDetails meal);

    @Query("SELECT * FROM MealDetails")
    List<MealDetails> getAllMealsForBackup();

    @Query("DELETE FROM MealDetails") // Replace with your table name
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMany(MealDetails... meals);
}