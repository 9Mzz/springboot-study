package hello.toyex.file;

import lombok.Data;

@Data
public class ItemUpload {


    /**
     * 업로드 할 원본 파일 이름
     */
    private String uploadFileName;

    /**
     * 서버에 저장할 UUID 이름
     */
    private String storeFileName;

    public ItemUpload(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
