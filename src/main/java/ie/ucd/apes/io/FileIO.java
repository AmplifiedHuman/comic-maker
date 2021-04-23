package ie.ucd.apes.io;

import ie.ucd.apes.entity.xml.ComicWrapper;
import ie.ucd.apes.utils.GifSequenceWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
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
            FileInputStream inputStream = new FileInputStream("src/main/resources/end_screen.png");
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
}
