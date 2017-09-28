package tk.mybatis.springboot.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.dto.OrderDTO;
import tk.mybatis.springboot.model.Country;
import tk.mybatis.springboot.model.OrderMaster;
import tk.mybatis.springboot.service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * Created by Dong_Liu
 * date：2017/9/27
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     *
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
//        PageRequest request = new PageRequest(page - 1, size);
        //存放查询条件
//        ModelAndView result = new ModelAndView("index");
//        result.addObject("pageInfo", new PageInfo<OrderDTO>(orderDTOPage));
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setPage(page - 1);
        orderMaster.setRows(size);
        List<OrderDTO> orderDTOPage = orderService.findList(orderMaster);
        map.put("orderDTOPage", new PageInfo<OrderDTO>(orderDTOPage));
        map.put("currentPage", page);
        map.put("size", size);
//        orderDTOPage.getTotalPages()
        return new ModelAndView("order/list", map);
    }


}
