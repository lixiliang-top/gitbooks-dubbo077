package cn.kgc.booksconsumer.controller;

import cn.kgc.pojo.Category;
import cn.kgc.pojo.Entry;
import cn.kgc.pojo.MyPageInfo;
import cn.kgc.service.CategoryService;
import cn.kgc.service.EntryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.plugin.PluginURLJarFileCallBack;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-12-14 19:43
 */
@Controller
public class EBookController {
    @Reference
    EntryService entryService;
    @Reference
    CategoryService categoryService;

    @RequestMapping("")
    public String toIndex(Model model, Integer type, Integer pageNum, Integer pageSize) {
        MyPageInfo<Entry> entryMyPageInfo = entryService.selectBycategoryid(type, pageNum, pageSize);
        model.addAttribute("entryMyPageInfo", entryMyPageInfo);
        return "ebook-select";
    }

    @RequestMapping("/toupdate/{id}")
    public String toupdate(@PathVariable("id") int id, Model model) {
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("categories",categories);


        Entry entry = entryService.selectById(id);
        model.addAttribute("entry", entry);
        return "ebook-update";
    }

    @RequestMapping("/delete/{id}")
    public String doDelete(@PathVariable("id") int id){
        entryService.del(id);
        return "redirect:/";
    }

    @RequestMapping("/doupdate")
    public String doupdate(Entry entry) {
        entryService.upd(entry);
        return "redirect:/";
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model) {
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("categories",categories);
        return "ebook-insert";
    }
    @RequestMapping("doAdd")
    public String doAdd(Entry entry) {
        entryService.add(entry);
        return "redirect:/";
    }
}
