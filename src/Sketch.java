import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Keegan
 * @author Brianna
 */
public class Sketch extends PApplet {

    int[] rectX = new int [70];
    int[] rectY = new int [70];

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }

    @Override
    public void settings() {
        size(600, 400); 
    }

    @Override
    public void setup() {
        makeRectangle(); // In setup to help get each rectangle from the array
    }

    @Override
    public void draw() {
        background(244);
        fallingRectangle(3);

    }
    /**
     * Creates multiple rectangles from 0-9 and gives it a random x and y value. The -400 y is so the blocks spawn at different heights and fall at different levels
     * Width / 40 makes it so there's columns. (600/40 = 15) Then once it generates a random number (3, 4, 2, 5, 15, etc) it'll * it by 40 to fit the entire width
     */
    private void makeRectangle() {
         for (int rectMultiple = 0; rectMultiple < rectX.length; rectMultiple++) {
            rectX[rectMultiple] = (int) random(0, width / 40) * 40;
            rectY[rectMultiple] = (int) random(-1500, 0);
        }
    }
    private void fallingRectangle(int speed) {
        for (int rectMultiple = 0; rectMultiple < rectX.length; rectMultiple++){
            rect(rectX[rectMultiple], rectY[rectMultiple], 40, 20);
            rectY[rectMultiple] += speed;
        }


    }
}
