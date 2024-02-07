package hello.toy.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UploadLogic {

    @Value("${file_dir}")
    private String fileDir;

    public String getFullPath(String StoreFileName) {
        return fileDir + StoreFileName;
    }

    public List<UploadFile> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> saveFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(multipartFile != null) {
                saveFiles.add(fileUpload(multipartFile));
            }
        }
        return saveFiles;
    }

    public UploadFile fileUpload(MultipartFile multipartFile) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();
        /**
         * 확장자 추출
         */
        String ext = extractExt(originalFilename);
        /**
         * RANDOM UUID로 변경
         */
        String StoreFileName = getUUIDName(ext);

        String fullPath = getFullPath(StoreFileName);
        multipartFile.transferTo(new File(fullPath));
        return new UploadFile(originalFilename, StoreFileName);
    }


    private String getUUIDName(String ext) {
        String uuid = UUID.randomUUID()
                .toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int i = originalFilename.lastIndexOf(".");
        return originalFilename.substring(i + 1);
    }


}
