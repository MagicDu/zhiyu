package com.zhiyu.gen.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhiyu.gen.domain.GenTable;
import com.zhiyu.gen.domain.query.GenTableQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author ruoyi
 */
public interface GenTableMapper extends BaseMapper<GenTable> {
    /**
     * 查询业务列表
     *
     * @param query 业务信息
     * @return 业务集合
     */
     IPage<GenTable> pageQuery(IPage<GenTable> page, @Param("query") GenTableQuery query);

    /**
     * 查询据库列表
     *
     * @param query 业务信息
     * @return 数据库表集合
     */
     IPage<GenTable> selectDbTableList(IPage<GenTable> page, @Param("query")GenTableQuery query);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
     List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
     List<GenTable> selectGenTableAll();

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
     GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
     GenTable selectGenTableByName(String tableName);

}
