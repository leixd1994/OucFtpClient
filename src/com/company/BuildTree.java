package com.company;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;

/**
 * Created by leixd on 17-4-2.
 */
public class BuildTree {
    static public void Build(DefaultMutableTreeNode root, FTPClient ftpClient,String path,int ceng)
    {
        if (ceng>=1) return;
        FTPFile []tf= null;
        try {
            System.out.println("PATH OF BUildTREE:  "+path);

            tf = ftpClient.listFiles(path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }



        for (FTPFile t3:tf)
        {
            DefaultMutableTreeNode t5=null;
            if (!t3.getName().equals("."))
            {
                if (!t3.getName().contains(".."))
                    t5 = new DefaultMutableTreeNode(new MyFTPFile(t3, path));
                else continue;
            }
            else continue;
            if (t3.isDirectory())
            {
                t5.add(new DefaultMutableTreeNode("Welcome to OUC"));
                System.out.println("t3Name"+"  "+t3.getName());
                System.out.println(path + t3.getName() + "/");
                if (!t3.getName().equals("."))
                    if (!(t3.getName().contains("..")))
                BuildTree.Build(t5, ftpClient, path + t3.getName() + "/",ceng+1);
            }
                root.add(t5);
        }


}
}
