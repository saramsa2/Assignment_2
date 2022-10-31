package nz.ac.unitec.cs.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;

import nz.ac.unitec.cs.assignment_2.DataModule.Category;
import nz.ac.unitec.cs.assignment_2.Rest.CategoryControllerRestAPI;
import nz.ac.unitec.cs.assignment_2.Rest.CategoryRestAPI;

public class EditTournamentActivity extends AppCompatActivity {

    EditText etName, etStartDate, etEndDate;
    Spinner spinCategory, spinDifficulty;

    private CategoryControllerRestAPI restapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tournament);



        etName = findViewById(R.id.et_edit_tournament_name);
        etStartDate = findViewById(R.id.et_edit_tournament_start);
        etEndDate = findViewById(R.id.et_edit_tournament_end);

        spinCategory = findViewById(R.id.spinner_edit_tournament_category);
        spinDifficulty = findViewById(R.id.spinner_edit_tournament_difficulty);

        initRestAPI();



    }

    private void initRestAPI() {
        restapi = new CategoryControllerRestAPI();
        restapi.start();

//        List<Category> categories = restapi.getCategoryList();
    }
}