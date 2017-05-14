package cf.nirvandil.filetrash.controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nirvana on 14.05.17.
 * This program is licensed under GPLv3 and later.
 */
public interface TrashMainController {
    ModelAndView indexController();
    ModelAndView uploadFileController(MultipartFile file, String gRecaptchaResponse, String host, HttpServletRequest request);
}
