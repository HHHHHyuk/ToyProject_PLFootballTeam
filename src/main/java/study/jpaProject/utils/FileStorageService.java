package study.jpaProject.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import study.jpaProject.error.CustomException;

import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileStorageService implements StorageService{

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e){
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    @Override
    public String store(MultipartFile file, boolean isImage) {
        String lowerFile = Objects.requireNonNull(file.getOriginalFilename()).toLowerCase();
        if(isImage && !(
                lowerFile.contains(".png") ||lowerFile.contains(".jpg") || lowerFile.contains(".gif") ||
                        lowerFile.contains(".jpeg")|| lowerFile.contains(".bmp") )
        ){
            throw new CustomException("이미지 파일만 등록이 가능합니다. ( png, jpg, jpeg, bmp, gif ) ");
        }
        String fileName = System.currentTimeMillis()+"."+ FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            if(file.isEmpty()){
                throw new Exception("ERROR : File is empty.");
            }
            Path root = Paths.get(uploadPath);
            if(!Files.exists(root)){
                init();
            }

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, root.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch ( Exception e) {
            throw new RuntimeException("Could not store the file. Error : " + e.getMessage());
        }
        return fileName;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {return Paths.get(uploadPath).resolve(filename); }

    @Override
    public Resource loadAsResource(String filename) {
        try{
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new RuntimeException("Colud not read file:" + filename);
            }
        }catch(MalformedURLException e){
            throw new RuntimeException("Colud not read file:" + filename, e);
        }
    }

    @Override
    public void deleteAll() {

    }

    public boolean deleteFile(String fileName) throws IOException {
        if(fileName!=null && !"".equals(fileName)){
            Path path = Paths.get(uploadPath);
            Path filePath = path.resolve(fileName);
            Files.delete(filePath);
        }
        return true;
    }
}
