package com.company;

import org.apache.commons.net.ftp.FTPFile;

/**
 * Created by leixd on 17-4-2.
 */
public class MyFTPFile  {
    public FTPFile ftpFile;
    public String path;
    public MyFTPFile(FTPFile t,String path)
    {
        this.ftpFile=t;
        this.path=path;
    };
    @Override
    public String toString() {
        return ftpFile.getName();
    }
}
