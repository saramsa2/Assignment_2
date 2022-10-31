package nz.ac.unitec.cs.assignment_2.DataModule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {
    @SerializedName("trivia_categories")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
