package com.zhejiang.manage.service;

import com.zhejiang.manage.mapper.InformationMapper;
import com.zhejiang.manage.model.Criteria;
import com.zhejiang.manage.model.Information;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;

    private static final Logger logger = LoggerFactory.getLogger(InformationServiceImpl.class);

    public int countByParams(Criteria example) {
        int count = this.informationMapper.countByParams(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Information selectByPrimaryKey(Integer id) {
        return this.informationMapper.selectByPrimaryKey(id);
    }

    public List<Information> selectByParams(Criteria example) {
        return this.informationMapper.selectByParams(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.informationMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Information record) {
        return this.informationMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Information record) {
        return this.informationMapper.updateByPrimaryKey(record);
    }

    public int deleteByParams(Criteria example) {
        return this.informationMapper.deleteByParams(example);
    }

    public int updateByParamsSelective(Information record, Criteria example) {
        return this.informationMapper.updateByParamsSelective(record, example.getCondition());
    }

    public int updateByParams(Information record, Criteria example) {
        return this.informationMapper.updateByParams(record, example.getCondition());
    }

    public int insert(Information record) {
        return this.informationMapper.insert(record);
    }

    public int insertSelective(Information record) {
        return this.informationMapper.insertSelective(record);
    }
}