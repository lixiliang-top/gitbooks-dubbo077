package cn.kgc.service;

import cn.kgc.pojo.Category;

import java.util.List;

public interface CategoryService {
    //查询所有图书分类
    List<Category> selectAll();
}
