package com.fileHandling;//                                              "જય શ્રી ગણેશ"

/*
    Ques :  File Handling in Java

    - What is File Handling?
    ans : File handling is an important part of any web application. You often need to open and read an external file for processing. 
          Java provides a new package in the Java SE 7 version called java.nio.file, which provides a file handling support.

    Time Complexity :  
    Space Complexity :  
*/

import java.io.File;
import java.io.IOException;

public class File_In_Java
{
    public static void main(String[] args)
    {
        System.out.println("RadhaKrishna");

        File file = new File("Radha.c"); // File class is used to create a new File instance by converting the given pathname string into an abstract pathname.

        if (file.exists()) // exists() method is used to check whether the file or directory exists or not.
        {
            System.out.println("File Exists");
        }
        else
        {
            System.out.println("File Not Exists");
        }

        // crearting file
        try
        {
            if (file.createNewFile()) /// createNewFile() method is used to create a new, empty file. and throws IOException.
            {
                System.out.println("File Created : " + file.getName());
                System.out.println("File Path : " + file.getAbsolutePath());
            }
            else
            {
                System.out.println("File Already Exists");
            }
        }
        catch (IOException e) // IOException is thrown when an input/output operation is interrupted.
        {
            System.out.println("An Error Occured");
            e.printStackTrace();
        }
    }
}