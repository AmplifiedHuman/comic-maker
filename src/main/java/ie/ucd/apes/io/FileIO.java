package ie.ucd.apes.io;

import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.entity.html.HTMLWrapper;
import ie.ucd.apes.entity.xml.ComicWrapper;
import ie.ucd.apes.utils.GifSequenceWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class FileIO {
    public static List<String> getFileNames(String path)
            throws URISyntaxException, IOException {
        URL dirURL = FileIO.class.getClassLoader().getResource(path);
        // for development build / IDEs
        if (dirURL != null && dirURL.getProtocol().equals("file")) {
            String[] array = new File(dirURL.toURI()).list();
            assert array != null;
            return Arrays.stream(array).collect(Collectors.toList());
        }
        assert dirURL != null;
        List<String> result = new ArrayList<>();
        // for packaged jar
        if (dirURL.getProtocol().equals("jar")) {
            String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
            JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8));
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(path)) {
                    String entry = name.substring(path.length() + 1);
                    if (!entry.isEmpty()) {
                        result.add(entry);
                    }
                }
            }
        }
        return result;
    }

    public static void exportGIF(File file, List<BufferedImage> images) {
        try {
            ImageOutputStream output = new FileImageOutputStream(file);
            GifSequenceWriter writer = new GifSequenceWriter(output, images.get(0).getType(), 1000, true);
            writer.writeToSequence(images.get(0));
            for (int i = 1; i < images.size(); i++) {
                writer.writeToSequence(images.get(i));
            }
            InputStream inputStream = FileIO.class.getClassLoader().getResourceAsStream("end_screen.png");
            assert inputStream != null;
            Image img = new Image(inputStream, 600, 550, false, false);
            BufferedImage endScreen = SwingFXUtils.fromFXImage(img, null);
            writer.writeToSequence(endScreen);
            writer.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Cannot export GIF");
            e.printStackTrace();
        }
    }

    public static void exportXML(File file, ComicWrapper comicWrapper) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ComicWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(comicWrapper, file);
        } catch (JAXBException e) {
            System.out.println("Cannot export XML");
            e.printStackTrace();
        }
    }

    public static ComicWrapper importXML(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ComicWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (ComicWrapper) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println("Cannot import XML");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isValidCharacterPose(String pose) {
        String imagePath = String.format("/%s/%s.png", Constants.CHARACTER_FOLDER, pose);
        return FileIO.class.getResourceAsStream(imagePath) != null;
    }

    public static List<String> loadTextResource(String fullPath) throws FileNotFoundException {
        InputStream inputStream = FileIO.class.getClassLoader().getResourceAsStream(fullPath);
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        List<String> result = new ArrayList<>();
        while (scanner.hasNextLine()) {
            result.add(scanner.nextLine());
        }
        scanner.close();
        return result;
    }

    public static void exportHTML(String rootPath, HTMLWrapper htmlWrapper) {
        InputStream inputStream = FileIO.class.getClassLoader().getResourceAsStream("html/style.css");
        if (inputStream != null) {
            try {
                // copy css
                Files.copy(inputStream, Paths.get(rootPath + "/style.css"), StandardCopyOption.REPLACE_EXISTING);
                // copy custom font
                File fontFile = new File("src/main/resources/html/AlloyInk-font.otf");
                Files.copy(fontFile.toPath(), Paths.get(rootPath + "/AlloyInk-font.otf"), StandardCopyOption.REPLACE_EXISTING);
                // copy custom background image
                File backgroundFile = new File("src/main/resources/html/background.png");
                Files.copy(backgroundFile.toPath(), Paths.get(rootPath + "/background.png"), StandardCopyOption.REPLACE_EXISTING);
                // copy images
                for (int i = 0; i < htmlWrapper.getImages().size(); i++) {
                    BufferedImage bImage = SwingFXUtils.fromFXImage(htmlWrapper.getImages().get(i), null);
                    File file = new File(String.format("%s/%s.png", rootPath, i + 1));
                    ImageIO.write(bImage, "png", file);
                }
                // write html
                FileWriter writer = new FileWriter(String.format("%s/index.html", rootPath));
                writer.append("<!DOCTYPE html> <html lang=\"en\"> <head> <meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" " +
                        "content=\"IE=edge\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <link rel=\"stylesheet\" " +
                        "href=\"style.css\">");
                // webpage title
                writer.append(String.format("<title>%s</title>", htmlWrapper.getPremise()));
                writer.append("</head><body>");
                // premise
                writer.append(String.format("<h1>%s</h1>", htmlWrapper.getPremise()));
                // add panels
                writer.append("<div class=\"grid\">");
                for (int i = 0; i < htmlWrapper.getImages().size(); i++) {
                    writer.append(String.format("<div class=\"panel\"> <img src=\"%s.png\" /> </div>", i + 1));
                }
                // add end image when number of panels is odd
                if (htmlWrapper.getImages().size() % 2 != 0){
                    File file = new File("src/main/resources/end_screen.png");
                    Files.copy(file.toPath(), Paths.get(rootPath + "/end_screen.png"), StandardCopyOption.REPLACE_EXISTING);
                    writer.append("<div class=\"panel\"> <img src=\"end_screen.png\" /> </div>");
                }
                writer.append("</div></body></html>");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("[Error] Cannot export comic html.");
                e.printStackTrace();
            }
        }
    }
}
