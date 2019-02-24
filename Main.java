package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws Exception{
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{



        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        VBox rootNode = loader.load();

        MenuBar menuBar = createMenu();
        rootNode.getChildren().add(0, menuBar);

        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);
        primaryStage.setTitle(" Converter Tool");
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    private MenuBar createMenu(){
        Menu fileMenu = new Menu("File");

        // Help Menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutApp=new MenuItem("About Tool");

        aboutApp.setOnAction(event -> aboutApp());

        SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
        MenuItem aboutMe=new MenuItem("About Devloper");
        
        aboutMe.setOnAction(event -> aboutMe());
        
        helpMenu.getItems().addAll(aboutApp,aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(helpMenu);
        return menuBar;

    }

    private void aboutMe() {

        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("About The Developer");
        alertDialog.setHeaderText("Aadarsh Ghodasara");
        alertDialog.setContentText("I am just beginner but soon I will be pro and start developing awesome TOOl in java..");
        alertDialog.show();
    }

    private void aboutApp() {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("About APP");
        alertDialog.setHeaderText("HOW TO WORK ?");
        alertDialog.setContentText("First of all enter any strnig into the textFiled," +
                "\n like -> 1+2+3 or 1/2+3  " +
                "\n      -> a+b+c or a/b+c  \n" +
                "then click on convert button so you can show below the button two filed, first is postfix and second is prefix is a output." +
                "\noutput is --> 1. postfix = 12+3+ prefix=++123\n" +
                "        --> 2. postfix = 12/3+ prefix=+/123\n" +
                "ensure Enter  string , you can not click button without enter string.");
        alertDialog.show();
    }

    @Override
    public void stop() throws Exception{
        super.stop();
    }

}

