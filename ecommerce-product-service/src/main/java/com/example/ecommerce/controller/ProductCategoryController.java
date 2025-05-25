package com.example.ecommerce.controller;


import com.example.ecommerce.entity.PageResult;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.entity.Result;
import com.example.ecommerce.service.IProductCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zfy
 * @since 2025-05-19
 */
@Controller
@RequestMapping("/category")
public class ProductCategoryController {
    @Autowired
    private IProductCategoryService categoryService;

    // ✅ 分页查询分类
    @GetMapping("/list")
    public Result<PageResult<ProductCategory>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductCategory> list = categoryService.list();
        PageInfo<ProductCategory> pageInfo = new PageInfo<>(list);
        return Result.success(PageResult.of(pageInfo));
    }

    // ✅ 查询单个分类
    @GetMapping("/{id}")
    public Result<ProductCategory> getById(@PathVariable Long id) {
        ProductCategory category = categoryService.getById(id);
        return category != null ? Result.success(category) : Result.error("分类不存在");
    }

    // ✅ 添加分类
    @PostMapping
    public Result<String> add(@RequestBody ProductCategory category) {
        boolean success = categoryService.save(category);
        return success ? Result.success("添加成功") : Result.error("添加失败");
    }

    // ✅ 修改分类
    @PutMapping
    public Result<String> update(@RequestBody ProductCategory category) {
        boolean success = categoryService.updateById(category);
        return success ? Result.success("修改成功") : Result.error("修改失败");
    }

    // ✅ 删除分类
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean success = categoryService.removeById(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}
