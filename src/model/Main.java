package model;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;

public class Main extends Application {
    public static String PervHod = "PK";
    public static HumanPlayer humanPlayer;
    public static PKPlayer pkPlayer;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../xo1.fxml"));

        Stage mainStage = new Stage(StageStyle.UTILITY);
        Scene scene = new Scene(root, 360, 280, null);
        mainStage.setTitle("XOGame");
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();

        activationGame();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void activationGame() {

        Field field = new Field();
        pkPlayer = new PKPlayer(field);
        humanPlayer = new HumanPlayer(field);

        new Thread(humanPlayer).start();
        new Thread(pkPlayer).start();

    }


}
/*

*/