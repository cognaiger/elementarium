package controller;

import javafx.scene.input.DataFormat;

public class IdData {
    private static IdData instance;
    private DataFormat dataFormat;


    private IdData() {
        dataFormat = new DataFormat("id");
    }

    // Static method to get the IdData instance
    public static IdData getInstance() {
        if (instance == null) {
            synchronized (IdData.class) {
                if (instance == null) {
                    instance = new IdData();
                }
            }
        }
        return instance;
    }

    public DataFormat getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(DataFormat dataFormat) {
        this.dataFormat = dataFormat;
    }

}
