package com.example.utils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Class made for returning buffered image resources. Loads images from resources folder based on final String chosen.
 * The path for every asset used in this game is saved as a String in this class. Game assets excluding fonts are from a
 * pack on itch.io by pixelfrog, found at the link <a href="https://pixelfrog-assets.itch.io/tiny-swords">...</a>.
 *
 * @author Pardeep Singh Manhas
 */
public class ResourceLoader {
    public static final String KNIGHT_PATH = "entities/knight.png";
    public static final String GOBLIN_PATH = "entities/goblin.png";
    public static final String TNT_PATH = "entities/TNT.png";
    public static final String MEAT_PATH = "entities/meat.png";
    public static final String GOLD_PATH = "entities/gold.png";
    public static final String TREES2_PATH = "entities/trees2.png";
    public static final String END_HOUSE_PATH = "entities/end-house.png";
    public static final String GRASS_PATH = "terrain/grass.png";
    public static final String LEVEL_ONE_PATH = "levels/levels.txt";
    public static final String LEVEL_TEST_PATH = "levels/testlevel.txt";

    public static final String BUTTON_UI = "ui/Button_Set.png";
    public static final String BUTTON_BG = "ui/Button_Background.png";
    public static final String BUTTON_BLUE = "ui/Button_Blue_3Slides.png";
    public static final String BUTTON_BLUEPRESSED = "ui/Button_Blue_3Slides_Pressed.png";
    public static final String BUTTON_RED = "ui/Button_Red_3Slides.png";
    public static final String BUTTON_REDPRESSED = "ui/Button_Red_3Slides_Pressed.png";
    public static final String BUTTON_YELLOW = "ui/Button_Hover_3Slides.png";
    public static final String BUTTON_YELLOWPRESSED = "ui/Button_Disable_3Slides.png";
    public static final String BUTTON_VERTICAL_BG = "ui/Banner_Vertical.png";
    public static final String DEFEAT = "ui/Ribbon_Red_3Slides.png";
    public static final String MENU_TOP_BLUE = "ui/Ribbon_Blue_3Slides.png";
    public static final String MENU_TOP_RED = "ui/Ribbon_Red_3Slides.png";
    public static final String MENU_TOP_YELLOW = "ui/Ribbon_Yellow_3Slides.png";
    public static final String VICTORY = "ui/Ribbon_Blue_3Slides.png";
    //Old London font by Dieter Steffmann from www.dafont.com.
    public static final String MEDIEVAL_FONT = "fonts/BLKCHCRY.ttf";


    /**
     * A static method that returns one of the static String paths of this class as a
     * buffered image, should it exist.
     *
     * @param filename The path to image. Generally one of the static paths of this class.
     * @return An object of Type BufferedImage that contains the image found at the file path, if it exists.
     * If not, returns a null BufferedImage.
     * @throws IOException Throws IOException if the file is not found.
     * @author Pardeep Singh Manhas
     */
    public static BufferedImage getImage(String filename) throws IOException {
        InputStream is = ResourceLoader.class.getResourceAsStream("/" + filename);
        BufferedImage img = null;
        img = ImageIO.read(is);
        return img;
    }

    /**
     * A static method that converts a file comprised of a matrix of integers into a 2d array for
     * the process of level building.
     *
     * @param filename A file path to the level data. The provided file should be a matrix of integers.
     * @return A 2d integer Array that represents the game world.
     */
    public static int[][] getLevel(String filename) {
        InputStream is = ResourceLoader.class.getResourceAsStream("/" + filename);
        ArrayList<String> lines = new ArrayList<>();
        try {
            InputStreamReader isReader = new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isReader);
            for (String line; (line = reader.readLine()) != null; ) {
                String newLine = line.replaceAll(" ", "");
                lines.add(newLine);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        int numberOfColumns = lines.get(0).length();
        int numberOfRows = lines.size();
        int[][] levelData = new int[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                levelData[i][j] = Character.getNumericValue(lines.get(i).charAt(j));
            }
        }
        return levelData;
    }

    public static Font getFont(String filename){
        InputStream is = ResourceLoader.class.getResourceAsStream("/" + filename);
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return font;
    }
}
