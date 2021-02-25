package Pixel;
import Screen.ScreenTile;

import java.awt.*;
    public class HealthyPixel extends Figure {


        /**
         * @param "Задаване на параметри на пикселите"
         * @author Antoan
         */
        public HealthyPixel(int row, int col, Color color) {
            super();
            this.row = row;
            this.col = col;
            this.color = color;

        }

        /**
         * @param "Визуализиране на пикселите"
         * @author Antoan
         */
        public void render(Graphics g) {
            int x = this.col * ScreenTile.TILE_SIZE;
            int y = this.row * ScreenTile.TILE_SIZE;
            g.setColor(this.color);
            g.fillRect(x + 9, y + 32, 8, 8);
        }

    }

