package com.emis.vi.boot.service;


import com.emis.vi.boot.mbg.model.PmsBrand;

import java.util.List;

/**
 * PmsBrandService
 */
public interface PmsBrandService {

    int create(PmsBrand brand);

    int update(Long id, PmsBrand brand);

    int delete(Long id);

    List<PmsBrand> list(int pageNum, int pageSize, String name);

    PmsBrand detail(Long id);
}
