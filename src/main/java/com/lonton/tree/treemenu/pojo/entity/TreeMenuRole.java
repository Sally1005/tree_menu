package com.lonton.tree.treemenu.pojo.entity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单角色类
 *
 * @author 张利红
 */
@Data
public class TreeMenuRole implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 编号
     */
      private Long id;

      /**
     * 菜单id
     */
      private Long menuId;

      /**
     * 角色id
     */
      private Long roleId;

      /**
     * 数据创建时间
     */
      private LocalDateTime gmtCreate;

      /**
     * 数据最后修改时间
     */
      private LocalDateTime gmtModified;

}
