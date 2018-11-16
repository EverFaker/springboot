package com.zhejiang.manage.controller;

import com.zhejiang.manage.model.Criteria;
import com.zhejiang.manage.model.InfoModel;
import com.zhejiang.manage.model.Information;
import com.zhejiang.manage.service.InformationService;
import com.zhejiang.manage.util.DateUtils;
import com.zhejiang.manage.util.ExportExcelUtil;
import com.zhejiang.manage.util.ExportExcelWrapper;
import com.zhejiang.manage.util.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author durantjiang
 */
@RestController
@RequestMapping("/infos")
public class InfoController {

    @Autowired
    private InformationService informationService;

    @GetMapping("")
    public ResultVo list(@RequestParam("title")String title,
                         @RequestParam("username")String username,
                         @RequestParam("mysqlOffset")Integer mysqlOffset,
                         @RequestParam("mysqlLength")Integer mysqlLength){
        ResultVo resultVo = new ResultVo(true);
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(title)){
            criteria.put("title","%"+title+"%");
        }
        if (!"admin".endsWith(username)) {
            criteria.put("attr1", username);
        }
        criteria.setMysqlOffset(mysqlOffset);
        criteria.setMysqlLength(mysqlLength);
        criteria.setOrderByClause("update_time desc");
        List<Information> information = informationService.selectByParams(criteria);
        if (!CollectionUtils.isEmpty(information)){
            information.forEach(e -> {
                e.setDate(DateUtils.dateToString(e.getUpdateTime()));
            });
        }
        int i = informationService.countByParams(criteria);
        Map<String,Object> map = new HashMap<>(2);
        map.put("total",i);
        map.put("data",information);
        resultVo.setData(map);
        return resultVo;
    }

    /**
     *  导出数据
     * @param ids
     * @param username
     * @param response
     */
    @GetMapping("/export")
    public void excel(String ids,String username,HttpServletResponse response){
        List<InfoModel> models = new ArrayList<>();
        // 入果不为空，那么导出选中的数据
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");

            for (int i = 0 ; i < split.length; i++){
                models.add(new InfoModel(informationService.selectByPrimaryKey(Integer.valueOf(split[i]))));
            };

        }else {
            // 如果list 为空，那么导出所有的数据，根据当前用户来导出
            Criteria criteria = new Criteria();
            if (!"admin".equals(username)){
                criteria.put("attr1",username);
            }
            List<Information> informationList = informationService.selectByParams(criteria);
            if (!CollectionUtils.isEmpty(informationList)){

                informationList.forEach( information -> {
                    models.add(new InfoModel(information));
                });
            }
        }

        String[] columnNames = { "标题", "单位", "联系人","联系电话","业务类型","类型","报告时间"};
        String fileName = "excel";
        ExportExcelWrapper<InfoModel> util = new ExportExcelWrapper<>();
        util.exportExcel("报告内容",fileName,columnNames,models,response,ExportExcelUtil.EXCEL_FILE_2003);

    }

    @PostMapping("")
    public ResultVo createInfo(@RequestBody Information information){
        ResultVo resultVo = new ResultVo(true);
        information.setDraftTime(new Date());
        information.setUpdateTime(new Date());
        int i = informationService.insertSelective(information);
        resultVo.setMsg("操作成功");
        return resultVo;
    }
}
