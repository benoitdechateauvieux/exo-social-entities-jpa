package org.exoplatform.social.dao;

import org.exoplatform.social.domain.Activity;

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
        TypedQuery query = entityManager.createNamedQuery("getActivitiesByLikerId", Activity.class);
        query.setParameter("likerId", "1");
        return query.getResultList();
    }
}
