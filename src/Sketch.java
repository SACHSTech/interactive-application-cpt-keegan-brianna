import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Keegan
 * @author Brianna
 */
public class Sketch extends PApplet {

  int[] rectX = new int[10];
  int[] rectY = new int[10];
  int[] rectXSpecial = new int[1];
  int[] rectYSpecial = new int[1];
  int cauldronX = 300;
  int cauldronY = 350;
  int score = 0;
  int randomColor;
  int[] powerUpPoints = new int[4]; // array to assign a specific amount of points to each color variation of powerUp
  int insideCauldron = color(255, 255, 255);

  public static void main(String[] args) {
    PApplet.main("Sketch");
  }

  @Override
  public void settings() {
    size(600, 400);
  }

  @Override
  public void setup() {

    powerUpPoints[0] = 2; // Red
    powerUpPoints[1] = 4; // Green
    powerUpPoints[2] = 6; // Blue 
    powerUpPoints[3] = 8; // Yellow 

    randomColor = (int) random(0, 4); // uses random to get a random color from the powerUpColor array

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
    //leaf(350,200);        
    //flower( 200,200);

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

  /**
   * @param speed type in an interger to determine the speed of the falling rectangle
   */
  private void fallingRectangle(int speed) {
    fill(23, 23, 23);
    for (int rectMultiple = 0; rectMultiple < rectX.length; rectMultiple++) {
      rect(rectX[rectMultiple], rectY[rectMultiple], 40, 20); // creates the rectangle

      rectY[rectMultiple] += speed;
    }

  }
  /**
   * @param speedSpecial type in an interger to determine the speed of the special object
   */
  private void fallingRectangleSpecial(int speedSpecial) {

    for (int rectMultipleSpecial = 0; rectMultipleSpecial < rectXSpecial.length; rectMultipleSpecial++) {
      if (randomColor == 0) { // Red mushroom = 2 points
        mushrooms(rectXSpecial[rectMultipleSpecial], rectYSpecial[rectMultipleSpecial]);
      } else if (randomColor == 1) { // Green leaf = 4 points
        leaf(rectXSpecial[rectMultipleSpecial], rectYSpecial[rectMultipleSpecial]);
      } else if (randomColor == 2) { // Water Droplet = 6 points

        waterDrop(rectXSpecial[rectMultipleSpecial], rectYSpecial[rectMultipleSpecial]);
      } else if (randomColor == 3) { // Yellow flower = 8 points
        flower(rectXSpecial[rectMultipleSpecial], rectYSpecial[rectMultipleSpecial]);
      }

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

      for (int rectMultipleSpecial = 0; rectMultipleSpecial < rectXSpecial.length; rectMultipleSpecial++) {
        if (rectYSpecial[rectMultipleSpecial] >= 290 && rectYSpecial[rectMultipleSpecial] <= 320 && rectXSpecial[rectMultipleSpecial] >= cauldronX - 60 && rectXSpecial[rectMultipleSpecial] <= cauldronX + 60) {

          // Set the cauldron color to match the item you caught
          if (randomColor == 0) {
            insideCauldron = color(186, 38, 15); // Mushroom Red
          } else if (randomColor == 1) {
            insideCauldron = color(78, 130, 70); // Leaf Green
          } else if (randomColor == 2) {
            insideCauldron = color(0, 150, 255); // Water Drop Blue (Fixed typo swapping Blue/Yellow)
          } else {
            insideCauldron = color(250, 240, 105); // Flower Yellow
          }

          score += powerUpPoints[randomColor];

          // Respawn and pick a new random type object
          rectYSpecial[rectMultipleSpecial] = (int) random(-1500, 0);
          rectXSpecial[rectMultipleSpecial] = (int) random(0, width / 40) * 40;
          randomColor = (int) random(0, 4);
        }
      }
    }

    fill(0);
    textSize(20);
    text("Score: " + score, 20, 30);
  }

  public int cauldron() {

    //pot
    noStroke();
    fill(42, 40, 46);
    circle(cauldronX, cauldronY, 100);
    ellipse(cauldronX, 310, 120, 30);
    //content
    noStroke();
    fill(insideCauldron);
    ellipse(cauldronX, 310, 90, 20);
    return cauldronX;
  }

  public void moveCauldron() {
    if (keyPressed && (key == 'a' || key == CODED && keyCode == LEFT) && cauldronX >= 50) {
      cauldronX -= 10;
    }

    if (keyPressed && (key == 'd' || key == CODED && keyCode == RIGHT) && cauldronX <= 550) {
      cauldronX += 10;
    }
  }

  public void mushrooms(int x, int y) {
    //stem
    noStroke();
    fill(240, 220, 194);
    ellipse(x, y + 20, 25, 30);
    //top
    fill(186, 38, 15);
    //fill(colorMushroom);
    ellipse(x, y, 50, 30);
    //spots
    fill(240, 220, 194);
    circle(x - 10, y, 7);
    circle(x + 5, y - 10, 10);
    circle(x + 18, y + 5, 5);

  }

  public void leaf(int x, int y) {
    noStroke();
    fill(78, 130, 70);
    ellipse(x, y, 40, 30);
    triangle(x - 14, y - 11, x - 14, y + 11, x - 35, y);
    strokeWeight(2);
    stroke(161, 179, 159);
    line(x - 35, y, x + 30, y);

  }

  public void flower(int x, int y) {
    //petals
    noStroke();
    fill(250, 240, 105);
    ellipse(x, y - 10, 15, 20);
    ellipse(x + 10, y, 15, 20);
    ellipse(x - 10, y, 15, 20);
    ellipse(x + 5, y + 10, 15, 20);
    ellipse(x - 5, y + 10, 15, 20);
    //middle
    fill(245, 177, 105);
    circle(x, y, 15);

  }

  public void waterDrop(int x, int y) {
    noStroke();

    // main drop
    fill(0, 150, 255);
    ellipse(x, y, 20, 30);

    // pointed top
    triangle(x - 10, y, x + 10, y, x, y - 18);
  }
}