package controller;

import elementarium.models.Element;
import elementarium.models.Result;
import elementarium.utils.InitialNumberElement;
import elementarium.utils.SceneUtil;
import elementarium.utils.animation.Animation;
import elementarium.utils.automatic_load_data.AutomaticLoadData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Chemistry2Controller {
    @FXML
    private GridPane gridPane;

    @FXML
    private ListView<ImageView> listView;
    protected List<Element> elements = new ArrayList<Element>();

    protected List<Integer> bar = new ArrayList<Integer>();

    public static final int ELEMENT_WIDTH = 80;
    public static final int ELEMENT_HEIGHT = 80;
    protected SceneUtil sceneUtil = SceneUtil.getInstance();


    public void setup () {
        bar.add(2);
        bar.add(58);
        bar.add(56);
        bar.add(60);
        bar.add(54);
        bar.add(56);
        bar.add(58);
        bar.add(60);
        bar.add(54);
        for(int i : bar){

        }


    }

    public void initialize() {

    }

}
