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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @RequestMapping("/")
    public String toIndex(Model model,
                          @RequestParam(value = "categoryid",required = false,defaultValue = "0") Integer categoryid,
                          @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                          @RequestParam(value = "pageSize",required = false,defaultValue = "3")Integer pageSize) {
        MyPageInfo<Entry> entryMyPageInfo = entryService.selectBycategoryid(categoryid, pageNum, pageSize);
        model.addAttribute("entryMyPageInfo", entryMyPageInfo);
        return "book-select";
    }

    @RequestMapping("/toupdate/{id}")
    public String toupdate(@PathVariable("id") int id, Model model,HttpSession session) {
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("categories",categories);
//        session.setAttribute("categories",categories);


        Entry entry = entryService.selectById(id);
        model.addAttribute("entry", entry);
//        session.setAttribute("entry", entry);
        return "books-update";
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

    @RequestMapping("/toAdd")
    public String toAdd(Model mode,HttpSession session) {
        List<Category> categories = categoryService.selectAll();
        mode.addAttribute("categories",categories);
//        session.setAttribute ("categories",categories);
        return "book-add";
    }
    @RequestMapping("/doAdd")
    public String doAdd(Entry entry) {
        entry.setCreatedate(new Date());
        entryService.add(entry);
        return "redirect:/";
    }
}
