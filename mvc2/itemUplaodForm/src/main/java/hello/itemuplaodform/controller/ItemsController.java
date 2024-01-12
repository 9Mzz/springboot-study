package hello.itemuplaodform.controller;

import hello.itemuplaodform.form.Items;
import hello.itemuplaodform.form.ItemsForm;
import hello.itemuplaodform.form.ItemsRepository;
import hello.itemuplaodform.form.UploadFile;
import hello.itemuplaodform.form.UploadLogic;
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
public class ItemsController {

  private final ItemsRepository itemsRepository;
  private final UploadLogic     uploadLogic;

  @GetMapping("/items/new")
  public String itemsForm(@ModelAttribute("itemsForm") ItemsForm itemsForm) {

    return "item-form";
  }

  @PostMapping("/items/new")
  public String itemsUpload(@ModelAttribute("itemsForm") ItemsForm itemsForm, RedirectAttributes redirectAttributes) throws IOException {

    UploadFile       attachFile = uploadLogic.uploadFile(itemsForm.getAttachFile());
    List<UploadFile> imageFiles = uploadLogic.uploadFiles(itemsForm.getImageFiles());

    Items items = new Items();
    items.setItemName(itemsForm.getItemName());
    items.setAttachFile(attachFile);
    items.setImageFiles(imageFiles);
    Items saveResult = itemsRepository.save(items);

    redirectAttributes.addAttribute("itemId", items.getId());

    return "redirect:/items/{itemId}";
  }

  @GetMapping("/items/{itemId}")
  public String getItems(@PathVariable("itemId") Long itemId, Model model) {
    Items item = itemsRepository.findById(itemId);
    model.addAttribute("item", item);
    return "item-view";
  }


  @ResponseBody
  @GetMapping("/images/{itemName}")
  public Resource getImages(@PathVariable("itemName") String itemName) throws MalformedURLException {
    String fullPath = uploadLogic.getFullPath(itemName);

    return new UrlResource("file:" + fullPath);
  }

  @GetMapping("/attach/{itemId}")
  public ResponseEntity<Resource> getAttach(@PathVariable("itemId") Long itemId) throws MalformedURLException {
    Items items = itemsRepository.findById(itemId);
    //원본 파일
    String uploadFileName = items.getAttachFile()
                                 .getUploadFileName();
    String encodedFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
    //uuid로 변형된 파일
    String storeFileName = items.getAttachFile()
                                .getStoreFileName();
    UrlResource urlResource = new UrlResource("file:" + uploadLogic.getFullPath(storeFileName));
    String      disposition = "attachment; filename=\"" + encodedFileName + "\"";
    return ResponseEntity.ok()
                         .header(HttpHeaders.CONTENT_DISPOSITION, disposition)
                         .body(urlResource);
  }

}
