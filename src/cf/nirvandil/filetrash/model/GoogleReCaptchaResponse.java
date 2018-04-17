package cf.nirvandil.filetrash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleReCaptchaResponse {
    private boolean success;
    private String hostname;
}
