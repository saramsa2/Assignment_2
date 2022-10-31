package nz.ac.unitec.cs.assignment_2.Rest;

import android.view.View;

import java.util.List;

import nz.ac.unitec.cs.assignment_2.DataModule.Categories;
import nz.ac.unitec.cs.assignment_2.DataModule.Category;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryControllerRestAPI implements retrofit2.Callback<Categories>{
    final String BASE_URL = "https://opentdb.com/";
    private Categories categories;
//    private List<Category> categoryList;
    private readCategoriesListeners readEventListener = null;

    public void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CategoryRestAPI categoryRestAPI = retrofit.create(CategoryRestAPI.class);
        Call<Categories> call = categoryRestAPI.getCategories();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Categories> call, Response<Categories> response) {
        if(response.isSuccessful()) {
            categories = response.body();
            readEventListener.readSucceed(categories);
//            categoryList = categories.getCategories();
//            if(categoryList != null) {
//                for(Category cate : categoryList) {
//
//                }
//            }
//            else {
//
//            }
        }
    }

    @Override
    public void onFailure(Call<Categories> call, Throwable t) {
        t.printStackTrace();
    }

    public Categories  getCategories() {
        return categories;
    }

    public void setReadCategoriesListeners(readCategoriesListeners readEventListener) {
        this.readEventListener = readEventListener;
    }

    public interface readCategoriesListeners {
        void readSucceed(Categories categories);
    }
}
