package com.example.ecommerce.controller;


import com.example.ecommerce.entity.PageResult;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Result;
import com.example.ecommerce.service.IProductService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zfy
 * @since 2025-05-19
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    // ✅ 分页查询
    @PostMapping("/list")
    public Result<PageResult<Product>> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productService.list();
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return Result.success(PageResult.of(pageInfo));
    }

    // ✅ 查询单个商品
    @GetMapping("/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return product != null ? Result.success(product) : Result.error("商品未找到");
    }

    // ✅ 新增商品
    @PostMapping
    public Result<String> add(@RequestBody Product product) {
        boolean success = productService.save(product);
        return success ? Result.success("新增成功") : Result.error("新增失败");
    }

    // ✅ 修改商品
    @PutMapping
    public Result<String> update(@RequestBody Product product) {
        boolean success = productService.updateById(product);
        return success ? Result.success("修改成功") : Result.error("修改失败");
    }

    // ✅ 删除商品
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean success = productService.removeById(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}
