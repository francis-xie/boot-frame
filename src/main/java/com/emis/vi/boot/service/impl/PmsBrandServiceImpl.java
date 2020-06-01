package com.emis.vi.boot.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.emis.vi.boot.mbg.mapper.PmsBrandMapper;
import com.emis.vi.boot.mbg.model.PmsBrand;
import com.emis.vi.boot.mbg.model.PmsBrandExample;
import com.emis.vi.boot.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsBrandService实现类
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public int create(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int update(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int delete(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> list(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample example = new PmsBrandExample();
        if(StrUtil.isNotEmpty(name)){
            example.createCriteria().andNameLike("%"+name+"%");
        }
        return brandMapper.selectByExample(example);
    }

    @Override
    public PmsBrand detail(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
