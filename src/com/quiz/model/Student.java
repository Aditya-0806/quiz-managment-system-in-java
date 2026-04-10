package com.quiz.model;
public class Student {
    private int id; private String name,email,password,phone,dob;
    public Student(){}
    public Student(int id,String name,String email,String password,String phone,String dob){
        this.id=id;this.name=name;this.email=email;this.password=password;this.phone=phone;this.dob=dob;}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
    public String getPassword(){return password;} public void setPassword(String p){this.password=p;}
    public String getPhone(){return phone;} public void setPhone(String p){this.phone=p;}
    public String getDob(){return dob;} public void setDob(String d){this.dob=d;}
}
