package meal.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "singleMeal")
public class SingleMeal {
    @PrimaryKey
    @ColumnInfo(name = "idMeal")
    @NonNull
    private Long idMeal;
    @ColumnInfo(name = "strMeal")
    private String strMeal;
    @ColumnInfo(name = "strMealThumb")
    private String strMealThumb;

    public SingleMeal(Long idMeal, String strMeal, String strMealThumb) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }

    public SingleMeal(String strMeal, String strMealThumb) {

        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
    }

    public SingleMeal() {
    }

    public Long getIdMeal() { return idMeal; }

    public void setIdMeal(Long idMeal) { this.idMeal = idMeal; }
    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
}