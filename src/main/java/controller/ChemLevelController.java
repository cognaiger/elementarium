package controller;

import elementarium.models.Element;
import elementarium.models.Result;
import elementarium.utils.SceneUtil;
import elementarium.utils.animation.Animation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import play.Main;

import java.util.ArrayList;
import java.util.List;

public class ChemLevelController extends Chemistry2Controller {
        public static int level;

        public ChemLevelController () {
            super();
            System.out.println("ok");
            createLevel();

        }

        void createLevel(){
            System.out.println(level);
            switch (level){
                case 1:
                {
                    text = "NaOH";
                    bar.add(60);
                    bar.add(58);
                    bar.add(56);
                    bar.add(60);
                    bar.add(54);
                    bar.add(56);
                    bar.add(58);
                    bar.add(60);
                    bar.add(54);
                    System.out.println("vcl");
                }
                case 2 : {
                    text = "Fe3O4";
                    bar.add(60);
                    bar.add(58);
                    bar.add(56);
                    bar.add(60);
                    bar.add(54);
                    bar.add(56);
                    bar.add(63);
                    bar.add(61);
                    bar.add(62);

                }
            }
        }





}
