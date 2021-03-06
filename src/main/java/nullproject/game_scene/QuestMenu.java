package nullproject.game_scene;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import nullproject.anim.Animation;
import nullproject.config.Config;
import nullproject.config.InitImage;

public class QuestMenu {

    private static QuestMenu ourInstance = new QuestMenu(); //Class instance

    public static QuestMenu getInstance() {
        return ourInstance;
    } //Static method for working with a class

    //Forbid to create an instance
    private QuestMenu() {
    }

    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewZoomSleepyBoy = new ImageView(InitImage.imageInLectureHallSleepyBoy);
        ImageView viewWelcomeToQuest = new ImageView(InitImage.imageInLectureHallWelcomeToQuest);

        viewWelcomeToQuest.setOpacity(0);

        //Set fixed width and height
        viewWelcomeToQuest.fitWidthProperty().bind(scene.widthProperty());
        viewWelcomeToQuest.fitHeightProperty().bind(scene.heightProperty());

        //Set fixed width and height
        viewZoomSleepyBoy.fitWidthProperty().bind(scene.widthProperty());
        viewZoomSleepyBoy.fitHeightProperty().bind(scene.heightProperty());

        Animation.fadeTransition(viewWelcomeToQuest, 5, 0, 1);
        Animation.getFadeTransition().setOnFinished(e->{
            Animation.getFadeTransition().stop();
        });

        //Initialize keyboard click for scene
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.R) {
                Animation.fadeTransition(pane, 1, 1, 0);
                Animation.getFadeTransition().setOnFinished(event -> {
                    Animation.getFadeTransition().stop();
                    ReadBookScene.getInstance().start(stage);
                });
            }
        });

        pane.getChildren().addAll(viewZoomSleepyBoy, viewWelcomeToQuest);
        scene.setFill(Color.BLACK);
        scene.getStylesheets().add(InLectureHall.class.getResource("../../style.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
