package Screen;

import java.awt.*;

public class ScreenTile {
    /**
     * @author Antoan
     * @param "Създавам си цвят"
     */
    public static final int TILE_SIZE = 10;

    private float row;
    private float col;
    private float tileSize;

    /**
     * @param "Инициализиране на стойностите за дисплея"
     * @author Antoan
     */
    public ScreenTile(float row, float col) {

        this.row = row;
        this.col = col;
        this.tileSize = 10
        ;
    }

    /**
     * @param "Обединяване на блока който случи за платно на което да се поставят пикселите "
     * @author Antoan
     */
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(8, 31, 640, 640);
    }
}


