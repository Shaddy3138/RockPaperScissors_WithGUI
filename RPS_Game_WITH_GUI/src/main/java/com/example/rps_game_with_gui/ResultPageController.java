package com.example.rps_game_with_gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ResultPageController {

    @FXML
    private Label gamesPlayed;
    @FXML
    private Label gamesWon;
    @FXML
    private Label gamesLost;
    @FXML
    private Button close;
    @FXML
    private Button playAgain;
    @FXML
    public AnchorPane scenePane;
    @FXML
    private Stage stage;
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

    @FXML
    public void initialize(){
        playAgain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LandingView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    LandingController landingController = fxmlLoader.getController();
                    landingController.setGps(Integer.parseInt(gamesPlayed.getText()));
                    landingController.setGws(Integer.parseInt(gamesWon.getText()));
                    landingController.setGls(Integer.parseInt(gamesWon.getText()));
                    stage.setScene(scene);
                    scene.setFill(Color.TRANSPARENT);
                    stage.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    public Label getGamesPlayed() {
        return gamesPlayed;
    }

    public Label getGamesWon() {
        return gamesWon;
    }

    public Label getGamesLost() {
        return gamesLost;
    }

    public void setGamesPlayed(Label gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesWon(Label gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setGamesLost(Label gamesLost) {
        this.gamesLost = gamesLost;
    }

}
