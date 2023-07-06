package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mars.ourmindmaze.common.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_log")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
