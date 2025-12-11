package com.pluralsight.NorthwindTradersAPI3.dao.impl;


import com.pluralsight.NorthwindTradersAPI3.dao.interfaces.ICategoryDao;
import com.pluralsight.NorthwindTradersAPI3.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDao implements ICategoryDao {

    private DataSource dataSource;

    @Autowired
    public CategoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM Categories";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int categoryID = resultSet.getInt("CategoryID");
                String categoryName = resultSet.getString("CategoryName");
                categoryList.add(new Category(categoryID,categoryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryList;
    }

    @Override
    public Category getById(int id) {
        String query = "SELECT * FROM Categories WHERE CategoryID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int categoryID = resultSet.getInt("CategoryID");
                    String categoryName = resultSet.getString("CategoryName");
                    return new Category(categoryID,categoryName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
