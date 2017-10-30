package com.github.mictaege.eval.jitter;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

public class TowerControlList {

    public static final ResourceBundle RES = getBundle(TowerControlList.class.getName());

    private final ObservableList<BearerTypeModel> bearers;
    private final TableView<BearerTypeModel> root;

    public TowerControlList() {
        super();
        bearers = observableArrayList();
        root = new TableView<>(bearers);
        root.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        final TableColumn<BearerTypeModel, String> nameCol = new TableColumn<>(RES.getString("name"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        final TableColumn<BearerTypeModel, String> spaceShipCol = new TableColumn<>(RES.getString("spaceShip"));
        spaceShipCol.setCellValueFactory(new PropertyValueFactory<>("spaceShip"));
        root.setItems(bearers);
        root.getColumns().addAll(nameCol, spaceShipCol);
    }

    public ObservableList<BearerTypeModel> getBearers() {
        return bearers;
    }

    public TableView<BearerTypeModel> getRoot() {
        return root;
    }
}
