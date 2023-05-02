module com.example.lr2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.codec;
    requires com.google.gson;
    requires gson.extras;


    opens com.example.lr2 to javafx.fxml;
    exports com.example.lr2;

    exports com.example.lr2.classes;
    opens com.example.lr2.classes;

    exports com.example.lr2.abstractFactory;
    exports com.example.lr2.plugins;
    exports com.example.lr2.serializer;
    opens com.example.lr2.abstractFactory to javafx.fxml;


}