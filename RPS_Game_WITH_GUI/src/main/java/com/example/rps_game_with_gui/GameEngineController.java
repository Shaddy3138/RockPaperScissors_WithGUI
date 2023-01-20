package com.example.rps_game_with_gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameEngineController {
    private Image image;
    private Stage stage;
    private RadioButton radioButton;
    private Integer round = 1;
    private Integer playerScore = 0;
    private Integer computerScore = 0;
    @FXML
    private Button rockBtn;
    @FXML
    private Button scissorsBtn;
    @FXML
    private Button paperBtn;
    @FXML
    private Button close;
    @FXML
    private Label playersName;
    @FXML
    private ImageView player;
    @FXML
    private ImageView computer;
    @FXML
    public AnchorPane scenePane;
    @FXML
    private Label roundCounter;
    @FXML
    private Label roundLimit;
    @FXML
    private Label result;
    public Integer maximumRounds;

    private Integer tempPlayerWins = 0;

    private Integer tempComputerWins = 0;

    @FXML
    private void playerTurn(ActionEvent event){
        Integer temp = Integer.parseInt(radioButton.getAccessibleText());
        roundCounter.setText(round + "/" + temp);
        while (maximumRounds > 0){

            String playerChoice = null;
            switch (((Button) event.getSource()).getId()) {
                case "paperBtn":
                    image = new Image("paper_icon.png");
                    playerChoice = Hands.PAPER.getOption();
                    round++;
                    maximumRounds--;
                    break;
                case "rockBtn":
                    image = new Image("rock_icon.png");
                    playerChoice = Hands.ROCK.getOption();
                    round++;
                    maximumRounds--;
                    break;
                case "scissorsBtn":
                    image = new Image("sicsors_icon.png");
                    playerChoice = Hands.SCISSORS.getOption();
                    round++;
                    maximumRounds--;
                    break;
            }
            player.setImage(image);
            winner(playerChoice,computerTurn());
           return;
       }
            try{
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResultPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                ResultPageController resultPageController = fxmlLoader.getController();
                resultPageController.setGamesPlayed(gameCounter(resultPageController.getGamesPlayed()));
                System.out.println(resultPageController.getGamesPlayed());
                resultPageController.setGamesWon(scoreSetter(resultPageController.getGamesWon(),resultPageController.getGamesLost()));
                System.out.println(resultPageController.getGamesWon());
                resultPageController.setGamesLost(scoreSetter(resultPageController.getGamesWon(),resultPageController.getGamesLost()));
                System.out.println(resultPageController.getGamesLost());
                stage.setScene(scene);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    private String computerTurn(){
        String computerChoice = null;
        Hands index = Hands.chooseGuess();
        switch (index) {
            case ROCK:
                image = new Image("rock_icon.png");
                computerChoice = Hands.ROCK.getOption();
                break;
            case PAPER:
                image = new Image("paper_icon.png");
                computerChoice = Hands.PAPER.getOption();
                break;
            case SCISSORS:
                image = new Image("sicsors_icon.png");
                computerChoice = Hands.SCISSORS.getOption();
                break;

        }
        computer.setImage(image);
        return computerChoice;
    }
    @FXML
    public void terminate(){
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage = (Stage) scenePane.getScene().getWindow();
                    stage.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void winner(String playerChoice, String computerChoice){
        if (playerChoice.equals(computerChoice)){
            tie();
            return;
        }
        if ((playerChoice.equals(Hands.ROCK.getOption())  && computerChoice.equals(Hands.SCISSORS.getOption())) || (playerChoice.equals(Hands.PAPER.getOption()) && computerChoice.equals(Hands.ROCK.getOption())) || (playerChoice.equals(Hands.SCISSORS.getOption()) && computerChoice.equals(Hands.PAPER.getOption()))){
            playerWin();
        }else {
            computerWin();
        }
    }

    private void playerWin(){
       result.setTextFill(Color.GREEN);
        result.setText("VICTORY!");
        playerScore++;
    }

    private void computerWin(){
       result.setTextFill(Color.RED);
        result.setText("Fatality!");
        computerScore++;
    }

    private void tie(){
       result.setTextFill(Color.WHITE);
        result.setText("TIE!!");
    }

    public Label scoreSetter(Label labelA, Label labelB) {
        if (playerScore > computerScore) {
            if (labelA.getAccessibleText() != null) {
                Integer temp = Integer.parseInt(labelA.getAccessibleText()) + 1;
                String tempTwo = temp.toString();
                labelA.setText(tempTwo);
                return labelA;
            } else {
                // handle the exception, for example by setting the text to "0"
                labelA.setText("1");
                return labelA;
            }
        } else if (computerScore > playerScore) {
            if (labelB.getAccessibleText() != null) {
                Integer temp = Integer.parseInt(labelB.getAccessibleText()) + 1;
                String tempTwo = temp.toString();
                labelB.setText(tempTwo);
                return labelB;
            } else {
                // handle the exception, for example by setting the text to "0"
                labelB.setText("1");
                return labelB;
            }
        }
        return labelA;
    }
    public Label gameCounter(Label labelC) {
            if (labelC.getAccessibleText() != null) {
                Integer local = Integer.parseInt(labelC.getAccessibleText()) + 1;
                String localTwo = local.toString();
                labelC.setText(localTwo);
                return labelC;
            } else {
                labelC.setText("1");
                return labelC;
            }
        }
    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public void setPlayersName( String name) {
        playersName.setText(name);
    }
    public void setRoundLimit(String limit) {
        roundLimit.setText(limit);
    }

    public void setMaximumRounds(Integer maximumRounds) {
        this.maximumRounds = maximumRounds;
    }
}
