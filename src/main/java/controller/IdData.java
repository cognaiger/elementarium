package controller;

import javafx.scene.input.DataFormat;

public class IdData {
    private static IdData instance;
    private final DataFormat dataFormat;

    private final DataFormat rowIndexFormat;
    private final DataFormat colIndexFormat;

    private IdData() {
        dataFormat = new DataFormat("id");
        rowIndexFormat = new DataFormat("row");
        colIndexFormat = new DataFormat("col");
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

    public DataFormat getRowIndexFormat() {
        return rowIndexFormat;
    }

    public DataFormat getColIndexFormat() {
        return colIndexFormat;
    }
}
