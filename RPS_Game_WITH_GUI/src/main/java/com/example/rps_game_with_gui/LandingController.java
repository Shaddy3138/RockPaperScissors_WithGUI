package com.example.rps_game_with_gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LandingController {
    @FXML
    private Button playBtn;
    @FXML
    private Button close;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private TextField playerName;
    @FXML
    public RadioButton numberOfRounds;
    @FXML
    private RadioButton threeRounds;
    @FXML
    private RadioButton fiveRounds;
    @FXML
    private RadioButton sevenRounds;
    public Stage stage;

    private Integer gps;

    private Integer gws;

    private Integer gls;



    @FXML
    public void initialize() {
        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameEngine.fxml"));
                    FXMLLoader fxmlLoaderTwo = new FXMLLoader(getClass().getResource("ResultPage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    fxmlLoaderTwo.load();
                    GameEngineController gameEngineController = fxmlLoader.getController();
                    ResultPageController resultPageController = fxmlLoaderTwo.getController();
                    selected();
                    gameEngineController.setRadioButton(numberOfRounds);
                    gameEngineController.setPlayersName(playerName.getText());
                    gameEngineController.setRoundLimit(numberOfRounds.getAccessibleText());
                    gameEngineController.setMaximumRounds(Integer.parseInt(numberOfRounds.getAccessibleText()));
                    try {
                        resultPageController.setGamesPlayed(converter(gps));
                        System.out.println(gps);
                        resultPageController.setGamesWon(converter(gws));
                        System.out.println(gws);
                        resultPageController.setGamesLost(converter(gls));
                        System.out.println(gls);
                    } catch (NullPointerException e) {
                        System.out.println("resultPageController is null");
                        e.printStackTrace();
                    }
                    stage.setScene(scene);
                    scene.setFill(Color.TRANSPARENT);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean selected() {
        if (threeRounds.isSelected()) {
            numberOfRounds = threeRounds;
        } else if (fiveRounds.isSelected()) {
            numberOfRounds = fiveRounds;
        } else {
            numberOfRounds = sevenRounds;
        }
        return false;
    }

    @FXML
    public void terminate() {
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stage = (Stage) scenePane.getScene().getWindow();
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setGps(Integer gps) {
        this.gps = gps;
    }

    public void setGws(Integer gws) {
        this.gws = gws;
    }

    public void setGls(Integer gls) {
        this.gls = gls;
    }
    private Label converter(Integer integer){
        try {
            String temp = integer.toString();
            Label label = null;
            label.setText(temp);
            return label;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

