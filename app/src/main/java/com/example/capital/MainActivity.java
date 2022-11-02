package com.example.capital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final int MAX_ANSWERS = 5;

    int questionsAnswered = 0;
    int numeroAcertos = 0;

    ArrayList<Question> questions;
    Question currentQuestion;
    EditText etAnswer;
    TextView tvEstado, tvPontuacao, tvResultadoQuestao;
    Button btnEnviar, btnNextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions = getQuestions();

        tvEstado = findViewById(R.id.textViewEstado);
        tvPontuacao = findViewById(R.id.textViewPontos);
        tvResultadoQuestao = findViewById(R.id.textViewAcertouOuNao);
        btnEnviar = findViewById(R.id.button);
        btnNextQuestion = findViewById(R.id.buttonProxPergunta);
        etAnswer = findViewById(R.id.editTextCapital);

        getNewQuestion();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void checkAnswer(View view) {
        hideKeyboard();
        if(etAnswer.length() == 0) {
            Toast.makeText(this, "Insira a capital!", Toast.LENGTH_LONG).show();
        } else {
            btnEnviar.setEnabled(false);
            btnNextQuestion.setEnabled(true);
            etAnswer.setEnabled(false);
            String answer = etAnswer.getText().toString().toLowerCase().trim();
            if(currentQuestion.capital.toLowerCase().trim().equals(answer)) {
                numeroAcertos++;
                tvPontuacao.setText("Pontuação: " + numeroAcertos);
                tvResultadoQuestao.setTextColor(Color.BLUE);
                tvResultadoQuestao.setText("Parabéns, Você acertou!");
            } else {
                tvPontuacao.setText("Pontuação: " + numeroAcertos);
                tvResultadoQuestao.setText("Você errou!");
                tvResultadoQuestao.setTextColor(Color.RED);
            }
        }
    }

    public void nextQuestion(View view) {
        if(questionsAnswered == MAX_ANSWERS) {
            btnEnviar.setEnabled(false);
            btnNextQuestion.setEnabled(false);
            tvResultadoQuestao.setText("O jogo acabou!");
            etAnswer.setText("");
            tvEstado.setText("");
            etAnswer.setEnabled(false);
        } else {
            btnNextQuestion.setEnabled(false);
            btnEnviar.setEnabled(true);
            etAnswer.setEnabled(true);
            getNewQuestion();
        }
    }

    private void getNewQuestion() {
        int randomIndex = (int) (Math.random() * questions.size()) + 1;
        currentQuestion = questions.get(randomIndex);
        tvEstado.setText(currentQuestion.estado);
        tvResultadoQuestao.setText("");
        etAnswer.setText("");
        questionsAnswered++;
    }

    private ArrayList<Question> getQuestions() {
        return new ArrayList<Question>(){{
            add(new Question("Acre", "Rio Branco"));
            add(new Question("Alagoas", "Maceio"));
            add(new Question("Amapá", "Macapa"));
            add(new Question("Amazonas", "Manaus"));
            add(new Question("Bahia", "Salvador"));
            add(new Question("Ceará", "Fortaleza"));
            add(new Question("Distrito Federal", "Brasilia"));
            add(new Question("Espírito Santo", "Vitoria"));
            add(new Question("Maranhão", "Sao Luis"));
            add(new Question("Mato Grosso", "Cuiaba"));
            add(new Question("Mato Grosso do Sul", "Campo Grande"));
            add(new Question("Minas Gerais", "Belo Horizonte"));
            add(new Question("Pará", "Belem"));
            add(new Question("Paraíba", "Joao Pessoa"));
            add(new Question("Paraná", "Curitiba"));
            add(new Question("Pernambuco", "Recife"));
            add(new Question("Rio de Janeiro", "Rio de Janeiro"));
            add(new Question("Rio Grande do Norte", "Natal"));
            add(new Question("Rio Grande do Sul", "Porto Alegre"));
            add(new Question("Rondônia", "Porto Velho"));
            add(new Question("Roraima", "Boa Vista"));
            add(new Question("Rondônia", "Porto Velho"));
            add(new Question("Santa Catarina", "Florianopolis"));
            add(new Question("São Paulo", "Sao Paulo"));
            add(new Question("Sergipe", "Aracaju"));
            add(new Question("Tocantins", "Palmas"));
        }};
    }

}

class Question {

    public String estado;
    public String capital;

    public Question(String estado, String capital) {
        this.estado = estado;
        this.capital = capital;
    }
}