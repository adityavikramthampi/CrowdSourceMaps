package kartavya.com.crowdsourcemaps.Entity;

/**
 * Created by karta on 27-02-2018.
 */

public class QuestionSet {

    private String Question;
    private String QuestionID;

    public QuestionSet() {
    }

    public QuestionSet(String question, String questionID) {
        Question = question;
        QuestionID = questionID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(String questionID) {
        QuestionID = questionID;
    }
}
