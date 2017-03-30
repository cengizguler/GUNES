package com.example.cengiz.cengo;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionLibrary {

    ArrayList<soru> list = new ArrayList<>();


    private int mQuestions [] = {R.drawable.besportakal,R.drawable.cilek_dort,R.drawable.ucelma,R.drawable.dorterik ,R.drawable.uckasik,R.drawable.ikicatal,
                                 R.drawable.dort_tabak,R.drawable.uc_bardak,R.drawable.ikikayisi,R.drawable.besseftali,R.drawable.ikisandalye};


    private String mChoices [][] = {
            {"2", "1", "5"},
            {"2", "4", "1"},
            {"4", "3", "5"},
            {"1", "4", "2"},
            {"3", "5", "2"},
            {"5", "4", "2"},
            {"4", "1", "3"},
            {"3", "1", "5"},
            {"5", "2", "4"},
            {"5", "2", "1"},
            {"4", "1", "2"},
    };

    private String mCorrectAnswers[] = {"5", "4", "3", "4","3","2","4","3","2","5","2"};

    private String Metin[] = {
            "Kaç tane portakal var",
            "Kaç tane çilek var",
            "Kaç tane elma var",
            "Kaç tane erik var",
            "Kaç tane kaşık var",
            "Kaç tane çatal var",
            "Kaç tane tabak var",
            "Kaç tane bardak var",
            "Kaç tane kayısı var",
            "Kaç tane şeftali var",
            "Kaç tane sandalye var"
    };

    public QuestionLibrary() {
        doldur();
    }

    private void doldur() {
        for(int i = 0; i < 10; i++) {
            soru s = new soru(mQuestions[i], mChoices[i][0], mChoices[i][1], mChoices[i][2], mCorrectAnswers[i], Metin[i]);
            list.add(s);
        }
        Collections.shuffle(list);
    }

    public int getQuestion(int a) {
        int question = list.get(a).question;
        return question;
    }

    public String getChoice1(int a) {
        String choice0 = list.get(a).choice1;
        return choice0;
    }

    public String getChoice2(int a) {
        String choice1 = list.get(a).choice2;
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = list.get(a).choice3;
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = list.get(a).answer;
        return answer;
    }

    public String getMetin(int a){
        String metin_x = list.get(a).metin;
        return metin_x;
    }
}

class soru {

    int question;
    String choice1;
    String choice2;
    String choice3;
    String answer;
    String metin;

    soru(int question, String choice1, String choice2, String choice3, String answer, String metin) {
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.answer = answer;
        this.metin = metin;
    }
}