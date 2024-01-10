package com.common.bean.entity;

import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description:
 * @Author: lin
 * @Date: 2023/11/11 15:18
 */
//@Tag(name = "用户", description = "用户实体类")
@Data
public class UserEntity {

    @Schema(name = "用户id", type = "long")
    private Long id;
    @Schema(name = "用户名", type = "long")
    private String name;
    @Schema(name = "密码", type = "password")
    private String password;

}

