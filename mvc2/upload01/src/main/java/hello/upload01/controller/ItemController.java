package hello.upload01.controller;

import hello.upload01.file.FileRepository;
import hello.upload01.file.FileUpload;
import hello.upload01.file.Item;
import hello.upload01.file.ItemForm;
import hello.upload01.file.ItemSave;
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

  private final FileRepository fileRepository;
  private final FileUpload fileUpload;

  @GetMapping("/items/new")
  public String itemForm(@ModelAttribute ItemForm itemForm) {

    return "item-form";
  }

  @PostMapping("/items/new")
  public String itemUpload(@ModelAttribute("itemForm") ItemForm itemForm, RedirectAttributes redirectAttributes) throws IOException {
    log.info("itemForm = {}", itemForm);

    ItemSave attchFile = fileUpload.saveFile(itemForm.getAttachFile());
    List<ItemSave> imageFiles = fileUpload.saveFiles(itemForm.getImageFiles());

    Item item = new Item();
    item.setItemName(itemForm.getItemName());
    item.setAttachFile(attchFile);
    item.setImageFiles(imageFiles);
    Item save = fileRepository.save(item);
    redirectAttributes.addAttribute("itemId", save.getId());

    return "redirect:/items/{itemId}";
  }

  @GetMapping("/items/{itemId}")
  public String uploadResult(@PathVariable("itemId") Long itemId, Model model) {
    Item item = fileRepository.findById(itemId);
    model.addAttribute("item", item);
    return "item-view";
  }

  @ResponseBody
  @GetMapping("/images/{imageFileName}")
  public Resource loadImages(@PathVariable("imageFileName") String imageFileName) throws MalformedURLException {
    log.info("imageFileName = {}", fileUpload.getFullPath(imageFileName));

    return new UrlResource("file:" + fileUpload.getFullPath(imageFileName));
  }

  @GetMapping("/attach/{itemId}")
  public ResponseEntity<Resource> loadAttch(@PathVariable("itemId") Long itemId) throws MalformedURLException {
    Item item = fileRepository.findById(itemId);
    String storeFileName = item.getAttachFile()
                               .getStoreFileName();
    String uploadFileName = item.getAttachFile()
                                .getUploadFileName();
    UrlResource urlResource       = new UrlResource("file:" + fileUpload.getFullPath(storeFileName));
    String      encodedFileName   = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
    String      stringDiscription = "attachment; filename=\"" + encodedFileName + "\"";

    return ResponseEntity.ok()
                         .header(HttpHeaders.CONTENT_DISPOSITION, stringDiscription)
                         .body(urlResource);
  }


}
