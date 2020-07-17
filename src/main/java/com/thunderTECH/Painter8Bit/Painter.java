package com.thunderTECH.Painter8Bit;

import com.thunderTECH.Painter8Bit.view.Viewer;
import com.thunderTECH.Painter8Bit.view.panels.PainterCanvas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Painter extends Application {
    private static Stage STAGE;
    private static int RECT_SIZE = 11; //SIZExSIZE rect
    private static int CANVAS_WIDTH = 337;
    private static int CANVAS_HEIGHT = 113;
    private static final Color GRID_LINES_COLOR = Color.LIGHTGREY;
    private static final Color RECT_COLOR = Color.TRANSPARENT;

    @Override
    public void start(Stage stage) {
        STAGE = stage;

        Scene scene = new Scene(Viewer.GET_VIEW_PANE(),1280,720);
        stage.setScene(scene);
        stage.setTitle("[8BitPainter]");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void SAVE_PAINTER_CANVAS_IMAGE_AS_PNG(PainterCanvas painterCanvas) {
        SAVE_PROJECT(painterCanvas);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            try {
                ImageIO.write(painterCanvas.getSnapshotImage(), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void SAVE_PROJECT(PainterCanvas painterCanvas) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(STAGE);
        //Filter for saving in .ser file format
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);

        String pathToFile = selectedFile.getPath();

        painterCanvas.saveCanvas(pathToFile);
    }

    public static void LOAD_PROJECT(PainterCanvas painterCanvas) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(STAGE);
        //Filter for showing only .ser files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);

        String pathToFile = selectedFile.getPath();

        painterCanvas.loadCanvas(pathToFile);
    }

    public static int GET_CANVAS_WIDTH() {
        return CANVAS_WIDTH;
    }

    public static int GET_CANVAS_HEIGHT() {
        return CANVAS_HEIGHT;
    }

    public static int GET_RECT_SIZE() {
        return RECT_SIZE;
    }

    public static Color GET_GRID_LINES_COLOR() {
        return GRID_LINES_COLOR;
    }

    public static Color GET_RECT_COLOR() {
        return RECT_COLOR;
    }
}
