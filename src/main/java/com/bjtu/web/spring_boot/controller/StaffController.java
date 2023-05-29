package com.bjtu.web.spring_boot.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjtu.web.spring_boot.common.Result;
import com.bjtu.web.spring_boot.entity.Staff;
import com.bjtu.web.spring_boot.entity.User;
import com.bjtu.web.spring_boot.mapper.StaffMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Random;
@Api(tags = "员工管理")
@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {
    @Resource
   StaffMapper staffMapper;

    @ApiOperation("保存员工")
    @PostMapping
    public Result<?> save(@RequestBody Staff staff){
        Random random = new Random();
        while (true)
        {
            long randomNumber = random.nextInt(900000000) + 10000000000L;
            if(staffMapper.selectById(randomNumber)==null)
            {
                staff.setEmployeeId(randomNumber);
                break;
            }
        }
        staffMapper.insert(staff);
        return Result.success();
    }
    @ApiOperation("分页查询员工信息")
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search){
        Page<Staff> staffPage = staffMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Staff>lambdaQuery().like(Staff::getEmployeeName, search));
        return Result.success(staffPage);
    }



    @ApiOperation("修改员工信息")
     @PutMapping
    public Result<?> update(@RequestBody Staff staff){
        staffMapper.updateById(staff);
        return Result.success();
     }
    @ApiOperation("删除员工信息")
     @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id){
        staffMapper.deleteById(id);
        return Result.success();
     }

    /**
     * 导出接口
     */
    @ApiOperation("导出员工信息")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Staff> list = staffMapper.selectList(null);
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名


        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("工作人员信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

}
