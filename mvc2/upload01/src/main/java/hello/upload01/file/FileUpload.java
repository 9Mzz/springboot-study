package hello.upload01.file;

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
public class FileUpload {

  @Value(value = "${file.Dir}")
  private String fileDir;

  public String getFullPath(String fileName) {

    return fileDir + fileName;
  }

  public List<ItemSave> saveFiles(List<MultipartFile> multipartFiles) throws IOException {
    List<ItemSave> itemSaveList = new ArrayList<>();

    for (MultipartFile multipartFile : multipartFiles) {
      if(!multipartFile.isEmpty()) {
        itemSaveList.add(saveFile(multipartFile));      //Multipart 횟수만큼 반복을 시키고, 각 횟수마다 List에다 저장함.
      }
    }

    return itemSaveList;
  }

  public ItemSave saveFile(MultipartFile multipartFile) throws IOException {

    if(multipartFile.isEmpty()) {
      return null;
    }

    String originalFilename = multipartFile.getOriginalFilename();    //MultipartFile 에서 파일 이름을 가져옴
    String uploadFileName   = getUploadFileName(originalFilename);    //서버에 저장할 uuid값으로 변경
    multipartFile.transferTo(new File(getFullPath(uploadFileName)));  //uploadFileName 이름으로 서버 dir에 저장

    return new ItemSave(originalFilename, uploadFileName);            //originalFilename, uploadFileName의 정보를 return
  }

  private static String getUploadFileName(String originalFilename) {
    String ext = extractExt(originalFilename);
    String uuid = UUID.randomUUID()
                      .toString();
    return uuid + "." + ext;
  }

  private static String extractExt(String originalFilename) {
    int ext = originalFilename.lastIndexOf(".");
    return originalFilename.substring(ext + 1);
  }

}
