package controllers;

import models.Member;
import play.mvc.Controller;

public class MemberCtrl extends Controller
{
    public static void index()
    {
        Member member = Accounts.getLoggedInMember();
        render("member.html", member);
    }

    public static void updateDetails(String firstname, String lastname, String password){
        Member member = Accounts.getLoggedInMember();
        if((firstname.isEmpty()) || (lastname.isEmpty()) || (password.isEmpty())){
            render("memberfail.html", member);
        }
        else {
            member.firstname = firstname;
            member.lastname = lastname;
            member.password = password;
            member.save();
            render("member.html", member);
        }
    }
}