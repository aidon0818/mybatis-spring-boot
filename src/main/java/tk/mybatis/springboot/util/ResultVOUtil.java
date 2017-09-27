package tk.mybatis.springboot.util;

import tk.mybatis.springboot.dto.ResultDTO;

/**
* @Author dong_liu 
* @Date 2017/9/27 14:39
*/
public class ResultVOUtil {

    public static ResultDTO success(Object object) {
        ResultDTO resultVO = new ResultDTO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultDTO success() {
        return success(null);
    }

    public static ResultDTO error(Integer code, String msg) {
        ResultDTO resultVO = new ResultDTO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
