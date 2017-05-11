package cf.nirvandil.filetrash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrashMainController {
	@RequestMapping("/index")
	public ModelAndView mainController()
	{
		String message = "<div><br>Just put your file and get direct link to it! <br><br></div>";
		return new ModelAndView("upload", "message", message);
	}
}
