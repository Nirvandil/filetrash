package cf.nirvandil.filetrash.controller;

import cf.nirvandil.filetrash.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Controller
public class TrashMainControllerImpl implements ServletContextAware, TrashMainController
{
	private ServletContext context;
	private CommonsMultipartResolver multipartResolver;

	public TrashMainControllerImpl() {}
    @Autowired
    public TrashMainControllerImpl(CommonsMultipartResolver multipartResolver)
    {
        this.multipartResolver = multipartResolver;
    }

    @Override
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexController()
	{
	    ModelAndView index = new ModelAndView("index", "dataSiteKey", context.getInitParameter("dataSiteKey"));
	    multipartResolver.getFileUpload().setFileSizeMax(Long.parseLong(context.getInitParameter("maxUploadSize")));
	    index.getModelMap().addAttribute("maxUploadSize", multipartResolver.getFileUpload().getFileSizeMax());
	    index.getModelMap().addAttribute("uploadedFile", new UploadedFile());
		return index;
	}

	@Override
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ModelAndView uploadFileController(
	        @ModelAttribute("uploadedFile") UploadedFile file,
            @RequestParam("g-recaptcha-response") String gRecaptchaResponse,
            @RequestHeader String host,
            HttpServletRequest request)
    {
        if (!validateCaptcha(gRecaptchaResponse))
            return new ModelAndView("error", "errorMessage", "Invalid CAPTCHA.");
        if (file.getFile().isEmpty())
            return new ModelAndView("error", "errorMessage", "Empty file.");
        try
        {
            String uploadPath = context.getInitParameter("uploadPath");
            String fileName = file.getFile().getOriginalFilename();
            writeFile(file.getFile(), uploadPath, fileName);
            String url = request.isSecure()? "https://":"http://" + host +
                    context.getContextPath() + uploadPath + "/" + fileName;
            return new ModelAndView("result", "url", url);
        }
        catch (Exception e)
        {
            return new ModelAndView("error", "errorMessage", e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String helpController()
    {
        return "help";
    }

    @Override
    @RequestMapping(value = "/terms", method = RequestMethod.GET)
    public String termsController()
    {
        return "terms";
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
                {
                    answer = true;
                    break;
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return answer;
	}

    private void writeFile(MultipartFile file, String uploadPath, String fileName) throws IOException
    {
        byte[] bytes = file.getBytes();
        // Get directory to store file
        File dir = new File(uploadPath);
        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath() + "/" + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
    }
}
