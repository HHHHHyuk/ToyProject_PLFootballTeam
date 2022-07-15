package study.jpaProject.utils;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class FileUtils {

    public static boolean move(HttpServletRequest req, String oldFilePath, String flagFolder, String newFilePath){
        boolean result = false;
        String filePath = req.getSession().getServletContext().getRealPath("/");

        File file = new File(oldFilePath);
        if(file.exists()){
            File folder = new File(filePath+flagFolder);
            System.out.println(folder.getPath());
            if(!folder.exists()) {  folder.mkdir(); }
            File newFile = new File(filePath + flagFolder + "/" + newFilePath);
            if(file.renameTo(newFile)){
                result = true;
            }
        }
        return result;
    }

    public static boolean delete(HttpServletRequest req, String fileName){
        boolean result = false;
        String filePath = req.getSession().getServletContext().getRealPath("/");

        File file = new File(filePath+fileName);
        if(file.exists()){
            result = file.delete();
        }
        return result;
    }
}
