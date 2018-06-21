package kartavya.com.crowdsourcemaps.Entity;

/**
 * Created by karta on 27-02-2018.
 */

public class RandQuestions {

    private String AnswerID;
    private String PlaceID;
    private String QuestionID;
    private String Answer;

    public RandQuestions() {
    }

    public RandQuestions(String answerID, String placeID, String questionID, String answer) {
        AnswerID = answerID;
        PlaceID = placeID;
        QuestionID = questionID;
        Answer = answer;
    }

    public String getAnswerID() {
        return AnswerID;
    }

    public void setAnswerID(String answerID) {
        AnswerID = answerID;
    }

    public String getPlaceID() {
        return PlaceID;
    }

    public void setPlaceID(String placeID) {
        PlaceID = placeID;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(String questionID) {
        QuestionID = questionID;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
