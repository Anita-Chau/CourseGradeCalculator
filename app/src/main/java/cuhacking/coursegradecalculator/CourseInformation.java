package cuhacking.coursegradecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CourseInformation extends AppCompatActivity {

    private Button addAssessmentButton;
    private TextView gradeLabel;
    private Course courseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_information);

        // Establishing a reference to the add assessment button
        addAssessmentButton = findViewById(R.id.addAssessmentButton);
        addAssessmentButton.setOnClickListener(v -> addAssessmentHandler());

        // Retrieving the selected course
        courseInfo = (Course)getIntent().getSerializableExtra("Course");

        // Establishing a reference to the listview
        ListView assessmentList = findViewById(R.id.assessmentList);
        //assessmentList.setOnItemLongClickListener((parent, view, position, id) -> listItemLongPressHandler((Assessment)parent.getItemAtPosition(position)));

        // Creating an adapter to change the listview when courses are added/removed
        ArrayAdapter<Assessment> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                courseInfo.getAssessments());
        assessmentList.setAdapter(adapter);

        // Establishing a reference to the grade text
        gradeLabel = findViewById(R.id.gradeLabel);

        // Updating the grade text
        updateGrade();
    }

    //----------------------------------------------------------------------------------------------
    // HELPER METHODS
    //----------------------------------------------------------------------------------------------

    // A method that updates the grade
    private void updateGrade()
    {
        String temp = String.format("Average Grade: %.02f", courseInfo.getCurrentCourseGrade()) + "%";

        if (temp.equals("Course Grade: NaN%"))
            temp = "Course Grade: ___";

        gradeLabel.setText(temp);
    }

    //----------------------------------------------------------------------------------------------
    // EVENT HANDLERS
    //----------------------------------------------------------------------------------------------

    // List item button
    private boolean listItemLongPressHandler(Assessment selectedAssessment)
    {
        // Declaring the intent to open the course information page
        Intent intent = new Intent(CourseInformation.this, CourseInformation.class);
        intent.putExtra("Assessment", selectedAssessment);
        startActivity(intent);
        return true;
    }

    // Add Assessment Handler
    private void addAssessmentHandler()
    {
        // Declaring the intent to move to the add assessment page
        Intent intent = new Intent(CourseInformation.this, AddAssessment.class);
        startActivityForResult(intent, 1);
    }

    // When a result is returned from the add course page
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Checking if the result was valid
        if (resultCode == RESULT_OK)
            courseInfo.newAssessment((Assessment)data.getSerializableExtra("Assessment"));

        // Updating the grade text
        updateGrade();
    }

    // When the activity is sent back to the course selection screen
    @Override
    public void onBackPressed()
    {
        // Returning the edited course object
        Intent intent = new Intent(CourseInformation.this, MainActivity.class);
        intent.putExtra("Course", courseInfo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
