package com.tlu.mathapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import androidx.appcompat.app.AppCompatActivity;


public class Game extends AppCompatActivity {
    private int gameLevel = 3; // See, mitmendast levelist alustad, hetkel LVL 3. Kui on 3, on kaks arvu, kui on 5 - 3 arvu, 7 - 4 arvu.
    private String calAnswer; // Õige vastus

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button genBtn1, genBtn2, genBtn3;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game); // Sätestab layouti
        newRandom(); // Alguses lisab juba ühe värgi
        genBtn1 = (Button) findViewById(R.id.answer1);
        genBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        genBtn2 = (Button) findViewById(R.id.answer2);
        genBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });

        genBtn3 = (Button) findViewById(R.id.answer3);
        genBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });
    }

    private void checkAnswer(int btnId){
        String quessedAnswer = getButtonText(btnId);
        if(calAnswer.equals(quessedAnswer)){
            newRandom();
        } else {
            setCalcText("VALE! Proovi uuesti!");
        }
    }
    private void newRandom() { // funktsioon, mis käivitub pärast vajutamist
        String[] calculation = this.returnArray(gameLevel); // Tekitab array gamelevel kohaga. See vajalik pärast uute tasemete lisamiseks. Gamelevel on 3, siis tagastatakse kolmekohaline array
        //double calAnswer = this.getCorrectAnswer(calculation); // Arvutab välja õige väärtuse
        StringBuilder sum = new StringBuilder(); // Siia liidetakse stringidena arvud ja märgid
        for (String calc: calculation) {
            sum.append(calc);
        }
        calAnswer = getCorrectAnswer(sum.toString());
        String calAnswerWrong1 = getWrongAnswer(calAnswer); // Arvutab randomilt õigele vastusele midagi juurde või maha, et tulemused oleks suht samas kandis, et oleks raskem pakkuda huupi
        String calAnswerWrong2 = getWrongAnswer(calAnswer);
        boolean done = false; // while tsükkli jaoks
        while (!done) {
            if((calAnswerWrong1.equals(calAnswerWrong2)) || (calAnswerWrong1.equals(calAnswer)) || (calAnswerWrong2.equals(calAnswer))){ // Tingimus, et ükski arv ei ühtiks üksteisega, tuleks võibolla lisada veel infinity jne ka.
                calAnswerWrong1 = getWrongAnswer(calAnswer);
                calAnswerWrong2 = getWrongAnswer(calAnswer);
            } else {
                done = true;
            }
        }
        setCalcText(sum.toString());
        String [] shuffledVastused = new String[] {calAnswer, calAnswerWrong1, calAnswerWrong2 };
        Collections.shuffle(Arrays.asList(shuffledVastused));
        setButtonsText(shuffledVastused[0], shuffledVastused[1], shuffledVastused[2]);
    }

    private int randomNumber(double max)
    {
        double rand = (int)(Math.random()*((max-1)+1))+1;
        return (int)rand;
    }
    private String[] returnArray(int nr){
        String[] arr = new String[nr];
        for (int i = 0; i < nr; i++) {
            if((i % 2) == 0){ // paarisarv kohtadele lisan mingi arvu vahemikus 1-10
                arr[i] = String.valueOf(randomNumber(10)); //suvalise arvu random
            } else { // paarituarvu kohale arrays lisan tehtemärgi numbri(1 - + , 2 - - , 3 - * )
                int rand = randomNumber(3);//tehtemärgi random
                if(rand == 1){arr[i] = "+";}
                else if(rand == 2){arr[i] = "-";}
                else if(rand == 3){arr[i] = "*";}
            }
        }
        return arr;
    }
    private String getCorrectAnswer(String ex) {
        Object result = new Object();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            result = engine.eval(ex);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    private String getWrongAnswer(String correctAnswer) {
        double sum = 0;
        int random = this.randomNumber(3);
        if(random == 1){sum = Double.parseDouble(correctAnswer) + randomNumber(10);}
        else if(random == 2){sum = Double.parseDouble(correctAnswer) - randomNumber(10);}
        else if(random == 3){sum = Double.parseDouble(correctAnswer) * randomNumber(10);}
        return String.valueOf(sum);
    }

    //Getters & Setters
    private void setCalcText(String s){
        final TextView calc = (TextView)findViewById(R.id.calc);
        calc.setText(s);
    }
    private void setButtonsText(String button1, String button2, String button3){
        final TextView answer1 = (TextView)findViewById(R.id.answer1);
        answer1.setText(button1);
        final TextView answer2 = (TextView)findViewById(R.id.answer2);
        answer2.setText(button2);
        final TextView answer3 = (TextView)findViewById(R.id.answer3);
        answer3.setText(button3);
    }
    private String getButtonText(int id){
        String txt = "";
        if(id == 1) {
            final TextView answer1 = (TextView) findViewById(R.id.answer1);
            txt = (String) answer1.getText();
        } else if(id == 2){
            final TextView answer2 = (TextView) findViewById(R.id.answer2);
            txt = (String) answer2.getText();
        } else if(id == 3){
            final TextView answer3 = (TextView) findViewById(R.id.answer3);
            txt = (String) answer3.getText();
        }
        return txt;
    }
}
