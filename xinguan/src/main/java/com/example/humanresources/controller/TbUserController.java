package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.entity.TbUser;
import com.example.humanresources.entity.TbUserRole;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.example.humanresources.service.TbDepartmentService;
import com.example.humanresources.service.TbUserRoleService;
import com.example.humanresources.service.TbUserService;
import com.example.humanresources.utils.Encrypt;
import com.example.humanresources.utils.ExportPoiUtil;
import com.example.humanresources.utils.JwtUtil;
import com.example.humanresources.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户表(TbUser)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:04
 */
@RestController
@RequestMapping("tbUser")
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;

    @Resource
    private TbUserRoleService tbUserRoleService;

    @Resource
    private TbDepartmentService tbDepartmentService;

    /**
     * zf
     * 添加用户信息
     */

    @Operation("添加用户信息")
    @PostMapping("add")
    public R addUser(@RequestBody TbUser userVO) {
        userVO.setSalt(userVO.getUsername());
        userVO.setPassword(Encrypt.getEncryption(userVO.getPassword(), userVO.getUsername()));
        tbUserService.insert(userVO);
        return R.ok().setData("添加用户成功");
    }

    /**
     * zf
     * 删除用户，从库里面彻底删除
     */
    @Operation("删除用户")
    @DeleteMapping("delete/{id}")
    public R deleteUser(@PathVariable Long id) {
//        TbUser user = new TbUser();
//        user.setId(id);
//        user.setStatus(0);
//
//        tbUserService.updateById(user);
//        return R.ok().setData("删除用户成功");
        tbUserService.deleteById(id);
        return R.ok().setData("删除用户成功");
    }

    /**
     * zf
     * 根据用户id获取用户信息
     */
    @GetMapping("edit/{id}")
    public R editUser(@PathVariable Long id) {
        TbUser user = tbUserService.selectById(id);
        if (user == null) {
            return R.exp(ErrorCodeEnum.USER_ACCOUNT_NOT_FOUND.getResultCode(), ErrorCodeEnum.USER_ACCOUNT_NOT_FOUND.getResultMsg());
        }
        return R.ok().setData(user);
    }

    /**
     * zf
     * 导出excel
     */
    @Operation("导出用户列表Excel")
    @GetMapping("excel")
    public void exportExcel(HttpServletResponse response) {
        String fileName = "用户列表";//excel文件名
        //获取用户列表
        List<TbUser> tbUsers = tbUserService.selectAllNotPaging();
        //列名
        String[] columnNames = {"编号", "用户名", "昵称", "邮箱", "电话号码", "创建时间", "修改时间", "性别",
                "密码盐值", "用户类型", "用户密码", "出生日期", "头像url"};
        //将要创建的Map的键
        String[] keys = {"id", "username", "nickname", "email", "phoneNumber", "createTime", "modifiedTime", "sex",
                "salt", "type", "password", "birth", "avatar"};
        //生成并返回文件
        try {
            ExportPoiUtil.start_download(response, fileName, tbUsers, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * zf
     * 登录
     */
    @PostMapping("login")
    public R login(@RequestBody TbUser loginBody) {
        Subject currentUser = SecurityUtils.getSubject();
        String md5Pwd = Encrypt.getEncryption(loginBody.getPassword(), loginBody.getUsername());
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginBody.getUsername(), md5Pwd);
        try {
            currentUser.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            throw new ServiceException(ErrorCodeEnum.USER_ACCOUNT_NOT_FOUND);
        } catch (IncorrectCredentialsException e) {
            throw new ServiceException(ErrorCodeEnum.USER_PASSWORD_ERROR);
        }
        long time = System.currentTimeMillis();
        return R.ok().setData(JwtUtil.toToken(loginBody.getUsername(), Long.toString(time)));

    }

    /**
     * zf
     * 用户登入后,根据角色加载菜单树
     */
    @GetMapping("findMenu")
    public R findRoleMenu(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        //从请求头中获取username
        String authorization = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(authorization);
        List<TbMenu> menu = tbUserService.findMenu(username);
        if (menu == null) {
            throw new ServiceException(ErrorCodeEnum.MENU_NOT_ERROR);
        }

        //返回菜单树
        return R.ok().setData(menu);
    }

    /**
     * 分页模糊查询用户列表
     */
    @GetMapping("findUserList")
    public R findUserList(TbUser user, Integer pageNum, Integer pageSize) {
        System.out.println(user.getEmail());
        return R.ok().setData(tbUserService.findUserList(pageNum, pageSize, user));
    }

    /**
     * 登录后返回改用户信息
     */
    @GetMapping("info")
    public R info(HttpServletRequest request) {
        String username = JwtUtil.getUsername(request.getHeader("Authorization"));
        TbUser user = tbUserService.getUserByUsername(username);
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("avatar", user.getAvatar());
        map.put("department", tbDepartmentService.selectById(user.getDepartmentId()).getName());
        map.put("nickname", user.getNickname());
        if (user.getType() == 0) {
            map.put("isAdmin", true);
        } else {
            map.put("roles", tbUserService.findRoleListByUserId(user.getId()));
        }
        return R.ok().setData(map);
    }

    /**
     * 修改用户信息
     */
    @Operation("修改用户信息")
    @PutMapping("update/{id}")
    public R update(@PathVariable Long id, @RequestBody TbUser user) {
        user.setId(id);
        return R.ok().setData(tbUserService.updateById(user));
    }

    /**
     * 禁用和启用这两种状态
     */
    @Operation("修改用户禁用状态")
    @PutMapping("updateStatus/{id}/{status}")
    public R updateStatus(@PathVariable Long id, @PathVariable Integer status, @RequestBody TbUser userEditVO) {
        userEditVO.setId(id);
        userEditVO.setStatus(status);
        return R.ok().setData(tbUserService.updateById(userEditVO));
    }

    /**
     * 角色分配给用户
     */
    @Operation("给角色分配用户")
    @PostMapping("{id}/assignRoles")
    public R assignRoles(@PathVariable Long id, @RequestBody long[] rids) {
        for (long rid : rids) {
            TbUserRole tbUserRole = new TbUserRole();
            tbUserRole.setUserId(id);
            tbUserRole.setRoleId(rid);
            tbUserRoleService.insert(tbUserRole);
        }
        return R.ok().setData("分配成功");
    }

    /**
     * 根据用户id，获取用户已经拥有的角色
     */
    @GetMapping("{id}/roles")
    public R getRolesById(@PathVariable Long id) {
        return R.ok().setData(tbUserService.findRoleListByUserId(id));
    }


}