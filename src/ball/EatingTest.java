package ball;

import java.util.Arrays;

public class EatingTest {
    public static void main(String[] args) {
        BallApp app = new BallApp();
        System.out.println(Arrays.toString(app.balls));
        System.out.println(app.balls.length);
        app.eating();
        System.out.println(app.balls.length);
    }


}
