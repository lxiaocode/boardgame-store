package com.lxiaocode.boardgame.product.domain.dto;

import com.lxiaocode.boardgame.product.constant.GameModeEnum;
import com.lxiaocode.boardgame.product.domain.Category;
import com.lxiaocode.boardgame.product.domain.Mechanics;
import com.lxiaocode.boardgame.product.domain.ParameterPrimary;
import com.lxiaocode.boardgame.product.domain.Theme;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午3:18
 * @blog http://www.lxiaocode.com/
 */
@Data
public class ProductParameterDTO {

    @NotBlank
    private String productId;

    @Min(value = 1, message = "最小人数不能小于1人")
    private Integer minPerson;

    @Max(value = 25, message = "最大人数不能大于25人")
    private Integer maxPerson;

    @NotNull
    private GameModeEnum mode;

    @Min(value = 1)
    @Max(value = 120)
    private Integer age;

    @NotBlank
    private String year;

    @Min(value = 1)
    @Max(value = 10)
    private Integer difficulty;

    @NotEmpty
    private String[] categories;

    @NotEmpty
    private String[] mechanics;

    @NotEmpty
    private String[] themes;

    private List<ParameterDTO> parameters;

    public ParameterPrimary getParameterPrimary() {
        ParameterPrimary parameterPrimary = new ParameterPrimary();
        BeanUtils.copyProperties(this, parameterPrimary);

        return parameterPrimary;
    }

    public Set<Category> getCategorySet() {
        return Arrays.stream(this.categories).map(c -> {
            Category category = new Category();
            category.setProductId(this.productId);
            category.setValue(c);

            return category;
        }).collect(Collectors.toSet());
    }

    public Set<Mechanics> getMechanicsSet() {
        return Arrays.stream(this.mechanics).map(m -> {
            Mechanics mechanics = new Mechanics();
            mechanics.setProductId(this.productId);
            mechanics.setValue(m);

            return mechanics;
        }).collect(Collectors.toSet());
    }

    public Set<Theme> getThemeSet() {
        return Arrays.stream(this.themes).map(t -> {
            Theme theme = new Theme();
            theme.setProductId(this.productId);
            theme.setValue(t);

            return theme;
        }).collect(Collectors.toSet());
    }
}
