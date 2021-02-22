package Screen;

import java.awt.*;

public class ScreenTile {
    /**
     *
     * @author Antoan
     * @param "Създавам си цвят"
     */
    public static final int TILE_SIZE = 50;

    private float row;
    private float col;
    private float tileSize;

    /**
     *
     * @author Antoan
     * @param "Инициализиране на стойностите за игралната плочка"
     */
    public ScreenTile(float row, float col) {

        this.row        = row;
        this.col        = col;
        this.tileSize   = 10;
    }
    /**
     *
     * @author Antoan
     * @param "Обединяване на 2та метода за визоализиране в едно"
     */
    public void render(Graphics g){
        RenderBorders(g);

    }

    /**
     *
     * @author Antoan
     * @param "Визуализиране на решетката на полето"
     */
    public void RenderBorders(Graphics g){
        g.setColor(Color.black);
        for (int i=31; i<=671 ;i+=10){
            for(int j=8;j<=648;j+=10){
                if(i<=661&&j<=638) {
                    g.fillRect(j, i, 1, 10);
                    g.fillRect(j, i, 10, 1);
                }  if(i==671&&j<648){
                    g.fillRect(j, i, 10, 1);
                }
                if(i<671&&j==648){
                    g.fillRect(j, i, 1, 10);
                }

            }
        }
    }
}