package nz.ac.unitec.cs.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import nz.ac.unitec.cs.assignment_2.DataModule.Category;
import nz.ac.unitec.cs.assignment_2.Rest.CategoryControllerRestAPI;

public class AdminActivity extends AppCompatActivity {
    LinearLayout containerFirst, containerNew;
    Button btNewTournament, btTournamentList;
    EditText etName, etStartDate, etEndDate;
    Spinner spinCategory, spinDifficulty;

    Button btCreate, btCancel;

    Calendar calendar = Calendar.getInstance();
    private CategoryControllerRestAPI restapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        containerFirst = findViewById(R.id.container_admin_first);
        containerNew = findViewById(R.id.container_admin_new);

        btNewTournament = findViewById(R.id.bt_admin_new_tournament);
        btTournamentList = findViewById(R.id.bt_admin_tournament_list);

        etName = findViewById(R.id.et_edit_tournament_name);
        etStartDate = findViewById(R.id.et_edit_tournament_start);
        etEndDate = findViewById(R.id.et_edit_tournament_end);

        spinCategory = findViewById(R.id.spinner_edit_tournament_category);
        spinDifficulty = findViewById(R.id.spinner_edit_tournament_difficulty);

        btCreate = findViewById(R.id.bt_admin_create);
        btCancel = findViewById(R.id.bt_admin_cancel);

        initRestAPI();
        addEventListeners();
        setScreen("");

    }

    private void setScreen(String page) {
        LinearLayout.LayoutParams params;
        switch (page){
            case "first":
                params = (LinearLayout.LayoutParams) containerFirst.getLayoutParams();
                params.weight = 1;
                containerFirst.setLayoutParams(params);
                params = (LinearLayout.LayoutParams) containerNew.getLayoutParams();
                params.weight = 0;
                containerNew.setLayoutParams(params);
                break;
            case "create":
                params = (LinearLayout.LayoutParams) containerFirst.getLayoutParams();
                params.weight = 0;
                containerFirst.setLayoutParams(params);
                params = (LinearLayout.LayoutParams) containerNew.getLayoutParams();
                params.weight = 1;
                containerNew.setLayoutParams(params);
                break;
            default:
                params = (LinearLayout.LayoutParams) containerFirst.getLayoutParams();
                params.weight = 1;
                containerFirst.setLayoutParams(params);
                params = (LinearLayout.LayoutParams) containerNew.getLayoutParams();
                params.weight = 2;
                containerNew.setLayoutParams(params);
        }
        params = (LinearLayout.LayoutParams) containerFirst.getLayoutParams();
        params.weight = 1;
        containerFirst.setLayoutParams(params);
        params = (LinearLayout.LayoutParams) containerNew.getLayoutParams();
        params.weight = 2;
        containerNew.setLayoutParams(params);
    }

    private void addEventListeners() {
        btNewTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(AdminActivity.this, EditTournamentActivity.class);
//                startActivity(intent);
                List<Category> categories = restapi.getCategoryList();
                /// category spinner set

                if(categories != null) {
                    List<String> categoryName = new ArrayList<>();
                    List<Integer> categoryId = new ArrayList<>();
                    categoryId.add(0);
                    categoryName.add("Any Category");
                    for(Category cate: categories) {
                        categoryId.add(cate.getId());
                        categoryName.add(cate.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminActivity.this, android.R.layout.simple_spinner_item, categoryName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinCategory.setAdapter(adapter);

                }
            }
        });

        btTournamentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        DatePickerDialog.OnDateSetListener startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                String dateFormat = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
                etStartDate.setText(sdf.format(calendar.getTime()));
            }
        };
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dPDialog = new DatePickerDialog(AdminActivity.this, startDate, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                DatePicker dPicker = dPDialog.getDatePicker();
//                dPicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
                dPDialog.show();
            }
        });

        DatePickerDialog.OnDateSetListener endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                String dateFormat = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
                etEndDate.setText(sdf.format(calendar.getTime()));
            }
        };
        etEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dPDialog = new DatePickerDialog(AdminActivity.this, endDate, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                DatePicker dPicker = dPDialog.getDatePicker();
//                dPicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
                dPDialog.show();
            }
        });

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setScreen("first");
            }
        });
    }

    private void initRestAPI() {
        restapi = new CategoryControllerRestAPI();
        restapi.start();
    }
}