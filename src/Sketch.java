import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Your Name
 */
public class Sketch extends PApplet {
    int rectX = (int) random(40, 360);
    int rectY = -20;    

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }

    @Override
    public void settings() {
        size(600, 400); 
    }

    @Override
    public void setup() {
        
    }

    @Override
    public void draw() {
        background(244);
        fallingRectangle();

    }

    /** Additional helper methods below */
    private void fallingRectangle() {
            rect(rectX, rectY, 40, 20);
            if (rectY < 620){
                rectY += 10;
            }
    }
}
