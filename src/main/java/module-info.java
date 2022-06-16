module pl.pjatk.gui.pjatk_gui_pro_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.pjatk.gui.pjatk_gui_pro_3 to javafx.fxml;
    exports pl.pjatk.gui.pjatk_gui_pro_3;
}