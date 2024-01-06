package hello.upload03.file;

import hello.upload03.Vo.UploadFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {

  /**
   * saveDir
   */
  @Value(value = "${file.route}")
  private String fileDir;

  public String getFullPath(String fileName) {
    return fileDir + fileName;
  }

  /**
   * 여러 이미지 파일 등록
   */
  public List<UploadFile> multiFileUpload(List<MultipartFile> multipartFiles) throws IOException {
    List<UploadFile> storeFileResult = new ArrayList<>();

    for (MultipartFile multipartFile : multipartFiles) {
      if(!multipartFile.isEmpty()) {
        storeFileResult.add(singleFileUpload(multipartFile));
      }
    }
    return storeFileResult;

  }

  /**
   * 파일 하나 등록
   */
  public UploadFile singleFileUpload(MultipartFile multipartFile) throws IOException {
    if(multipartFile.isEmpty()) {
      return null;
    }
    String originalFilename = multipartFile.getOriginalFilename();

    String storeFileName = createStoreFileName(originalFilename);
    String fullPath      = getFullPath(storeFileName);

    multipartFile.transferTo(new File(fullPath));

    return new UploadFile(originalFilename, storeFileName);
  }

  /**
   * 랜덤 id 생성
   */
  private static String createStoreFileName(String originalFilename) {
    String uuid = UUID.randomUUID()
                      .toString();
    String ext = extractExt(originalFilename);
    return uuid + "," + ext;
  }

  /**
   * 확장자 추출
   */
  private static String extractExt(String originalFilename) {
    int i = originalFilename.lastIndexOf(".");
    return originalFilename.substring(i + 1);
  }


}
