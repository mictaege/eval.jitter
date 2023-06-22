package com.github.mictaege.eval.jitter;

import static java.util.Optional.ofNullable;
import static java.util.ResourceBundle.getBundle;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TowerControlApp extends Application {

    public static final ResourceBundle RES = getBundle(TowerControlApp.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        final TowerControlList list = new TowerControlList();
        final List<BearerTypeModel> types = new Dao().findBearers().stream()
                .map(BearerTypeModel::new)
                .collect(Collectors.toList());
        list.getBearers().addAll(types);

        final VBox details = new VBox();
        details.setPadding(new Insets(10.0));
        details.setSpacing(3.0);

        final TowerControlForm form = new TowerControlForm();
        form.getModel().bind(list.getRoot().getSelectionModel().selectedItemProperty());
        form.getRoot().setPadding(new Insets(0.0, 0.0, 40.0, 0.0));
        details.getChildren().add(form.getRoot());

        final BearerTypeImgView imgView = new BearerTypeImgView();

        final Button btn = new Button(RES.getString("launch"));
        btn.setOnAction(e -> {
            final BearerTypeModel model = ofNullable(form.getModel().get())
                    .orElse(new BearerTypeModel(null));
            final ImageView img = new ImageView(model.getImg());
            img.fitHeightProperty().setValue(300.0);
            img.fitWidthProperty().setValue(300.0);
            img.setPreserveRatio(true);
            imgView.replaceChildren(img);
        });
        details.getChildren().add(btn);

        details.getChildren().add(imgView.getRoot());

        final SplitPane root = new SplitPane(list.getRoot(), details);
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

}
