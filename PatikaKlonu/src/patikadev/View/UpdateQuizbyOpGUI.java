package patikadev.View;

import patikadev.Helper.Config;
import patikadev.Helper.Helper;
import patikadev.Model.Quiz;

import javax.swing.*;

public class UpdateQuizbyOpGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_update;
    private JTextField txt_quiz;
    private int quiz_id;
    private Quiz quiz;

    public UpdateQuizbyOpGUI(int quiz_id) {
        this.quiz_id = quiz_id;
        this.quiz = Quiz.getQuizbyID(quiz_id);
        add(wrapper);
        setSize(300, 450);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        txt_quiz.setText(quiz.getQuiz_questions());
        btn_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_quiz)) {
                Helper.showMessage("fill");
            } else {
                if (Quiz.update(quiz_id, txt_quiz.getText())){
                    Helper.showMessage("done");
                }
                dispose();
            }
        });
    }
}
