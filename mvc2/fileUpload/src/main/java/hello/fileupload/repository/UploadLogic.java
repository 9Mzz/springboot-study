package hello.fileupload.repository;

import hello.fileupload.dto.UplaodFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadLogic {

  @Value(value = "${file.dir}")
  private String fileDir;

  public String getFullPath(String uploadFileName) {
    return fileDir + uploadFileName;
  }

  public List<UplaodFile> saveFiles(List<MultipartFile> multipartFiles) throws IOException {
    List<UplaodFile> storeFiles = new ArrayList<>();

    for (MultipartFile multipartFile : multipartFiles) {
      if(!multipartFile.isEmpty()) {
        storeFiles.add(saveFile(multipartFile));
      }
    }
    return storeFiles;
  }

  public UplaodFile saveFile(MultipartFile multipartFile) throws IOException {
    if(multipartFile.isEmpty()) {
      return null;
    }

    String originalFilename = multipartFile.getOriginalFilename();
    String uploadFileName   = getUploadFileName(originalFilename);

    multipartFile.transferTo(new File(getFullPath(uploadFileName)));

    return new UplaodFile(originalFilename, uploadFileName);
  }


  private static String getUploadFileName(String originalFilename) {
    String ext = extractExt(originalFilename);
    String uuid = UUID.randomUUID()
                      .toString();
    return uuid + "." + ext;
  }

  private static String extractExt(String originalFilename) {
    int ext = originalFilename.indexOf(".");
    return originalFilename.substring(ext + 1);
  }

}
