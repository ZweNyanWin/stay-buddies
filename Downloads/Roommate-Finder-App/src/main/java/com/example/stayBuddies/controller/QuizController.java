package com.example.stayBuddies.controller;

import com.example.stayBuddies.model.QuizQuestion;
import com.example.stayBuddies.service.MatchService;
import com.example.stayBuddies.service.QuizAnswerService;
import com.example.stayBuddies.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {
    private final QuizService quizService;
    private final MatchService matchService;
    private final QuizAnswerService answerService;
    //private final UserService userService;

    @Autowired
    public QuizController(QuizService quizService, QuizAnswerService answerService,
                          MatchService matchService) {
        this.quizService = quizService;
        this.answerService = answerService;
        this.matchService = matchService;

    }

    @GetMapping("/take-quiz")
    public String showQuizLanding() {
        return "take-quiz";       // src/main/resources/templates/take-quiz.html
    }

    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        List<QuizQuestion> questions = quizService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "quiz";            // src/main/resources/templates/quiz.html
    }

    @PostMapping("/quiz/submit")
    public String submitQuiz(HttpSession session, HttpServletRequest request) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        List<QuizQuestion> questions = quizService.getAllQuestions();
        Map<Long, Integer> answers = new HashMap<>();
        for (int i = 0; i < questions.size(); i++) {
            String param = request.getParameter("answer" + i);
            if (param != null) {
                answers.put(
                        questions.get(i).getId(),
                        Integer.valueOf(param)
                );
            }
        }

        answerService.saveUserAnswers(userId, answers);
        matchService.generateMatchesForUser(userId, 5);
        return "redirect:/matches";
    }

}
