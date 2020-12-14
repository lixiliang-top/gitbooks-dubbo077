package cn.kgc.booksprovider.service;

import cn.kgc.booksprovider.mapper.CategoryMapper;
import cn.kgc.pojo.Category;
import cn.kgc.service.CategoryService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shkstart
 */
@Component
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectByExample(null);
    }
}
