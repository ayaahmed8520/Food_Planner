package search.allCategory.model;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import network.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import search.allCategory.presenter.CategoryPresenter;


public class AllCategoriesRepo {
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    @SuppressLint("CheckResult")
    public static void getAllCategories(){

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        ApiService apiService =retrofit.create(ApiService.class);

        Observable<CategoryResponse> call = apiService.getListAllCategories();

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        rootCategories -> {
                            CategoryPresenter.resultSuccess(rootCategories.getCategories());
                        },
                        error->{
                            CategoryPresenter.resultFailure(error.getMessage());
                        }
                );
    }
}
