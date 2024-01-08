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

  private final FileUpload     fileUpload;
  private final FileRepository fileRepository;

  @GetMapping("/items/new")
  public String itemForm(@ModelAttribute ItemForm itemForm) {

    return "item-form";
  }

  @PostMapping("/items/new")
  public String itemUpload(@ModelAttribute ItemForm itemForm, RedirectAttributes redirectAttributes) throws IOException {
    ItemSave       attachFile = fileUpload.saveFile(itemForm.getAttachFile());
    List<ItemSave> imageFiles = fileUpload.saveFiles(itemForm.getImageFiles());
    Item           item       = new Item();
    item.setItemName(itemForm.getItemName());
    item.setAttachFile(attachFile);
    item.setImageFiles(imageFiles);
    fileRepository.save(item);

    redirectAttributes.addAttribute("itemId", item.getId());

    return "redirect:/items/{itemId}";
  }

  @GetMapping("/items/{itemId}")
  public String itemUploadResult(@PathVariable("itemId") Long itemId, Model model) {
    Item item = fileRepository.findById(itemId);
    model.addAttribute("item", item);

    return "item-view";
  }

  @ResponseBody
  @GetMapping("/images/{fileName}")
  public Resource getImageFile(@PathVariable("fileName") String fileName) throws MalformedURLException {

    return new UrlResource("file:" + fileUpload.getFullPath(fileName));
  }

  @GetMapping("/attach/{itemId}")
  public ResponseEntity<Resource> getattchFile(@PathVariable("itemId") Long itemId) throws MalformedURLException {
    Item item = fileRepository.findById(itemId);
    String storeFileName = item.getAttachFile()
                               .getStoreFileName();
    String uploadFileName = item.getAttachFile()
                                .getUploadFileName();
    String      encodedFileName    = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
    UrlResource urlResource        = new UrlResource("file:" + fileUpload.getFullPath(storeFileName));
    String      contentDiscription = "attachment; filename=\"" + encodedFileName + "\"";
    return ResponseEntity.ok()
                         .header(HttpHeaders.CONTENT_DISPOSITION, contentDiscription)
                         .body(urlResource);
  }


}
