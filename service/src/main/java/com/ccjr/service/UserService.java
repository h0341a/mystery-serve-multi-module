package com.ccjr.service;

import com.ccjr.model.dto.UserDTO;
import com.ccjr.response.BusinessException;

import java.security.NoSuchAlgorithmException;

/**
 * @author ling
 */
public interface UserService {

    /**
     * 用户账号密码验证
     * @param userDTO 接收到的用户信息
     * @return 验证成功则返回uid,不成功则直接抛异常
     */
    public int userVerify(UserDTO userDTO) throws NoSuchAlgorithmException, BusinessException;
}