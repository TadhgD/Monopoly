///////////////////////////////////////////////////////LOGIN PAGE
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(0, 0, 0, 0));
//        grid.setPrefSize(200, 610);
//
//
//        Image image = new Image(getClass().getResourceAsStream("leftlogo.jpg"));
//        Label labellogo = new Label("", new ImageView(image));
//        grid.add(labellogo, 0, 0, 2, 1);
//
//        Label userName = new Label("User Name:");
//        grid.add(userName, 0, 1);
//
//        TextField userTextField = new TextField();
//        grid.add(userTextField, 0, 2);
//
//
//        btn = new Button("Sign in");
//        btn.setId("button1");
//        HBox hbBtn = new HBox(10);
//        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn.getChildren().add(btn);
//        grid.add(hbBtn, 0, 3);
//
//        BorderPane p1left = new BorderPane();
//        p1left.setId("p1left");
//        p1left.setCenter(grid);
//
//        btn.setOnAction((ActionEvent e) -> {
//            //System.out.print(label0.getId());
//        });
//
//        p1.setLeft(p1left);
//////////////////////////////////////////////////////////////
//        p2.setOnMouseMoved((MouseEvent e) ->{
//            p2.setScaleX(2);
//            p2.setScaleY(2);
//        });
//        p2.setOnMouseExited((MouseEvent e) ->{
//            p2.setScaleX(1);
//            p2.setScaleY(1);
//        });