package cf.nirvandil.filetrash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class TrashMainController implements ServletContextAware
{
	private ServletContext context;
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexController()
	{
		String message = "<div><br> Just put your file and get direct link to it! <br></div>";
		return new ModelAndView("upload", "message", message);
	}
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader String host)
	{
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                // Get directory to store file
				String rootPath = context.getInitParameter("uploadPath");
				File dir = new File(rootPath);
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				String url = "http://" + host + context.getContextPath() + rootPath + File.separator + fileName;
				return new ModelAndView("result", "url", url);
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("error", "errorMessage", e.toString());
			}
		} else {
			return new ModelAndView("error", "errorMessage", "Empty body.");
		}
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
}
