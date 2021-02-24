package Screen;



import Pixel.DeadPixel;
import Pixel.HealthyPixel;
import Pixel.NearDeathPixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;



    /**
     *
     * @author Antoan
     * @param "проектиране на игралното поле"
     */
    public class ScreenSummon extends JFrame implements MouseListener {
        public static final int TILE_SIDE_COUNT = 64;
        private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private HealthyPixel[][] hPixel;
        private NearDeathPixel[][] nPixel;
        private DeadPixel[][] dPixel;
        int spawnCounter =4096;
        int colorOption,pixelOption;
        Color pixelColor;
        /**
         * @param "Инициализацията на игралното поле заедно със всички тайлове"
         * @author Antoan
         */
        public ScreenSummon() {
            int row=-1,col=0;
            this.dPixel = new DeadPixel[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
            this.nPixel = new NearDeathPixel[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
            this.hPixel = new HealthyPixel[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
            summonPixel(row,col);
            setTitle(randomСerialНumber(10));
            this.setSize(700, 700);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
            this.addMouseListener(this);
        }
        public  void summonPixel(int row, int col){
            do {
                colorOption = ThreadLocalRandom.current().nextInt(0, 3);
                pixelOption = ThreadLocalRandom.current().nextInt(0, 3);
                switch (colorOption) {
                    case 1 -> pixelColor = Color.BLUE;
                    case 2 -> pixelColor = Color.RED;
                    default -> pixelColor = Color.GREEN;
                }
                if(row<63)
                    row++;
                else {
                    row=0;
                    if(col<63)
                        col++;
                    else col=-1;
                }
                switch (colorOption) {
                    case 1 -> this.dPixel[row][col]= (new DeadPixel(row,col,pixelColor));
                    case 2 -> this.hPixel[row][col]= (new HealthyPixel(row,col,pixelColor));
                    default -> this.nPixel[row][col]= (new NearDeathPixel(row,col,pixelColor));
                }
                spawnCounter--;
            }while (spawnCounter>0);
        }
        public static String randomСerialНumber(int count) {
            StringBuilder builder = new StringBuilder();
            while (count-- != 0) {
                int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));

            }
            System.out.println(builder);
            return builder.toString();
        }
        /**
         * @param "Инициализацията на играча"
         * @author Antoan
         */

        /**
         * @param "Инициализирането на случаен принцип на къщите на Баба яга и на непроходимите места"
         * @author Antoan
         */

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = this.getBoardDimentionBasedOnCoordinates(e.getY());
            int col = this.getBoardDimentionBasedOnCoordinates(e.getX());
            row--;

        }


        @Override

        public void paint(Graphics g) {

            super.paint(g);
            ScreenTile tile = new ScreenTile(0, 0);
            tile.render(g);

            for (int row = 0; row < 64; row++) {
                for (int col = 0; col < 64; col++) {
                    renderScreenPiece(g, row, col);
                }
            }

        }


        private DeadPixel getDeadPixel(int row, int col) {
            return this.dPixel[row][col];

        }

        private boolean hasDeadPixel(int row, int col) {
            return this.getDeadPixel(row, col) != null;
        }
        private HealthyPixel getHealthyPixel(int row, int col) {
            return this.hPixel[row][col];

        }

        private boolean hasHealthyPixel(int row, int col) {
            return this.getHealthyPixel(row, col) != null;
        }
        private NearDeathPixel getNearDeathPixel(int row, int col) {
            return this.nPixel[row][col];

        }

        private boolean hasNearDeathPixel(int row, int col) {
            return this.getNearDeathPixel(row, col) != null;
        }
        private void renderScreenPiece(Graphics g, int row, int col) {
            if (this.hasDeadPixel(row, col)) {
                DeadPixel p = (DeadPixel) this.getDeadPixel(row, col);
                p.render(g);

            }
            if (this.hasNearDeathPixel(row, col)) {
                NearDeathPixel p1 = (NearDeathPixel) this.getNearDeathPixel(row, col);
                p1.render(g);

            }
            if (this.hasHealthyPixel(row, col)) {
                HealthyPixel p2 = (HealthyPixel) this.getHealthyPixel(row, col);
                p2.render(g);

            }
        }


        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        private int getBoardDimentionBasedOnCoordinates(int coordinates) {
            return coordinates / ScreenTile.TILE_SIZE;
        }

    }

