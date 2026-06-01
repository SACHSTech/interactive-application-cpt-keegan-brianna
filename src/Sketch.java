import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Keegan
 * @author Brianna
 */
public class Sketch extends PApplet {

    int[] rectX = new int [10];
    int[] rectY = new int [10];
    int cauldronX = 300;
    int cauldronY = 350;
    int score = 0;

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }

    @Override
    public void settings() {
        size(600, 400); 
    }

    @Override
    public void setup() {
        rectangleSpawnLocation(); // In setup to help get each rectangle from the array
    }

    @Override
    public void draw() {
        background(244);
        fallingRectangle(3);
        cauldron();
        moveCauldron();
        collectObjects();


    }
    /**
     * Creates multiple rectangles from 0-9 and gives it a random x and y value. The -400 y is so the blocks spawn at different heights and fall at different levels
     * Width / 40 makes it so there's columns. (600/40 = 15) Then once it generates a random number (3, 4, 2, 5, 15, etc) it'll * it by 40 to fit the entire width
     */

    

    private void rectangleSpawnLocation() {
        fill(23, 23, 32);
         for (int rectMultiple = 0; rectMultiple < rectX.length; rectMultiple++) {


            rectX[rectMultiple] = (int) random(0, width / 40) * 40;
            rectY[rectMultiple] = (int) random(-1500, 0);
        }
    }
    private void fallingRectangle(int speed) {
        fill(23,23,23);
        for (int rectMultiple = 0; rectMultiple < rectX.length; rectMultiple++){
            rect(rectX[rectMultiple], rectY[rectMultiple], 40, 20); // creates the rectangle
            rectY[rectMultiple] += speed;
        }


    }

    public void collectObjects() {
        for (int rectMultiple = 0; rectMultiple < rectX.length; rectMultiple++) {
            // Must be within a specific y coordinate at all times, and mnust be within the caludron's bondaries hence the -60, +60
            if (rectY[rectMultiple] >= 290 && rectY[rectMultiple] <= 320 && rectX[rectMultiple] >= cauldronX - 60 && rectX[rectMultiple] <= cauldronX + 60) {

                score++;

                // Respawn rectangle at top so it doesn't continue falling
                rectY[rectMultiple] = (int) random(-1500, 0);
                rectX[rectMultiple] = (int) random(0, width / 40) * 40;

            }
        }

        fill(0);
        textSize(20);
        text("Score: " + score, 20, 30);
}
    

    public int cauldron( )
    {
      
        //pot
        noStroke();
        fill(42, 40, 46);
        circle(cauldronX,cauldronY,100);
        ellipse(cauldronX, 310, 120, 30);
        //content
        noStroke();
        fill(250,250,250);
        ellipse(cauldronX, 310, 90, 20);
        return cauldronX;
    }

    public void moveCauldron()
    {
            if(keyPressed && key == 'a'  && cauldronX >= 50)
            {
                cauldronX -= 10;
            }

            if(keyPressed && key == 'd'  && cauldronX <= 550)
            {
                cauldronX += 10;
            }
    }

}
