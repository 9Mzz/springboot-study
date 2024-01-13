package hello.fileupload.controller;

import hello.fileupload.dto.Item;
import hello.fileupload.dto.UplaodFile;
import hello.fileupload.repository.ItemRepository;
import hello.fileupload.repository.UploadLogic;
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
  private final UploadLogic    uploadLogic;

  @GetMapping("/items/new")
  public String itemForm(@ModelAttribute("form") ItemForm form) {

    return "item-form";
  }

  @PostMapping("/items/new")
  public String itemUpload(@ModelAttribute("form") ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
    //    log.info("form data = {}", form);

    UplaodFile       attachFile = uploadLogic.saveFile(form.getAttachFile());
    List<UplaodFile> imageFiles = uploadLogic.saveFiles(form.getImageFiles());
    Item             item       = itemRepository.save(new Item(form.getItemName(), attachFile, imageFiles));
    redirectAttributes.addAttribute("itemId", item.getId());

    return "redirect:/items/{itemId}";
  }

  @GetMapping("/items/{itemId}")
  public String getItem(@PathVariable("itemId") Long itemId, Model model) {
    Item formItem = itemRepository.findById(itemId);
    log.info("item data = {}", formItem);
    model.addAttribute("item", formItem);
    return "item-view";
  }

  @ResponseBody
  @GetMapping("/images/{itemName}")
  public Resource getImages(@PathVariable("itemName") String itemName) throws MalformedURLException {
    String fullPath = uploadLogic.getFullPath(itemName);

    return new UrlResource("file:" + fullPath);
  }

  @GetMapping("/attach/{itemId}")
  public ResponseEntity getAttachFile(@PathVariable("itemId") Long itemId) throws MalformedURLException {
    Item item = itemRepository.findById(itemId);

    String originalFileName = item.getAttachFile()
                                  .getOriginalFileName();
    String uploadFileName = item.getAttachFile()
                                .getUploadFileName();

    String      encodedFileName   = UriUtils.encode(originalFileName, StandardCharsets.UTF_8);
    UrlResource urlResource       = new UrlResource("file:"+ uploadLogic.getFullPath(uploadFileName));
    String      stringDisposition = "attachment; filename=\"" + encodedFileName + "\"";

    return ResponseEntity.ok()
                         .header(HttpHeaders.CONTENT_DISPOSITION, stringDisposition)
                         .body(urlResource);
  }


}
