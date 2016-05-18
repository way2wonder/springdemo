package com.feifan.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class FileUtil
{
     public String  readFile(String path) throws Exception
     {
         File file = new File(path);
         
         InputStream is = new FileInputStream(file);
         StringBuffer sb = new StringBuffer();
         byte[] b = new byte[1024];
         while(is.read(b) != -1)
         {
            System.out.println(new String(b));
            sb.append(new String(b));
         }
         
         is.close();
         
         
         return sb.toString();
     }
     
     
     @Test
     public void test()
     {
         Path path=Paths.get("C:\\Users\\zhy\\Desktop\\bookmarks_16_5_9.html");
         StringBuffer sb = new StringBuffer();
         try
        {
            for (String line : Files.readAllLines(path,Charset.defaultCharset()) )
             {
                 sb.append(line);
                // System.out.println(line);
             }
            
            String regex = "((?i)href)=\"[^\"]+\"";
            Pattern p = Pattern.compile(regex);
            Matcher match =  p.matcher(sb);
            while(match.find())
            {
                System.out.println(sb.substring(match.start(),match.end()));
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }
}
