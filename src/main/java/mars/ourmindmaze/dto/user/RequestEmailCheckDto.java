package mars.ourmindmaze.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestEmailCheckDto {
    @Schema(example = "inhoo23@naver.com")
    private String email;
}
