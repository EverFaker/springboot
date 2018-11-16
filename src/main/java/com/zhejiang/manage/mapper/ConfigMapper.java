package com.zhejiang.manage.mapper;

import com.zhejiang.manage.model.Config;
import com.zhejiang.manage.model.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByParams(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Config record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Config record);

    /**
     * 根据条件查询记录集
     */
    List<Config> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    Config selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") Config record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") Config record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Config record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Config record);
}