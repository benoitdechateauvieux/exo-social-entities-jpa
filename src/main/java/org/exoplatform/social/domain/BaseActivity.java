package org.exoplatform.social.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by bdechateauvieux on 3/25/15.
 */
@MappedSuperclass
public abstract class BaseActivity {
    @Column(length = 2000)
    private String title;
    @Column(length = 36)
    private String titleId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date posted;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @Column(length = 36)
    private String posterId;
    @Column(length = 36)
    private String ownerId;
    @Column(length = 255)
    private String permaLink;
    @Column(length = 36)
    private String appId;
    @Column(length = 36)
    private String externalId;
    private Boolean locked;
    private Boolean hidden;

    @Deprecated
    @Column(length = 2000)
    private String body;
    @Deprecated
    @Column(length = 36)
    private String bodyId;
    @Deprecated
    private float priority;
}
