package mars.temporence.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {

    private String status;

    private String message;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{status:").append(status).append(", ");
        sb.append("message:").append(message).append('}');
        return sb.toString();
    }

}
