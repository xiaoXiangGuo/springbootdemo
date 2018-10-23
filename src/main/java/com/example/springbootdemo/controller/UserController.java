package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.entity.UserExample;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.resultBean.common.ResultBean;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.util.FileUtils;
import com.example.springbootdemo.util.StringUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    /*@Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;*/
    @Autowired
    private UserService userService;

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
    public ResultBean login(String phone, String password) {
        try {
            User user = userService.login(phone, password);
            if (null == user) {
                return ResultBean.fail("帐号或密码错误");
            } else {
                return ResultBean.success(user);
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
    public ResultBean register(String phone ,String password,String description, @RequestParam("icon") MultipartFile file) {
        ResultBean resultBean = new ResultBean();
        String fileName = file.getOriginalFilename();

        if (file.isEmpty() || StringUtils.isEmpty(file.getOriginalFilename())) {
            resultBean.setMessage("文件为空");
            return resultBean;
        }
        String contentType = file.getContentType();
        if (StringUtils.isEmpty(contentType)){
            resultBean.setMessage("文件格式不正确");
            return resultBean;
        }
        if (!contentType.contains("")) {
            resultBean.setMessage("文件格式不正确");
            return resultBean;
        }
        String root_fileName = file.getOriginalFilename();
        System.out.println("上传图片:name={"+root_fileName+"},type={"+contentType+"}");
        //处理图片
//        User currentUser = userService.getCurrentUser();

        //获取路径
        String return_path = ImageUtil.getFilePath(currentUser);
        String filePath = location + return_path;
        logger.info("图片保存路径={}", filePath);
        String file_name = null;
        try {
            file_name = ImageUtil.saveImg(multipartFile, filePath);
            MarkDVo markDVo = new MarkDVo();
            markDVo.setSuccess(0);
            if(StringUtils.isNotBlank(file_name)){
                markDVo.setSuccess(1);
                markDVo.setMessage("上传成功");
                markDVo.setUrl(return_path+File.separator+file_name);
                markDVo.setCallback(callback);
            }
            logger.info("返回值：{}",markDVo);
            return markDVo;
        } catch (IOException e) {
            throw new BusinessException(ResultEnum.SAVE_IMG_ERROE);
        }


        String imageUri = null;
        if (fileName != null) {
            if (fileName.contains(".jpg") || fileName.contains(".png")) {
                File saveFile = FileUtils.saveFile(new File(FileUtils.ICON_IMAGE), fileName, file);
                if (null != saveFile) {
                    imageUri = saveFile.getAbsolutePath();
                } else {
                    resultBean.setMessage("图像上传失败");
                }
            } else {
                resultBean.setMessage("图片格式不正确");
            }
        }
        try {
            User user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            user.setIcon(imageUri);
            int insert = userService.register(user);
            System.out.println("注册成功:" + insert);

        } /*catch (DuplicateKeyException e) {
            e.printStackTrace();
            return ResultBean.fail("该用户名已被注册");
        }*/ catch (Exception e) {
            e.printStackTrace();
            return ResultBean.exception();
        }
        return ResultBean.success("注册成功:" + resultBean.getMessage());
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
