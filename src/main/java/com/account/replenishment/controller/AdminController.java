package com.account.replenishment.controller;

import com.account.replenishment.model.StatusDTO;
import com.account.replenishment.model.web.JournalDTO;
import com.account.replenishment.model.web.UserDTO;
import com.account.replenishment.service.JournalService;
import com.account.replenishment.service.UserService;
import com.account.replenishment.util.ConvertDate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 04.07.2016
 */
@Controller
public class AdminController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private JournalService journalService;

    @Autowired
    private ConvertDate convertDate;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)

    public String printWelcomeStart() {
        return "redirect:admin/1";
    }

    /**
     * This method return all users (whose role is 'USER')
     * or Users hwo falls under the search parameter (E-mail)
     */
    @RequestMapping(value = "/admin/{numberPage}", method = RequestMethod.GET)
    public ModelAndView printWelcome(@PathVariable Integer numberPage,
                                     @RequestParam(value = "email", required = false) String email) {
        int countInfoPerPage = 10;//count result per page
        long countOfPage = userService.getCountOfUsersPlusPagination(email);
        long lastPage = countOfPage % countInfoPerPage == 0 ? countOfPage / countInfoPerPage : countOfPage / countInfoPerPage + 1;

        //option for search logic
        if (numberPage > lastPage) {
            numberPage = 1;
        }
        int begin = Math.max(1, numberPage - 1);
        int end = Math.min(begin + 2, (int) lastPage);
        int pageNumber = numberPage - 1;

        //Create Pageable object
        Pageable pageRequest = new PageRequest(pageNumber, countInfoPerPage);
        List<UserDTO> list = userService.getAllUserPlusPagination(email, pageRequest);

        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("currentPage", numberPage);
        modelAndView.addObject("lastPage", lastPage);
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("searchValue", email);

        //if search don`t return any result
        if (list == null) {
            modelAndView.addObject("message", "Ошибка сервера. Повторите через 5 мин.");
            modelAndView.addObject("currentPage", 1);
            modelAndView.addObject("lastPage", 0);
            modelAndView.addObject("endIndex", 0);
        } else if (list.size() == 0) {
            modelAndView.addObject("message", "Поиск не дал результатов!");
            modelAndView.addObject("currentPage", 1);
            modelAndView.addObject("lastPage", 0);
            modelAndView.addObject("endIndex", 0);
        } else {
            modelAndView.addObject("users", list);
        }
        return modelAndView;
    }

    /**
     * This method fill money to user and update
     * user balance
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public StatusDTO addNewJournal(@RequestBody JournalDTO journalDTO) {
        return journalService.addJournal(journalDTO);
    }

    @RequestMapping(value = "/admin_journal", method = RequestMethod.GET)
    public String printJournalWelcomeStart() {
        return "redirect:admin_journal/1";
    }

    /**
     * This method return all money refill in balance
     * or refill hwo falls under the search parameter (E-mail)
     */
    @RequestMapping(value = "/admin_journal/{numberPage}", method = RequestMethod.GET)
    public ModelAndView printJournalWelcome(@PathVariable Integer numberPage,
                                            @RequestParam(value = "dateStartStr", required = false) String dateStartStr,
                                            @RequestParam(value = "dateEndStr", required = false) String dateEndStr) {
        //get array date
        Date[] dateParam = convertDate.convertStringToDate(dateStartStr, dateEndStr);

        int countInfoPerPage = 10;//count result per page
        long countOfPage = (journalService.getCountJournalsFromCustomPeriod(dateParam[0], dateParam[1]));
        long lastPage = countOfPage % countInfoPerPage == 0 ? countOfPage / countInfoPerPage : countOfPage / countInfoPerPage + 1;

        //option for search logic
        if (numberPage > lastPage) {
            numberPage = 1;
        }

        int begin = Math.max(1, numberPage - 1);
        int end = Math.min(begin + 2, (int) lastPage);
        int pageNumber = numberPage - 1;

        //Create Pageable object
        Pageable pageRequest = new PageRequest(pageNumber, countInfoPerPage);
        List<JournalDTO> list = journalService.getJournalsFromCustomPeriod(dateParam[0], dateParam[1], pageRequest);

        ModelAndView modelAndView = new ModelAndView("admin_journal");
        modelAndView.addObject("currentPage", numberPage);
        modelAndView.addObject("lastPage", lastPage);
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("searchValueStart", dateStartStr);
        modelAndView.addObject("searchValueEnd", dateEndStr);

        //if search don`t return any result
        if (list == null) {
            modelAndView.addObject("message", "Ошибка сервера. Повторите через 5 мин.");
            modelAndView.addObject("currentPage", 1);
            modelAndView.addObject("lastPage", 0);
            modelAndView.addObject("endIndex", 0);
        } else if (list.size() == 0) {
            modelAndView.addObject("message", "Поиск не дал результата или введены некоректные данные! ");
            modelAndView.addObject("currentPage", 1);
            modelAndView.addObject("lastPage", 0);
            modelAndView.addObject("endIndex", 0);
        } else {
            modelAndView.addObject("journal", list);
        }
        return modelAndView;
    }
}
