package org.exoplatform.social.domain;

import javax.persistence.*;

/**
 * Created by bdechateauvieux on 3/26/15.
 */
@Entity
@Table(name = "SOC_STREAM_ITEMS")
public class StreamItem {
    public static final String TYPE_MY_ACTIVITIES   = "MY_ACTIVITIES";
    public static final String TYPE_CONNECTIONS     = "CONNECTIONS";

    @Id
    @GeneratedValue
    @Column(name="STREAM_ITEM_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ACTIVITY_ID")
    private Activity activity;

    @Column(length = 36)
    private String ownerId;
    @Column(length = 36)
    private String ownerStreamType;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerStreamType() {
        return ownerStreamType;
    }

    public void setOwnerStreamType(String ownerStreamType) {
        this.ownerStreamType = ownerStreamType;
    }
}
