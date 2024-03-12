package com.example.dao;

import com.example.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class Itemdao {

    private final SessionFactory sessionFactory;


    public Itemdao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //       --------------------ADD BOOK---------------------


    public void createItemdao(Item item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(item);
            tx.commit();
        }
    }


    public void postItems(List<Item> items) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            for (Item item1 : items) {
                session.saveOrUpdate(item1);
            }
            tx.commit();
        }
    }


    //       --------------------------GET BOOK---------------------


    public List<Item> getAllItems() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Item where isExist = 1", Item.class).getResultList();
        }
    }


    public Item getItemById(Long id, Item item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Item item1 = session.get(Item.class, id);
            if (item1.getIsExist() == 1) {
                return session.get(Item.class, id);
            }
            return null;
        }
    }

    public Item getItemById(String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            return session.get(Item.class, name);
        }
    }

    public Item getItemByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Query<Item> query = session.createQuery("FROM Item WHERE name = :name", Item.class);
            query.setParameter("name", name);

            return query.uniqueResult();
        }
    }


    //       --------------------------UPDATE BOOK---------------------


    public void updateItem(Long id, Item item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            item.setId(id);
            Item item1 = session.get(Item.class, id);
            if (item1 != null) {
                updateField(item1, item.getName(), "name");
                updateField(item1, item.getPrice(), "price");
                updateField(item1, item.getGenre(), "genre");
                updateField(item1, item.getAuthormail(), "authormail");
                updateField(item1, item.getAvailablecount(), "availablecount");
                updateField(item1, item.getAuthor(), "author");
                updateField(item1, item.getPublishedyear(), "publishedyear");
            }
            session.save(item1);
            tx.commit();
        }
    }

    private <T> void updateField(Item item, T value, String fieldName) {
        if (value != null) {
            if ("name".equals(fieldName)) {
                item.setName((String) value);
            } else if ("price".equals(fieldName)) {
                item.setPrice((Integer) value);
            } else if ("genre".equals(fieldName)) {
                item.setGenre((String) value);
            } else if ("authormail".equals(fieldName)) {
                item.setAuthormail((String) value);
            } else if ("availablecount".equals(fieldName)) {
                item.setAvailablecount((Integer) value);
            } else if ("author".equals(fieldName)) {
                item.setAuthor((String) value);
            } else if ("publishedyear".equals(fieldName)) {
                item.setPublishedyear((Integer) value);
            }
        }
    }


    public void updateItemInPostman(Long id, Item item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Item item1 = session.get(Item.class, id);
            if (item1 != null) {
                item1.setName(item.getName());
                item1.setPrice((Integer) item.getPrice());
                item1.setGenre(item.getGenre());
                item1.setAuthormail(item.getAuthormail());
                item1.setAvailablecount((Integer) item.getAvailablecount());
                item1.setAuthor(item.getAuthor());
                item1.setPublishedyear((Integer) item.getPublishedyear());
            }
            session.save(item1);
            tx.commit();
        }
    }


    //---------------------DELETE-----------------------


    public void deleteItem(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Item item = session.get(Item.class, id);
            if (item != null)
                item.setIsExist(0);
            tx.commit();
        }
    }




    //---------------------------------PICK A BOOK---------------------------------------


    public void drawABook(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Item item = session.get(Item.class, id);
            int a = item.getAvailablecount();
            int isPresent = item.getIsExist();
            if (isPresent != 0) {
                if (a > 0) {
                    a -= 1;
                    item.setAvailablecount(a);
                }
            }
            session.save(item);
            tx.commit();
        }
    }



    //---------------------IDENTIFYING THE BOOK ID and EMAIL IS PRESENT OR NOT----------------------

    public boolean isIdPresentOrNot(Item item) {
        Session session = sessionFactory.getCurrentSession();
        Long getId = item.getId();
        Item item1 = session.get(Item.class, getId);
        return item1 != null;
    }


    public boolean isEmailPresentOrNotDao(String authormail) {
        Session session = sessionFactory.getCurrentSession();
        Item item = (Item) session.createQuery("FROM Item WHERE authormail = :authormail")
                .setParameter("authormail", authormail)
                .uniqueResult();
        return item != null;
    }





    //--------------------ADD STOCK----------------------------------------


        public void addBookStock(Long id, Integer i) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Item item = session.get(Item.class, id);
            if (item.getIsExist() == 1) {
                Integer currentCount=item.getAvailablecount();
                if(currentCount==null)
                    currentCount=0;
                Integer newAvailableCount = currentCount + i;
                item.setAvailablecount(newAvailableCount);
                session.save(item);
                tx.commit();
            }
        }
    }
}
