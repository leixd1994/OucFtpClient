package com.company;


import com.sun.javaws.progress.Progress;
import org.apache.commons.net.ftp.*;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import static java.lang.System.exit;

public class Main {

     public static JFrame frame;
     public static JPanel jPanel=new JPanel();       //Jframe无法设置BoxLayout布局，间接套Jpanel
     public static String currentServiceName="Ouc Ftp Server 1";
    public static void main(String[] args) {
        frame=new JFrame("中国海洋大学校园资源浏览器 Made By Thunder");


        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        JPanel centerJPanel=new JPanel();       //中部显示树的Jpanel

        jPanel.add(getPanel.getUp(centerJPanel));         //添加上部Jpanel、

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                exit(0);
            }
        });

 //       frame.add(new JScrollPane(new CreatJtreeFromUrl().getJtree(frame,"ftp.ouc.edu.cn")));

        centerJPanel.add(new JScrollPane(new JScrollPane(new CreatJtreeFromUrl().getJtree(frame,"ftp.ouc.edu.cn"))));
        jPanel.add(centerJPanel);
        frame.add(jPanel);

        frame.pack();
        frame.setVisible(true);

    }
}
