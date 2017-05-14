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
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Controller
public class TrashMainController implements ServletContextAware
{
	private ServletContext context;
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexController()
	{
		return new ModelAndView("upload", "dataSiteKey", context.getInitParameter("dataSiteKey"));
	}
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ModelAndView uploadFile(
	        @RequestParam("file") MultipartFile file,
            @RequestParam("g-recaptcha-response") String gRecaptchaResponse,
            @RequestHeader String host)
    {
        if (validateCaptcha(gRecaptchaResponse) && (!file.isEmpty()))
        {
            try
                {
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
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return new ModelAndView("error", "errorMessage", e.getMessage());
                }
            } else
                {
                    return new ModelAndView("error", "errorMessage", "Empty file body.");
                }
    }
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
	private boolean validateCaptcha(String gRecaptchaResponse)
	{
	    String url = context.getInitParameter("checkCaptchaUrl");
	    String charset = "UTF-8";
	    String secret = context.getInitParameter("siteKeySecret");
	    boolean answer = false;
        try
        {
            String query = String.format("secret=%s&response=%s",
                    URLEncoder.encode(secret, charset),
                    URLEncoder.encode(gRecaptchaResponse, charset));
            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true); // POST
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }
            //TODO: Handle it as JSON, not simple string
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (reader.ready())
            {
                String line = reader.readLine();
                if (line.contains("success") && line.contains("true"))
                    answer = true;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return answer;
	}
}
