import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Keegan
 * @author Brianna
 */
public class Sketch extends PApplet {

    int[] rectX = new int [10];
    int[] rectY = new int [10];
    int[] rectXSpecial = new int [1];
    int[] rectYSpecial = new int [1];
    int cauldronX = 300;
    int cauldronY = 350;
    int score = 0;
    int[] powerUpColors = new int[4]; //array for all 4 of the possible powerUp colors 
    int randomColor;
    
    

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }

    @Override
    public void settings() {
        size(600, 400); 
    }

    @Override
    public void setup() {
        
        //powerup color master list 
        powerUpColors[0] = color(255, 0, 0);   // Red
        powerUpColors[1] = color(0, 255, 0);   // Green
        powerUpColors[2] = color(0, 0, 255);   // Blue
        powerUpColors[3] = color(255, 255, 0); // Yellow
        randomColor = (int) random(0,powerUpColors.length);// uses random to get a random color from the powerUpColor array

        rectangleSpawnLocation(); // In setup to help get each rectangle from the array
        }

    @Override
    public void draw() {
        background(244);
        fallingRectangle(3);
        cauldron();
        moveCauldron();
        collectObjects();

        fallingRectangleSpecial(4);


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

    private void rectangleSpawnLocationSpecial() {
        
         for (int rectMultipleSpecial = 0; rectMultipleSpecial < rectXSpecial.length; rectMultipleSpecial++) {


            rectXSpecial[rectMultipleSpecial] = (int) random(0, width / 40) * 40;
            rectYSpecial[rectMultipleSpecial] = (int) random(-1500, 0);
        }
    }
    private void fallingRectangleSpecial(int speedSpecial) {
        fill(powerUpColors[randomColor]); //fills it thta specific color
        for (int rectMultipleSpecial = 0; rectMultipleSpecial < rectXSpecial.length; rectMultipleSpecial++){
            rect(rectXSpecial[rectMultipleSpecial], rectYSpecial[rectMultipleSpecial], 40, 20); // creates the rectangle
            rectYSpecial[rectMultipleSpecial] += speedSpecial;
        }
    

    }

    public void collectObjects() {
        for (int rectMultiple = 0; rectMultiple < rectX.length; rectMultiple++) {
            // Must be within a specific y coordinate at all times, and mnust be within the caludron's bondaries hence the -60, +60
            if (rectY[rectMultiple] >= 290 && rectY[rectMultiple] <= 320 && rectX[rectMultiple] >= cauldronX - 60 && rectX[rectMultiple] <= cauldronX + 60) {

                score++;

                // Respawn rectangle at top so it doesn't continue fallingn - reuses the same rectangle
                rectY[rectMultiple] = (int) random(-1500, 0);
                rectX[rectMultiple] = (int) random(0, width / 40) * 40;

            }

        }

        for (int rectMultipleSpecial = 0; rectMultipleSpecial < rectXSpecial.length; rectMultipleSpecial++){
            if (rectYSpecial[rectMultipleSpecial] >= 290 && rectYSpecial[rectMultipleSpecial] <= 320 && rectXSpecial[rectMultipleSpecial] >= cauldronX - 60 && rectXSpecial[rectMultipleSpecial] <= cauldronX + 60) {

                score+= 5;

                // Respawn rectangle at top so it doesn't continue falling
                rectYSpecial[rectMultipleSpecial] = (int) random(-1500, 0);
                rectXSpecial[rectMultipleSpecial] = (int) random(0, width / 40) * 40;

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
            if(keyPressed && (key == 'a' || key == CODED && keyCode == LEFT )&& cauldronX >= 50)
            {
                cauldronX -= 10;
            }

            if(keyPressed && (key == 'd' || key == CODED && keyCode == RIGHT)  && cauldronX <= 550)
            {
                cauldronX += 10;
            }
    }

}
