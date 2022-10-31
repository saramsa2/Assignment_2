package nz.ac.unitec.cs.assignment_2.Rest;

import java.util.List;

import nz.ac.unitec.cs.assignment_2.DataModule.Categories;
import nz.ac.unitec.cs.assignment_2.DataModule.Category;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryControllerRestAPI implements retrofit2.Callback<Categories>{
    final String BASE_URL = "https://opentdb.com/";
//    private Categories categories;
    private List<Category> categoryList;

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
            Categories categories = response.body();
            categoryList = categories.getCategories();
            if(categoryList != null) {
                for(Category cate : categoryList) {

                }
            }
            else {

            }
        }
    }

    @Override
    public void onFailure(Call<Categories> call, Throwable t) {
        t.printStackTrace();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
