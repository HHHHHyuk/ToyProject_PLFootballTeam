package study.jpaProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import study.jpaProject.utils.ApiUtils;
import study.jpaProject.utils.FileStorageService;
import study.jpaProject.utils.StorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileStorageService storageService;

    @PostMapping("/file/upload")
    public ApiUtils.ApiResult<String> upload(HttpServletRequest req) throws IllegalStateException, IOException {
        MultipartHttpServletRequest multipartReq = (MultipartHttpServletRequest) req;
        MultiValueMap<String, MultipartFile> multiFileMap = multipartReq.getMultiFileMap();
        List<MultipartFile> file = multiFileMap.get("file");
        return ApiUtils.succes(storageService.store(file.get(0)));
    }
}
