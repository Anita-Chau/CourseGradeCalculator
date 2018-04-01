package cuhacking.coursegradecalculator;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable
{
    private String courseName;
    private ArrayList<Assessment> assessments; //list of evaluations for this course
    private float currentCourseGrade; //current course grade in (%)
    private float desiredGrade;

    public Course (String name)
    {
        this.courseName = name;
        assessments = new ArrayList<>();
    }

    //----------------------------------------------------------------------------------------------
    // HELPER METHODS
    //----------------------------------------------------------------------------------------------

    // A method that re-calculates the grade
    private void updateGrade()
    {
        float totalMarks = 0;
        float totalWeights = 0;

        for(Assessment a : assessments){
            totalMarks += a.getGrade() * a.getWeight();
            totalWeights += a.getWeight();
        }

        System.out.println("MARKS: " + totalMarks + " WEIGHTS: ");
        currentCourseGrade = (totalMarks/totalWeights);
    }

//    public float gradeNeeded(float wantedGrade){
//        float neededGrade = 0;
//        currentGrade(); //ensures currentCourseGrade is updated correctly
//        float remainingWeight = 0;
//        float totalWeights = 0;
//        //calculate totalWeight to obtain remaining weight
//        for(Assessment e : evalList){
//            totalWeights += e.getWeight();
//        }
//        remainingWeight = 1 - totalWeights;
//        neededGrade = (wantedGrade-(currentCourseGrade*totalWeights))/remainingWeight;
//        return neededGrade;
//    }

    // Add an assessment to the course
    public void newAssessment(Assessment e) { assessments.add(e); }

    // Remove an assessment from the list
    public void removeAssessment(Assessment e){ assessments.remove(e); }

    //----------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    //----------------------------------------------------------------------------------------------

    public String getCourseName() {
        return courseName;
    }
    public float getCurrentCourseGrade() { updateGrade(); return currentCourseGrade; }
    public String toString(){
        return this.courseName;
    }
    public ArrayList<Assessment> getAssessments() { return assessments; }
}