package study.jpaProject.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FileUtilsTest {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Autowired
    private ServletContext context;

    @Test
    public void 파일이동(){
        File file = new File(uploadPath + "/" + "1657708617555.png");
        if(file.exists()){
            File folder = new File(context.getContextPath()+"/static/images/empty_team.png");
            System.out.println(folder.getPath());
            if(folder.exists()){
                System.out.println("11");
            }
        }

    }

}