package hust.project.base.header;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.Objects;

import static hust.project.base.constants.MetricsConstants.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Header extends BorderPane {
    private TextField searchForm;

    public Header() {
//        setStyle("-fx-padding: 10px");
        setPrefWidth(MAIN_WIDTH * 0.9);
        TextField searchForm = new TextField();
        searchForm.setPromptText("Tìm kiếm");
        searchForm.setPrefWidth(MAIN_WIDTH * 0.7);
        searchForm.setPrefHeight(30);
        searchForm.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: #000000; -fx-border-radius: 20");

        VBox userInfo = new VBox();
        userInfo.setSpacing(5);
        userInfo.setAlignment(Pos.CENTER);

        Label label = new Label("Hello, Admin");
        label.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #000000");

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/image/image1.jpg")).toExternalForm()));
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        userInfo.getChildren().addAll(imageView, label);
        HBox headerContainer = new HBox();
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.setSpacing(50);
        headerContainer.getChildren().addAll(searchForm, userInfo);
        setLeft(headerContainer);
    }

}
