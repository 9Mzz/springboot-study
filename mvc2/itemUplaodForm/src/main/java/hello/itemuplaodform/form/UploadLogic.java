package hello.itemuplaodform.form;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class UploadLogic {

  @Value(value = "${file.route}")
  private String fileDir;

  public String getFullPath(String storeFileName) {
    return fileDir + storeFileName;
  }

  public List<UploadFile> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
    List<UploadFile> storeFiles = new ArrayList<>();
    for (MultipartFile multipartFile : multipartFiles) {
      if(!multipartFile.isEmpty()) {
        storeFiles.add(uploadFile(multipartFile));
      }
    }
    return storeFiles;
  }


  public UploadFile uploadFile(MultipartFile multipartFile) throws IOException {
    if(multipartFile.isEmpty()) {
      return null;
    }
    String uploadFileName = multipartFile.getOriginalFilename();
    String storeFileName  = getStoreFileName(uploadFileName);

    String fullPath = getFullPath(storeFileName);
    log.info("fullPath = {}", fullPath);
    multipartFile.transferTo(new File(fullPath));

    return new UploadFile(uploadFileName, storeFileName);
  }


  private static String getStoreFileName(String originalFilename) {
    String ext = extractExt(originalFilename);
    String uuid = UUID.randomUUID()
                      .toString();
    return uuid + "." + ext;
  }

  /**
   * 확장자 추출
   */
  private static String extractExt(String originalFilename) {
    int lastIndexOf = originalFilename.lastIndexOf(".");
    return originalFilename.substring(lastIndexOf + 1);
  }

}
