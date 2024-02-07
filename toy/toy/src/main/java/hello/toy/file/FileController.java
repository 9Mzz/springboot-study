package hello.toy.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileController {

    private final ItemRepository itemRepository;
    private final UploadLogic    uploadLogic;

    @Value("${file_dir}")
    private String fileDir;

    @GetMapping("/file/item-form")
    public String itemForm(@ModelAttribute("item") ItemForm itemForm) {

        return "file/item-form";
    }

    @PostMapping("/file/item-form")
    public String itemUpload(@ModelAttribute("item") ItemForm itemForm, RedirectAttributes redirectAttributes)
            throws IOException {
        UploadFile       attachFile    = uploadLogic.fileUpload(itemForm.getAttachFile());
        List<UploadFile> getimageFiles = uploadLogic.uploadFiles(itemForm.getImageFiles());

        Item item = new Item();
        item.setItemName(itemForm.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(getimageFiles);
        Item saveResult = itemRepository.save(item);
        log.info("saveResult = {}", saveResult);
        redirectAttributes.addAttribute("itemId", saveResult.getId());

        return "redirect:/file/{itemId}";
    }

    @GetMapping("/file/{itemId}")
    public String getItem(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "file/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{imageFile}")
    public Resource getImaefiles(@PathVariable("imageFile") String imageFile) throws MalformedURLException {
        String      fullPath    = uploadLogic.getFullPath(imageFile);
        UrlResource urlResource = new UrlResource("file:" + fullPath);
        return urlResource;
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<UrlResource> getAttach(@PathVariable("itemId") Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        //실제 파일 이름
        String uploadFileName = item.getAttachFile()
                .getUploadFileName();
        String encodeFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String DISPOSITION    = "attachment; filename=\"" + encodeFileName + "\"";
        System.out.println("DISPOSITION = " + DISPOSITION);

        //서버에 저장된 파일 이름
        String storeFileName = item.getAttachFile()
                .getStoreFileName();
        String      fullPath    = uploadLogic.getFullPath(storeFileName);
        UrlResource urlResource = new UrlResource("file:" + fullPath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, DISPOSITION)
                .body(urlResource);
    }


    /**
     *
     */

    @GetMapping("/file/basic-upload")
    public String basicUploadForm() {
        return "file/upload-form";
    }

    @PostMapping("/file/basic-upload")
    public String basicUpload(@RequestParam("itemName") String itemName, @RequestParam("file") MultipartFile file)
            throws IOException {

        log.info("itemName = {}", itemName);
        log.info("file = {}, {}", file, file.getOriginalFilename());

        file.transferTo(new File(fileDir + file.getOriginalFilename()));

        return "file/upload-form";
    }
}
