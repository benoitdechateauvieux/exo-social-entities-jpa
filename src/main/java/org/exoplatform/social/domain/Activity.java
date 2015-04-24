package org.exoplatform.social.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bdechateauvieux on 3/24/15.
 */
@Entity
@Table(name = "SOC_ACTIVITIES")
//@EntityListeners({Activity.ActivityEntityListener.class})
@NamedQueries({
        @NamedQuery(name = "Activity.getActivitiesByLikerId",
                query = "select a from Activity a join a.likerIds likers where likers = :likerId"),
        @NamedQuery(name = "Activity.getActivityStream",
        query = "select DISTINCT s.activity from StreamItem s where s.ownerId = :ownerId")
})
public class Activity extends BaseActivity {
    @Id
    @GeneratedValue
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid2")
//    @Column(name="ACTIVITY_ID", length=36)
//    private String id;
    private Long id;

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

    @OneToMany(mappedBy = "activity")
    private List<Comment> comments;

    public void addLiker(String likerId) {
        this.likerIds.add(likerId);
    }

//    public void setId(String id) {
//        this.id = id;
//    }

//    public static class ActivityEntityListener {
//        @PrePersist
//        public void onPrePersist(Activity activity) {
//            activity.setId(UUID.randomUUID().toString());
//        }
//    }
}
