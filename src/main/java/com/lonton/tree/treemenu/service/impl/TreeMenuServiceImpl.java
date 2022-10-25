package com.loo.tree.treemenu.service.impl;


import com.loo.tree.treemenu.entity.TreeMenu;
import com.loo.tree.treemenu.mapper.TreeMenuMapper;
import com.loo.tree.treemenu.service.ITreeMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (TreeMenu)表服务实现类
 *
 * @author
 */
@Slf4j
@Service("treeMenuService")
public class TreeMenuServiceImpl implements ITreeMenuService {
    @Resource
    private TreeMenuMapper treeMenuMapper;

    /**
     * 查询数据库所有数据
     *
     * @return 数据库所有数据
     */
    @Override
    public List queryAll() {
        return treeMenuMapper.queryAll(null);
    }

    /**
     * 查询数据库数据，并处理后返回树形数据
     *
     * @return 树形数据
     */
    @Override
    public List<TreeMenu> listWithTree() {
        // 查找所有菜单数据
       List<TreeMenu> lists = treeMenuMapper.queryAll(null);
        // 把数据组合成树形结构
        List result = lists.stream().filter(treeMenu -> treeMenu.getMenuLevel() == 1)
                // 查找子菜单并放到第一级菜单中
                .map(menu -> {
                    menu.setTreeMenu(getChildren(menu, lists));
                    return menu;
                })
                // 根据排序字段排序
                .sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                // 把处理结果收集成一个 List 集合
                .collect(Collectors.toList());
        return result;
    }
    /**
     * 递归获取子菜单
     *
     * @param root 父菜单
     * @param all 总的数据
     * @return 子菜单
     */
    public List<TreeMenu> getChildren(TreeMenu root ,List<TreeMenu> all){
        List<TreeMenu> children = all.stream()
                // 根据父菜单 ID 查找当前菜单 ID，以便于找到当前菜单的子菜单
                .filter(menu ->menu.getParentMenuId() == root.getMenuId())
                // 递归查找子菜单的子菜单
                .map((menu) -> {
                    menu.setTreeMenu(getChildren(menu,all));
                    return menu;
                })
                // 根据排序字段排序
                .sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                // 把处理结果收集成一个 List 集合
                .collect(Collectors.toList());
        return children;
    }
}

     /*   Gson gson = new Gson();
        log.info("结果是：{}",gson.toJson(lists));
        Map<Long, TreeMenu> treeMenuMap = lists.stream().collect(Collectors.toMap(i -> i.getMenuId(), i -> i));
        List<TreeMenu> rootList = lists.stream().filter(i -> !treeMenuMap.containsKey(i.getParentMenuId())).collect(Collectors.toList());
        log.info("rootList is:{}", gson.toJson(rootList));
        for(TreeMenu treeMenu : lists){
            if(treeMenuMap.containsKey(treeMenu.getParentMenuId())){

            }
        }*/
      /*  Gson gson = new Gson();
        List<TreeVo> list = TreeVo.allTreeVoList();
        logger.info("结果是:{}", gson.toJson(list));
        Map<Integer, TreeVo> treeVoMap = list.stream().collect(Collectors.toMap(i -> i.getId(), i -> i));
        //获取顶级树节点
        List<TreeVo> rootList = list.stream().filter(i -> !treeVoMap.containsKey(i.getParent())).collect(toList());
        logger.info("rootList is:{}", gson.toJson(rootList));
        for (TreeVo treeVo : list) {
            if (treeVoMap.containsKey(treeVo.getParent())) {
                TreeVo parentTree = treeVoMap.get(treeVo.getParent());
                List<TreeVo> children = parentTree.getChildren();
                children.add(treeVo);
            }
        }
        logger.info("最后树结构是:{}", gson.toJson(rootList));
*/

        /*if(! CollectionUtils.isEmpty(lists)) {
            // 1、将根节点放入临时List中
            List<TreeMenu> tempList = lists.stream().filter(entity -> entity.getParentMenuId() == 0).collect(Collectors.toList());
            // 2、将所有数据放入Treemap中,遍历所有节点
            Map<Long, TreeMenu> treeMenuMap = lists.stream().collect(Collectors.toMap(TreeMenu::getMenuId, e -> e));
            for (Map.Entry<Long, TreeMenu> LongTreeMenuEntry : treeMenuMap.entrySet()) {
                TreeMenu treeMenu = LongTreeMenuEntry.getValue();
                // 2.1获取当前节点
                Long parentMenuId = treeMenu.getParentMenuId();
                // 2.2获取当前节点的父节点
                TreeMenu treeMenuParent = treeMenuMap.get(parentMenuId);
                // 跳过根节点
                if (ObjectUtils.isEmpty(treeMenuParent)) {
                    continue;
                }
                // 2.3将当前节点放入父节点的Children集合中
                if (ObjectUtils.isEmpty(treeMenuParent.getTreeMenu())) {
                    //2.3.1每新增一层，都要new ArrayList<>()
                    ArrayList<TreeMenu> treeMenuList = new ArrayList<>();
                    treeMenuList.add(treeMenu);
                    treeMenuParent.setTreeMenu(treeMenuList);
                } else {
                    // 2.3.2同层之间，在一个list中新增
                    treeMenuParent.getTreeMenu().add(treeMenu);
                }
                // 3 最后返回tempList即可
                String treeString = JSONObject.toJSONString(tempList);
                log.info("treeString:", treeString);
                listListResult.setStatus(200);
                listListResult.setData(tempList);
                listListResult.setMessage("查询树形结构成功");
            }
                return tempList;

            }
      }*/

