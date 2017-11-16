package com.github.mictaege.eval.jitter;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BearerTypeImgView {

    private final Pane root;

    public BearerTypeImgView() {
        root = new Pane();
    }

    public Pane getRoot() {
        return root;
    }

    public void replaceChildren(final Node newChild) {
        root.getChildren().clear();
        root.getChildren().add(newChild);
    }
}
