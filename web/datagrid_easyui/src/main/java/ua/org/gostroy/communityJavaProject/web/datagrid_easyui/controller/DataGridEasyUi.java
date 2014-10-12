package ua.org.gostroy.communityJavaProject.web.datagrid_easyui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;
import ua.org.gostroy.communityJavaProject.core_jpa_hibernate.service.UserServiceOverJpaHibernate;
import ua.org.gostroy.communityJavaProject.web.datagrid_easyui.json.JSONResponseBuilder;
import ua.org.gostroy.communityJavaProject.web.datagrid_easyui.json.UserDataGridJSONResponseBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by Panov Sergey on 10/7/2014.
 */
@Controller
@RequestMapping("/datagrideasyui")
public class DataGridEasyUi {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    public static final String DATAGRID_EASYUI_PAGE = "datagrideasyui";

    @Autowired
    private UserServiceOverJpaHibernate userServiceOverJpaHibernate;

    private JSONResponseBuilder jsonResponseBuilder = new UserDataGridJSONResponseBuilder();

/*
    @PostConstruct
    protected void beforeConstruct(){
        System.out.println(getClass() + " :post construct method invoked");
    }
    @PreDestroy
    protected void beforeDestroy() {
        System.out.println(getClass() + "before destroy method invoked");
    }
*/

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        LOG.trace(getClass() + ": index() ...");
        return DATAGRID_EASYUI_PAGE;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public List<User> search() {
        LOG.trace(getClass() + ": search() ...");
        return userServiceOverJpaHibernate.findAll();
    }
}
