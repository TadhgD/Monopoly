package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Main extends Application implements EventHandler<ActionEvent>  {

    Stage window;//Main frame of the GUI
    Scene scene1, scene2;//scene1 for initial page, scene2 for the game page

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //Background music
//        AudioPlayer myBackgroundPlayer = AudioPlayer.player;
//        ContinuousAudioDataStream myLoop = null;
//        try {
//            AudioStream myBackgroundMusic = new AudioStream(getClass().getResourceAsStream("Shooting Stars2.wav"));
//            AudioData myData = myBackgroundMusic.getData();
//            myLoop = new ContinuousAudioDataStream(myData);
//        }catch(Exception error){
//            System.out.println("File Not Found");
//            System.out.println(error);
//        }
//        myBackgroundPlayer.start(myLoop);
///////////////////////////////////////////////////////////////scene 1
        //press button to begin game
        Button button2 = new Button("Begin Game");
        button2.setOnAction(e -> window.setScene(scene2));
        button2.setId("begingame");
        button2.setPrefSize(100,40);

        //pubcrawl logo
        Label labelpub = new Label();
        labelpub.setId("labelpub");
        labelpub.setTranslateX(220);
        labelpub.setPrefSize(800,285);
        BorderPane borderscene1 = new BorderPane();
        borderscene1.setCenter(button2);
        borderscene1.setTop(labelpub);
        borderscene1.setAlignment(button2,Pos.CENTER);
        borderscene1.setId("borderscene1");
        scene1 = new Scene(borderscene1, 1210, 610);
        String mainStyle1 = this.getClass().getResource("mainStyle.css").toExternalForm();
        scene1.getStylesheets().add(mainStyle1);

//////////////////////////////////////////////////////////////scene 2
        //p1 is the main frame of scene2
        BorderPane p1 = new BorderPane();
        p1.setLeft(new Rectangle(300, 610, Color.ALICEBLUE));
        p1.setCenter(new Rectangle(610, 610));
        p1.setRight(new Rectangle(300, 610, Color.ALICEBLUE));
        p1.setId("p1");

        //p2 is the container of 40 squares
        BorderPane p2 = new BorderPane();
        p2.setLeft(new Rectangle(75, 460, Color.BEIGE));
        p2.setRight(new Rectangle(75, 460, Color.BEIGE));
        p2.setTop(new Rectangle(610, 75, Color.BISQUE));
        p2.setBottom(new Rectangle(610, 75, Color.BISQUE));
        p2.setPrefSize(610,610);
        p2.setId("backgroundColor");

        p1.setCenter(p2);

        //p1left for managing the properties
        BorderPane p1left = new BorderPane();
        p1left.setId("p1left");
        p1left.setPrefSize(300,610);
        p1.setLeft(p1left);
        ////////////////////////////////////////////////p1 left
        //p1right for output the information
        BorderPane p1right = new BorderPane();
        p1right.setId("p1right");
        p1right.setPrefSize(300,610);
        p1.setRight(p1right);

        //the pubcrawl logo
        Label p2centre = new Label();
        p2centre.setPrefSize(460,460);
        p2centre.setRotate(-45);
        p2centre.setTranslateX(-10);
        p2centre.setTranslateY(-35);
        p2centre.setId("p2centre");
        p2.setCenter(p2centre);

        Game g = new Game();
        Label l = new Label(g.positionsString());

        //each label represents a square. 40 labels in total
        Label labelb0 = new Label();
        labelb0.setPrefSize(75,30);
        labelb0.setText(g.usersAtSquare(0));

        Label labelb1 = new Label();
        labelb1.setPrefSize(50,25);

        Label labelb2 = new Label();
        labelb2.setPrefSize(50,25);

        Label labelb3 = new Label();
        labelb3.setPrefSize(50,25);

        Label labelb4 = new Label();
        labelb4.setPrefSize(50,25);

        Label labelb5 = new Label();
        labelb5.setPrefSize(50,25);

        Label labelb6 = new Label();
        labelb6.setPrefSize(50,25);

        Label labelb7 = new Label();
        labelb7.setPrefSize(50,25);

        Label labelb8 = new Label();
        labelb8.setPrefSize(50,25);

        Label labelb9 = new Label();
        labelb9.setPrefSize(50,25);

        Label labelb10 = new Label();
        labelb10.setPrefSize(75,25);

        Label labelb11 = new Label();
        labelb11.setPrefSize(25,50);

        Label labelb12 = new Label();
        labelb12.setPrefSize(25,50);

        Label labelb13 = new Label();
        labelb13.setPrefSize(25,50);

        Label labelb14 = new Label();
        labelb14.setPrefSize(25,50);

        Label labelb15 = new Label();
        labelb15.setPrefSize(25,50);

        Label labelb16 = new Label();
        labelb16.setPrefSize(25,50);

        Label labelb17 = new Label();
        labelb17.setPrefSize(25,50);

        Label labelb18 = new Label();
        labelb18.setPrefSize(25,50);

        Label labelb19 = new Label();
        labelb19.setPrefSize(25,50);

        Label labelb20 = new Label();
        labelb20.setPrefSize(75,25);

        Label labelb21 = new Label();
        labelb21.setPrefSize(50,25);

        Label labelb22 = new Label();
        labelb22.setPrefSize(50,25);

        Label labelb23 = new Label();
        labelb23.setPrefSize(50,25);

        Label labelb24 = new Label();
        labelb24.setPrefSize(50,25);

        Label labelb25 = new Label();
        labelb25.setPrefSize(50,25);

        Label labelb26 = new Label();
        labelb26.setPrefSize(50,25);

        Label labelb27 = new Label();
        labelb27.setPrefSize(50,25);

        Label labelb28 = new Label();
        labelb28.setPrefSize(50,25);

        Label labelb29 = new Label();
        labelb29.setPrefSize(50,25);

        Label labelb30 = new Label();
        labelb30.setPrefSize(75,25);

        Label labelb31 = new Label();
        labelb31.setPrefSize(25,50);

        Label labelb32 = new Label();
        labelb32.setPrefSize(25,50);

        Label labelb33 = new Label();
        labelb33.setPrefSize(25,50);

        Label labelb34 = new Label();
        labelb34.setPrefSize(25,50);

        Label labelb35 = new Label();
        labelb35.setPrefSize(25,50);

        Label labelb36 = new Label();
        labelb36.setPrefSize(25,50);

        Label labelb37 = new Label();
        labelb37.setPrefSize(25,50);

        Label labelb38 = new Label();
        labelb38.setPrefSize(25,50);

        Label labelb39 = new Label();
        labelb39.setPrefSize(25,50);
////////////////////////////////////////////////////square 0
        BorderPane border0 = new BorderPane();
        border0.setId("border");
        border0.setPrefSize(75,75);
        border0.setBottom(labelb0);

        Label labela00 = new Label();
        labela00.setId("labela00");
        labela00.setPrefSize(75,20);
        border0.setTop(labela00);
        //GO
        Label labelgo = new Label();
        labelgo.setId("labelgo");
        labelgo.setPrefSize(25,25);
        border0.setCenter(labelgo);
/////////////////////////////////////////////////square 1
        BorderPane border1 = new BorderPane();
        border1.setId("border");
        border1.setPrefSize(50,75);
        border1.setBottom(labelb1);

        Label labels11 = new Label("Southside €60");
        labels11.setWrapText(true);
        labels11.setPrefSize(50,30);
        labels11.setId("labelString");
        border1.setCenter(labels11);

        Label labelc11 = new Label();
        labelc11.setPrefSize(50,15);
        labelc11.setId("labelbrown");
        border1.setTop(labelc11);
        //////////////////////////////////////////////////////square 2
        BorderPane border2 = new BorderPane();
        border2.setId("border");
        border2.setPrefSize(50,75);
        border2.setBottom(labelb2);

        Label labels21 = new Label("Community Chest");
        labels21.setWrapText(true);
        labels21.setFont(Font.font(8.5));
        labels21.setPrefSize(50,20);
        labels21.setId("labelString");
        border2.setTop(labels21);

        Label labelchest = new Label();
        labelchest.setId("labelchest");
        labelchest.setPrefSize(50,30);
        border2.setCenter(labelchest);
//////////////////////////////////////////////////////square 3
        BorderPane border3 = new BorderPane();
        border3.setId("border");
        border3.setPrefSize(50,75);
        border3.setBottom(labelb3);

        Label labels31 = new Label("Sportsman €60");
        labels31.setWrapText(true);
        labels31.setPrefSize(50,30);
        labels31.setId("labelString");
        border3.setCenter(labels31);

        Label labelc31 = new Label();
        labelc31.setPrefSize(50,15);
        labelc31.setId("labelbrown");
        border3.setTop(labelc31);
        //////////////////////////////////////////////// square 4
        BorderPane border4 = new BorderPane();
        border4.setId("border");
        border4.setPrefSize(50,75);
        border4.setBottom(labelb4);

        Label labelincomeTax = new Label("Income Tax");
        labelincomeTax.setFont(Font.font(8.5));
        labelincomeTax.setWrapText(true);
        labelincomeTax.setId("labelincomeTax");
        labelincomeTax.setPrefSize(50,30);
        border4.setTop(labelincomeTax);

        Label labels41 = new Label("    €200");
        labels41.setFont(Font.font(8.5));
        labels41.setWrapText(true);
        labels41.setPrefSize(50,30);
        border4.setCenter(labels41);
        //////////////////////////////////////////////////////// square 5
        BorderPane border5 = new BorderPane();
        border5.setId("border");
        border5.setPrefSize(50,75);
        border5.setBottom(labelb5);

        Label labels51 = new Label("H.billy €200");
        labels51.setFont(Font.font(8.5));
        labels51.setWrapText(true);
        labels51.setId("speedo");
        labels51.setPrefSize(50,25);
        border5.setTop(labels51);

        Label labelburger = new Label();
        labelburger.setId("labelburger1");
        labelburger.setPrefSize(50,30);
        border5.setCenter(labelburger);
        ////////////////////////////////////////////////////// square 6
        BorderPane border6 = new BorderPane();
        border6.setId("border");
        border6.setPrefSize(50,75);
        border6.setBottom(labelb6);

        Label labels61 = new Label("Moks €100");
        labels61.setWrapText(true);
        labels61.setPrefSize(50,30);
        labels61.setId("labelString");
        border6.setCenter(labels61);

        Label labelc61 = new Label();
        labelc61.setPrefSize(50,15);
        labelc61.setId("labelblue");
        border6.setTop(labelc61);
        /////////////////////////////////////////////////// square 7
        BorderPane border7 = new BorderPane();
        border7.setId("border");
        border7.setPrefSize(50,75);
        border7.setBottom(labelb7);

        Label labelchance1 = new Label();
        labelchance1.setId("labelchance1");
        labelchance1.setPrefSize(50,50);
        border7.setTop(labelchance1);
        /////////////////////////////////////////////////////////// square 8
        BorderPane border8 = new BorderPane();
        border8.setId("border");
        border8.setPrefSize(50,75);
        border8.setBottom(labelb8);

        Label labels81 = new Label("Gallows €100");
        labels81.setWrapText(true);
        labels81.setPrefSize(50,30);
        labels81.setId("labelString");
        border8.setCenter(labels81);

        Label labelc81 = new Label();
        labelc81.setPrefSize(50,15);
        labelc81.setId("labelblue");
        border8.setTop(labelc81);
        //////////////////////////////////////////////////////// square 9
        BorderPane border9 = new BorderPane();
        border9.setId("border");
        border9.setPrefSize(50,75);
        border9.setBottom(labelb9);

        Label labels91 = new Label("C.Youngs €120");
        labels91.setWrapText(true);
        labels91.setPrefSize(50,30);
        labels91.setId("labelString");
        border9.setCenter(labels91);

        Label labelc91 = new Label();
        labelc91.setPrefSize(50,15);
        labelc91.setId("labelblue");
        border9.setTop(labelc91);
        ///////////////////////////////////////////////////// square 10
        BorderPane border10 = new BorderPane();
        border10.setId("border");
        border10.setPrefSize(75,75);
        border10.setBottom(labelb10);

        //Jail
        Label labeljail = new Label();
        labeljail.setId("labeljail");
        labeljail.setPrefSize(40,40);
        border10.setCenter(labeljail);

        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(0, 0, 0, 0));
        hbox1.setSpacing(1);
        hbox1.setId("hbox1");
        hbox1.getChildren().addAll(border10, border9, border8, border7, border6, border5, border4, border3, border2, border1, border0);

        p2.setBottom(hbox1);
///////////////////////////////////////////////////// square 11
        BorderPane border11 = new BorderPane();
        border11.setId("border");
        border11.setPrefSize(75,51);
        border11.setLeft(labelb11);

        Label labels111 = new Label("AnnieMacs €140");
        labels111.setWrapText(true);
        labels111.setId("labelString");
        labels111.setRotate(90);
        border11.setCenter(labels111);

        Label labelc111 = new Label();
        labelc111.setPrefSize(15,51);
        labelc111.setId("labelpink");
        border11.setRight(labelc111);
///////////////////////////////////////////////////////// square 12
        BorderPane border12 = new BorderPane();
        border12.setId("border");
        border12.setPrefSize(75,51);
        border12.setLeft(labelb12);

        Label labels121 = new Label("Elec €200");
        labels121.setWrapText(true);
        labels121.setPrefSize(25,50);
        labels121.setFont(Font.font(7));
        labels121.setRotate(90);
        border12.setRight(labels121);

        Label labelbulb = new Label();
        labelbulb.setId("labelbulb");
        labelbulb.setPrefSize(30,50);
        border12.setCenter(labelbulb);
///////////////////////////////////////////////////// square 13
        BorderPane border13 = new BorderPane();
        border13.setId("border");
        border13.setPrefSize(75,51);
        border13.setLeft(labelb13);

        Label labels131 = new Label("Bradleys €140");
        labels131.setWrapText(true);
        labels131.setId("labelString");
        labels131.setRotate(90);
        border13.setCenter(labels131);

        Label labelc131 = new Label();
        labelc131.setPrefSize(15,50);
        labelc131.setId("labelpink");
        border13.setRight(labelc131);
////////////////////////////////////////////////////////// square 14
        BorderPane border14 = new BorderPane();
        border14.setId("border");
        border14.setPrefSize(75,51);
        border14.setLeft(labelb14);

        Label labels141 = new Label("T.Lynchs €160");
        labels141.setWrapText(true);
        labels141.setId("labelString");
        labels141.setRotate(90);
        border14.setCenter(labels141);

        Label labelc141 = new Label();
        labelc141.setPrefSize(15,50);
        labelc141.setId("labelpink");
        border14.setRight(labelc141);
///////////////////////////////////////////////////// square 15

        BorderPane border15 = new BorderPane();
        border15.setId("border");
        border15.setPrefSize(75,51);
        border15.setLeft(labelb15);

        Label labels151 = new Label("Speedo €200");
        labels151.setWrapText(true);
        labels151.setPrefSize(25,50);
        labels151.setFont(Font.font(7));
        labels151.setRotate(90);
        border15.setRight(labels151);

        Label labelburger2 = new Label();
        labelburger2.setId("labelburger2");
        labelburger2.setPrefSize(30,50);
        border15.setCenter(labelburger2);
///////////////////////////////////////////////////// square 16
        BorderPane border16 = new BorderPane();
        border16.setId("border");
        border16.setPrefSize(75,51);
        border16.setLeft(labelb16);

        Label labels161 = new Label("T.Barrys €180");
        labels161.setWrapText(true);
        labels161.setId("labelString");
        labels161.setRotate(90);
        border16.setCenter(labels161);


        Label labelc161 = new Label();
        labelc161.setPrefSize(15,50);
        labelc161.setId("labelorange");
        border16.setRight(labelc161);
///////////////////////////////////////////////////////square 17
        BorderPane border17 = new BorderPane();
        border17.setId("border");
        border17.setPrefSize(75,51);
        border17.setLeft(labelb17);

        Label labelchest22 = new Label();
        labelchest22.setId("labelchest22");
        labelchest22.setPrefSize(55,50);
        border17.setCenter(labelchest22);
///////////////////////////////////////////////////// square 18
        BorderPane border18 = new BorderPane();
        border18.setId("border");
        border18.setPrefSize(75,51);
        border18.setLeft(labelb18);

        Label labels181 = new Label("Barberella €180");
        labels181.setWrapText(true);
        labels181.setId("labelString");
        labels181.setRotate(90);
        border18.setCenter(labels181);


        Label labelc181 = new Label();
        labelc181.setPrefSize(15,50);
        labelc181.setId("labelorange");
        border18.setRight(labelc181);

/////////////////////////////////////////////////////// square 19
        BorderPane border19 = new BorderPane();
        border19.setId("border");
        border19.setPrefSize(75,50);
        border19.setLeft(labelb19);

        Label labels191 = new Label("F.Ent €200");
        labels191.setWrapText(true);
        labels191.setId("labelString");
        labels191.setRotate(90);
        border19.setCenter(labels191);

        Label labelc191 = new Label();
        labelc191.setPrefSize(15,50);
        labelc191.setId("labelorange");
        border19.setRight(labelc191);

        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(0, 0, 0, 0));
        vbox1.setSpacing(1);
        vbox1.setId("vbox1");
        vbox1.getChildren().addAll(border19, border18, border17, border16, border15, border14, border13, border12, border11);

        p2.setLeft(vbox1);

        //////////////////////////////////////////////////////square 20
        BorderPane border20 = new BorderPane();
        border20.setId("border");
        border20.setPrefSize(75,75);
        border20.setTop(labelb20);

//FREE PARKING
        Label labelfp = new Label();
        labelfp.setId("labelfp");
        labelfp.setPrefSize(40,40);
        border20.setCenter(labelfp);
///////////////////////////////////////////////////square 21
        BorderPane border21 = new BorderPane();
        border21.setId("border");
        border21.setPrefSize(50,75);
        border21.setTop(labelb21);

        Label labels211 = new Label("Costigans €220");
        labels211.setWrapText(true);
        labels211.setPrefSize(50,30);
        labels211.setId("labelString");
        border21.setCenter(labels211);

        Label labelc211 = new Label();
        labelc211.setPrefSize(50,15);
        labelc211.setId("labelred");
        border21.setBottom(labelc211);
        //////////////////////////////////////////////////////square 22
        BorderPane border22 = new BorderPane();
        border22.setId("border");
        border22.setPrefSize(50,75);
        border22.setTop(labelb22);

        Label labelchance2 = new Label();
        labelchance2.setId("labelchance2");
        labelchance2.setPrefSize(50,50);
        border22.setBottom(labelchance2);
/////////////////////////////////////////////////square 23
        BorderPane border23 = new BorderPane();
        border23.setId("border");
        border23.setPrefSize(50,75);
        border23.setTop(labelb23);

        Label labels231 = new Label("S.Fanach €220");
        labels231.setWrapText(true);
        labels231.setPrefSize(50,30);
        labels231.setId("labelString");
        border23.setCenter(labels231);

        Label labelc231 = new Label();
        labelc231.setPrefSize(50,15);
        labelc231.setId("labelred");
        border23.setBottom(labelc231);
/////////////////////////////////////////////////square 24
        BorderPane border24 = new BorderPane();
        border24.setId("border");
        border24.setPrefSize(50,75);
        border24.setTop(labelb24);

        Label labels241 = new Label("Old Oak €240");
        labels241.setWrapText(true);
        labels241.setPrefSize(50,30);
        labels241.setId("labelString");
        border24.setCenter(labels241);

        Label labelc241 = new Label();
        labelc241.setPrefSize(50,15);
        labelc241.setId("labelred");
        border24.setBottom(labelc241);
////////////////////////////////////////////////square 25
        BorderPane border25 = new BorderPane();
        border25.setId("border");
        border25.setPrefSize(50,75);
        border25.setTop(labelb25);

        Label labels251 = new Label("McD's €200");
        labels251.setWrapText(true);
        labels251.setFont(Font.font(8.5));
        labels251.setPrefSize(50,25);
        labels251.setId("mcd");
        border25.setBottom(labels251);

        Label labelburger3 = new Label();
        labelburger3.setId("labelburger3");
        labelburger3.setPrefSize(50,30);
        border25.setCenter(labelburger3);
/////////////////////////////////////////////////square 26
        BorderPane border26 = new BorderPane();
        border26.setId("border");
        border26.setPrefSize(50,75);
        border26.setTop(labelb26);

        Label labels261 = new Label("Bróg €260");
        labels261.setWrapText(true);
        labels261.setPrefSize(50,30);
        labels261.setId("labelString");
        border26.setCenter(labels261);

        Label labelc261 = new Label();
        labelc261.setPrefSize(50,15);
        labelc261.setId("labelyellow");
        border26.setBottom(labelc261);
/////////////////////////////////////////////////square 27
        BorderPane border27 = new BorderPane();
        border27.setId("border");
        border27.setPrefSize(50,75);
        border27.setTop(labelb27);

        Label labels271 = new Label("F.Well €260");
        labels271.setWrapText(true);
        labels271.setPrefSize(50,30);
        labels271.setId("labelString");
        border27.setCenter(labels271);


        Label labelc271 = new Label();
        labelc271.setPrefSize(50,15);
        labelc271.setId("labelyellow");
        border27.setBottom(labelc271);
/////////////////////////////////////////////////square 28
        BorderPane border28 = new BorderPane();
        border28.setId("border");
        border28.setPrefSize(50,75);
        border28.setTop(labelb28);

        Label labels281 = new Label("Water €200");
        labels281.setWrapText(true);
        labels281.setFont(Font.font(8.5));
        labels281.setPrefSize(50,25);
        labels281.setId("water");
        border28.setBottom(labels281);

        Label labelwater = new Label();
        labelwater.setId("labelwater");
        labelwater.setPrefSize(50,30);
        border28.setCenter(labelwater);
/////////////////////////////////////////////////square 29
        BorderPane border29 = new BorderPane();
        border29.setId("border");
        border29.setPrefSize(50,75);
        border29.setTop(labelb29);

        Label labels291 = new Label("S.Lane €280");
        labels291.setWrapText(true);
        labels291.setPrefSize(50,30);
        labels291.setId("labelString");
        border29.setCenter(labels291);

        Label labelc291 = new Label();
        labelc291.setPrefSize(50,15);
        labelc291.setId("labelyellow");
        border29.setBottom(labelc291);
/////////////////////////////////////////////////square 30
        BorderPane border30 = new BorderPane();
        border30.setId("border");
        border30.setPrefSize(75,75);
        border30.setTop(labelb30);

        //go jail
        Label labelgojail = new Label();
        labelgojail.setId("labelgojail");
        labelgojail.setPrefSize(40,40);
        border30.setCenter(labelgojail);

        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(0, 0, 0, 0));
        hbox2.setSpacing(1);
        hbox2.setId("hbox2");
        hbox2.getChildren().addAll(border20, border21, border22, border23, border24, border25, border26, border27, border28, border29, border30);

        p2.setTop(hbox2);

        ////////////////////////////////////////////////////////////square 31
        BorderPane border31 = new BorderPane();
        border31.setId("border");
        border31.setPrefSize(75,51);
        border31.setRight(labelb31);

        Label labels311 = new Label("V.town €300");
        labels311.setWrapText(true);
        labels311.setId("labelString");
        labels311.setRotate(270);
        border31.setCenter(labels311);


        Label labelc311 = new Label();
        labelc311.setPrefSize(15,51);
        labelc311.setId("labelgreen");
        border31.setLeft(labelc311);
///////////////////////////////////////////////////////////////square 32

        BorderPane border32 = new BorderPane();
        border32.setId("border");
        border32.setPrefSize(75,51);
        border32.setRight(labelb32);

        Label labels321 = new Label("Friary €300");
        labels321.setWrapText(true);
        labels321.setId("labelString");
        labels321.setRotate(270);
        border32.setCenter(labels321);


        Label labelc321 = new Label();
        labelc321.setPrefSize(15,51);
        labelc321.setId("labelgreen");
        border32.setLeft(labelc321);
//////////////////////////////////////////////////////////////square 33
        BorderPane border33 = new BorderPane();
        border33.setId("border");
        border33.setPrefSize(75,51);
        border33.setRight(labelb33);

        Label labelchest3 = new Label();
        labelchest3.setId("labelchest3");
        labelchest3.setPrefSize(55,50);
        border33.setCenter(labelchest3);
////////////////////////////////////////////////////////////square 34
        BorderPane border34 = new BorderPane();
        border34.setId("border");
        border34.setPrefSize(75,51);
        border34.setRight(labelb34);

        Label labels341 = new Label("Charlies €320");
        labels341.setWrapText(true);
        labels341.setId("labelString");
        labels341.setRotate(270);
        border34.setCenter(labels341);

        Label labelc341 = new Label();
        labelc341.setPrefSize(15,51);
        labelc341.setId("labelgreen");
        border34.setLeft(labelc341);
///////////////////////////////////////////////////////////square 35
        BorderPane border35 = new BorderPane();
        border35.setId("border");
        border35.setPrefSize(75,51);
        border35.setRight(labelb35);

        Label labels351 = new Label("4Star €200");
        labels351.setWrapText(true);
        labels351.setPrefSize(25,50);
        labels351.setFont(Font.font(7));
        labels351.setRotate(270);
        border35.setLeft(labels351);

        Label labelburger4 = new Label();
        labelburger4.setId("labelburger4");
        labelburger4.setPrefSize(30,50);
        border35.setCenter(labelburger4);
/////////////////////////////////////////////////////////square 36
        BorderPane border36 = new BorderPane();
        border36.setId("border");
        border36.setPrefSize(75,51);
        border36.setRight(labelb36);

        Label labelchance3 = new Label();
        labelchance3.setId("labelchance3");
        labelchance3.setPrefSize(50,50);
        border36.setLeft(labelchance3);
////////////////////////////////////////////////////////square 37
        BorderPane border37 = new BorderPane();
        border37.setId("border");
        border37.setPrefSize(75,51);
        border37.setRight(labelb37);

        Label labels371 = new Label("Havanas €350");
        labels371.setWrapText(true);
        labels371.setId("labelString");
        labels371.setRotate(270);
        border37.setCenter(labels371);

        Label labelc371 = new Label();
        labelc371.setPrefSize(15,51);
        labelc371.setId("labeldarkblue");
        border37.setLeft(labelc371);
///////////////////////////////////////////////////////square 38
        BorderPane border38 = new BorderPane();
        border38.setId("border");
        border38.setPrefSize(75,51);
        border38.setRight(labelb38);

        Label labels381 = new Label("LuxuryTax €200");
        labels381.setWrapText(true);
        labels381.setPrefSize(25,50);
        labels381.setFont(Font.font(7));
        labels381.setRotate(270);
        border38.setLeft(labels381);

        Label labelring = new Label();
        labelring.setId("labelring");
        labelring.setPrefSize(30,50);
        border38.setCenter(labelring);
//////////////////////////////////////////////////////square 39
        BorderPane border39 = new BorderPane();
        border39.setId("border");
        border39.setPrefSize(75,51);
        border39.setRight(labelb39);

        Label labels391 = new Label("Voodoo €400");
        labels391.setWrapText(true);
        labels391.setId("labelString");
        labels391.setRotate(270);
        border39.setCenter(labels391);


        Label labelc391 = new Label();
        labelc391.setPrefSize(15,51);
        labelc391.setId("labeldarkblue");
        border39.setLeft(labelc391);

        VBox vbox2 = new VBox();
        vbox2.setPadding(new Insets(0, 0, 0, 0));
        vbox2.setSpacing(1);
        vbox2.setId("vbox2");
        vbox2.getChildren().addAll(border31, border32, border33, border34, border35, border36, border37, border38, border39);

        p2.setRight(vbox2);
        /////////////////////////////////////////////////////////END SQUARE
        ///////////////////////////////////////////////////////p1left
        Label labelplayers = new Label("Player in turn:");
        labelplayers.setPrefSize(150,30);
        labelplayers.setFont(Font.font(20));
        labelplayers.setId("labelplayers");
//////////////////////////////////////////////////////////////plright
        ScrollPane sp2 = new ScrollPane();
        Text text1 = new Text();
        text1.setWrappingWidth(300);
        sp2.setContent(text1);
        sp2.setTranslateY(5);
        sp2.setPrefSize(300,200);
        sp2.setId("sp2");
        sp2.setFitToWidth(true);
        sp2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp2.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Label labeloption = new Label("Options:");
        labeloption.setPrefSize(150,30);
        labeloption.setFont(Font.font(20));
        labeloption.setId("labelplayers");

        //scrollpane for output information
        ScrollPane sp3 = new ScrollPane();
        Text text2 = new Text();
        text2.setWrappingWidth(300);
        sp3.setContent(text2);
        sp3.setTranslateY(5);
        sp3.setPrefSize(300,250);
        sp3.setFitToHeight(true);
        sp3.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp3.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Button gobutton = new Button("Go");
        gobutton.setPrefSize(80,50);
        gobutton.setAlignment(Pos.CENTER);
        gobutton.setFont(Font.font ("Verdana", 25));

        //textfield for input your choice
        TextField gotext = new TextField();
        gotext.setPromptText("Enter Your Option");

        Button manage = new Button("Manage");
        manage.setPrefSize(300,50);
        manage.setFont(Font.font ("Verdana", 25));
        manage.setId("manage");

        VBox vbox4 = new VBox(10);
        vbox4.setPadding(new Insets(10, 5, 10, 5));
        vbox4.getChildren().addAll(labelplayers,sp2,labeloption,sp3,gotext,gobutton,manage);

        p1left.setCenter(vbox4);
///////////////////////////////////////////////////////////////p1right
        Button rolldice = new Button("Roll Dice");
        rolldice.setPrefSize(300,50);
        rolldice.setFont(Font.font ("Verdana", 25));
        rolldice.setId("rolldice");
        rolldice.setAlignment(Pos.TOP_CENTER);
        Label test1 = new Label("test");
        test1.setWrapText(true);

        ScrollPane sp1 = new ScrollPane();
        Text text = new Text();
        text.setWrappingWidth(300);
        sp1.setContent(text);
        sp1.setTranslateY(5);
        sp1.setPrefSize(300,500);
        sp1.setFitToWidth(true);
        sp1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp1.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        VBox vbox3 = new VBox(45);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setPadding(new Insets(10, 5, 10, 5));
        vbox3.getChildren().addAll(sp1,rolldice);

        p1right.setCenter(vbox3);

        //action handler for rolldice button
        rolldice.setOnAction(e -> {
            g.turn();
            text.setText(g.turnString);
            text1.setText(g.infoBox());
            l.setText(g.positionsString());
            test1.setText(g.positionsString());
            Label[] labelPos = {
                    labelb0, labelb1, labelb2, labelb3, labelb4, labelb5, labelb6, labelb7, labelb8, labelb9, labelb10,
                    labelb11, labelb12, labelb13, labelb14, labelb15, labelb16, labelb17, labelb18, labelb19, labelb20,
                    labelb21, labelb22, labelb23, labelb24, labelb25, labelb26, labelb27, labelb28, labelb29, labelb30,
                    labelb31, labelb32, labelb33, labelb34, labelb35, labelb36, labelb37, labelb38, labelb39};
            for(int i = 0;i<40;i++){
                labelPos[i].setText(g.usersAtSquare(i));
                labelPos[i].setWrapText(true);
            }
            if(g.waitingForBuyingInput()) {
                text2.setText(g.getBuyString());
            }
        });

        //action handler for go button
        gobutton.setOnAction(e-> {
            final String ryan = gotext.getText();
            g.sendInput(ryan);
            gotext.setText("");
            if(g.managing){
                text2.setText(g.getManageString());
            } else {
                text2.setText(g.getOptionBox());
            }
            text.setText(g.turnString);
        });

        //action handler for manage button
        manage.setOnAction(e ->{
            if(g.getNextUser() instanceof HumanUser) {
                //check if next player is human
                g.manage1();
                text2.setText(g.getManageString());
            } else {
                g.turnString += "\nNot your turn";
                text.setText(g.turnString);
            }
        });
        labelb0.setWrapText(true);

        scene2 = new Scene(p1, 1210, 610);
        String mainStyle2 = this.getClass().getResource("mainStyle.css").toExternalForm();
        scene2.getStylesheets().add(mainStyle2);
        window.setTitle("Monopoly Men");
        window.setScene(scene1);
        window.setHeight(640);
        window.setWidth(1215);
        window.setResizable(false);
        window.show();
    }

    @Override
    public void handle(ActionEvent event) {
    }
}