package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

// Keyboard events
public class FXB extends Application
{
    Game g = new Game();
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle( "Click the Target!" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 500, 500 );

        root.getChildren().add( canvas );

        theScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getX()<100 && e.getY()<100){
                    if(g.run){g.autoGame();}
                    else {
//                        g.stop();
//                        g.run = true;
                    }
                }
                g.turn();
            }
        });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gc.setFill( new Color(0.85, 0.85, 1.0, 1.0) );
                gc.fillRect(0,0, 512,512);
            }
        }.start();
        theStage.show();
    }
}