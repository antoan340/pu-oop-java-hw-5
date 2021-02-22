package Screen;



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
        /**
         * @param "Инициализацията на игралното поле заедно със всички тайлове"
         * @author Antoan
         */
        public ScreenSummon() {

            setTitle(randomСerialНumber(10));
            this.setSize(700, 700);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
            this.addMouseListener(this);
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

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    ScreenTile tile = new ScreenTile(row, col);
                    tile.render(g);
                }
            }
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    renderScreenPiece(g, row, col);
                }
            }

        }

        /**
         * @param "Принтирането на Играча и игралните полета"
         * @author Antoan
         */
        private void renderScreenPiece(Graphics g, int row, int col) {

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

