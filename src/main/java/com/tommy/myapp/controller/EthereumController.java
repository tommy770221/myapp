package com.tommy.myapp.controller;

import com.tommy.model.springjpa.Disease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Tommy on 2017/7/2.
 */
@RequestMapping("/ethereum")
@Controller
public class EthereumController {
    @RequestMapping(value = "genKey",produces = "text/html")
    @ResponseBody
    public String list(@RequestParam(value = "page", required = false) Integer page,
                       @RequestParam(value = "size", required = false) Integer size,
                       @RequestParam(value = "sortFieldName", required = false) String sortFieldName,
                       @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {

        return null;
    }

}
