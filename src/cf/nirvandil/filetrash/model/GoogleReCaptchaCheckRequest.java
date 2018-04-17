package cf.nirvandil.filetrash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleReCaptchaCheckRequest {
    private String secret;
    private String response;
}
