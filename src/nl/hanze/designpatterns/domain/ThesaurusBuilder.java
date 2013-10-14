package nl.hanze.designpatterns.domain;

import java.util.ArrayList;

public class ThesaurusBuilder {
	
	public ThesaurusBuilder() {
		
	}
	
	public Question createTree() {
		//1
		Question question = new Question("Kabels", "Zijn alle kabels aangesloten?");
		Answer answer = new Answer("Kabels", "Sluit alle kabels aan en herstart de computer");
		question.add(answer);
		
		//2
		Question question1 = new Question("Kabels", "Reageert de hardware?");
		answer.add(question1);
		
		Answer answer1 = new Answer("Kabels", "Stuur hardware support.");
		Question question2 = new Question("Kabels", "Kunt u op het start menu klikken?");
		question1.add(answer1);
		question1.add(question2);
		
		Answer answer2 = new Answer("Kabels", "Herstart de computer");
		Question question3 = new Question("Kabels", "Toont het internet icoon een actieve internet verbinding aan?");
		question2.add(answer2);
		question2.add(question3);

		//3
		Question question4 = new Question("Kabels", "Klopt de tijd op de klok?");
		Question question5 = new Question("Kabels", "Werken de overige functies van het systeem?");
		question3.add(question4);
		question3.add(question5);
		
		
		Answer answer3 = new Answer("Kabels", "Herstart de applicatie.");
		question4.add(answer1);
		question5.add(answer3);
		
		return question;
	}

}
