package mars.ourmindmaze.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DmVO {
    private Long id;
    private String sender;
    private String reciver;
    private String content;
    private LocalDateTime createdDate;
}
