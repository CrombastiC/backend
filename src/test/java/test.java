import com.dletc.dao.ClazzDao;
import com.dletc.dao.UserDao;
import com.dletc.dao.impl.ClazzDaoImpl;
import com.dletc.dao.impl.UserDaoImpl;
import com.dletc.domain.Clazz;
import com.dletc.domain.Count;
import com.dletc.domain.PageBean;
import com.dletc.domain.User;
import com.dletc.service.ClazzService;
import com.dletc.service.UserService;
import com.dletc.service.impl.ClazzServiceImpl;
import com.dletc.service.impl.UserServiceImpl;
import com.dletc.utils.DBUtils;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.util.List;

public class test {
    @Test
    public void getConn() {

//        //测试数据库连接
//        Connection conn = DBUtils.getConnection();
//     if (conn == null){
//            System.out.println("数据库连接失败");
//     }else {
//         System.out.println("数据库连接成功");
//     }
//        UserDao userDao = new UserDaoImpl();
//        User user = userDao.getUserByUkeyAndPwd("admin", "123456");
//        System.out.println(user);
//
//        //测试业务层
//        UserService userService = new UserServiceImpl();
//        User admin=userService.login("admin","123456");
//        System.out.println(admin);

        //测试插入数据
        ClazzDao clazzDao = new ClazzDaoImpl();
        int i = clazzDao.addClazz(new Clazz(null, "java", "张三", "java基础班"));
        if (i>0) {
            System.out.println("插入成功");

        }
    }

    //测试dao层分页查询的方法
    @Test
    public void testPage() {
        ClazzDao clazzDao = new ClazzDaoImpl();
        List<Clazz> pageClazz = clazzDao.getPageClazz(1, 5);
        for (Clazz clazz : pageClazz) {
            System.out.println(clazz);

        }
    }
    //测试dao层获取总记录数的方法
    @Test
    public void testCount(){
        ClazzDao clazzDao = new ClazzDaoImpl();
        long count = clazzDao.getCount();
        System.out.println(count);
    }

    //测试业务层分页查询的方法
    @Test
    public void testPageBean(){
        ClazzService clazzService=new ClazzServiceImpl();
       PageBean<Clazz> pageBean = clazzService.getClazzByPage(1, 5);
        System.out.println(pageBean);
    }
    //测试dao层删除数据的方法
    @Test
    public void testDelData(){
        ClazzDao clazzDao = new ClazzDaoImpl();
        int i = clazzDao.deleteClazzById(1);
        if (i>0){
            System.out.println("删除成功");
        }
    }
    //测试业务层删除数据的方法
    @Test
    public void testDelData2(){
        ClazzService clazzService=new ClazzServiceImpl();
        int i = clazzService.removeClazzById(2);
        if (i>0){
            System.out.println("删除成功");
        }
    }
    //测试根据id修改数据
    @Test
    public void testUpdate(){
        ClazzDao clazzDao = new ClazzDaoImpl();
        int i = clazzDao.updateClazz(new Clazz(3, "java", "张三", "java基础班"));
        if (i>0){
            System.out.println("修改成功");
        }
    }
    //测试获取班级人数
    @Test
    public void testCountByClazz(){
        ClazzService clazzService=new ClazzServiceImpl();
        List<Count>  lsi= clazzService.findClazzCount();
        System.out.println(lsi);
    }
    //测试查询表中所有的数据
    @Test
    public void testFindAll(){
        ClazzService clazzService=new ClazzServiceImpl();
        List<Clazz>  lsi= clazzService.findAll();
        System.out.println(lsi);
    }
}

