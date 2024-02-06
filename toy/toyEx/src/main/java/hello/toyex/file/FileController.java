package hello.toyex.file;

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

    @Value("${filePath}")
    private String filePath;

    @GetMapping("/file/item-form")
    public String itemForm(@ModelAttribute("item") ItemForm itemParam) {
        return "/file/item-form";
    }

    @PostMapping("/file/item-form")
    public String itemUpload(@ModelAttribute("item") ItemForm itemParam, RedirectAttributes redirectAttributes)
            throws IOException {
        ItemUpload       attachFile      = uploadLogic.upload(itemParam.getAttachFile());
        List<ItemUpload> storeImageFiles = uploadLogic.uploads(itemParam.getImageFiles());
        Item             item            = new Item();
        item.setItemName(itemParam.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);
        Item saveResult = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveResult.getId());
        return "redirect:/file/{itemId}";
    }

    @GetMapping("/file/{fileId}")
    public String getUploadFile(@PathVariable("fileId") Long fileId, Model model) {
        Item item = itemRepository.findById(fileId);
        model.addAttribute("item", item);

        return "file/item-view";
    }


    @ResponseBody
    @GetMapping("/images/{imageName}")
    public Resource getImage(@PathVariable("imageName") String imageName) throws MalformedURLException {
        String fullPath = uploadLogic.fullPath(imageName);

        return new UrlResource("file:" + fullPath);
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> getAttachFile(@PathVariable("itemId") Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        String uploadFileName = item.getAttachFile()
                .getUploadFileName();
        //저장할 파일 이름
        String encodeFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String DISPOSITION    = "attachment; filename=\"" + encodeFileName + "\"";
        //서버에서 꺼낼 파일 이름
        String storeFileName = item.getAttachFile()
                .getStoreFileName();
        String      fullPath    = uploadLogic.fullPath(storeFileName);
        UrlResource urlResource = new UrlResource("file:" + fullPath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, DISPOSITION)
                .body(urlResource);
    }

    /**
     *
     */

    @GetMapping("/file/fileForm")
    public String uploadForm() {
        return "file/upload-form";
    }

    @PostMapping("/file/fileForm")
    public String upload(@RequestParam("itemName") String itemName, @RequestParam("file") MultipartFile file)
            throws IOException {

        String fullPath = filePath + file.getOriginalFilename();
        log.info("itemName = {}", itemName);
        log.info("file data = {}, {}", fullPath, file.getSize());
        file.transferTo(new File(fullPath));

        return "file/upload-form";
    }

}
