package Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            Configuration config=new Configuration().configure();
            sessionFactory=config.buildSessionFactory();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static SessionFactory getsessionFactory(){
        return sessionFactory;
    }


}
