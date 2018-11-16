package com.zhejiang.manage.controller;

import com.zhejiang.manage.model.Criteria;
import com.zhejiang.manage.service.ConfigService;
import com.zhejiang.manage.util.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author durantjiang
 */
@RestController
@RequestMapping("/configs")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     *
     * @param type 类型，两种类型之一
     * @return
     */
    @GetMapping("/{type}")
    public ResultVo getConfigs(@PathVariable String type){
        ResultVo resultVo = new ResultVo();

        if (StringUtils.isNotBlank(type)){
            resultVo.setStatus(true);
            resultVo.setData(configService.selectByParams(new Criteria(){{
                put("type",type);
            }}));
        }else {
            resultVo.setStatus(false);
            resultVo.setData(null);
        }
        return resultVo;
    }
}
