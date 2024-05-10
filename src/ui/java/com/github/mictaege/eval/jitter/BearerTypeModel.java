package com.github.mictaege.eval.jitter;

import com.github.mictaege.jitter.api.Fork;
import com.github.mictaege.jitter.api.OnlyIf;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import static com.github.mictaege.eval.jitter.Flavour.ESA;
import static com.github.mictaege.eval.jitter.Flavour.NASA;
import static com.github.mictaege.eval.jitter.Flavour.ROSKOSMOS;
import static java.util.Optional.ofNullable;

public class BearerTypeModel {

    private SimpleStringProperty name;
    private SimpleStringProperty img;
    @OnlyIf(ESA)
    private IntegerProperty constructionYear;
    private SimpleStringProperty spaceShip;

    public BearerTypeModel(final BearerType type) {
        init(type);
    }

    @Fork(ifActive = ESA, to = "initEsa")
    @Fork(ifActive = NASA, to = "initDefault")
    @Fork(ifActive = ROSKOSMOS, to = "initDefault")
    private void init(final BearerType type) {
        initDefault(type);
    }

    @OnlyIf(ESA)
    private void initEsa(final BearerType type) {
        this.name = new SimpleStringProperty(ofNullable(type).map(BearerType::getName).orElse(""));
        this.img = new SimpleStringProperty(ofNullable(type).map(BearerType::getImg).orElse("com/github/mictaege/eval/spoon/example/Default.png"));
        this.constructionYear = new SimpleIntegerProperty(ofNullable(type).map(BearerType::getConstructionYear).orElse(0));
        this.spaceShip = new SimpleStringProperty(ofNullable(type).map(t -> t.getSpaceShip().type().getName()).orElse(""));
    }

    @OnlyIf({NASA, ROSKOSMOS})
    private void initDefault(BearerType type) {
        this.name = new SimpleStringProperty(ofNullable(type).map(BearerType::getName).orElse(""));
        this.img = new SimpleStringProperty(ofNullable(type).map(BearerType::getImg).orElse("com/github/mictaege/eval/spoon/example/Default.png"));
        this.spaceShip = new SimpleStringProperty(ofNullable(type).map(t -> t.getSpaceShip().type().getName()).orElse(""));
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getImg() {
        return img.get();
    }

    public SimpleStringProperty imgProperty() {
        return img;
    }

    public void setImg(String img) {
        this.img.set(img);
    }

    @OnlyIf(ESA)
    public int getConstructionYear() {
        return constructionYear.get();
    }

    @OnlyIf(ESA)
    public IntegerProperty constructionYearProperty() {
        return constructionYear;
    }

    public String getSpaceShip() {
        return spaceShip.get();
    }

    public SimpleStringProperty spaceShipProperty() {
        return spaceShip;
    }

    public void setSpaceShip(String spaceShip) {
        this.spaceShip.set(spaceShip);
    }
}
