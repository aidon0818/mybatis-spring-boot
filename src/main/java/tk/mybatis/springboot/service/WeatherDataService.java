package tk.mybatis.springboot.service;

import tk.mybatis.springboot.dto.WeatherResponse;

/**
 * Created by Dong_Liu
 * date：2017/11/8
 */
public interface WeatherDataService {
    /**
     * 根据城市ID查询天气数据 * @param cityId * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据 * @param cityId * @return
     */
    WeatherResponse getDataByCityName(String cityName);

}
