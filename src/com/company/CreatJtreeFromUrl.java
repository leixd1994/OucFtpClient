package com.company;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by leixd on 17-4-23.
 */
public class CreatJtreeFromUrl {

    public JTree getJtree(JFrame frame,String Url)
    {
        // write your code here
        FTPClient ftpClient=new FTPClient();
        ftpClient.setControlEncoding("GBK");
        ftpClient.configure(new FTPClientConfig(FTPClientConfig.SYST_UNIX));
        ftpClient.setDataTimeout(2000);

        DefaultMutableTreeNode root=new DefaultMutableTreeNode(Main.currentServiceName);
        try {

            ftpClient.connect(Url);
            ftpClient.login("anonymous","");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            String []t=ftpClient.listNames();
            for (String t2:t)
            {
                System.out.println(t2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        BuildTree.Build(root,ftpClient,"/",0);

        JTree jTree=new JTree(root);
        jTree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                System.out.println("Expandeded");
                DefaultMutableTreeNode t= (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                MyFTPFile t1=(MyFTPFile) t.getUserObject();
                System.out.println("Expandeded:"+t1.path+t1.ftpFile.getName()+"/");
                if (t.getChildCount()>1) return;
                t.removeAllChildren();
                BuildTree.Build(t,ftpClient,t1.path+t1.ftpFile.getName()+"/",0);
                DefaultTreeModel defaultTreeModel=(DefaultTreeModel) jTree.getModel();
                defaultTreeModel.reload(t);
                jTree.expandPath(new TreePath(t.getPath()));
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {

            }
        });

        jTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
            }
        });

        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {


                DefaultMutableTreeNode t= (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                MyFTPFile t1=(MyFTPFile) t.getUserObject();
                if (t1.ftpFile.isFile())

                    System.out.println("点击的文件路径"+"ftp://ftp.ouc.edu.cn"+t1.path+t1.ftpFile.getName());
                else return;
                String filePath="ftp://ftp.ouc.edu.cn"+t1.path+t1.ftpFile.getName();
                String ExpandName=t1.ftpFile.getName().substring(t1.ftpFile.getName().lastIndexOf("."),t1.ftpFile.getName().length());
                Runtime runtime=Runtime.getRuntime();
                JFileChooser jFileChooser=new JFileChooser();
                jFileChooser.setFileSelectionMode(2);
                int result=jFileChooser.showSaveDialog(frame);
                final String FileSavePath;
                if (result==JFileChooser.APPROVE_OPTION)
                    FileSavePath=jFileChooser.getSelectedFile().getPath();
                else
                    return;
                switch (ExpandName.toLowerCase())               //感谢Java7啊 支持Switch String 了。。。啊啊啊啊啊啊
                {
                    case ".mp3":



                        new Thread(()->{
                            try {

//                                JProgressBar jProgressBar=new JProgressBar();
//                                jProgressBar.setString("正在下载："+t1.ftpFile.getName()+" ");
//
//                                jProgressBar.setStringPainted(true);
//                                frame.add(jProgressBar, BorderLayout.SOUTH);
//                                frame.repaint();
                                InputStream InputStream = ftpClient.retrieveFileStream(t1.path + t1.ftpFile.getName());
                                if (InputStream==null) return;
                                File tFile = new File(FileSavePath, "/" + "0001.mp3");

                                FileOutputStream fileOutputStream = new FileOutputStream(tFile);

                                byte[] tbyte = new byte[1000000];
                                Process process = null;
                                long totalWrite = 0;


                                do {
                                    int hasRead = InputStream.read(tbyte);
                                    if (hasRead==-1) break;

                                    fileOutputStream.write(tbyte,0,hasRead);
                                    System.out.println("已经写入了" + hasRead + "字节");
                                    System.out.println("总共写入了" + totalWrite + "字节");
                                    totalWrite += hasRead;
                                    if (process == null)
                                        if (totalWrite > 1000000) {
                                            fileOutputStream.flush();
                                            System.out.println(tFile.getAbsolutePath());
                                            process = runtime.exec("totem " + tFile.getAbsolutePath());
                                        }
                                }while (true);

                                fileOutputStream.close();
                                //  InputStream.close();

                                if (process==null)
                                    process=runtime.exec("totem "+tFile.getAbsolutePath());
                                ftpClient.disconnect();

                                ftpClient.connect("ftp.ouc.edu.cn");
                                ftpClient.login("anonymous","");


                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }


                        }).start();
                        break;


                    case ".wmv":case ".mkv":case ".avi":case ".mp4":case ".rmvb":case ".rm":case "dat":
                    new Thread(()-> {
                        try {
                            InputStream InputStream = ftpClient.retrieveFileStream(t1.path + t1.ftpFile.getName());
                            if (InputStream == null) return;
                            File tFile = new File(FileSavePath, "/" + "0001.mp3");

                            FileOutputStream fileOutputStream = new FileOutputStream(tFile);

                            byte[] tbyte = new byte[1000000];
                            Process process = null;
                            long totalWrite = 0;
                            do {
                                int hasRead = InputStream.read(tbyte);
                                if (hasRead == -1) break;

                                fileOutputStream.write(tbyte, 0, hasRead);
                                System.out.println("已经写入了" + hasRead + "字节");
                                System.out.println("总共写入了" + totalWrite + "字节");
                                totalWrite += hasRead;
                                if (process == null)
                                    if (totalWrite > 1000000) {
                                        fileOutputStream.flush();
                                        System.out.println(tFile.getAbsolutePath());
                                        process = runtime.exec("totem " + tFile.getAbsolutePath());
                                    }
                            } while (true);

                            fileOutputStream.close();
                            //  InputStream.close();

                            if (process == null)
                                process = runtime.exec("totem " + tFile.getAbsolutePath());
                            ftpClient.disconnect();

                            ftpClient.connect("ftp.ouc.edu.cn");
                            ftpClient.login("anonymous", "");


                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }).start();
                    break;

                }
            }
        });

        return jTree;

    }
}
