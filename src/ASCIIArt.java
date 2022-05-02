// https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-console/src/main/java/com/baeldung/asciiart/AsciiArt.java

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class ASCIIArt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("texto: ");
        String string = scanner.nextLine();

        final int START = 0;
        final int BLANK = -16777216;
        final int WIDTH = 80;
        final int HEIGHT = 10;

        Settings settings = new Settings(null, WIDTH, HEIGHT);

        BufferedImage image = getImageIntegerMode(settings.width, settings.height);

        Graphics2D graphics2D = getGraphics2D(image.getGraphics(), settings);
        graphics2D.drawString(string, START, settings.height-2);

        for (int y = 0; y < settings.height; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < settings.width; x++) {
                stringBuilder.append(image.getRGB(x, y) == BLANK ? " " : "-");
            }

            System.out.println(stringBuilder);
        }
    }

    private static BufferedImage getImageIntegerMode(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private static Graphics2D getGraphics2D(Graphics graphics, Settings settings) {
        graphics.setFont(settings.font);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        return graphics2D;
    }
}

class Settings {
    Font font;
    int width;
    int height;

    public Settings(Font font, int width, int height) {
        this.font = font;
        this.width = width;
        this.height = height;
    }
}
