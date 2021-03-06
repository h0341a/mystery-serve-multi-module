package com.ccjr.api.admin;

import com.ccjr.model.dataobject.Blog;
import com.ccjr.model.dto.BlogDTO;
import com.ccjr.model.vo.BlogVO;
import com.ccjr.response.BusinessException;
import com.ccjr.response.Result;
import com.ccjr.service.AdminBlogService;
import com.ccjr.service.impl.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ling
 */
@Api("后台博客管理接口")
@RestController
@RequestMapping("/api/admin/")
public class BlogController {

    private final AdminBlogService blogService;

    public BlogController(AdminBlogService blogService) {
        this.blogService = blogService;
    }

    //todo 分页功能待实现
    @ApiOperation("获取博客列表")
    @GetMapping("/blogList")
    public Result blogList() throws BusinessException {
        //用户验证
        //blogService获取结果
        List<BlogVO> blogVOList = blogService.getBlogList();
        //返回
        return Result.ofSuccess(blogVOList);
    }

    @ApiOperation("根据分类获取博客列表")
    @GetMapping("/blogList/{category}")
    public Result blogListByCategory(@PathVariable("category") String categoryName) throws BusinessException {
        List<BlogVO> blogVOList = blogService.getBlogListByCategoryName(categoryName);
        return Result.ofSuccess(blogVOList);
    }

    @ApiOperation("创建一个新博客")
    @PostMapping("/blog")
    public Result newBlog(@Validated BlogDTO blogDTO) throws BusinessException {
        //用户验证
        //将blogDTO传入到service
        blogService.addNewBlog(blogDTO);
        //返回结果
        return Result.ofSuccess("插入成功");
    }

    @ApiOperation("删除一个博客")
    @DeleteMapping("/blog/{bid}")
    public Result deleteBlog(@PathVariable("bid") Integer bid) throws BusinessException {
        //用户验证
        //传入到service
        blogService.deleteBlog(bid);
        //返回结果
        return Result.ofSuccess("删除成功");
    }
    @ApiOperation("分组列表")
    @GetMapping("/categoryList")
    public Result categoryList(){
        List<String> categoryList = blogService.getCategoryList();
        return Result.ofSuccess(categoryList);
    }

    @ApiOperation("编辑一个博客")
    @PutMapping("/blog/{bid}")
    public Result alterBlog(@PathVariable("bid") Integer bid,@Validated BlogDTO blogDTO) throws BusinessException {
        //用户验证
        //传入service
        blogService.updateBlog(bid, blogDTO);
        //返回结果
        return Result.ofSuccess("编辑成功");
    }


}
