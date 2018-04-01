package cuhacking.coursegradecalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddAssessment extends AppCompatActivity {

    private Button addAssessmentButton;
    private Button cancelButton;
    private Spinner typeSpinner;
    private EditText nameField;
    private EditText weightField;
    private EditText gradeField;
    private TextView requiredText;
    private static String[] assessmentTypes = {"Assignment", "Essay", "Project", "Quiz", "Test", "Midterm", "Exam", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

        // Establishing a reference to the add button
        addAssessmentButton = findViewById(R.id.addAssessmentButton);
        addAssessmentButton.setOnClickListener(v -> addAssessmentHandler());

        // Establishing a reference to the cancel button
        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> cancelHandler());

        // Establishing a reference to the assessment type spinner
        typeSpinner = findViewById(R.id.typeSpinner);

        // Creating an adapter to change the listview when courses are added/removed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                assessmentTypes);
        typeSpinner.setAdapter(adapter);

        // Establishing a reference to the many text fields
        nameField = findViewById(R.id.nameField);
        weightField = findViewById(R.id.weightField);
        gradeField = findViewById(R.id.gradeField);

        // Establishing a reference to the required text
        requiredText = findViewById(R.id.requiredLabel);
    }

    //----------------------------------------------------------------------------------------------
    // EVENT HANDLERS
    //----------------------------------------------------------------------------------------------

    // Add button handler
    private void addAssessmentHandler()
    {
        // Checking if the course name field has text
        if (!weightField.getText().toString().equals("") && !nameField.getText().toString().equals(""))
        {
            // Creating the intent to go back to the course page and returning the class name
            Intent intent = new Intent(AddAssessment.this, CourseInformation.class);

            // Declaring the assessment object to be returned to the course page
            Assessment newAssessment;

            // Checking if the grade was included
            if (gradeField.getText().toString().equals(""))
                newAssessment = new Assessment(
                        nameField.getText().toString(),
                        typeSpinner.getSelectedItem().toString(),
                        Integer.parseInt(gradeField.getText().toString()));
            else
                newAssessment = new Assessment(
                        nameField.getText().toString(),
                        typeSpinner.getSelectedItem().toString(),
                        Integer.parseInt(weightField.getText().toString()),
                        Integer.parseInt(gradeField.getText().toString()));

            // Returning the created assessment
            intent.putExtra("Assessment", newAssessment);
            setResult(RESULT_OK, intent);
            finish();
        }
        else
        {
            // Turning the Required field red
            requiredText.setTextColor(Color.rgb(255, 0, 0));
        }
    }

    // Cancel Button Handler
    private void cancelHandler()
    {
        // Closing this activity and returning nothing
        setResult(RESULT_CANCELED);
        finish();
    }
}
