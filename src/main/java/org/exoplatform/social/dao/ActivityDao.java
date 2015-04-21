package org.exoplatform.social.dao;

import org.exoplatform.social.domain.Activity;
import org.exoplatform.social.domain.StreamItem;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bdechateauvieux on 4/18/15.
 */
public class ActivityDao {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("bch-entities");

    public void saveActivity(Activity activity) {
        //TODO One activityManager per session
        EntityManager entityManager = FACTORY.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
    }

    public List<Activity> getActivityByLikerId(String likerId) {
        //TODO One activityManager per session
        EntityManager entityManager = FACTORY.createEntityManager();
        TypedQuery query = entityManager.createNamedQuery("Activity.getActivitiesByLikerId", Activity.class);
        query.setParameter("likerId", likerId);
        return query.getResultList();
    }

    public void saveStreamItem(StreamItem item) {
        //TODO One activityManager per session
        EntityManager entityManager = FACTORY.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public List<Activity> getActivityStream(String ownerId) {
        //TODO One activityManager per session
        EntityManager entityManager = FACTORY.createEntityManager();
        TypedQuery query = entityManager.createNamedQuery("Activity.getActivityStream", Activity.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }
}
