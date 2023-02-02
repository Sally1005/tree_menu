package com.lonton.tree.treemenu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单角色
 * </p>
 *
 * @author 张利红
 */
@Data
public class TreeMenuRole implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 编号
     */
        @TableId(value = "id", type = IdType.AUTO)
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
