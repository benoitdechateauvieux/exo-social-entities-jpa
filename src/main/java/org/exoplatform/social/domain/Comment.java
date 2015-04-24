package org.exoplatform.social.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * Created by bdechateauvieux on 3/24/15.
 */
@Entity
@Table(name = "SOC_COMMENTS")
public class Comment extends BaseActivity{
    @Id
    @GeneratedValue
    @Column(name="COMMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ACTIVITY_ID")
    private Activity activity;

    @ElementCollection
    @CollectionTable(
            name = "SOC_COMMENT_LIKERS",
            joinColumns=@JoinColumn(name = "COMMENT_ID")
    )
    @Column(name="LIKER_ID")
    private List<String> likerIds;

    @ElementCollection
    @JoinTable(
            name = "SOC_COMMENT_TEMPLATE_PARAMS",
            joinColumns=@JoinColumn(name = "COMMENT_ID")
    )
    @MapKeyColumn(name="TEMPLATE_PARAM_KEY")
    @Column(name="TEMPLATE_PARAM_VALUE")
    private Map<String, String> templateParams;
}
