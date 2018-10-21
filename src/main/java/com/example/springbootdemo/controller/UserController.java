package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.entity.UserExample;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.resultBean.common.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    /*@Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;*/
    @Autowired
    private UserMapper mapper;

    /*public UserController() {
        new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SqlSession sqlSession = sqlSessionFactory.openSession(false);
            mapper = sqlSession.getMapper(UserMapper.class);
        });


    }*/


    @RequestMapping("/login")
    public ResultBean login(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(username)
                .andPasswordEqualTo(password);
        try {
            List<User> users = mapper.selectByExample(userExample);
            if (null == users || users.size() == 0) {
                return ResultBean.fail("帐号或密码错误");
            } else {
                return ResultBean.success(users);
            }
        } catch (Exception e) {
            return ResultBean.exception();
        }
        /*String sql = "select into user values(null,?,?)";
        try {
            DataSource dataSource = jdbcTemplate.getDataSource();
            Connection connection = dataSource.getConnection();
            PreparedStatement preState = connection.prepareStatement(sql);
            preState.setString(1, username);
            preState.setString(2, password);
            if (preState.execute()) {
                return "login失败";
            } else {
                return "login成功";
            }
        } catch (MySQLIntegrityConstraintViolationException | DuplicateKeyException e) {
            e.printStackTrace();
            return "login重复";
        } catch (Exception e) {
            e.printStackTrace();
            return "login报错:" + e.toString();
        }*/
    }

    @RequestMapping("/register")
    public ResultBean register(String username, String password) {
        try {
            int insert = mapper.insert(new User(username, password));
            System.out.println("注册成功:" + insert);

        } /*catch (DuplicateKeyException e) {
            e.printStackTrace();
            return ResultBean.fail("该用户名已被注册");
        }*/ catch (Exception e) {
            e.printStackTrace();
            return ResultBean.exception();
        }
        return ResultBean.success("注册成功");
        /*String sql = "insert into user values(null,?,?,null)";
        Connection connection = null;
        try {
            DataSource dataSource = jdbcTemplate.getDataSource();
            if (null == dataSource) {
                return null;
            }
            connection = dataSource.getConnection();
            PreparedStatement preState = connection.prepareStatement(sql);
            preState.setString(1, username);
            preState.setString(2, password);
            boolean execute = preState.execute();
            if (execute) {
                return null;
            } else {
                return null;
            }
        } catch (MySQLIntegrityConstraintViolationException | DuplicateKeyException e) {
            e.printStackTrace();
            Class<? extends UserController> aClass = getClass();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != connection) {
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}
