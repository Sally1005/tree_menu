package com.lonton.tree.treemenu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author 张利红
 */
@Data
public class TreeRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

        /**
     * 角色名称
     */
      private String name;

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
