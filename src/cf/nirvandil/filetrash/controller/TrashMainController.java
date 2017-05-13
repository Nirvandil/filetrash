package cf.nirvandil.filetrash.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader String host)
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
				return "You successfully uploaded file " + file.getOriginalFilename() + ".\nIt " +
                        "can be downloaded from <a href=\"http://" + host + context.getContextPath() + rootPath +
                        File.separator + fileName + "\">this link</a>";
			} catch (Exception e) {
				e.printStackTrace();
				return "You failed to upload  => " + e.toString(); 
			}
		} else {
			return "You failed to upload, because the file was empty.";
		}
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
}
