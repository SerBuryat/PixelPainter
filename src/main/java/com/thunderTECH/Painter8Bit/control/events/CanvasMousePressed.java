package com.thunderTECH.Painter8Bit.control.events;

import com.thunderTECH.Painter8Bit.ActionBuffer;
import com.thunderTECH.Painter8Bit.model.Pixel;
import com.thunderTECH.Painter8Bit.view.panels.PainterCanvas;
import com.thunderTECH.Painter8Bit.view.panels.instruments.InstrumentPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class CanvasMousePressed implements EventHandler<MouseEvent> {
    private final PainterCanvas painterCanvas;

    public CanvasMousePressed(PainterCanvas painterCanvas) {
        this.painterCanvas = painterCanvas;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        painterCanvas.getCanvas().requestFocus();

        Pixel pixel = painterCanvas.getPixel((int)mouseEvent.getX(), (int)mouseEvent.getY());

        if(pixel != null) {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                ActionBuffer.ADD_TO_BUFFER(pixel.getRectangle());
                InstrumentPane.ADD_LAST_USED_COLOR(painterCanvas.getCurrentRectColor());
                painterCanvas.paint(pixel.getRectangle(), painterCanvas.getCurrentRectColor());
            }

            if (mouseEvent.getButton() == MouseButton.SECONDARY)
                painterCanvas.setCurrentRectColor(pixel.getRectangle().getColor());

            if(mouseEvent.getButton() == MouseButton.MIDDLE) {
                painterCanvas.setCanvasDragX(mouseEvent.getSceneX());
                painterCanvas.setCanvasDragY(mouseEvent.getSceneY());
                
                painterCanvas.setCanvasTranslateX(painterCanvas.getCanvas().getTranslateX());
                painterCanvas.setCanvasTranslateY(painterCanvas.getCanvas().getTranslateY());
            }

        }
    }
}