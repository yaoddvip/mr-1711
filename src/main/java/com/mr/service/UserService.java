package com.mr.service;

import com.mr.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by ydd on 2018/5/7.
 */
public interface UserService {
    List<User> selectUserList();

    void export(ServletOutputStream outputStream);

    void importPoi(MultipartFile xxx, String filename) throws IOException;

    List<User> getList();

    void add(User user);
}
