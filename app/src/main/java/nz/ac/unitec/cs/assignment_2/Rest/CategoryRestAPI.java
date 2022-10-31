package nz.ac.unitec.cs.assignment_2.Rest;

import nz.ac.unitec.cs.assignment_2.DataModule.Categories;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryRestAPI {
    @GET("api_category.php")
    Call<Categories> getCategories();
}
