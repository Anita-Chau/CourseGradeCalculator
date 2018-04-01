package cuhacking.coursegradecalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddCourse extends AppCompatActivity {

    private Button addButton;
    private Button cancelButton;
    private EditText courseNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        // Establishing a reference to the add button
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> addHandler());

        // Establishing a reference to the cancel button
        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> cancelHandler());

        // Establishing a reference to the name field
        courseNameField = findViewById(R.id.courseNameField);
    }

    //----------------------------------------------------------------------------------------------
    // EVENT HANDLERS
    //----------------------------------------------------------------------------------------------

    // Add Button handler
    private void addHandler()
    {
        // Checking if the course name field has text
        if (!courseNameField.getText().toString().equals(""))
        {
            // Creating the intent to go back to the course page and returning the class name
            Intent intent = new Intent(AddCourse.this, MainActivity.class);
            intent.putExtra("Course Name", courseNameField.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
        else
        {
            // Turning the text field red
            courseNameField.setHint("i.e. COMP 1406  *Required*");
            courseNameField.setHintTextColor(Color.rgb(255, 0, 0));
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
