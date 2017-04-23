package com.company;

import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.Main.frame;

/**
 * Created by leixd on 17-4-23.
 */
public class getPanel {
    public static JProgressBar jProgressBar;
    public static JPanel getUp(JPanel jPanelFather)    //获取顶部Jpanel   传入上级容器
    {
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new FlowLayout());
        JButton button1=new JButton("海大FTP服务器1");
        JButton button3=new JButton("海大FTP服务器3");
        JButton button4=new JButton("海大FTP服务器4");
        jPanel.add(button1);jPanel.add(button3);jPanel.add(button4);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.currentServiceName="OUC Ftp Server 1";
                jPanelFather.remove(0);
                jPanelFather.add(new JScrollPane(new CreatJtreeFromUrl().getJtree(frame,"ftp.ouc.edu.cn")));
                jPanelFather.updateUI();

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.currentServiceName="Ouc Ftp Server 3";
                jPanelFather.remove(0);
                jPanelFather.add(new JScrollPane(new CreatJtreeFromUrl().getJtree(frame,"ftp3.ouc.edu.cn")));
                jPanelFather.updateUI();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.currentServiceName="Ouc Ftp Server 4";
                jPanelFather.remove(0);
                jPanelFather.add(new JScrollPane(new CreatJtreeFromUrl().getJtree(frame,"ftp4.ouc.edu.cn")));
                jPanelFather.updateUI();
            }
        });



        return jPanel;
    }

    static public JPanel getDown()           //Get 下部Jpanel
    {
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.X_AXIS));
        jProgressBar=new JProgressBar();
        jProgressBar.setValue(40);
        jProgressBar.setString("Downloading: "+jProgressBar.getValue()+"%");
        jProgressBar.setStringPainted(true);
        jPanel.add(jProgressBar);
        return jPanel;
    }

}
