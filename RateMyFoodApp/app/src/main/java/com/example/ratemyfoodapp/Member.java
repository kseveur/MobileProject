package com.example.ratemyfoodapp;

public class Member {


    String Firstname;
    String Lastname;
    String Login;
    String PhoneNumber;

    public Member() {

    }

    public Member(String first, String lname, String loginId, String phone) {
        this.Firstname = first;
        this.Lastname = lname;
        this.Login = loginId;
        this.PhoneNumber = phone;
    }

    public String getFname() {
        return Firstname;
    }

    public String getLname() {
        return Lastname;
    }

    public String getLoginId() {
        return Login;
    }


    public String getPhone() {
        return PhoneNumber;
    }


    public void setFirst(String first) {
        Firstname = first;
    }

    public void setLast(String lname) {
        Lastname = lname;
    }

    public void setTheLogin(String loginID) {
        Login = loginID;
    }

    public void setPhoneNum(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
