package com.emis.vi.boot.dao;

import com.emis.vi.boot.mbg.model.PmsProductCategory;
import com.emis.vi.boot.mbg.model.PmsProductCategoryExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//加入Spring管理
@Repository
//@Mapper表示这是一个Mybatis Mapper接口。
//@Mapper
public interface PmsProductCategoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductCategory record);

    int insertSelective(PmsProductCategory record);

    //使用@Select注解表示调用findAll方法会去执行对应的sql语句。
    @Select("select * from pms_product_category")
    List<PmsProductCategory> findAll();

    @Insert(" insert into pms_product_category ( name ) values (#{name}) ")
    public int save(PmsProductCategory category);

    @Delete(" delete from pms_product_category where id= #{id} ")
    public void delete(Long id);

    @Select("select * from pms_product_category where id= #{id} ")
    public PmsProductCategory get(Long id);

    @Update("update pms_product_category set name=#{name} where id=#{id} ")
    public int update(PmsProductCategory category);

    List<PmsProductCategory> selectByExample(PmsProductCategoryExample example);

    PmsProductCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductCategory record);

    int updateByPrimaryKey(PmsProductCategory record);
}