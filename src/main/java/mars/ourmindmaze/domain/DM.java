package mars.ourmindmaze.domain;

import lombok.*;
import mars.ourmindmaze.common.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_dm")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DM extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "dm_content", length = 120)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reciver_id")
    private User reciver;
}
