package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ChemLevelController extends Chemistry2Controller {
    public static int level;

    @FXML
    private ImageView resetBtn;

    public ChemLevelController() {
        super();
        System.out.println("ok");
        createLevel();

    }

        void createLevel(){
            System.out.println(level);
            switch (level){
                case 1:
                {
                    helpSingleText = "Phản ứng hóa hợp là phản ứng hóa học của hai hay nhiều chất và chỉ tạo thành một chất sản phẩm\n";
                    text = "Hãy tạo ra Fe3O4";
                    resId = 64;
                    bar.add(60);
                    bar.add(58);
                    bar.add(56);
                    bar.add(60);
                    bar.add(54);
                    bar.add(56);
                    bar.add(63);
                    bar.add(61);
                    bar.add(62);
                    break;

                }
                case 2 : {
                    helpSingleText="Phản ứng phân hủy là phản ứng hóa học của một chất ban đầu bị phân hủy thành hai hay nhiều chất khác nhau";
                    text = "Hãy tạo H2";
                    resId = 56;
                    bar.add(64);
                    bar.add(84);
                    bar.add(57);
                    bar.add(60);
                    bar.add(59);
                    bar.add(56);
                    bar.add(66);
                    bar.add(61);
                    bar.add(64);
                    break;
                }
                case 3 : {
                    helpSingleText="Phản ứng oxi hóa khử là phản ứng hóa học mà các chất tham gia có xảy ra sự oxi hóa và sự khử";
                    text = "Hãy tạo ra NaOH";
                    resId = 61;
                    bar.add(65);
                    bar.add(58);
                    bar.add(56);
                    bar.add(60);
                    bar.add(54);
                    bar.add(56);
                    bar.add(58);
                    bar.add(60);
                    bar.add(57);
                    System.out.println("vcl");
                    break;

                }
                case 4 : {
                    helpSingleText = "Phản ứng thế là phản ứng giữa các đơn chất và hợp chất";
                    text = "Hãy tạo ra Hcl";
                    resId = 66;
                    bar.add(58);
                    bar.add(59);
                    bar.add(57);
                    bar.add(60);
                    bar.add(62);
                    bar.add(63);
                    bar.add(65);
                    bar.add(61);
                    bar.add(67);
                    break;
                }
            }}


    public void resetGame() {
        try {

            Scene main = sceneUtil.loadScene("/layout/ChemistryGame.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToMain() {
        try {
            Scene main = sceneUtil.loadScene("/layout/SelectedChemistry.fxml");
            sceneUtil.showScene(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onClose() {
        knowledgeBox.setVisible(!knowledgeBox.isVisible());
        knowledgeBox.setDisable(true);
    }


}
