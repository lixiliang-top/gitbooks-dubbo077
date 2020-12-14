package cn.kgc.service;

import cn.kgc.pojo.Entry;
import cn.kgc.pojo.MyPageInfo;

import java.util.List;

public interface EntryService {
    //根据图书分类查询 并分页
    MyPageInfo<Entry> selectBycategoryid(Integer categoryid, Integer pageNum, Integer pageSize);

    //新增电子图书
    void add(Entry entry);

    //根据图书id查询图书
    Entry selectById(Integer id);

    //修改
    void upd(Entry entry);

    //删除
    void del(Integer id);

}
