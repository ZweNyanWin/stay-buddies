package com.example.stayBuddies.config;

import com.example.stayBuddies.model.QuizQuestion;
import com.example.stayBuddies.model.QuizOption;
import com.example.stayBuddies.repository.QuizQuestionRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizDataLoader implements ApplicationRunner {

    private final QuizQuestionRepository quizQuestionRepo;

    public QuizDataLoader(QuizQuestionRepository quizQuestionRepo) {
        this.quizQuestionRepo = quizQuestionRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (quizQuestionRepo.count() == 0) {
            List<QuizQuestion> questions = List.of(
                    make("How tidy are you?",
                            List.of("Extremely tidy",
                                    "Moderately tidy",
                                    "Not very tidy",
                                    "Not tidy at all")),
                    make("What are your thoughts on sharing and borrowing?",
                            List.of("Love it",
                                    "Comfortable with it",
                                    "Prefer minimal",
                                    "Do not share")),
                    make("How do you handle dishes?",
                            List.of("Wash immediately",
                                    "Wash within a day",
                                    "Wash when I remember",
                                    "Rarely rinse")),
                    make("Does smoking bother you?",
                            List.of("Strongly bothers me",
                                    "Slightly bothers me",
                                    "Not an issue",
                                    "I smoke")),
                    make("What time do you typically go to bed on weeknights?",
                            List.of("Before 10 PM",
                                    "10 PM – 12 AM",
                                    "12 AM – 2 AM",
                                    "After 2 AM")),
                    make("What are your study habits?",
                            List.of("Study daily",
                                    "Study a few times/week",
                                    "Rarely study at home",
                                    "Prefer quiet study elsewhere")),
                    make("Will any guests be staying over?",
                            List.of("Frequently",
                                    "Sometimes",
                                    "Rarely",
                                    "Never")),
                    make("Do you mind pets?",
                            List.of("Love pets",
                                    "Okay with small pets",
                                    "Prefer no pets",
                                    "Allergic to pets")),
                    make("How often will you do your share of cleaning?",
                            List.of("Daily",
                                    "Weekly",
                                    "Monthly",
                                    "Only when necessary")),
                    make("When is noise acceptable?",
                            List.of("Anytime",
                                    "During the day",
                                    "Only on weekends",
                                    "Never"))
            );

            quizQuestionRepo.saveAll(questions);
        }
    }

    /**
     * Helper to build a QuizQuestion + its options in one go.
     */
    private QuizQuestion make(String text, List<String> labels) {
        QuizQuestion q = new QuizQuestion();
        q.setText(text);
        for (int i = 0; i < labels.size(); i++) {
            QuizOption o = new QuizOption();
            o.setLabel(labels.get(i));
            o.setValue(i + 1);     // value 1–4
            o.setQuestion(q);
            q.getOptions().add(o);
        }
        return q;
    }
}
