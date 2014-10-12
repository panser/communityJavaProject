package ua.org.gostroy.communityJavaProject.web.datagrid_easyui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;
import ua.org.gostroy.communityJavaProject.core_jpa_hibernate.service.UserServiceOverJpaHibernate;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Panov Sergey on 10/11/2014.
 */
@Controller
@RequestMapping("/viewexcel")
public class ViewExcel {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserServiceOverJpaHibernate userServiceOverJpaHibernate;

    @ModelAttribute("users")
    public List<User> getUsers() {
        List<User> users = userServiceOverJpaHibernate.findAll();
        LOG.trace(getClass() + ": getUsers(), users = " + users);
        return users;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("users") ArrayList<User> users) {
        LOG.trace(getClass() + ": index() ...");
        LOG.trace(getClass() + ": index(), users = " + users);
        return new ModelAndView("excelBuilder", "users", users);
    }
}
