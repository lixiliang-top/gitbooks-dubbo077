package cn.kgc.booksprovider.service;

import cn.kgc.booksprovider.mapper.EntryMapper;
import cn.kgc.pojo.Entry;
import cn.kgc.pojo.EntryExample;
import cn.kgc.pojo.MyPageInfo;
import cn.kgc.service.EntryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shkstart
 */
@Component
@Service
public class EntryServiceImpl implements EntryService {
    @Resource
    EntryMapper entryMapper;

    @Override
    public MyPageInfo<Entry> selectBycategoryid(Integer categoryid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        EntryExample example = new EntryExample();
        EntryExample.Criteria criteria = example.createCriteria();
        if (categoryid == 1 || categoryid == 2) {
            criteria.andCategoryidEqualTo(categoryid);
            List<Entry> ebook_entries = entryMapper.selectByExample(example);
            PageInfo<Entry> pageInfo = new PageInfo<>(ebook_entries);
            MyPageInfo<Entry> myPageInfo = new MyPageInfo<>();
            BeanUtils.copyProperties(pageInfo, myPageInfo);
            return myPageInfo;
        }else if(categoryid==0){
            List<Entry> ebook_entries = entryMapper.selectByExample(null);
            PageInfo<Entry> pageInfo = new PageInfo<>(ebook_entries);
            MyPageInfo<Entry> myPageInfo = new MyPageInfo<>();
            BeanUtils.copyProperties(pageInfo, myPageInfo);
            return myPageInfo;
        }
        return null;
    }

    @Override
    public int add(Entry entry) {
        return entryMapper.insert(entry);
    }


    @Override
    public Entry selectById(Integer id) {
        return entryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int upd(Entry entry) {
        return entryMapper.updateByPrimaryKeySelective(entry);
    }

    @Override
    public int del(Integer id) {
        return entryMapper.deleteByPrimaryKey(id);
    }


}
