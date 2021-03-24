package ie.ucd.apes.utils;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class ColorChange {
    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {-1, 1}};

    public static Color generateColor(int r, int g, int b) {
        return new Color(r / 255.0, g / 255.0, b / 255.0, 1.0);
    }

    public void changeColor(ImageView imageView, Color c1, Color c2, boolean isFuzzy) {
        if (imageView == null) {
            return;
        }
        Image image = imageView.getImage();
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int readX = 0; readX < image.getWidth(); ++readX) {
            for (int readY = 0; readY < image.getHeight(); ++readY) {
                Color color = pixelReader.getColor(readX, readY);
                if (color.equals(c1)) {
                    pixelWriter.setColor(readX, readY, c2);
                    // need to nuke closest non target pixels
                    if (isFuzzy) {
                        for (int i = 1; i <= 2; i++) {
                            for (int[] direction : directions) {
                                int offsetX = readX + i * direction[0];
                                int offsetY = readY + i * direction[1];
                                if (isValidPixel(offsetX, offsetY, image.getWidth(), image.getHeight())) {
                                    Color offsetColor = pixelReader.getColor(offsetX, offsetY);
                                    if (areColoursSimilar(offsetColor, c1)) {
                                        pixelWriter.setColor(offsetX, offsetY, c2);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    pixelWriter.setColor(readX, readY, color);
                }
            }
        }
        imageView.setImage(wImage);
    }

    private boolean isValidPixel(int x, int y, double maxX, double maxY) {
        return x >= 0 && y >= 0 && x < (int) maxX && y < (int) maxY;
    }

    private boolean areColoursSimilar(Color c1, Color c2) {
        double euclideanDistance = Math.sqrt(Math.pow(c1.getRed() - c2.getRed(), 2) +
                Math.pow(c1.getBlue() - c2.getBlue(), 2) + Math.pow(c1.getGreen() - c2.getGreen(), 2));
        return euclideanDistance <= 0.4 && euclideanDistance > 0;
    }

}
