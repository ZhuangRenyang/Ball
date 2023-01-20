package ball;

import java.awt.Color;

import java.awt.*;
import java.time.Year;

public class Ball {
    double x;         //声明变量   x 球的x坐标
    double y;         //声明变量   y 球的y坐标
    int d;            //声明变量   d 球的直径
    int r;            //声明 color 三原色 r 随机颜色 256 - 0;
    int g;            //声明 color 三原色 g 随机颜色 256 - 0;
    int b;            //声明 color 三原色 b 随机颜色 256 - 0;
    Color color;      //声明颜色 color 调用 color方法
    double offsetX;   //声明浮点数 offsetX 坐标移动
    double offsetY;   //声明浮点数 offsetY 坐标移动

    public Ball() {  //封装Ball 方法

        d = (int) (Math.random() * (45 - 10) + 10);  //初始化 d 球的随机大小；
        x = Math.random() * (800 - d);             //初始 X 化球的碰撞位置返回
        y = Math.random() * (600 - d);             //初始 Y 化球的碰撞位置返回
        r = (int) (Math.random() * 256);           //r随机颜色
        g = (int) (Math.random() * 256);           //g随机颜色
        b = (int) (Math.random() * 256);           //b随机颜色
        color = new Color(r, g, b);                //球的颜色
        offsetX = Math.random() * (6 - 1) * 1;     //初始化X坐标位置
        offsetY = Math.random() * (6 - 1) * 1;     //初始化Y坐标位置
        offsetX = Math.random() > 0.5 ? offsetX : -offsetX;  //如果X 随机数 > 0.5 ,X 正方向turn，否则反方向false
        offsetY = Math.random() > 0.5 ? offsetY : -offsetY;  //如果Y 随机数 > 0.5 ,Y 正方向turn，否则反方向false

    }

    public void move() { //判断方法无返回值 类型 move
        x += offsetX;    //移动位置offsetX += x 赋值x X 正方向turn offsetX，否则反方向false -offsetX;
        y += offsetY;    //移动位置offsetX += x 赋值y Y 正方向turn offsetY，否则反方向false -offsetY;
        if (x > 800 - d) {   //如果球 x 位置 碰到 800- d 位置 反方向移动 -offsetX赋值 offsetX 防止bug越界 判断 x = 800-d
            offsetX = -offsetX;
            x = 800 - d;
        } else if (y > 600 - d) { //如果球 y 位置 碰到 600- d 位置 反方向移动 -offsetY赋值 offsetY 防止bug越界 判断 y = 600-d
            offsetY = -offsetY;
            y = 600 - d;
        } else if (x < 0) {    //如果球 x 位置 碰到 0 位置 反方向移动 -offsetX赋值 offsetX 防止bug越界 判断 x = 0
            offsetX = -offsetX;
            x = 0;
        } else if (y < 0) {   //如果球 y 位置 碰到 0 位置 反方向移动 -offsetY赋值 offsetY 防止bug越界 判断 y = 0
            offsetY = -offsetY;
            y = 0;
        }
    }

    public void paint(Graphics2D g) {  //球

        g.setColor(color);    //球的颜色
        g.fillOval((int) x, (int) y, d, d);  //球的位置，球的大小
    }

    public boolean eat(Ball ball) { //碰撞球大球吃小球

        double X = this.x, Y = this.y, D = this.d;  //大球
        double x = ball.x, y = ball.y, d = ball.d; //小球
        //检查球的直径是否合理
        if (d > D) {
            return false;
        }
        //利用勾股定理计算两个求之间的距离
        double a = (X + D / 2) - (x + d / 2);
        double b = (y + d / 2) - (Y + D / 2);
        double c = Math.sqrt(a * a + b * b);
        boolean eaten = c < D / 2 - d / 2;
        //如果发生“吃了”就进行两个圆的合并
        if (eaten) {
            //计算合并以后的圆面积
            double R = D / 2, r = d / 2;
            double area = Math.PI * R * R + Math.PI * r * r;
            double rx = Math.sqrt(area / Math.PI);
            this.d = (int) (rx * 2);  //替换当前圆的直径

        }
        return eaten;
    }

}
