package ie.ucd.apes.utils;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class ColorChange {

    // parameters r,g,b = initial colors
    // r1,g1,b1 = colors to be changed to
    public void changeColor(ImageView imageView, int r, int g, int b, int r1, int g1, int b1) {
        Image image = imageView.getImage();
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

        //init pixel reader and writer
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = wImage.getPixelWriter();

        //iterate each pixel of the image row by row
        for (int readY = 0; readY < image.getHeight(); readY++) {
            for (int readX = 0; readX < image.getWidth(); readX++) {
                Color color = pixelReader.getColor(readX, readY);
                if ((int) (color.getRed() * 255) == r && (int) (color.getGreen() * 255) == g && (int) (color.getBlue() * 255) == b) {
                    Color newColor = new Color((double) r1 / 255, (double) g1 / 255, (double) b1 / 255, 1);
                    System.out.println(color.getRed() * 255);
                    pixelWriter.setColor(readX, readY, newColor);
                } else {
                    pixelWriter.setColor(readX, readY, color);
                }
            }
        }

        imageView.setImage(wImage);
    }

}
