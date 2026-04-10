package com.quiz.model;
public class Question {
    private int id; private String subject,questionText,optionA,optionB,optionC,optionD,correctAnswer;
    public Question(){}
    public Question(int id,String subject,String questionText,String a,String b,String c,String d,String correct){
        this.id=id;this.subject=subject;this.questionText=questionText;
        optionA=a;optionB=b;optionC=c;optionD=d;correctAnswer=correct;}
    public int getId(){return id;} public void setId(int i){id=i;}
    public String getSubject(){return subject;} public void setSubject(String s){subject=s;}
    public String getQuestionText(){return questionText;} public void setQuestionText(String q){questionText=q;}
    public String getOptionA(){return optionA;} public void setOptionA(String o){optionA=o;}
    public String getOptionB(){return optionB;} public void setOptionB(String o){optionB=o;}
    public String getOptionC(){return optionC;} public void setOptionC(String o){optionC=o;}
    public String getOptionD(){return optionD;} public void setOptionD(String o){optionD=o;}
    public String getCorrectAnswer(){return correctAnswer;} public void setCorrectAnswer(String a){correctAnswer=a;}
}
