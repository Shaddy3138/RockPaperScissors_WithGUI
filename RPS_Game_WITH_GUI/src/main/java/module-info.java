module com.example.rps_game_with_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rps_game_with_gui to javafx.fxml;
    exports com.example.rps_game_with_gui;
}