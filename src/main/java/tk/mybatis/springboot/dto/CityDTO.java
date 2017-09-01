package tk.mybatis.springboot.dto;

public class CityDTO extends BaseQueryDTO {
    private String name;
    private Integer state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
