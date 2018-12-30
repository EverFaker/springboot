package com.zhejiang.manage.controller;

import com.zhejiang.manage.model.Criteria;
import com.zhejiang.manage.model.User;
import com.zhejiang.manage.service.UserService;
import com.zhejiang.manage.util.DateUtils;
import com.zhejiang.manage.util.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author durantjiang
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登陆
     *
     * @param user 用户账号和密码
     */
    @PutMapping("")
    public ResultVo userLogin(@RequestBody User user, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        if (Objects.isNull(user)) {
            resultVo.setStatus(false);
            resultVo.setMsg("请输入用户名和密码");
        } else {
            if (StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {
                List<User> users = userService.selectByParams(new Criteria() {{
                    put("username", user.getUsername());
                    put("password", user.getPassword());
                    put("attr2", "1");
                }});
                if (!CollectionUtils.isEmpty(users)) {
                    request.getSession().setAttribute("user", users.get(0));

                    user.setId(users.get(0).getId());
                    userService.updateByPrimaryKeySelective(user);
                    resultVo.setStatus(true);
                    resultVo.setData(users.get(0));
                } else {
                    resultVo.setStatus(false);
                    resultVo.setMsg("用户名或密码错误");
                }

            } else {
                resultVo.setStatus(false);
                resultVo.setMsg("请输入用户名和密码");
            }
        }
        return resultVo;
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @PutMapping("/password")
    public ResultVo updatePassword(@RequestBody User user, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();

        if (Objects.isNull(user)) {
            resultVo.setStatus(false);
            resultVo.setMsg("请输入用户名和密码");
        } else {
            if (user.getId() != null && StringUtils.isNotBlank(user.getNewPassword()) && StringUtils.isNotBlank(user.getUsername())
                    && StringUtils.isNotBlank(user.getPassword())) {

                List<User> users = userService.selectByParams(new Criteria() {{
                    put("username", user.getUsername());
                    put("password", user.getPassword());
                    put("attr2", "1");
                }});
                if (!CollectionUtils.isEmpty(users)) {
                    user.setPassword(user.getNewPassword());
                    userService.updateByPrimaryKeySelective(user);
                    request.getSession().removeAttribute("user");
                    resultVo.setStatus(true);
                    resultVo.setMsg("修改密码成功，请重新登陆");
                } else {
                    resultVo.setStatus(false);
                    resultVo.setMsg("原密码错误");
                }

            } else {
                resultVo.setStatus(false);
                resultVo.setMsg("输入信息不全");
            }
        }
        return resultVo;
    }

    /**
     * 退出登陆
     *
     * @return
     */
    @GetMapping("/out")
    public ResultVo logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new ResultVo(1, "退出成功", true);
    }

    /**
     * 获取联系人
     * @param company
     * @return
     */
    @GetMapping("/con/{company}")
    public ResultVo getConUser(@PathVariable String company) {
        ResultVo resultVo = new ResultVo(true);

        resultVo.setData(userService.selectByParams(new Criteria() {{
            put("attr1", company);
            put("attr2", "2");
        }}));
        return resultVo;
    }

    /**
     *  查询所有用户
     * @param username
     * @param mysqlOffset
     * @param mysqlLength
     * @return
     */
    @GetMapping("")
    public ResultVo getAllUser(@RequestParam("username")String username,
                               @RequestParam("mysqlOffset")Integer mysqlOffset,
                               @RequestParam("mysqlLength")Integer mysqlLength){
        ResultVo resultVo = new ResultVo(true);
        Criteria criteria = new Criteria();
        criteria.setMysqlLength(mysqlLength);
        criteria.setMysqlOffset(mysqlOffset);
        criteria.setOrderByClause("last_login desc");
        criteria.put("attr2","1");
        if (StringUtils.isNotBlank(username)){
            criteria.put("uname","%"+username+"%");
        }
        List<User> users = userService.selectByParams(criteria);
        if (!CollectionUtils.isEmpty(users)){
            users.forEach(e -> {
                e.setDate(DateUtils.dateToString(e.getLastLogin()));
            });
        }
        int i = userService.countByParams(criteria);
        Map<String,Object> map = new HashMap<>(2);
        map.put("total",i);
        map.put("data",users);
        resultVo.setData(map);
        return resultVo;
    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("")
    public ResultVo createUser(@RequestBody User user){
        ResultVo resultVo = new ResultVo(true);
        if (Objects.isNull(user)){
            resultVo.setStatus(false);
            resultVo.setMsg("请填写用户信息");
        }else {
            if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getAttr1())|| StringUtils.isEmpty(user.getPhone())){
                resultVo.setStatus(false);
                resultVo.setMsg("请填写完整的用户信息");
            }else {
                Criteria criteria = new Criteria();
                criteria.put("username",user.getUsername());
                criteria.put("attr2","1");
                List<User> users = userService.selectByParams(criteria);
                if (!CollectionUtils.isEmpty(users)){
                    resultVo.setStatus(false);
                    resultVo.setMsg("用户已存在");
                }else {
                    user.setPassword("123456");
                    user.setLastLogin(new Date());
                    user.setAttr2("1");
                    userService.insertSelective(user);
                    resultVo.setMsg("新增用户成功");
                }
            }
        }
        return resultVo;
    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public ResultVo createUser1(@RequestBody User user){
        ResultVo resultVo = new ResultVo(true);
        if (Objects.isNull(user)){
            resultVo.setStatus(false);
            resultVo.setMsg("请填写用户信息");
        }else {
            if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPhone())){
                resultVo.setStatus(false);
                resultVo.setMsg("请填写完整的用户信息");
            }else {
                Criteria criteria = new Criteria();
                criteria.put("username",user.getUsername());
                criteria.put("attr2","2");
                criteria.put("attr1",user.getAttr1());
                List<User> users = userService.selectByParams(criteria);
                if (!CollectionUtils.isEmpty(users)){
                    resultVo.setStatus(false);
                    resultVo.setMsg("联系人已存在");
                }else {
                    user.setAttr2("2");
                    userService.insertSelective(user);
                    resultVo.setMsg("新增联系人成功");
                }
            }
        }
        return resultVo;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable Integer id){
        ResultVo resultVo = new ResultVo(true);
        int i = userService.deleteByPrimaryKey(id);
        if (i <= 0){
            resultVo.setStatus(false);
            resultVo.setMsg("系统错误，删除失败");
        }
        return resultVo;
    }


}