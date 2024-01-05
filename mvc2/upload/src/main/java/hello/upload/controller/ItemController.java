package hello.upload.controller;

import hello.upload.domain.Item;
import hello.upload.domain.ItemRepository;
import hello.upload.domain.UploadFile;
import hello.upload.file.FileStore;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemRepository itemRepository;
  private final FileStore      fileStore;

  @GetMapping("/items/new")
  public String newItem(@ModelAttribute ItemForm itemForm) {

    return "item-form";
  }

  @PostMapping("/items/new")
  public String saveItem(@ModelAttribute ItemForm itemForm, RedirectAttributes redirectAttributes) throws IOException {
    //첨부파일 하나
    UploadFile attatchFile = fileStore.storeFile(itemForm.getAttachFile());
    //첨부파일 여러개
    List<UploadFile> storeImageFiles = fileStore.storeFiles(itemForm.getImageFiles());

    //DB에 저장
    Item item = new Item();
    item.setItemName(itemForm.getItemName());
    item.setAttachFile(attatchFile);
    item.setImageFiles(storeImageFiles);
    itemRepository.save(item);

    redirectAttributes.addAttribute("itemId", item.getId());

    return "redirect:/items/{itemId}";
  }

  @GetMapping("/items/{id}")
  public String items(@PathVariable("id") Long id, Model model) {
    Item item = itemRepository.findById(id);
    model.addAttribute("item", item);
    return "item-view";
  }

  @ResponseBody
  @GetMapping("/images/{fileName}")
  public Resource downloadImage(@PathVariable("fileName") String fileName) throws MalformedURLException {

    UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(fileName));
    log.info("urlResource = {}", urlResource);
    return urlResource;
  }

  @GetMapping("/attach/{itemId}")
  public ResponseEntity<Resource> downloadAttach(@PathVariable("itemId") Long itemId) throws MalformedURLException {
    Item item = itemRepository.findById(itemId);
    String storeFileName = item.getAttachFile()
                               .getStoreFileName();
    String uploadFileName = item.getAttachFile()
                                .getUploadFileName();
    UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
    log.info("uploadFileName = {}", uploadFileName);
    log.info("storeFileName = {}", storeFileName);
    String encodeUpLoadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
    String contentDisposition   = "attachment; filename=\"" + encodeUpLoadFileName + "\"";
    return ResponseEntity.ok()
                         .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                         .body(resource);
  }


}
