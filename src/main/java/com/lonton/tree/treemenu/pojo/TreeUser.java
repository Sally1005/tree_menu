package com.lonton.tree.treemenu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author 张利红
 */
@Data
@Accessors(chain = true)
public class TreeUser implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 编号
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 用户名
     */
      private String username;

      /**
     * 昵称
     */
      private String nickName;

      /**
     * 密码（密文）
     */
      private String password;

      /**
     * 头像URL
     */
      private String avatar;

      /**
     * 手机号码
     */
      private String phone;

      /**
     * 电子邮箱
     */
      private String email;

      /**
     * 用户状态，1=禁用，0=正常
     */
      private Integer status;

      /**
     * 数据创建时间
     */
      private LocalDateTime gmtCreate;

      /**
     * 数据最后修改时间
     */
      private LocalDateTime gmtModified;

      /**
     * 是否删除  -1：已删除  0：正常
     */
      private Integer isDelete;

}
