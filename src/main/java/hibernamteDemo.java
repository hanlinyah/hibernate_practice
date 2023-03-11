import Entity.TbBrandEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

//hibernate6.0棄用方法之說明參考
//https://github.com/hibernate/hibernate-orm/discussions/4520
public class hibernamteDemo {
    public  static SessionFactory sessionFactory=Util.hibernateUtil.getsessionFactory();
    public  static Session session=sessionFactory.openSession();
    public static Transaction transaction;
    public static void main(String[] args) {
        System.out.println("---1.1查詢全部(HQL)---");
        Query query=session.createQuery("from TbBrandEntity");
        List<TbBrandEntity> list = query.list();
        for(TbBrandEntity tb1: list) {
            System.out.println(tb1);
        }

        System.out.println("---1.2根據ID查詢---");
        System.out.println("\t查詢ID為2的資料...");
        TbBrandEntity tbBrandEntity=session.get(TbBrandEntity.class, 2);
        System.out.println(tbBrandEntity);


        System.out.println("---1.3條件查詢(HQL)---");
        System.out.println("\t查詢HQL語句：from TbBrandEntity  where brandName like ?1 and companyName like ?2 and status=?3");
        query=session.createQuery("from TbBrandEntity  where brandName like ?1 and companyName like ?2 and status=?3");
        query.setParameter(1,"%brandName%");
        query.setParameter(2,"%companyName%");
        query.setParameter(3,0);
        list = query.list();
        for(TbBrandEntity tb1: list) {
            System.out.println(tb1);
        }

        System.out.println("---2.新增單筆資料---");
        transaction=session.beginTransaction();
        String testbrandName= "insertBrandName";
        String testcompanyName= "insertCompanyName";
        Integer testordered= 12345678;
        String testdescription= "insertdescription";
        Integer teststatus = 999;
        tbBrandEntity=new TbBrandEntity(
                testbrandName,
                testcompanyName,
                testordered,
                testdescription,
                teststatus);
        session.persist(tbBrandEntity);
        transaction.commit();
        int insertid=tbBrandEntity.getId();
        System.out.println("\t插入的資料ID為："+insertid+"，插入單筆資料後全部數據如下：");
        query=session.createQuery("from TbBrandEntity");
        list = query.list();
        for(TbBrandEntity tb1: list) {
            System.out.println(tb1);
        }

        System.out.println("---3.修改資料---");
        System.out.println("\t修改ID為5的BrandName與CompanyName名稱...");
        transaction=session.beginTransaction();
        tbBrandEntity=session.get(TbBrandEntity.class,5);
        tbBrandEntity.setBrandName("updatebrandName");
        tbBrandEntity.setCompanyName("updatecompanyName");
        session.merge(tbBrandEntity);
        transaction.commit();
        System.out.println("\t修改資料後全部數據如下：");
        query=session.createQuery("from TbBrandEntity");
        list = query.list();
        for(TbBrandEntity tb1: list) {
            System.out.println(tb1);
        }

        System.out.println("---4.1刪除單筆資料---");
        System.out.println("\t刪除前一步驟新增之ID為5的資料...");
        transaction=session.beginTransaction();
        tbBrandEntity=session.get(TbBrandEntity.class,insertid);
        session.remove(tbBrandEntity);
        transaction.commit();
        System.out.println("\t刪除單筆資料後全部數據如下：");
        query=session.createQuery("from TbBrandEntity");
        list = query.list();
        for(TbBrandEntity tb1: list) {
            System.out.println(tb1);
        }

        System.out.println("---4.2刪除多筆資料---");
        System.out.println("\t刪除ID為1、3的資料...");
        transaction=session.beginTransaction();
        int[] ids={1,3};
        for(int id: ids) {
            tbBrandEntity=session.get(TbBrandEntity.class,id);
            session.remove(tbBrandEntity);
        }
        transaction.commit();
        System.out.println("\t刪除多筆資料後全部數據如下：");
        query=session.createQuery("from TbBrandEntity");
        list = query.list();
        for(TbBrandEntity tb1: list) {
            System.out.println(tb1);
        }

        session.close();
        sessionFactory.close();
    }
}
