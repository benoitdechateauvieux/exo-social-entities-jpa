package org.exoplatform.social.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * Created by bdechateauvieux on 3/24/15.
 */
@Entity
@Table(name = "SOC_ACTIVITIES")
@EntityListeners({Activity.ActivityEntityListener.class})
@NamedQuery(name = "getActivitiesByLikerId",
            query = "select a from Activity a join a.likerIds likers where likers = :likerId")
public class Activity extends BaseActivity {
    @Id
//    @GeneratedValue
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column(name="ACTIVITY_ID", length=36)
//    private Long id;
    private String id;

    private String type;

    @ElementCollection
    @CollectionTable(
            name = "SOC_ACTIVITY_LIKERS",
            joinColumns=@JoinColumn(name = "ACTIVITY_ID")
    )
    @Column(name="LIKER_ID")
    private Set<String> likerIds = new HashSet<String>();

    @ElementCollection
    @JoinTable(
            name = "SOC_ACTIVITY_TEMPLATE_PARAMS",
            joinColumns=@JoinColumn(name = "ACTIVITY_ID")
    )
    @MapKeyColumn(name="TEMPLATE_PARAM_KEY")
    @Column(name="TEMPLATE_PARAM_VALUE")
    private Map<String, String> templateParams;

    @OneToMany
    @JoinTable(
            name = "SOC_ACTIVITY_COMMENTS",
            joinColumns = @JoinColumn(name = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMMENT_ID")
    )
    private List<Comment> comments;

    public void setId(String id) {
        this.id = id;
    }

    public void addLiker(String likerId) {
        this.likerIds.add(likerId);
    }

    public static class ActivityEntityListener {
        @PrePersist
        public void onPrePersist(Activity activity) {
            activity.setId(UUID.randomUUID().toString());
        }
    }
}
