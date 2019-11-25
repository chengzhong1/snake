package com.kuang.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int lenth;
    int[] snakeX=new int[600];
    int[] snakeY=new int[600];
    int speed=100;
    String fx="R";
    boolean isStart=false;
    Timer timer=new Timer(speed,this);
    int foodX;
    int foodY;
    Random random=new Random();
    boolean isDead=false;
    int score=0;
    //该类构造器
    public GamePanel(){
        init();
        //获取监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    //初始化
    public void init(){
        lenth=3;
        snakeX[0]=100;snakeY[0]=100;
        snakeX[1]=75;snakeY[1]=100;
        snakeX[2]=50;snakeY[2]=100;
        fx="R";
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);


    }
    //画板：画界面，画蛇
    //Graphics；画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        Date.header.paintIcon(this,g,25,11);
        //绘制游戏区域
        g.fillRect(25,75,850,600);
        //画一条静态的小蛇
        //小蛇头方向
        if(fx.equals("R")){
            Date.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("L")){
            Date.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("U")){
            Date.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("D")){
            Date.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        //画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font(" 微软雅黑",Font.BOLD,18));
        g.drawString("长度："+lenth,750,35);
        g.drawString("分数："+score,750,50);
        //画食物
        Date.food.paintIcon(this,g,foodX,foodY);
        for(int i=1;i<lenth;i++){
            Date.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
    //游戏提醒：是否开始
    if(isStart==false) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(" 微软雅黑",Font.BOLD,40));
        g.drawString("按下空格键开始游戏",300,300);

        }
        if (isDead){
            g.setColor(Color.RED);
            g.setFont(new Font(" 微软雅黑",Font.BOLD,40));
            g.drawString("按下空格键开始游戏",300,300);
        }
    }


    //监听键盘输入
    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下未释放响应
        int keyCode=e.getKeyCode();
        if(keyCode==KeyEvent.VK_SPACE){
            if(isDead){
                isDead=false;
                init();
            }else{
                isStart=!isStart;
            }

            repaint();
        }
        if(keyCode==KeyEvent.VK_LEFT){
            fx="L";
//            snakeY[0]=snakeY[0]+25;
            repaint();
        }else if(keyCode==KeyEvent.VK_RIGHT){
            fx="R";   repaint();
        }else if(keyCode==KeyEvent.VK_UP){
            fx="U";   repaint();
        }else if(keyCode==KeyEvent.VK_DOWN){
            fx="D";   repaint();
        }
//        if(keyCode==KeyEvent.VK_W){
//            speed=speed+50;
//            timer.start();repaint();
//        }
//        if(keyCode==KeyEvent.VK_S){
//            speed=speed-50;
//            timer.start();repaint();
//        }







    }
    //定时器：监听时间，定时刷新  帧：执行定时操作，化静态为动态
    //先定时，后刷新，定时器和刷新都得开启
    public void actionPerformed(ActionEvent e) {
        if(isStart&&isDead==false){
            for(int i=lenth-1;i>0;i--){
                snakeX[i]=snakeX[i-1];
                snakeY[i]=snakeY[i-1];

            }
            if(fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            }else if(fx.equals("L")){
                snakeX[0]=snakeX[0]-25;
                if(snakeX[0]<25){
                    snakeX[0]=850;
                }
            }else if(fx.equals("U")){
                snakeY[0]=snakeY[0]-25;
                if(snakeY[0]<75){
                    snakeY[0]=650;
                }
            }else if(fx.equals("D")){
                snakeY[0]=snakeY[0]+25;
                if(snakeY[0]>650){
                    snakeY[0]=75;
                }
            }
            if(snakeX[0]==foodX&&snakeY[0]==foodY){
                lenth++;
                score+=10;
                foodX=25+25*random.nextInt(34);
                foodY=75+25*random.nextInt(24);
            }
            for(int i=1;i<lenth;i++){
                if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                    isDead=true;
                }
            }

            repaint();

        }
        timer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //释放某个键
    }

    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起响应
    }

    //接受键盘的输入，监听
}
