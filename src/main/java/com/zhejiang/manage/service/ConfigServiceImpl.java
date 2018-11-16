package com.zhejiang.manage.service;

import com.zhejiang.manage.mapper.ConfigMapper;
import com.zhejiang.manage.model.Config;
import com.zhejiang.manage.model.Criteria;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    public int countByParams(Criteria example) {
        int count = this.configMapper.countByParams(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Config selectByPrimaryKey(Integer id) {
        return this.configMapper.selectByPrimaryKey(id);
    }

    public List<Config> selectByParams(Criteria example) {
        return this.configMapper.selectByParams(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.configMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Config record) {
        return this.configMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Config record) {
        return this.configMapper.updateByPrimaryKey(record);
    }

    public int deleteByParams(Criteria example) {
        return this.configMapper.deleteByParams(example);
    }

    public int updateByParamsSelective(Config record, Criteria example) {
        return this.configMapper.updateByParamsSelective(record, example.getCondition());
    }

    public int updateByParams(Config record, Criteria example) {
        return this.configMapper.updateByParams(record, example.getCondition());
    }

    public int insert(Config record) {
        return this.configMapper.insert(record);
    }

    public int insertSelective(Config record) {
        return this.configMapper.insertSelective(record);
    }
}