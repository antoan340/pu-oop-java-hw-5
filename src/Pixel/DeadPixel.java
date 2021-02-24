package Pixel;
import Screen.ScreenTile;

import java.awt.*;
public class DeadPixel extends Figure {
    public Color color;

    /**
     * @param "Задаване на параметри на непроходимите местности"
     * @author Antoan
     */
    public DeadPixel(int row, int col, Color color) {
        super();
        this.row = row;
        this.col = col;
        this.color = color;

    }

    /**
     * @param "Визуализиране на непроходимите местности"
     * @author Antoan
     */
    public void render(Graphics g) {
        int x = this.col * ScreenTile.TILE_SIZE;
        int y = this.row * ScreenTile.TILE_SIZE;
        g.setColor(this.color);
        g.fillRect(x + 9, y + 32, 8, 8);
    }

}