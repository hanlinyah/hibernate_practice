import Entity.TbBrandEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

//hibernate6.0棄用方法之說明參考
//https://github.com/hibernate/hibernate-orm/discussions/4520
public class hibernateTest {
    public  static SessionFactory sessionFactory=Util.hibernateUtil.getsessionFactory();
    public  static Session session=sessionFactory.openSession();
    public static Transaction transaction;
    @BeforeClass
    public static void beforeclass() throws Exception{
        transaction=session.beginTransaction();
    }
    @AfterClass
    public static void afterclass(){
//        transaction.rollback();
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public  void sellectAll_HQL(){
        Query query=session.createQuery("from TbBrandEntity");
        System.out.println("---查詢全部(HQL)---");
        List<TbBrandEntity> list = query.list();
        for(TbBrandEntity tbBrandEntity: list) {
            System.out.println(tbBrandEntity);
        }
    }

    @Test
    public  void sellectByCondition_HQL(){
        Query query=session.createQuery("from TbBrandEntity  where brandName like ?1 and companyName like ?2 and status=?3");
        query.setParameter(1,"%brandName%");
        query.setParameter(2,"%companyName%");
        query.setParameter(3,0);
        System.out.println("---條件查詢(HQL)---");
        List<TbBrandEntity> list = query.list();
        for(TbBrandEntity tbBrandEntity: list) {
            System.out.println(tbBrandEntity);
        }
    }
    @Test
    public  void sellectById(){
        TbBrandEntity tbBrandEntity=session.get(TbBrandEntity.class, 2);
        System.out.println("---根據ID查詢---");
        System.out.println(tbBrandEntity);
    }

    @Test
    public void insert(){
        String testbrandName= "insertBrandName";
        String testcompanyName= "insertCompanyName";
        Integer testordered= 12345678;
        String testdescription= "insertdescription";
        Integer teststatus = 999;
        TbBrandEntity tbBrandEntity=new TbBrandEntity(
                testbrandName,
                testcompanyName,
                testordered,
                testdescription,
                teststatus);
        System.out.println("---新增資料---");
        session.persist(tbBrandEntity);
        System.out.println("\t插入的資料ID為："+tbBrandEntity.getId());
    }
    @Test
    public void update(){
        TbBrandEntity tbBrandEntity=session.get(TbBrandEntity.class,5);
        tbBrandEntity.setBrandName("updatebrandName");
        tbBrandEntity.setCompanyName("updatecompanyName");
        System.out.println("---更新資料---");
        session.merge(tbBrandEntity);
    }

    @Test
    public void remove(){
        System.out.println("---刪除資料---");
        TbBrandEntity tbBrandEntity=session.get(TbBrandEntity.class,5);
        session.remove(tbBrandEntity);
    }

    @Test
    public void removeIDS(){
        System.out.println("---批量刪除資料---");
        int[] ids={1,3};
        for(int id: ids) {
            TbBrandEntity tbBrandEntity=session.get(TbBrandEntity.class,id);
            session.remove(tbBrandEntity);
        }
    }
}
