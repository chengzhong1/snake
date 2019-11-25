package com.kuang.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        //绘制一个静态窗口
        JFrame frame=new JFrame("Java钟大仙-贪吃蛇游戏");
        frame.setBounds( 10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}
