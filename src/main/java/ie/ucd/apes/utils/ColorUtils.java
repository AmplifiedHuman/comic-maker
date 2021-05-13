package ie.ucd.apes.utils;

import ie.ucd.apes.entity.PruneLevel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.regex.Pattern;

public class ColorUtils {
    private static final Map<String, Color> hexToColorMap = new HashMap<>();
    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {-1, 1}};

    public static Color generateColor(int r, int g, int b) {
        return new Color(r / 255.0, g / 255.0, b / 255.0, 1.0);
    }

    public static String toHexString(Color c) {
        return String.format("#%s%s%s", formatColorValue(c.getRed()),
                formatColorValue(c.getGreen()), formatColorValue(c.getBlue())).toUpperCase();
    }

    public static Color toFXColor(String hexString) {
        return Color.web(hexString);
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

    //Changes pure white color to transparent
    public static void makeTransparent(ImageView imageView) {
        Color c1 = Color.WHITE;
        Color c2 = Color.TRANSPARENT;
        changeColor(imageView, c1, c2, PruneLevel.MEDIUM);
    }

    // safety check to keep black edges intact
    private static boolean areColoursSimilar(Color c1, Color c2) {
        double euclideanDistance = Math.sqrt(Math.pow(c1.getRed() - c2.getRed(), 2) +
                Math.pow(c1.getBlue() - c2.getBlue(), 2) + Math.pow(c1.getGreen() - c2.getGreen(), 2));
        return euclideanDistance <= 0.80;
    }

    private static String formatColorValue(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public static Color getColorFromHexOrColorName(String colorString) {
        if (hexToColorMap.isEmpty()) {
            initHexToColorMap();
        }
        if (hexToColorMap.containsKey(colorString)) {
            return hexToColorMap.get(colorString);
        } else if (validateHexString(colorString)) {
            return toFXColor(colorString);
        } else {
            return null;
        }
    }

    private static boolean validateHexString(String s) {
        return Pattern.matches("^#[0-9A-F]{6}$", s);
    }

    private static void initHexToColorMap() {
        hexToColorMap.put("brown", toFXColor("#964B00"));
        hexToColorMap.put("blue", Color.BLUE);
        hexToColorMap.put("pink", Color.PINK);
        hexToColorMap.put("white", toFXColor("#F5FFFA"));
        hexToColorMap.put("cyan", Color.CYAN);
        hexToColorMap.put("gray", Color.GRAY);
        hexToColorMap.put("grey", Color.GRAY);
        hexToColorMap.put("green", Color.GREEN);
        hexToColorMap.put("orange", Color.ORANGE);
        hexToColorMap.put("magenta", Color.MAGENTA);
        hexToColorMap.put("red", Color.RED);
        hexToColorMap.put("yellow", Color.YELLOW);
        hexToColorMap.put("dark gray", Color.DARKGRAY);
        hexToColorMap.put("dark grey", Color.DARKGREY);
        hexToColorMap.put("light gray", Color.LIGHTGRAY);
        hexToColorMap.put("light grey", Color.LIGHTGREY);
        hexToColorMap.put("gold", toFXColor("#FFD700"));
        hexToColorMap.put("olive", toFXColor("#FFE4B5"));
        hexToColorMap.put("violet", toFXColor("#EE82EE"));
        hexToColorMap.put("silver", toFXColor("#C0C0C0"));
        hexToColorMap.put("slate", toFXColor("#778899"));
        hexToColorMap.put("dark slate", toFXColor("#2F4F4F"));
        hexToColorMap.put("purple", toFXColor("#800080"));
        hexToColorMap.put("plum", toFXColor("#DDA0DD"));
        hexToColorMap.put("lavender", toFXColor("#E6E6FA"));
        hexToColorMap.put("crimson", toFXColor("#DC143C"));
        hexToColorMap.put("brick red", toFXColor("#B22222"));
        hexToColorMap.put("maroon", toFXColor("#800000"));
        hexToColorMap.put("lime green", toFXColor("#32CD32"));
        hexToColorMap.put("forest green", toFXColor("#228B22"));
        hexToColorMap.put("sea green", toFXColor("#8FBC8F"));
        hexToColorMap.put("khaki", toFXColor("#F0E68C"));
        hexToColorMap.put("aquamarine", toFXColor("#7FFFD4"));
        hexToColorMap.put("lemon chiffon", toFXColor("#FFFACD"));
        hexToColorMap.put("light yellow", toFXColor("#FFFFCC"));
        hexToColorMap.put("light pink", toFXColor("#FFB6C1"));
        hexToColorMap.put("hot pink", toFXColor("#FF69B4"));
        hexToColorMap.put("dark yellow", toFXColor("#CCCC00"));
        hexToColorMap.put("indigo", toFXColor("#4B0082"));
        hexToColorMap.put("coral", toFXColor("#FF7F50"));
        hexToColorMap.put("chocolate", toFXColor("#D2691E"));
        hexToColorMap.put("tomato", toFXColor("#FF6347"));
        hexToColorMap.put("dark orange", toFXColor("#FF8C00"));
        hexToColorMap.put("blond", toFXColor("#FAF0BE"));
        hexToColorMap.put("black", Color.BLACK);
        hexToColorMap.put("bald", Color.WHITE);
    }
}
