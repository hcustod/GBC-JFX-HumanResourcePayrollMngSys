module org.live.humanresourcemangandpayrollsys {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.live.humanresourcemangandpayrollsys to javafx.fxml;
    exports org.live.humanresourcemangandpayrollsys;
    exports org.live.humanresourcemangandpayrollsys.model;
    opens org.live.humanresourcemangandpayrollsys.model to javafx.fxml;
}