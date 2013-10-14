package nl.hanze.designpatterns.DAO.impl.file.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nl.hanze.designpatterns.domain.Answer;
import nl.hanze.designpatterns.domain.Question;

public class QuestionDaoImplSer {
	private Question rootQuestion;
	
	public QuestionDaoImplSer() {
		rootQuestion = new Question();
		if(treeExists() == false) {
			rootQuestion = createTree();
			writeTree(rootQuestion);
		} else {
			readTree();
		}
	}
	
	public void writeTree(Question root) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("thesaurus.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(rootQuestion);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in thesaurus.ser");
	     } catch(IOException i) {
	          i.printStackTrace();
	     }
	}
	
	public void readTree() {
		try {
			FileInputStream fileIn = new FileInputStream("thesaurus.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			rootQuestion = (Question) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("thesaurus class not found");
			c.printStackTrace();
			return;
		}
	}
	
	public boolean treeExists() {
		File f = new File("thesaurus.ser");
		System.out.println("File exists: " + f.exists());
		return f.exists();
	}
	
	public Question getRootQuestion() {
		return rootQuestion;
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
		
		Answer answer2 = new Answer("Kabels", "Herstart de computer.");
		Question question3 = new Question("Kabels", "Toont het internet icoon een actieve internet verbinding aan?");
		Answer answer10 = new Answer("Kabels", "Update internet settings.");
		question3.add(answer10);
		
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
