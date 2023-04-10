module com.example.lr2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lr2 to javafx.fxml;
    exports com.example.lr2;

    opens com.example.lr2.classes to javafx.base;
    exports com.example.lr2.classes;


}