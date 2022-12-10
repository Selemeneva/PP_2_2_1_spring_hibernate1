package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

/*    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }*/

   @Override
   public List<User> getUserByCar(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      String HQL = "from User as user where user.car.model =:model and user.car.series =:series";
      TypedQuery<User> query = session.createQuery(HQL, User.class).setParameter("model", model)
              .setParameter("series", series);
      return query.getResultList();
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
