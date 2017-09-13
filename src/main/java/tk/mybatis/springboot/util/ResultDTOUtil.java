package tk.mybatis.springboot.util;

import tk.mybatis.springboot.dto.ResultDTO;

/**
 * @Author dong_liu
 * @Date 2017/9/13 15:11
 * 返回结果控制
 */
public class ResultDTOUtil {
    public static ResultDTO success(Object object) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setData(object);
        resultDTO.setCode(0);
        resultDTO.setMsg("成功");
        return resultDTO;
    }

    public static ResultDTO success() {
        return success(null);
    }

    public static ResultDTO error(Integer code, String msg) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        return resultDTO;
    }
}
