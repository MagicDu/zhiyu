package com.zhiyu.gen.controller;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.gen.domain.GenTable;
import com.zhiyu.gen.domain.GenTableColumn;
import com.zhiyu.gen.domain.query.GenTableQuery;
import com.zhiyu.gen.service.IGenTableColumnService;
import com.zhiyu.gen.service.IGenTableService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/tool/gen")
public class GenController {
    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @PostMapping("/list")
    public ApiResult<IPage<GenTable>> genList(@RequestBody GenTableQuery query) {
        return new ApiResult<>(genTableService.pageQuery(query));
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{tableId}")
    public ApiResult<Map<String, Object>> getInfo(@PathVariable Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return new ApiResult<>(map);
    }

    /**
     * 查询数据库列表
     */
    @PostMapping("/db/list")
    public ApiResult<IPage<GenTable>> dataList(@RequestBody GenTableQuery query) {
        return new ApiResult<>(genTableService.selectDbTableList(query));
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{talbleId}")
    public ApiResult<List<GenTableColumn>> columnList(Long tableId) {
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        return new ApiResult<>(list);
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public ApiResult<Boolean> importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return new ApiResult<>(true);
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public ApiResult<Boolean> editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return new ApiResult<>(true);
    }

    /**
     * 删除代码生成
     */
    @DeleteMapping("/{tableIds}")
    public ApiResult<Boolean> remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return new ApiResult<>(true);
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    public ApiResult<Map<String, String>> preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return new ApiResult<>(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @GetMapping("/genCode/{tableName}")
    public ApiResult<Boolean> genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return new ApiResult<>(true);
    }

    /**
     * 同步数据库
     */
    @GetMapping("/synchDb/{tableName}")
    public ApiResult<Boolean> synchDb(@PathVariable("tableName") String tableName) {
        genTableService.synchDb(tableName);
        return new ApiResult<>(true);
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}

