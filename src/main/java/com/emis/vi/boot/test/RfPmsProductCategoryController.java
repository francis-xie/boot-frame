package com.emis.vi.boot.test;

import com.emis.vi.boot.dao.PmsProductCategoryDao;
import com.emis.vi.boot.mbg.model.PmsProductCategory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 产品分类管理Restful风格Controller
 */
//@Api：用于修饰Controller类，生成Controller相关文档信息
@Api(tags = "RfPmsProductCategoryController", description = "产品分类Restful风格")
@Controller
@RequestMapping("/test")
public class RfPmsProductCategoryController {
    //自动装配对象
    @Autowired
    PmsProductCategoryDao pmsProductCategoryDao;

    //@ApiOperation：用于修饰Controller类中的方法，生成接口方法相关文档信息
    //@ApiParam：用于修饰接口中的参数，生成接口参数相关文档信息
    @ApiOperation("分页查询分类列表")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String listCategoryPage(Model m, @RequestParam(value = "start", defaultValue = "0") @ApiParam("页码") int start, @RequestParam
            (value = "size", defaultValue = "2") @ApiParam("每页数量") int size) throws Exception {
        start = start < 0 ? 0 : start;
        //2. 根据start,size进行分页，并且设置id 倒排序
        PageHelper.startPage(start, size, "id desc");
        //3. 因为PageHelper的作用，这里就会返回当前分页的集合了
        List<PmsProductCategory> cs = pmsProductCategoryDao.findAll();
        //4. 根据返回的集合，创建PageInfo对象
        PageInfo<PmsProductCategory> page = new PageInfo<>(cs);
        //PageInfo<PmsProductCategory> page = categoryService.findAll(start, size);
        //5. 把PageInfo对象扔进model，以供后续显示
        m.addAttribute("page", page);
        //6. 跳转到listCategory.jsp
        return "rfListCategory";
    }

    @ApiOperation("添加分类")
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String addCategory(PmsProductCategory c) throws Exception {
        pmsProductCategoryDao.save(c);
        return "redirect:/test/categories";
    }

    @ApiOperation("删除指定id的分类")
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(PmsProductCategory c) throws Exception {
        pmsProductCategoryDao.delete(c.getId());
        return "redirect:/test/categories";
    }

    @ApiOperation("更新指定id分类信息")
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    public String updateCategory(PmsProductCategory c) throws Exception {
        pmsProductCategoryDao.update(c);
        return "redirect:/test/categories";
    }

    @ApiOperation("获取指定id的分类详情")
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String editCategory(@PathVariable("id") Long id, Model m) throws Exception {
        PmsProductCategory c = pmsProductCategoryDao.get(id);
        m.addAttribute("c", c);
        return "rfEditCategory";
    }
}
