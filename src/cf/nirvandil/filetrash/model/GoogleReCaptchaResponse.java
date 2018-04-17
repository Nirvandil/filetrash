package cf.nirvandil.filetrash.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleReCaptchaResponse {
    private boolean success;
    private String hostname;
    @JsonProperty("error-codes")
    private List<String> errorCodes;
}
