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

        System.out.print("símbolo: ");
        String characters = scanner.nextLine();

        String background = "  ";

        System.out.print("texto: ");
        String string = scanner.nextLine();

        final int START = 0;
        final int BLANK = -16777216;
        final int WIDTH = 80; // podia pegar o width do length do texto

        System.out.println("fonte: ");
        System.out.println("1 - DIALOG");
        System.out.println("2 - DIALOG_INPUT");
        System.out.println("3 - Monospaced");
        System.out.println("4 - SansSerif");
        System.out.println("5 - Serif");
        int fontOption = scanner.nextInt();

        String fontName;
        switch (fontOption) {
            case 1:
                fontName = Font.DIALOG;
                break;
            case 2:
                fontName = Font.DIALOG_INPUT;
                break;
            case 3:
                fontName = Font.MONOSPACED;
                break;
            case 4:
                fontName = Font.SANS_SERIF;
                break;
            case 5:
                fontName = Font.SERIF;
                break;
            default:
                throw new IllegalArgumentException("opção de fonte inválida");
        }

        System.out.println("estilo: ");
        System.out.println("1 - simples");
        System.out.println("2 - negrito");
        System.out.println("3 - itálico");
        System.out.println("4 - negrito e itálico");
        int styleOption = scanner.nextInt();

        int style;
        switch (styleOption) {
            case 1:
                style = Font.PLAIN;
                break;
            case 2:
                style = Font.BOLD;
                break;
            case 3:
                style = Font.ITALIC;
                break;
            case 4:
                style = Font.BOLD | Font.ITALIC;
                break;
            default:
                throw new IllegalArgumentException("opção de estilo inválida");
        }

        System.out.print("tamanho (algo entre 12 e 40 é recomendado): ");
        int size = scanner.nextInt();

        Font font = new Font(fontName, style, size);
        BufferedImage image = new BufferedImage(WIDTH, size, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setFont(font);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); // variar essas constantes
        // graphics2D.setColor(Color.BLUE); // not working?
        graphics2D.drawString(string, START, (int) (size * 0.8));

        for (int y = 0; y < size; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < WIDTH; x++) {
                stringBuilder.append(image.getRGB(x, y) == BLANK ? background : characters);
            }

            if (!stringBuilder.toString().trim().isEmpty()) {
                System.out.println(stringBuilder);
            }
        }
    }
}
