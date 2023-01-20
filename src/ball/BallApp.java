package ball;

import java.awt.Graphics2D;

import cn.tedu.util.App;

import java.awt.*;
import java.util.Arrays;

public class BallApp extends App { //调用球App Class

    Ball[] balls = new Ball[50];  //创建100个对象

    public BallApp() {  //调用球方法

        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball();  //循环球的个数，当前长度100
        }
    }

    public void painting(Graphics2D g) {
        for (int i = 0; i < balls.length; i++) {
            balls[i].move();
            balls[i].paint(g);
        }
        eating();
    }

    public void eating() {
        //一组大球，一组小球
        Ball[] big = balls;
        Ball[] small = balls;
        boolean[] eaten = new boolean[small.length];//创建了吃掉标志默认都是 false，表示没有吃掉
        int n = 0;//记录有几个球被吃掉了
        for (int i = 0; i < big.length; i++) { //每一个大球
             //如果大球已经被吃掉了，就忽略
            if (eaten[i]) {
                continue;
            }
            for (int j = 0; j < small.length; j++) { //每一个小球
                //球不能吃自己
                if (i == j) {
                    continue;
                }
                //如果小球被吃掉的就忽略
                if (eaten[j]) {
                    continue;
                }
                if (big[i].eat(small[j])) {
                    //System.out.println("吃...");
                    //System.out.println(big[i].x + "," + big[i].y + "," + big[i].d);
                    //System.out.println(big[j].x + "," + big[j].y + "," + big[j].d);
                    //把小球位置设置为 true
                    eaten[j] = true;
                    n++;
                }
            }
        }
        // System.out.println(Arrays.toString(eaten));
        // System.out.println(n);
        if (n == 0) {
            return;
        }
        //缩容处理
        Ball[] arr = new Ball[small.length];
        int index = 0;
        for (int i = 0; i < small.length; i++) {
            if (!eaten[i]) {
                arr[index++] = small[i];
            }
        }
        //    System.out.println(Arrays.toString(arr));
        //缩容，并替换原始数组
        balls = Arrays.copyOf(arr, arr.length - n);

    }

    public static void main(String[] args) {
        BallApp app = new BallApp();
        app.start();
    }
}
