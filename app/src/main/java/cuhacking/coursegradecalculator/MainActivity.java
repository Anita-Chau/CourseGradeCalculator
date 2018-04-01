package cuhacking.coursegradecalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button addCourseButton;
    private TextView averageLabel;
    private Semester currentSemester = new Semester();
    private int lastEditedCourse;

    private final int INFO_PAGE_RESULT = 1;
    private final int ADD_COURSE_RESULT = 2;
    private final String SAVE_LOCATION = "course-data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Establishing a link to the listview
        ListView courseListView = findViewById(R.id.courseListView);
        courseListView.setOnItemClickListener( (parent, view, position, id) -> {
            lastEditedCourse = position;
            listItemHandler((Course)parent.getItemAtPosition(position));
        });

        // Creating an adapter to change the listview when courses are added/removed
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                currentSemester.getCourses());
        courseListView.setAdapter(adapter);

        // Establishing a link to the add button
        addCourseButton = findViewById(R.id.addCourseButton);
        addCourseButton.setOnClickListener(v -> addCourseHandler());

        // Esablishing a link to the average label
        averageLabel = findViewById(R.id.averageLabel);
    }

    //----------------------------------------------------------------------------------------------
    // HELPER METHODS
    //----------------------------------------------------------------------------------------------

    // A method that updates the grade
    private void updateAverage()
    {
        String temp = String.format("Average Grade: %.02f", currentSemester.getAvg()) + "%";

        if (temp.equals("Average Grade: NaN%"))
            temp = "Average Grade: ___";

        averageLabel.setText(temp);
    }

    //----------------------------------------------------------------------------------------------
    // EVENT HANDLERS
    //----------------------------------------------------------------------------------------------

    // List item button
    private void listItemHandler(Course selectedCourse)
    {
        // Declaring the intent to open the course information page
        Intent intent = new Intent(MainActivity.this, CourseInformation.class);
        intent.putExtra("Course", selectedCourse);
        startActivityForResult(intent, INFO_PAGE_RESULT);
    }

    // Add Course button
    private void addCourseHandler()
    {
        // Declaring the intent to move to the add course page
        Intent intent = new Intent(MainActivity.this, AddCourse.class);
        startActivityForResult(intent, ADD_COURSE_RESULT);
    }

    // When a result is returned from the add course page
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Checking if the result was valid
        if (resultCode == RESULT_OK)
        {
            // Checking if the result was from the course information page
            if (requestCode == INFO_PAGE_RESULT)
            {
                // Replacing the sent off course with the new one
                currentSemester.setCourse(lastEditedCourse, (Course)data.getSerializableExtra("Course"));
            }

            // Checking if the result was from the add course page
            if (requestCode == ADD_COURSE_RESULT)
            {
                // Adding the new course to the semester
                currentSemester.addClass(new Course(data.getStringExtra("Course Name")));
            }
        }

        // Updating the onscreen average
        updateAverage();
    }
}
