import java.util.ArrayList;
import java.util.Scanner;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void display() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean isCorrect(int answer) {
        return answer == correctAnswer;
    }
}

class Quiz {
    ArrayList<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void startQuiz() {
        Scanner sc = new Scanner(System.in);
        int score = 0;
        for (Question q : questions) {
            q.display();
            System.out.print("Your answer (1-4): ");
            int answer = sc.nextInt();
            if (q.isCorrect(answer)) {
                score++;
            }
        }
        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Quiz quiz = new Quiz();

        while (true) {
            System.out.println("\n--- Online Quiz Management ---");
            System.out.println("1. Add Question (Admin)");
            System.out.println("2. Take Quiz (User)");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter question: ");
                    String qText = sc.nextLine();
                    String[] options = new String[4];
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Option " + (i + 1) + ": ");
                        options[i] = sc.nextLine();
                    }
                    System.out.print("Enter correct option (1-4): ");
                    int correct = sc.nextInt();
                    sc.nextLine();
                    quiz.addQuestion(new Question(qText, options, correct));
                    System.out.println("Question added successfully.");
                    break;

                case 2:
                    if (quiz.questions.isEmpty()) {
                        System.out.println("No questions available. Please ask admin to add.");
                    } else {
                        quiz.startQuiz();
                    }
                    break;

                case 3:
                    System.out.println("Exiting. Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

