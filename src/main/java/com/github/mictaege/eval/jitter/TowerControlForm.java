package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ResourceBundle;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static java.util.Optional.ofNullable;
import static java.util.ResourceBundle.getBundle;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.getDefault;
import static javafx.scene.text.FontWeight.EXTRA_BOLD;

public class TowerControlForm {

    public static final ResourceBundle RES = getBundle(TowerControlForm.class.getName());

    private ObjectProperty<BearerTypeModel> model;

    private GridPane root;
    private Label bearerField;
    @OnlyIf(ESA)
    private Label constructionYearField;
    private Label spaceShipField;

    public TowerControlForm() {
        super();
        init();
    }

    @Fork(ifActive = ESA, to = "initEsa")
    @Fork(ifActive = NASA, to = "initDefault")
    @Fork(ifActive = ROSKOSMOS, to = "initDefault")
    private void init() {
        initDefault();
    }


    @OnlyIf({NASA, ROSKOSMOS})
    private void initDefault() {
        model = new SimpleObjectProperty<>(new BearerTypeModel(null));
        model.addListener((observable, oldValue, newValue) -> ofNullable(newValue).ifPresent(m -> {
            bearerField.setText(m.getName());
            spaceShipField.setText(m.getSpaceShip());
        }));
        root = new GridPane();
        root.setHgap(10.0);
        root.setVgap(10.0);

        final Label title = new Label(RES.getString("title"));
        title.setFont(font(getDefault().getFamily(), EXTRA_BOLD, 14.0));
        root.add(title, 0, 0);

        root.add(new Label(RES.getString("bearer")), 0, 1);
        bearerField = new Label();
        root.add(bearerField, 1, 1);

        root.add(new Label(RES.getString("ship")), 0, 2);
        spaceShipField = new Label();
        root.add(spaceShipField, 1, 2);
    }

    @OnlyIf(ESA)
    private void initEsa() {
        model = new SimpleObjectProperty<>(new BearerTypeModel(null));
        model.addListener((observable, oldValue, newValue) -> ofNullable(newValue).ifPresent(m -> {
            bearerField.setText(m.getName());
            constructionYearField.setText(Integer.toString(m.getConstructionYear()));
            spaceShipField.setText(m.getSpaceShip());
        }));
        root = new GridPane();
        root.setHgap(10.0);
        root.setVgap(10.0);

        final Label title = new Label(RES.getString("title"));
        title.setFont(font(getDefault().getFamily(), EXTRA_BOLD, 14.0));
        root.add(title, 0, 0);

        root.add(new Label(RES.getString("bearer")), 0, 1);
        bearerField = new Label();
        root.add(bearerField, 1, 1);

        root.add(new Label(RES.getString("constructionYear")), 0, 2);
        constructionYearField = new Label();
        root.add(constructionYearField, 1, 2);

        root.add(new Label(RES.getString("ship")), 0, 3);
        spaceShipField = new Label();
        root.add(spaceShipField, 1, 3);
    }

    public GridPane getRoot() {
        return root;
    }

    public ObjectProperty<BearerTypeModel> getModel() {
        return model;
    }
}
