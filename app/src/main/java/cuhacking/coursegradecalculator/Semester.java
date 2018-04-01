package cuhacking.coursegradecalculator;

import java.io.File;
import java.util.ArrayList;

public class Semester
{
    private ArrayList<Course> courses;
    private float semesterAvg;
    private File saveData;

    public Semester()
    {
        courses = new ArrayList<>();

        // TODO: Retrieve saved data
    }

    //----------------------------------------------------------------------------------------------
    // INSTANCE METHODS
    //----------------------------------------------------------------------------------------------

    // A method for adding a class
    public void addClass(Course classToAdd)
    {
        // Adding the given class
        courses.add(classToAdd);

        // Recalculating the average for this semester
        calcAvg();

        // TODO: Save new data
    }

    //----------------------------------------------------------------------------------------------
    // HELPER METHODS
    //----------------------------------------------------------------------------------------------

    // A method that recalculates the average
    private void calcAvg()
    {
        // Looping through the courses
        float totalGrade = 0;
        for (Course aClass : courses)
        {
            totalGrade += aClass.getCurrentCourseGrade();
        }

        // Making the calculation
        semesterAvg = (totalGrade/courses.size());
    }

    //----------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    //----------------------------------------------------------------------------------------------

    public float getAvg() { calcAvg(); return semesterAvg; }
    public ArrayList<Course> getCourses() { return courses; }
    public ArrayList<Float> getCourseGrades()
    {
        ArrayList<Float> grades = new ArrayList<>();
        for (Course course : courses)
        {
            grades.add(course.getCurrentCourseGrade());
        }
        return grades;
    }

    public void setCourse(int position, Course newCourse) { courses.set(position, newCourse); }
}
