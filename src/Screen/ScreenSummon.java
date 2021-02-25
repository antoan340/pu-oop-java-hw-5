package Screen;
import Pixel.ArrayList;
import Pixel.DeadPixel;
import Pixel.HealthyPixel;
import Pixel.NearDeathPixel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        int colorOption,deadPixelCount=0,checkCount=0;
        double pixelOption;
        int lifeCount=3;
        Color pixelColor;
        private static final ArrayList<String> healthyCollection = new ArrayList<>();
        private static final ArrayList<String> deathCollection = new ArrayList<>();
        private static int phonesChecked=0;

        /**
         * @param Основата на телефона
         * @author Antoan
         */
        public ScreenSummon() {
            int row=-1,col=0;
            this.dPixel = new DeadPixel[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
            this.nPixel = new NearDeathPixel[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
            this.hPixel = new HealthyPixel[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
            summonPixel(row,col);
            String number = randomСerialНumber(10);
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                 onExit(deadPixelCount,number);
                }
            });
            setTitle(number);
            this.setSize(700, 700);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setVisible(true);
            this.addMouseListener(this);

        }
        /**
         * @param Даване на възможност да се проверят няколко различни телефона един след друг
         * @author Antoan
         */
        public static void onExit(int deadPixels,String title) {

            if(deadPixels>2048){
                deathCollection.add(title);
            } else if(deadPixels >= 0) {
                healthyCollection.add(title);
            }
            if(phonesChecked <4){
                phonesChecked++;
                ScreenSummon screenSummon = new ScreenSummon();
            } else{
                System.out.println("Good phone collection");
                System.out.println(" ");
                healthyCollection.display();
                System.out.println(" ");
                System.out.println("Bad phones collection");
                System.out.println(" ");
                deathCollection.display();
            }
        }
        /**
         * @param Създаването на всички пиксели на случаен принцип
         * @author Antoan
         */
        public  void summonPixel(int row, int col){
            do {
                spawn (row, col);
                spawnCounter--;
            }while (spawnCounter>0);
        }
        /**
         * @param Генерирането на случаен сериен код за всеки телефон
         * @author Antoan
         */
        public static String randomСerialНumber(int count) {
            StringBuilder builder = new StringBuilder();
            while (count-- != 0) {
                int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));

            }
            return builder.toString();
        }
        /**
         * @param Намаляване на големината на метода summonPixel
         * @author Antoan
         */
        public void spawn (int row, int col){
            colorOption = ThreadLocalRandom.current().nextInt(0, 3);
            pixelOption = ThreadLocalRandom.current().nextDouble(0, 11);
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
            if(pixelOption>=0 && pixelOption<2) {
                this.dPixel[row][col] = (new DeadPixel(row, col, pixelColor));
                deadPixelCount++;
            }
            if(pixelOption>=2 && pixelOption<=5.5) {
                this.nPixel[row][col] = (new NearDeathPixel(row, col, pixelColor));
                deadPixelCount++;
            }
            if(pixelOption> 5.5 && pixelOption<=11)
                this.hPixel[row][col]= (new HealthyPixel(row,col,pixelColor));
        }


        /**
         * @param Маус клик евента в който е направен проверяването на качеството на пикселите
         * @author Antoan
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = this.getBoardDimentionBasedOnCoordinates(e.getY());
            int col = this.getBoardDimentionBasedOnCoordinates(e.getX());
            row-=3;
            col-=1;
            if(hasNearDeathPixel(row,col)&&nPixel[row][col].color!=Color.BLACK) {
                lifeCount--;
                if (lifeCount == 0) {
                    nPixel[row][col].color = Color.BLACK;
                    lifeCount=3;
                    checkCount++;
                }
            }
            if(hasDeadPixel(row,col)&&dPixel[row][col].color!=Color.BLACK){
                dPixel[row][col].color=Color.BLACK;
                lifeCount=3;
                checkCount++;
            }
            repaint();
            if (hasHealthyPixel(row,col))
                checkCount++;
            if (checkCount==4096){
                if(deadPixelCount>2048)
                Modal.render(this, "Внимание", "Телефона и успешно проверен: Неработещ екран");
                else  Modal.render(this, "Внимание", "Телефона и успешно проверен: Работещ екран");
            }
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
        /**
         * @param Взимането на пиксел от даденната група
         * @author Antoan
         */

        private DeadPixel getDeadPixel(int row, int col) {
            return this.dPixel[row][col];

        }
        /**
         * @param Проверяване дали на тази позиция има пиксел от дадената група
         * @author Antoan
         */
        private boolean hasDeadPixel(int row, int col) {
            return this.getDeadPixel(row, col) != null;
        }
        /**
         * @param Взимането на пиксел от даденната група
         * @author Antoan
         */

        private HealthyPixel getHealthyPixel(int row, int col) {
            return this.hPixel[row][col];

        }
        /**
         * @param Проверяване дали на тази позиция има пиксел от дадената група
         * @author Antoan
         */
        private boolean hasHealthyPixel(int row, int col) {
            return this.getHealthyPixel(row, col) != null;
        }
        /**
         * @param Взимането на пиксел от даденната група
         * @author Antoan
         */

        private NearDeathPixel getNearDeathPixel(int row, int col) {
            return this.nPixel[row][col];

        }
        /**
         * @param Проверяване дали на тази позиция има пиксел от дадената група
         * @author Antoan
         */
        private boolean hasNearDeathPixel(int row, int col) {
            return this.getNearDeathPixel(row, col) != null;
        }
        /**
         * @param Рендърването на всеки вид пиксел
         * @author Antoan
         */
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

