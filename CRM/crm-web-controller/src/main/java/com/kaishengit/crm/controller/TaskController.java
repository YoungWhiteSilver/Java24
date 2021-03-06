package com.kaishengit.crm.controller;

import com.google.common.collect.Maps;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.service.TaskService;
import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/14
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController{

    @Autowired
    private TaskService taskService;

    private static final String USER = "curr_account";

    @GetMapping
    public String toList(@RequestParam(required = false) Integer show,
                         Model model) {

        Account account = getAccount();
        model.addAttribute("taskList", taskService.findAll(show, account));

        return "task/list";

    }



    @GetMapping("/new")
    public String addTask() {

        return "task/new";

    }

    @PostMapping("/new")
    @ResponseBody
    public AjaxResult addTask(@RequestParam String title,
                              @RequestParam String finishTime,
                              @RequestParam(required = false) String remindTime,
                              @RequestParam Integer accountId,
                              @RequestParam(required = false) Integer custId,
                              @RequestParam(required = false) Integer saleId,
                              Model model) {

        Map<String, Object> map = Maps.newHashMap();

        map.put("title", title);
        map.put("finishTime",finishTime);
        map.put("remindTime", remindTime);
        map.put("accountId", accountId);
        map.put("custId", custId);
        map.put("saleId", saleId);

        try{

            taskService.addTask(map);
            return AjaxResult.success();

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }


    }

    @GetMapping("/{id:\\d+}/state/done")
    public String done(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        try {

            taskService.updateDone(id);
            redirectAttributes.addAttribute("message", "任务完成");
        } catch (ServiceException e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/task";
    }















}
