package ie.ucd.apes.utils;

import ie.ucd.apes.entity.PruneLevel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class ColorUtils {
    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {-1, 1}};

    public static Color generateColor(int r, int g, int b) {
        return new Color(r / 255.0, g / 255.0, b / 255.0, 1.0);
    }

    public static void changeColor(ImageView imageView, Color c1, Color c2, PruneLevel pruneLevel) {
        if (imageView == null) {
            return;
        }
        Image image = imageView.getImage();
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = wImage.getPixelWriter();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        boolean[][] visited = new boolean[width][height];
        for (boolean[] array : visited) {
            Arrays.fill(array, false);
        }

        int range = 1;
        boolean enablePruning = false;
        switch (pruneLevel) {
            case LOW:
                enablePruning = true;
                break;
            case MEDIUM:
                enablePruning = true;
                range = 3;
        }

        for (int readX = 0; readX < width; ++readX) {
            for (int readY = 0; readY < height; ++readY) {
                Color color = pixelReader.getColor(readX, readY);
                if (!visited[readX][readY]) {
                    if (color.equals(c1)) {
                        bfs(visited, readX, readY, width, height, pixelReader, pixelWriter, c1, c2, enablePruning, range);
                    } else {
                        pixelWriter.setColor(readX, readY, color);
                    }
                }
            }
        }
        imageView.setImage(wImage);
    }

    private static boolean isValidPixel(int x, int y, double maxX, double maxY) {
        return x >= 0 && y >= 0 && x < (int) maxX && y < (int) maxY;
    }

    private static void bfs(boolean[][] visited, int i, int j, int maxW, int maxH,
                            PixelReader pixelReader, PixelWriter pixelWriter, Color c1, Color c2,
                            boolean enablePruning, int range) {
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(Arrays.asList(i, j));
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            List<Integer> cur = queue.poll();
            int readX = cur.get(0);
            int readY = cur.get(1);
            // check all neighbours
            Color currentColor = pixelReader.getColor(readX, readY);
            for (int k = 1; k <= range; k++) {
                for (int[] direction : directions) {
                    int offsetX = readX + direction[0] * k;
                    int offsetY = readY + direction[1] * k;
                    if (isValidPixel(offsetX, offsetY, maxW, maxH) && !visited[offsetX][offsetY]) {
                        Color offsetColor = pixelReader.getColor(offsetX, offsetY);
                        if (enablePruning && currentColor.equals(c1) && areColoursSimilar(offsetColor, currentColor)) {
                            visited[offsetX][offsetY] = true;
                            pixelWriter.setColor(offsetX, offsetY, c2);
                            queue.offer(Arrays.asList(offsetX, offsetY));
                        } else if (!enablePruning && offsetColor.equals(c1)) {
                            visited[offsetX][offsetY] = true;
                            pixelWriter.setColor(offsetX, offsetY, c2);
                            queue.offer(Arrays.asList(offsetX, offsetY));
                        }
                    }
                }
            }
        }
    }

    // safety check to keep black edges intact
    private static boolean areColoursSimilar(Color c1, Color c2) {
        double euclideanDistance = Math.sqrt(Math.pow(c1.getRed() - c2.getRed(), 2) +
                Math.pow(c1.getBlue() - c2.getBlue(), 2) + Math.pow(c1.getGreen() - c2.getGreen(), 2));
        return euclideanDistance <= 0.80;
    }
}
