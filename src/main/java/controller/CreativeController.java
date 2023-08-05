package controller;

import elementarium.models.Element;
import elementarium.models.Result;

import elementarium.utils.InitialNumberElement;
import elementarium.utils.SceneUtil;

import elementarium.utils.animation.Animation;
import elementarium.utils.animation.BoomEffect;

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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import play.CreativeGame;
import play.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CreativeController extends DragAndDropWindow {
    public CreativeController() throws SQLException, ClassNotFoundException {
        super();
        initialNumberElements = InitialNumberElement.CREATIVE_MODE;
        elements = AutomaticLoadData.getAllElements();
    }
}
