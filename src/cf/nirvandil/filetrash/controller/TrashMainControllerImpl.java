package cf.nirvandil.filetrash.controller;

import cf.nirvandil.filetrash.model.GoogleReCaptchaCheckRequest;
import cf.nirvandil.filetrash.model.GoogleReCaptchaResponse;
import cf.nirvandil.filetrash.model.UploadedFile;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

@Slf4j
@Controller
public class TrashMainControllerImpl implements ServletContextAware, TrashMainController {
    private ServletContext context;
    private CommonsMultipartResolver multipartResolver;
    private final RestTemplate restTemplate;

    @Autowired
    public TrashMainControllerImpl(CommonsMultipartResolver multipartResolver, RestTemplate restTemplate) {
        this.multipartResolver = multipartResolver;
        this.restTemplate = restTemplate;
    }

    @Override
    @GetMapping(value = "/index")
    public ModelAndView indexController() {
        ModelAndView index = new ModelAndView("index", "dataSiteKey",
                context.getInitParameter("dataSiteKey"));
        multipartResolver.setMaxUploadSize(Long.parseLong(context.getInitParameter("maxUploadSize")));
        index.getModelMap().addAttribute("maxUploadSize", multipartResolver.getFileUpload().getSizeMax());
        index.getModelMap().addAttribute("uploadedFile", new UploadedFile());
        return index;
    }

    @Override
    @PostMapping(value = "/upload")
    public ModelAndView uploadFileController(
            @ModelAttribute("uploadedFile") UploadedFile file,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @RequestHeader String host,
            HttpServletRequest request) {
        if (!validateCaptcha(captchaResponse))
            return new ModelAndView("error", "errorMessage", "Invalid CAPTCHA.");
        if (file.getFile().isEmpty())
            return new ModelAndView("error", "errorMessage", "Empty file.");
        try {
            String uploadPath = context.getInitParameter("uploadPath");
            String fileName = file.getFile().getOriginalFilename();
            writeFile(file.getFile(), uploadPath, fileName);
            String url = request.isSecure() ? "https://" : "http://" + host +
                    context.getContextPath() + uploadPath + "/" + fileName;
            return new ModelAndView("result", "url", url);
        } catch (Exception e) {
            log.warn("Catches exception when uploading file.", e);
            return new ModelAndView("error", "errorMessage", e.getMessage());
        }
    }

    @Override
    @GetMapping(value = "/help")
    public String helpController() {
        return "help";
    }

    @Override
    @GetMapping(value = "/terms")
    public String termsController() {
        return "terms";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @SneakyThrows
    private boolean validateCaptcha(String captchaResponse) {
        String url = context.getInitParameter("checkCaptchaUrl");
        String secret = context.getInitParameter("siteKeySecret");
        GoogleReCaptchaCheckRequest captchaCheckRequest = new GoogleReCaptchaCheckRequest(secret, captchaResponse);
        log.info("Sending captcha check request: {}.", captchaCheckRequest);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        map.add("secret", secret);
        map.add("response", captchaResponse);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        GoogleReCaptchaResponse response = restTemplate.postForEntity(url, request, GoogleReCaptchaResponse.class).getBody();
        log.info("Response from google captcha check: {}", response);
        return response.isSuccess();
    }

    private void writeFile(MultipartFile file, String uploadPath, String fileName) throws IOException {
        byte[] bytes = file.getBytes();
        // Get directory to store file
        File dir = new File(uploadPath);
        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
    }
}
