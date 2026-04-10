package com.quiz.model;
public class Result {
    private int id,studentId,totalQuestions,correctAnswers,wrongAnswers;
    private String studentName,subject,attemptDate; private double percentage;
    public Result(){}
    public Result(int studentId,String studentName,String subject,int total,int correct,int wrong,double pct){
        this.studentId=studentId;this.studentName=studentName;this.subject=subject;
        this.totalQuestions=total;this.correctAnswers=correct;this.wrongAnswers=wrong;this.percentage=pct;}
    public int getId(){return id;} public void setId(int i){id=i;}
    public int getStudentId(){return studentId;} public void setStudentId(int i){studentId=i;}
    public String getStudentName(){return studentName;} public void setStudentName(String n){studentName=n;}
    public String getSubject(){return subject;} public void setSubject(String s){subject=s;}
    public int getTotalQuestions(){return totalQuestions;} public void setTotalQuestions(int t){totalQuestions=t;}
    public int getCorrectAnswers(){return correctAnswers;} public void setCorrectAnswers(int c){correctAnswers=c;}
    public int getWrongAnswers(){return wrongAnswers;} public void setWrongAnswers(int w){wrongAnswers=w;}
    public double getPercentage(){return percentage;} public void setPercentage(double p){percentage=p;}
    public String getAttemptDate(){return attemptDate;} public void setAttemptDate(String d){attemptDate=d;}
}
