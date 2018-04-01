package cuhacking.coursegradecalculator;

import java.io.Serializable;

public class Assessment implements Serializable
{
    private String name;
    private String type;
    private float grade;
    private float weight;

    public Assessment(String _name, String _type, float _weight)
    {
        name = _name;
        type = _type;
        weight = _weight;
    }

    public Assessment(String _name, String _type, float _weight, float _grade)
    {
        this(_name, _type, _weight);
        grade = _grade;
    }

    //public int compareTo(Assessment a {return (this.name.compareTo(a.name));}

    //----------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    //----------------------------------------------------------------------------------------------

    public String getName() { return toString(); }
    public String toString() { return name; }
    public float getGrade() {
        return grade;
    }
    public float getWeight() { return weight; }
}
