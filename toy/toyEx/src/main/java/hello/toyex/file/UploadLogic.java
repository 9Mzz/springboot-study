package hello.toyex.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadLogic {

    @Value("${filePath}")
    private String filePath;

    public String fullPath(String storeFileName) {

        return filePath + storeFileName;
    }

    public List<ItemUpload> uploads(List<MultipartFile> multipartFiles) throws IOException {
        List<ItemUpload> itemUploadList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            itemUploadList.add(upload(multipartFile));
        }
        return itemUploadList;
    }

    public ItemUpload upload(MultipartFile multipartFile) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();
        /**
         * 확장자 추출
         */
        String ext = extractedExt(originalFilename);

        /**
         * 랜덤 UUID로 변환
         */
        String storeFileName = getUUID(ext);
        multipartFile.transferTo(new File(fullPath(storeFileName)));

        return new ItemUpload(originalFilename, storeFileName);
    }

    private String getUUID(String ext) {
        return UUID.randomUUID()
                .toString() + "." + ext;
    }

    private String extractedExt(String originalFilename) {
        int i = originalFilename.lastIndexOf(".");
        return originalFilename.substring(i + 1);
    }


}
