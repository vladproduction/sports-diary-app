module com.example.sportdiaryfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.desktop;
    requires java.sql;
    requires Sport.diary.backend;


    opens com.example.sportdiaryfrontend to javafx.fxml;
    exports com.example.sportdiaryfrontend;
}