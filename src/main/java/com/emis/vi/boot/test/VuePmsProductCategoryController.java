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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 产品分类管理vue风格Controller
 */
//@Api：用于修饰Controller类，生成Controller相关文档信息
@Api(tags = "VuePmsProductCategoryController", description = "产品分类vue风格")
@RestController
@RequestMapping("/test")
public class VuePmsProductCategoryController {
    //自动装配对象
    @Autowired
    PmsProductCategoryDao pmsProductCategoryDao;

    //@ApiOperation：用于修饰Controller类中的方法，生成接口方法相关文档信息
    //@ApiParam：用于修饰接口中的参数，生成接口参数相关文档信息
    @ApiOperation("分页查询分类列表")
    @RequestMapping(value = "/categoriev", method = RequestMethod.GET)
    public PageInfo<PmsProductCategory> listCategoryPage(@RequestParam(value = "start", defaultValue = "0") @ApiParam("页码") int start, @RequestParam
            (value = "size", defaultValue = "2") @ApiParam("每页数量") int size) throws Exception {
        start = start < 0 ? 0 : start;
        //2. 根据start,size进行分页，并且设置id 倒排序
        PageHelper.startPage(start, size, "id desc");
        //3. 因为PageHelper的作用，这里就会返回当前分页的集合了
        List<PmsProductCategory> cs = pmsProductCategoryDao.findAll();
        System.out.println(cs.size());
        //4. 根据返回的集合，创建PageInfo对象
        PageInfo<PmsProductCategory> page = new PageInfo<>(cs, 5); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        //6. 跳转到listCategory.jsp
        return page;
    }

    @ApiOperation("添加分类")
    @RequestMapping(value = "/categoriev", method = RequestMethod.POST)
    public String addCategory(@RequestBody PmsProductCategory c) throws Exception {
        pmsProductCategoryDao.save(c);
        return "success";
    }

    @ApiOperation("删除指定id的分类")
    @RequestMapping(value = "/categoriev/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(PmsProductCategory c) throws Exception {
        pmsProductCategoryDao.delete(c.getId());
        return "success";
    }

    @ApiOperation("更新指定id分类信息")
    @RequestMapping(value = "/categoriev/{id}", method = RequestMethod.PUT)
    public String updateCategory(@RequestBody PmsProductCategory c) throws Exception {
        pmsProductCategoryDao.update(c);
        return "success";
    }

    @ApiOperation("获取指定id的分类详情")
    @RequestMapping(value = "/categoriev/{id}", method = RequestMethod.GET)
    public PmsProductCategory editCategory(@PathVariable("id") Long id) throws Exception {
        System.out.println(id);
        PmsProductCategory c = pmsProductCategoryDao.get(id);
        System.out.println(c);
        return c;
    }

    /*页面跳转 部分*/
    @RequestMapping(value = "/listCategoriev", method = RequestMethod.GET)
    public ModelAndView listHero() {
        ModelAndView mv = new ModelAndView("vListCategory");
        return mv;
    }

    @RequestMapping(value = "/editCategoriev", method = RequestMethod.GET)
    public ModelAndView editHero() {
        ModelAndView mv = new ModelAndView("vEditCategory");
        return mv;
    }
}
