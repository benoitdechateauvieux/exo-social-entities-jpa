package org.exoplatform.social.service;

import org.exoplatform.social.domain.Activity;
import org.exoplatform.social.domain.StreamItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebListener;

/**
 * Created by bdechateauvieux on 3/25/15.
 */
@WebListener
public class MyServletListener {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("bch-entities");
            System.out.println("Entity Menager Test.............." + entityManagerFactory);

            EntityManager em = entityManagerFactory.createEntityManager();
            long start = System.currentTimeMillis();
            em.getTransaction().begin();
            Activity activity = new Activity();
            em.persist(activity);
//            for (int i=0; i<198; i++) {
            for (int i=0; i<90000; i++) {
                StreamItem item = new StreamItem();
                item.setActivity(activity);
                em.persist(item);
            }
            em.getTransaction().commit();
            System.out.println(System.currentTimeMillis() - start);
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);

        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
