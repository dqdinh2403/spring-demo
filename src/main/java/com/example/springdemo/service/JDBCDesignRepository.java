package com.example.springdemo.service;

import com.example.springdemo.domain.Design;
import com.example.springdemo.domain.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;

@Repository
public class JDBCDesignRepository implements DesignRepository {

    private JdbcTemplate jdbc;

    public JDBCDesignRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Design save(Design design) {
        long designId = saveDesignInfo(design);
        design.setId(designId);
        for(Ingredient ingredient : design.getIngredients()){
            saveIngredientToDesign(ingredient, designId);
        }

        return design;
    }

    private long saveDesignInfo(Design design){
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory("insert into Design(name) value (?)", Types.VARCHAR)
                .newPreparedStatementCreator(Arrays.asList(design.getName()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToDesign(Ingredient ingredient, long designId){
        jdbc.update("insert into Design_Ingredients(design, ingredient) values(?,?)",
                designId, ingredient.getId());
    }

}
